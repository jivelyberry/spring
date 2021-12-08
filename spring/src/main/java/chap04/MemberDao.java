package chap04;

import org.springframework.stereotype.Component;

/*
    XXDao는 데이터 처리를 위한 클래스 
    Dao (Date Accecc Object) 
    예) insert, select, update, delete ....
 */
@Component //@Component 을 쓸 수 없다는건 이미 다른 라이브러리에 있다는 것 - > @Bean 방식을 써야함
public class MemberDao {
	
	
	
	//db에 회원 등록하는 메서드 
	public int insert(String name) {
		//db에 저장
		System.out.println(name + " 저장!!!");
		return 1; // 성공시 1 리턴 
	}
}
