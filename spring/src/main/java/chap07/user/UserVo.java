package chap07.user;

import java.sql.Timestamp;

public class UserVo {

	private int userno;
	private String id;
	private String pwd;
	private String name;
	private Timestamp regdate;
	private String checkId; // 아이디저장
	
	private String[] school;
	private String[] year;
	
	public String[] getSchool() {
		return school;
	}
	public void setSchool(String[] school) {
		this.school = school;
	}
	public String[] getYear() {
		return year;
	}
	public void setYear(String[] year) {
		this.year = year;
	}
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
}
