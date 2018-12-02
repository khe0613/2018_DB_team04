package theater;

import java.util.Scanner;
import dao.ScreeningMovieInfoSettingDAO;

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
	
	private void menu(String menu) {
		switch(menu) {
		case "1":
			Print.printMessage("-> 상영영화정보 등록");
			addScreeningMovieInfo();
			
			// 성공
			if(new ScreeningMovieInfoSettingDAO().addScreeningMovieInfoSQL(getMovieNo(), getMovieBranchNo())) {
				Print.printMessage("!! 영화정보 등록 성공");
			}
			else {
				Print.printMessage("!! 영화정보 등록 실패");
			}
			break;
		case "2":
			Print.printMessage("-> 상영영화정보 삭제");
			deleteScreeningMovieInfo();
			if(new ScreeningMovieInfoSettingDAO().deleteScreeningMovieInfoSQL(getMovieNo(), getMovieBranchNo())) {
				Print.printMessage("!! 영화정보 삭제 성공");
			}
			else {
				Print.printMessage("!! 영화정보 삭제 실패");
			}
			break;
			
		default:
			break;
		}
	}
	
	private void deleteScreeningMovieInfo() {
		Print.printMessage("-> 영화 정보를 삭제하려는 영화관 지점의 코드를  입력하세요.");
		setMovieNo(sc.nextInt());
		Print.printMessage("-> 같이 삭제하려는 영화코드를 입력하세요.");
		setMovieBranchNo(sc.nextInt());
	}
	
	private void addScreeningMovieInfo() {
		Print.printMessage("-> 영화 정보를 입력하려는 영화관 지점의 코드를  입력하세요.");
		setMovieNo(sc.nextInt());
		Print.printMessage("-> 영화 정보가 담긴 영화코드를 입력하세요.");
		setMovieBranchNo(sc.nextInt());
	}
}
