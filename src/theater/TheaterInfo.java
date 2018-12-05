package theater;

import java.util.ArrayList;
import java.util.Scanner;

import dao.TheaterInfoDAO;

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
	
	public void start() {
		Scanner sce = new Scanner(System.in);
		String inputMenu = "";
		Print.printMessage("------------- 영 화 관 정 보 관 리 -------------");
		Print.printMessage("-> 원하시는 메뉴를 선택하세요.");
		Print.printMessage("1: 영화관 등록   2: 영화관 정보 수정   3: 영화관 삭제   4. 영화관목록출력");
		inputMenu = sce.next();
		menu(inputMenu);
	}
	
	private void menu(String menu) {
		switch(menu) {
		case "1":
			Print.printMessage("-> 영화관 등록");
			// 성공
			if(addTheater()) {
				Print.printMessage("!! 영화관 등록 성공");
			}
			else {
				Print.printMessage("!! 영화관 등록 실패");
			}
			break;
		case "2":
			Print.printMessage("-> 영화관 정보 수정");
			
			if(modifyTheaterInfo()) {
				Print.printMessage("!! 영화관 수정 성공");
			}
			else {
				Print.printMessage("!! 영화관 수정 실패");
			}
			break;
		case "3":
			Print.printMessage("-> 영화관 삭제");
			
			if(removeTheater()) {
				Print.printMessage("!! 영화관 삭제 성공");
			}
			else {
				Print.printMessage("!! 영화관 삭제 실패");
			}
			break;
		case "4":
			theaterList();
			break;
		default:
			break;
		}
	}
	
	// 영화관 등록
	public boolean addTheater() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("---------------- 영화관 등록 ----------------");
		System.out.print("상영관 수 : ");	this.screenNum = sc.nextInt();
		System.out.print("주소 : ");		this.address = sc.nextLine();
		System.out.print("전화번호 : ");	this.tel = sc.nextLine();
		System.out.print("지점명 : ");		this.branchName = sc.nextLine();

		sc.close();
		
		if((this.branchNo = new TheaterInfoDAO().registerTheater(this)) != -1) {			// 영화관 등록 성공(지점코드가 리턴됨)
			return true;
		}else {
			return false;
		}
		

	}
	
	
	// 영화관 정보 수정
	public boolean modifyTheaterInfo() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("---------------- 영화관 정보 수정 ----------------");
		System.out.print("상영관 수 : ");
		int new_screenNum = sc.nextInt();
		
		System.out.print("주소 : ");
		String new_address = sc.nextLine();
		
		System.out.print("전화번호 : ");
		String new_tel = sc.nextLine();
		
		System.out.print("지점명 : ");	
		String new_branchName = sc.nextLine();
		
		sc.close();
		
		if(new TheaterInfoDAO().modifyTheaterInfo(this.branchNo, new_screenNum, new_address, new_tel, new_branchName)) {
			this.screenNum = new_screenNum;
			this.address = new_address;
			this.tel = new_tel;
			this.branchName = new_branchName;
			
			return true;
		}else {
			return false;
		}
	
	}
	
	// 영화관 삭제
	public boolean removeTheater() {
		return new TheaterInfoDAO().removeTheater(this.branchNo);		// 성공할 경우 true, 실패할 경우 false가 리턴됨
	}
	
	public void theaterList() {
		TheaterInfoDAO theaterInfoDAO = new TheaterInfoDAO();
		ArrayList<TheaterInfo> result = theaterInfoDAO.getMovieInfoListSQL();
		
		if(result == null) {
			Print.printMessage("!! 아무 정보도 등록되지 않았습니다.");
			Print.printMessage("-----------------------------------------------------");
			return;
		}
		
		Print.printMessage("지점명코드	상영관수	주소	전화번호	지점명");
		int i = 0;
		while(i < result.size()) {
			TheaterInfo temp = result.get(i);
			Print.printMessage(temp.getBranchNo() + "	" + temp.getScreenNum() + "	" + temp.getAddress()
			+ "	" + temp.getTel() + " " + temp.getBranchName());
			
			i++;
		}
		Print.printMessage("총 " + i + "개의 영화 정보가 저장되어있습니다.");
		Print.printMessage("-----------------------------------------------------");
	}
}
