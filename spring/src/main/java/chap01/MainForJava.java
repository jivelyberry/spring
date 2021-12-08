package chap01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForJava {

		public static void main(String[] args) {
			//설정파일을 읽어와서 빈등록 
			//greeter라는 이름으로 객체(빈)을 컨테이너에 등록 (싱글톤으로 등록) 
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
			
			//빈을 가져오기
			Greeter g = (Greeter)ctx.getBean("greeter"); // getBean이 오브젝트를 리턴 -> 강제형변환 -> 자식으로 다시 바꿔줌 
			Greeter g2 = (Greeter)ctx.getBean("greeter",Greeter.class); // Greeter.class : Greeter 타입을 가져옴
			
			System.out.println(g == g2); // 같은 객체 (같은주소를 가르킨다) -> 참조자료형 
			
		}
}
