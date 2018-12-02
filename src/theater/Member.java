package theater;

import java.text.BreakIterator;

import dao.MemberDAO;

public class Member {
	private String id;
	private String pw;
	private String name;
	private String birth;
	private String address;
	private String phoneNum;
	private int point;
	
	public Member() {
		
	}
	
	public Member(String id, String pw, String name, String birth, String address, String phoneNum, int point) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.address = address;
		this.phoneNum = phoneNum;
		this.point = point;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBirth() {
		return birth;
	}
	
	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	public boolean AdminCheck(String id) {
		if(id.equals("root")) 
			return true;
		else
			return false;
	}
	
	// 회원 가입
	public boolean register(String id, String pw, String name, String birth, String address, String phoneNum, int point) {
		if(new MemberDAO().registerMember(id, pw, name, birth, address, phoneNum, point)) {	// 성공
			this.id = id;
			this.pw = pw;
			this.name = name;
			this.birth = birth;
			this.address = address;
			this.phoneNum = phoneNum;
			this.point = point;
			
			return true;
		}else {
			return false;
		}
	}
	
	// 회원 탈퇴
	public boolean delete(String id) {
		if(new MemberDAO().deleteMember(id)) {
			this.id = null;
			this.pw = null;
			this.name = null;
			this.birth = null;
			this.address = null;
			this.phoneNum = null;
			this.point = 0;
			return true;
		}else {
			return false;
		}
	}
	
	// 회원 정보 수정
	public boolean modify(String id, String pw, String name, String birth, String address, String phoneNum, int point) {
		if(new MemberDAO().modifyMember(id, pw, name, birth, address, phoneNum, point)) {	// 성공
			this.id = id;
			this.pw = pw;
			this.name = name;
			this.birth = birth;
			this.address = address;
			this.phoneNum = phoneNum;
			this.point = point;
			
			return true;
		}else {
			return false;
		}
	}

	
}
