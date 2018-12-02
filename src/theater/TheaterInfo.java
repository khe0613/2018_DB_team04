package theater;

import java.util.Scanner;

// 영화관정보관리
public class TheaterInfo {
	private int branchNo;
	private int screenNum;
	private String address;
	private String tel;
	private String branchName;
	
	
	public TheaterInfo() {
		
	}
	
	public TheaterInfo(int branchNo, int screenNum, String address, String tel, String branchName) {
		this.branchNo = branchNo;
		this.screenNum = screenNum;
		this.address = address;
		this.tel = tel;
		this.branchName = branchName;
	}
	
	public int getBranchNo() {
		return branchNo;
	}
	
	public void setBranchNo(int branchNo) {
		this.branchNo = branchNo;
	}
	
	public int getScreenNum() {
		return screenNum;
	}
	
	public void setScreenNum(int screenNum) {
		this.screenNum = screenNum;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getBranchName() {
		return branchName;
	}
	
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	
	// 영화관 등록
	public boolean addTheater() {
Scanner sc = new Scanner(System.in);
		
		System.out.println("---------------- 영화관 등록 ----------------");
		System.out.print("상영관 수 : ");	this.screenNum = sc.nextInt();
		System.out.print("주소 : ");		this.address = sc.nextLine();
		System.out.print("전화번호 : ");	this.tel = sc.nextLine();
		System.out.print("지점명 : ");		this.branchName = sc.nextLine();

		
		if((this.branchNo = new TheaterInfoDAO().registerTheater(this)) != -1) {			// 영화관 등록 성공(지점코드가 리턴됨)
			return true;
		}else {
			return false;
		}
		

	}
	
	public boolean modifyTheaterInfo(int branchNo, int screenNum, String address, String tel, String branchName) {
		
		return true;
	}
	
	public boolean removeTheater(int branchNo) {
		
		return true;
	}
	
	
}
