package chap05;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/*
  	RestAPI 컨트롤러 
 */

@RestController
public class ApiController {
	
	//@Autowide
	// 
	
	@GetMapping("/api/test")
	public MemberVo test() {
		MemberVo vo = new MemberVo();
		vo.setName("홍길동");
		vo.setEmail("hong@gmail.com");
		vo.setNo(1);
		return vo;
	}
	
	/*
	 	 파라미터를 받는 4번째 방법 
	 	 @PathVariable  경로의 변수 
	 	 /api/list/1/자바 -> page=1, serchWord=자바 
	 	 /api/list/2/파이썬 -> page=2, serchWord=파이썬 
	 */
	
	
	// 안드로이드에서 모바일에서 웹으로 이동할때 사용 
	@GetMapping("/api/list/{page}/{searchWord}") //  "/api/list/{page}/{searchWord}" 경로처럼 생겼지만 파라미터이다.
	public List<MemberVo> list(@PathVariable int page, @PathVariable String searchWord){
		System.out.println("page : "+page);
		System.out.println("searchWord : "+searchWord);
		
		List<MemberVo> list = new ArrayList<MemberVo>();
		MemberVo vo = new MemberVo();
		vo.setName("홍길동");
		vo.setEmail("hong@gmail.com");
		vo.setNo(1);
		list.add(vo); 
		list.add(vo); 
		return list;
		
		
//	@GetMapping("/api/list/{page}/{searchWord}") //  "/api/list/{page}/{searchWord}" 경로처럼 생겼지만 파라미터이다.
//	public List<BoardVo> list(@PathVariable int page, @PathVariable String searchWord){
//		System.out.println("page : "+page);
//		System.out.println("searchWord : "+searchWord);
//		List<BoardVo> list = service.selectList();
//		return list;
	
		
	}
}
