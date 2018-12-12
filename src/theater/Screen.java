package theater;

import java.util.ArrayList;
import java.util.Scanner;

import dao.ScreenDAO;
import dao.SeatDAO;

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
		int branchNo;
		int screenNo;
		int totalSeatNum;
		
		Theater theater = new Theater();
		Print.printMessage("---------------- 상 영 관 정 보 관 리 ----------------");
		Print.printMessage("-> 원하시는 메뉴를 선택하세요.");
		Print.printMessage("1: 상영관 등록    2: 상영관 수정   3: 상영관 삭제  4: 상영관 조회");
		Scanner sc = new Scanner(System.in);
		String menu = sc.next();
		
		switch(menu) {
		case "1":
			theater.theaterList(); // 영화관 정보 출력
			Print.printMessage("-> 등록하려는 지점코드를 입력하세요.");
			branchNo = sc.nextInt();
			Print.printMessage("-> 등록하려는 상영관 번호를 입력하세요.");
			screenNo = sc.nextInt();
			Print.printMessage("-> 원하시는 이 상영관의 전체 좌석 수를 입력하세요.");
			totalSeatNum = sc.nextInt();
			if(registerScreen(branchNo, screenNo, totalSeatNum)) 
				Print.printMessage("!! 상영관 등록이 성공하였습니다.");
			else
				Print.printMessage("!! 상영관 등록이 실패하였습니다.");
			break;
		case "2":
			Print.printMessage("-> 수정하려는 지점코드를 입력하세요.");
			branchNo = sc.nextInt();
			Print.printMessage("-> 수정하려는 상영관 번호를 입력하세요.");
			screenNo = sc.nextInt();
			Print.printMessage("-> 수정하려는 전체 좌석 수를 입력하세요.");
			totalSeatNum = sc.nextInt();
			if(modifyScreen(screenNo,branchNo,totalSeatNum)) 
				Print.printMessage("!! 상영관 수정이 성공하였습니다.");
			else
 				Print.printMessage("!! 상영관 수정이 실패하였습니다.");
			break;
		case "3":
			Print.printMessage("-> 삭제하려는 지점코드를 입력하세요.");
			branchNo = sc.nextInt();
			Print.printMessage("-> 삭제하려는 상영관 번호를 입력하세요.");
			screenNo = sc.nextInt();
			if(removeScreen(branchNo,screenNo)) 
				Print.printMessage("!! 상영관 삭제가 성공하였습니다.");
			else
				Print.printMessage("!! 상영관 삭제가 실패하였습니다.");
			break;
		case "4":
			Print.printMessage("-> 저장된 상영관 목록");
			getScreenInfoList();
			break;
		default:
			break;
		}
	}
	
	// 상영관 등록
	public boolean registerScreen(int branchNo, int screenNo, int totalSeatNum) {
		ScreenDAO screenDAO = new ScreenDAO();
		SeatDAO seatDAO = new SeatDAO();
		boolean success = true;
		
		System.out.println("---------------- 상영관 등록 ----------------");
		this.setScreenNo(screenNo); 
		this.setBranchNo(branchNo);
		this.setTotalseatNum(totalSeatNum);
		
		if(screenDAO.registerScreen(this)) {
			for(int seat = 1; seat<= totalSeatNum; seat++) {
				if(!seatDAO.addSeat(seat, screenNo, branchNo)) {
					success = false;
				}
			}
		}else {
			success = false;
		}
		
		if(success) {
			System.out.println("상영관 등록에 성공하셨습니다.");
		}else {
			System.out.println("상영관 등록에 실패하셨습니다.");
		}
		
		return success;
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

	/* 저장된 상영관 정보 목록 출력 */
	public void getScreenInfoList() {
		ScreenDAO screenDAO = new ScreenDAO();
		ArrayList<Screen> arrayList = new ArrayList<Screen>();
		arrayList = screenDAO.getScreenInfo();
		if(arrayList == null) {
			Print.printMessage("아무 정보도 등록되지 않았습니다.");
			Print.printMessage("-----------------------------------------------------");
			return;
		}
		Print.printMessage("상영관번호\t지점코드\t전체좌석수");
		int i = 0;
		while(i < arrayList.size()) {
			Screen temp = arrayList.get(i);
			Print.printMessage(temp.getScreenNo() + "\t" + temp.getBranchNo() + "\t" + temp.getTotalseatNum());
			i++;
		}
		Print.printMessage("총 " + (i) + "개의 상영관 정보가 저장되어있습니다.");
		Print.printMessage("-----------------------------------------------------");
	}
}
