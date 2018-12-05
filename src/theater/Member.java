package theater;



import java.util.Scanner;

import dao.MemberDAO;
import dao.MemberDAO.loginResult;

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
	
	// 로그인
	public Member login() {
		Member member = new Member();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("---------------- 로 그 인 ----------------");
		System.out.print("아이디 : ");		this.id = sc.nextLine();
		System.out.print("비밀번호 : ");	this.pw = sc.nextLine();
		
		sc.close();
		loginResult result = new MemberDAO().loginMember(this);
		switch(result) {
		case SUCCESS:
			Print.printMessage("로그인에 성공하였습니다.");
			member.setId(this.id);
			member.setPw(this.pw);
			return member;
		case DB_FAILED:
			Print.printMessage("로그인에 실패하였습니다.");
			Print.printMessage("실패사유 : DB 처리 과정 중 오류 발생");
			return null;
		case INVALID_PW:
			Print.printMessage("로그인에 실패하였습니다.");
			Print.printMessage("실패사유 : 맞지 않는 비밀번호 입력");
			return null;
		case NOT_ID_IN_DB:
			Print.printMessage("로그인에 실패하였습니다.");
			Print.printMessage("실패사유 : 입력하신 아이디가 존재하지 않음");
			return null;
		}
		return null;
		
	}
	// 회원 가입
	public boolean register() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("---------------- 회원 가입 ----------------");
		System.out.print("아이디 : ");		this.id = sc.nextLine();
		System.out.print("비밀번호 : ");	this.pw = sc.nextLine();
		System.out.print("성명 : ");		this.name = sc.nextLine();
		System.out.print("생년월일 : ");	this.birth = sc.nextLine();
		System.out.print("주소 : ");		this.address = sc.nextLine();
		System.out.print("전화번호 : ");	this.phoneNum = sc.nextLine();
		this.point = 0;
		
		sc.close();
		
		if(new MemberDAO().registerMember(this)) {	
			return true;									// 성공
		}else {
			return false;									// 실패
		}
		
	}
	
	// 회원 탈퇴
	public boolean delete() {
		return new MemberDAO().deleteMember(this.id);	// 성공할 경우 true, 실패할 경우 false가 리턴됨
	}
	
	// 회원 정보 수정
	public boolean modify() {
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("---------------- 회원 정보 수정 ----------------");
		System.out.print("아이디 : ");
		String new_id = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String new_pw = sc.nextLine();
		
		System.out.print("성명 : ");
		String new_name = sc.nextLine();
		
		System.out.print("생년월일 : ");
		String new_birth = sc.nextLine();
		
		System.out.print("주소 : ");
		String  new_address = sc.nextLine();
		
		System.out.print("전화번호 : ");
		String new_phoneNum = sc.nextLine();
		
		sc.close();
		
		if(new MemberDAO().modifyMember(new_id, new_pw, new_name, new_birth, new_address, new_phoneNum)) {	// 성공
			this.id = new_id;
			this.pw = new_pw;
			this.name = new_name;
			this.birth = new_birth;
			this.address = new_address;
			this.phoneNum = new_phoneNum;
	
			return true;
		}else {
			return false;
		}
	}

	
}
