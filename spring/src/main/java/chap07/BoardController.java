package chap07;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import chap07.user.UserVo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

	@Autowired
	BoardService boardService; // BoardService으로 적어야 다형성 개념을 적용할 수 있음 
	
	@GetMapping({"/board/index.do", "/user/mypage.do"})
	public String index(Model model, HttpServletRequest req, BoardVo vo) {
		log.info(req.getRequestURI());
		log.info(req.getServletPath());
		System.out.println("서민구");
		System.out.println("kimjisu");
		
		String view = "board/index";
		if("/user/mypage.do".equals(req.getServletPath())) { // 마이페이지로 접속한 경우 
			vo.setUserno(((UserVo)req.getSession().getAttribute("loginInfo")).getUserno());
			view = "user/mypage";
		}
		
		int totCount = boardService.count(vo);
		int totPage = totCount / 10; //총페이지수 
		if(totCount % 10 > 0) totPage++;
		System.out.println("totpage : "+totPage);
		
		int startIdx = (vo.getPage()-1)*10;
		vo.setStartIdx(startIdx);
		
		List<BoardVo> list = boardService.selectList(vo);
		model.addAttribute("list",list);
		model.addAttribute("totPage",totPage);
		return "board/index";
	}
	
	@GetMapping("/board/test.do")
	public String test() {
		return "board/test";
	}
	
	/*
    뷰에서 사용하기 위한 값을 컨트롤에서 저장하는 방법  
    - request에 set 
    - session에 set 
    - model에 add 
    - ModelAndView에 add 
	 */
	
//	@GetMapping("/board/index.do")
//	public String index(Model model,HttpServletRequest req, BoardVo vo) {
//		String searchWord = req.getParameter("searchWord");
//		String searchType = req.getParameter("searchType");
//		List<BoardVo> list = boardService.selectList(vo);  // list 타입에는 table 에 있는 arrayList 객체들이 들어가있음 .~~() : 메소드 호출 
//		// boardService.selectList()
//		model.addAttribute("list",list); // (문자,객체)
//		model.addAttribute("word",searchWord);
//		return "board/index";
//	} 
	
	@RequestMapping("/board/write.do")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/board/insert.do")
	public String insert(BoardVo vo, HttpServletRequest req, MultipartFile file, HttpSession sess) {
		vo.setUserno(((UserVo)sess.getAttribute("loginInfo")).getUserno());
		//파일저장 
		if (!file.isEmpty()) { // 사용자가 파일을 첨부했다면 
			try {
				String path = req.getRealPath("/upload/");
				String filename = file.getOriginalFilename(); // 사용자가 업로드한 원본 파일
				file.transferTo(new File(path+filename)); // 경로에 파일을 저장 
				vo.setFilename(filename);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		int r = boardService.insert(vo);
		System.out.println("r : "+r);
		
		// 정상적으로 등록되었습니다. alert 띄우고 
		// index.do 로 이동 
		if(r > 0) {
		req.setAttribute("msg", "정상적으로 등록되었습니다");
		req.setAttribute("url", "index.do");
		} else {
			req.setAttribute("msg", "등록 오류 ");
			req.setAttribute("url", "write.do");
		}
		
		return "include/result";
		}

	@GetMapping("/board/detail.do")
	public String detail(Model model, @RequestParam int boardno) {
		model.addAttribute("data", boardService.selectOne(boardno));
		return "board/detail";
	}
	@GetMapping("/board/detail2.do")
	public String detail2(Model model, @RequestParam int boardno) {
		model.addAttribute("data", boardService.selectOne2(boardno));
		return "board/detail2";
	}
	
	@GetMapping("/board/edit.do")
	public String edit(Model model, @RequestParam int boardno) {
		model.addAttribute("data", boardService.selectOne(boardno));
		return "board/edit";
	}
	
	@PostMapping("/board/update.do")
	public String update(Model model, BoardVo vo) {
		int r = boardService.update(vo);
		if(r > 0) {
			model.addAttribute("msg","정상적으로 수정되었습니다.");
			model.addAttribute("url","detail.do?boardno="+vo.getBoardno()); // 성공 했을때 상세페이지 이동 
		}else {
			model.addAttribute("msg","수정오류");
			model.addAttribute("url","edit.do?boardno="+vo.getBoardno()); //실패했을때 수정페이지 이동 
		}
		return "include/result";
	}
	
	@GetMapping("/board/delete.do")
	public String delete(Model model, BoardVo vo) {
		int r = boardService.delete(vo); 
		if(r > 0) {
			model.addAttribute("msg","정상적으로 수정되었습니다.");
			model.addAttribute("url","index.do"); // 성공 했을때 상세페이지 이동 
		}else {
			model.addAttribute("msg","삭제 오류");
			model.addAttribute("url","detail.do?boardno="+vo.getBoardno()); //실패했을때 상세페이지 이동 
		}
		return "include/result";
	}
}

/*
 	mybatise
 	객체 : SQL 매핑 
 	dao 에서 주입받은 객체를 통해 메서드 실행  
	 등록 :insert
	 수정 : update
	 삭제 : delete
	 한건조회 : selectOne
	 여러건조회 : selectList
	 
	 메서드 호출
	 객체.메서드명("namespace.id",[매개변수]);
	 
	 mapper파일(xml)
	 -namespace
	 -id
	 각(select, insert, update, delete)태그의 속성 
	 -parameterType : 파라미터 자료형 
	 -resultType : 결과 자료형
	 -resultMap: 결과 맵(컬럼명과 객체의 필드명이 다른 경우)
	 
	  #{변수명} : Prepared 
	  insert into ...values(#{title}... <- 이 자리에 ? 로 들어감 
	  ${변수명} : Statement 
	  insert into ... values('${title} .. <- 이 자리에 ${title}값이 그대로 들어감 
	  
	 조건(동적으로 변경) 
	 <where>라는 태그!!!! 
	 <if>, <choose> 
	 -> dynamic sql (동적 SQL) 
	  
	 
*/ 
