package chap07.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import chap07.BoardVo;

public interface UserService {
	boolean insert(UserVo vo, HttpServletRequest req);
	int idcheck(String id);
	boolean login(UserVo vo,HttpSession sess);
//	List<UserVo> selectList(UserVo vo);
//	int count(UserVo vo);
}

