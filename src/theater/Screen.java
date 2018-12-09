package theater;

import java.util.Scanner;

import dao.ScreenDAO;

// 상영관 관리
public class Screen {
	private int screenNo;		// 상영관 코드
	private int branchNo;		// 지점 코드
	private int totalSeatNum; 	// 전체좌석수
	
	public Screen() {
		
	}
	
	public int getScreenNo() {
		return screenNo;
	}


	public void setScreenNo(int screenNo) {
		this.screenNo = screenNo;
	}


	public int getBranchNo() {
		return branchNo;
	}


	public void setBranchNo(int branchNo) {
		this.branchNo = branchNo;
	}
	
	public int getTotalseatNum() {
		return totalSeatNum;
	}


	public void setTotalseatNum(int totalSeatNum) {
		this.totalSeatNum = totalSeatNum;
	}
	
	public void start() {
		Print.printMessage("---------------- 상 영 관 정 보 관 리 ----------------");
		Print.printMessage("-> 원하시는 메뉴를 선택하세요.");
		Print.printMessage("1: 상영관 등록    2: 상영관 삭제   3: 상영관 수정");
		Scanner sc = new Scanner(System.in);
		String menu = sc.next();
		
		switch(menu) {
		case "1":
			Print.printMessage("-> 등록하려는 지점코드를 입력하세요.");
			setBranchNo(sc.nextInt());
			Print.printMessage("-> 원하시는 이 상영관의 전체 좌석 수를 입력하세요.");
			setTotalseatNum(sc.nextInt());
			if(registerScreen(branchNo,totalSeatNum)) 
				Print.printMessage("!! 상영관 등록이 성공하였습니다.");
			else
				Print.printMessage("!! 상영관 등록이 실패하였습니다.");
			break;
		case "2":
			Print.printMessage("-> 삭제하려는 지점코드를 입력하세요.");
			setBranchNo(sc.nextInt()); 
			Print.printMessage("-> 삭제하려는 상영관코드를 입력하세요.");
			setScreenNo(sc.nextInt());
			if(removeScreen(branchNo,screenNo)) 
				Print.printMessage("!! 상영관 삭제가 성공하였습니다.");
			else
				Print.printMessage("!! 상영관 삭제가 실패하였습니다.");
			break;
		case "3":
			Print.printMessage("-> 수정하려는 지점코드를 입력하세요.");
			setBranchNo(sc.nextInt()); 
			Print.printMessage("-> 수정하려는 상영관코드를 입력하세요.");
			setScreenNo(sc.nextInt());
			Print.printMessage("-> 수정하려는 전체 좌석 수를 입력하세요.");
			setTotalseatNum(sc.nextInt());
			if(modifyScreen(screenNo,branchNo,totalSeatNum)) 
				Print.printMessage("!! 상영관 수정이 성공하였습니다.");
			else
 				Print.printMessage("!! 상영관 수정이 실패하였습니다.");
			break;
		default:
			break;
		}
	}
	
	// 상영관 등록
	public boolean registerScreen(int branchNo, int totalSeatNum) {
		ScreenDAO screenDAO = new ScreenDAO();
		
		System.out.println("---------------- 상영관 등록 ----------------");
		this.setScreenNo(screenDAO.getScreenNum(branchNo)+1); // dao에서  함수 호출하여 불러온거에 +1해준 것
		this.setBranchNo(branchNo);
		this.setTotalseatNum(totalSeatNum);
		
		if(screenDAO.registerScreen(this)) {
			System.out.println("상영관 등록에 성공하셨습니다.");
			return true;
		}else {
			System.out.println("상영관 등록에 실패하셨습니다.");
			return false;
		}
	}
	
	// 상영관 삭제
	public boolean removeScreen(int branchNo, int screenNo) {
		ScreenDAO screenDAO = new ScreenDAO();
		
		System.out.println("---------------- 상영관 삭제 ----------------");
		if(screenDAO.removeScreen(branchNo,screenNo)) {
			System.out.println("상영관 삭제에 성공하셨습니다.");
			return true;
		}else {
			System.out.println("상영관 삭제에 실패하셨습니다..");
			return false;
		}
	}
	
	// 상영관 수정
	public boolean modifyScreen(int screenNo, int branchNo, int totalSeatNum) {
		ScreenDAO screenDAO = new ScreenDAO();
		
		System.out.println("---------------- 상영관 수정 ----------------");
		this.setScreenNo(screenNo);
		this.setBranchNo(branchNo);
		this.setTotalseatNum(totalSeatNum);
		
		if(screenDAO.modifyScreen(this)) {
			System.out.println("상영관 수정에 성공하셨습니다.");
			return true;
		}else {
			System.out.println("상영관 수정에 실패하셨습니다.");
			return false;
		}
	}

	
}
