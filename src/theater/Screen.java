package theater;

import java.util.Scanner;

import dao.ScreenDAO;

// 상영관 관리
public class Screen {
	private int screenNo;		// 상영관 코드
	private int branchNo;		// 지점 코드
	private int scheduleCode;	// 일정코드
	private int totalSeatNum; 	// 전체좌석수
	private int leftSeatNum;	// 잔여석
	
	public Screen() {
		
	}
	
	public int getScreenNo() {
		return screenNo;
	}
	
	public int getBranchNo() {
		return this.branchNo;
	}
	
	public int getScheduleCode() {
		return this.scheduleCode;
	}
	

	public int getTotalSeatNum() {
		return totalSeatNum;
	}
	
	public int getLeftSeatNum() {
		return leftSeatNum;
	}
	
	public void start() {
		Print.printMessage("---------------- 상 영 관 정 보 관 리 ----------------");
		Print.printMessage("-> 원하시는 메뉴를 선택하세요.");
		Print.printMessage("1: 상영관 등록    2: 상영관 삭제   3: 상영관 수정");
		Scanner sc = new Scanner(System.in);
		String menu = sc.next();
		
		int branchno, screenno;
		switch(menu) {
		case "1":
			Print.printMessage("-> 원하시는 screenNo를 입력하세요.");
			screenno = sc.nextInt();
			Print.printMessage("-> 원하시는 branchNo를 입력하세요.");
			branchno = sc.nextInt();
			if(registerScreen(screenno, branchno)) 
				Print.printMessage("!! 상영관 등록이 성공하였습니다.");
			else
				Print.printMessage("!! 상영관 등록이 실패하였습니다.");
			break;
		case "2":
			Print.printMessage("-> 원하시는 screenNo를 입력하세요.");
			screenno = sc.nextInt();
			Print.printMessage("-> 원하시는 branchNo를 입력하세요.");
			branchno = sc.nextInt();
			if(removeScreen(screenno, branchno)) 
				Print.printMessage("!! 상영관 삭제가 성공하였습니다.");
			else
				Print.printMessage("!! 상영관 삭제가 실패하였습니다.");
			break;
		case "3":
			
			break;
			default:
				break;
		}
	}
	
	// 상영관 등록
	public boolean registerScreen(int screenNo, int branchNo) {
		ScreenDAO screenDAO = new ScreenDAO();
		Scanner sc = new Scanner(System.in);
		boolean success;
		
		System.out.println("---------------- 상영관 등록 ----------------");
		this.screenNo = screenNo;
		this.branchNo = branchNo;
		System.out.print("전체좌석수 : ");	this.totalSeatNum = sc.nextInt();
		this.leftSeatNum = this.totalSeatNum;
		
		
		if(screenDAO.registerScreen(this)) {
			System.out.println("상영관 등록에 성공하셨습니다.");
			success = true;
		}else {
			System.out.println("상영관 등록에 실패하셨습니다.");
			success = false;
		}
		
		screenDAO.disconnectDB();
		return success;
	}
	
	// 상영관 삭제
	public boolean removeScreen(int screenNo, int branchNo) {
		ScreenDAO screenDAO = new ScreenDAO();
		boolean success;
		
		System.out.println("---------------- 상영관 삭제 ----------------");
		if(screenDAO.removeScreen(screenNo, branchNo)) {
			System.out.println("상영관 삭제에 성공하셨습니다.");
			success = true;
		}else {
			System.out.println("상영관 삭제에 실패하셨습니다..");
			success = false;
		}
		
		screenDAO.disconnectDB();
		
		return success;
	}
	
	// 상영관 수정
	public boolean modifyScreen(Screen screen) {
		ScreenDAO screenDAO = new ScreenDAO();
		boolean success;
		
		System.out.println("---------------- 상영관 수정 ----------------");
		if(screenDAO.modifyScreen(screen)) {
			this.screenNo = screen.getScreenNo();
			this.branchNo = screen.getBranchNo();
			this.scheduleCode = screen.getScheduleCode();
			this.totalSeatNum = screen.getTotalSeatNum();
			this.leftSeatNum =  screen.getLeftSeatNum();
			
			System.out.println("상영관 수정에 성공하셨습니다.");
			success = true;
		}else {
			System.out.println("상영관 수정에 실패하셨습니다.");
			success = false;
		}
		
		return success;
	}
	

	
	
	
	
	
	
}
