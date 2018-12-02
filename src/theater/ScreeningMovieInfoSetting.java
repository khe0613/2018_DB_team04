package theater;

import java.util.Scanner;

// 상영영화정보관리
public class ScreeningMovieInfoSetting {
	private Scanner sc = new Scanner(System.in);
	String inputMenu = "";
	private int movieBranchNo;
	private int movieNo;
	
	public int getMovieNo() {
		return movieNo;
	}
	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}
	public int getMovieBranchNo() {
		return movieBranchNo;
	}
	public void setMovieBranchNo(int movieBranchNo) {
		this.movieBranchNo = movieBranchNo;
	}
	
	public void start() {
		Print.printMessage("---------- 상 영 영 화 정 보 관 리 ----------");
		Print.printMessage("-> 원하시는 메뉴를 선택하세요.");
		Print.printMessage("1: 상영영화 등록   2: 상영영화 삭제");
		inputMenu = sc.next();
		menu(inputMenu);
	}
}
