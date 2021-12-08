package chap07;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class BoardVo extends Parameter { // Parameter.java 에서 상속받아옴 

	private int boardno;
	private String title;
	private String content;
	private String writer;
	private Timestamp regdate;
	private String filename;
	private int userno;
	
   
}
