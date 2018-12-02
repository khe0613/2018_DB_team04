package theater;

import java.util.Scanner;

import dao.ScreenDAO;

// 상영관 관리
public class Screen {
	private int screenNo;		// 상영관 코드
	private int branchNo;		// 지점 코드
	private int totalSeatNum; 	// 전체좌석수
	private int leftSeatNum;	// 잔여석
	
	public Screen() {
		
	}
	
	public int getScreenNo() {
		return screenNo;
	}
	
	public int getBranchNo() {
		return branchNo;
	}
	

	
	public int getTotalSeatNum() {
		return totalSeatNum;
	}
	
	public int getLeftSeatNum() {
		return leftSeatNum;
	}
	
	public boolean registerScreen(int screenNo, int branchNo) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("---------------- 상영관 등록 ----------------");
		this.screenNo = screenNo;
		this.branchNo = branchNo;
		System.out.print("전체좌석수 : ");	this.totalSeatNum = sc.nextInt();
		this.leftSeatNum = this.totalSeatNum;
		
		if(new ScreenDAO().registerScreen(this))
		
		return true;
	}
	
	public boolean removeScreen() {
		return true;
	}
	
	public boolean modifyScreen() {
		return true;
	}
	

	
	
	
	
	
	
}
