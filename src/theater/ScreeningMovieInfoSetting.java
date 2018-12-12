package theater;

import java.util.ArrayList;
import java.util.Scanner;

import dao.ScreeningMovieInfoSettingDAO;

// 상영영화정보관리
public class ScreeningMovieInfoSetting {
	private Scanner sc = new Scanner(System.in);
	String inputMenu = "";
	private int screeningTableNo;
	private int movieBranchNo;
	private int screenNo;
	private int movieNo;
	private int schNo;

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

	public int getScreenNo() {
		return screenNo;
	}

	public void setScreenNo(int screenNo) {
		this.screenNo = screenNo;
	}

	public int getSchNo() {
		return schNo;
	}

	public void setSchNo(int schNo) {
		this.schNo = schNo;
	}

	public void start() {
		Print.printMessage("---------- 상 영 영 화 정 보 관 리 ----------");
		Print.printMessage("-> 원하시는 메뉴를 선택하세요.");
		Print.printMessage("1: 상영영화 등록   2: 상영영화 삭제");
		inputMenu = sc.next();
		menu(inputMenu);
	}

	private void menu(String menu) {
		switch (menu) {
		case "1":
			Print.printMessage("-> 상영영화정보 등록");
			addScreeningMovieInfo();

			// 성공
			if (new ScreeningMovieInfoSettingDAO().addScreeningMovieInfoSQL(getMovieBranchNo(),getScreenNo(),getMovieNo(),getSchNo())) {
				Print.printMessage("!! 영화정보 등록 성공");
			} else {
				Print.printMessage("!! 영화정보 등록 실패");
			}
			break;
		case "2":
			Print.printMessage("-> 상영영화정보 삭제");
			deleteScreeningMovieInfo();
			if (new ScreeningMovieInfoSettingDAO().deleteScreeningMovieInfoSQL(getMovieBranchNo(),getScreenNo(),getMovieNo(),getSchNo())) {
				Print.printMessage("!! 영화정보 삭제 성공");
			} else {
				Print.printMessage("!! 영화정보 삭제 실패");
			}
			break;

		default:
			break;
		}
	}

	private void deleteScreeningMovieInfo() {
		Screen screen = new Screen();
		Print.printMessage("-> 영화관&상영관 정보");
		screen.getScreenInfoList(); // 상영관 정보 출력
		MovieInfoSetting movie = new MovieInfoSetting();
		Print.printMessage("-> 영화 정보");
		movie.getMovieInfoList(); // 영화 정보 출력
		Schedule schedule = new Schedule();
		Print.printMessage("-> 일정 정보");
		schedule.getScheduleInfoList(); //일정 정보 출력
		Print.printMessage("-> 등록된 상영 영화 정보");
		getScreeningMovieInfoList();
		Print.printMessage("-> 상영 영화 정보를 삭제하려는 영화관 지점의 코드를  입력하세요.");
		setMovieBranchNo(sc.nextInt());
		Print.printMessage("-> 상영 영화 정보를 삭제하려는 상영관의 번호를 입력하세요.");
		setScreenNo(sc.nextInt());
		Print.printMessage("-> 상영 영화 정보를 삭제할 영화코드를 입력하세요.");
		setMovieNo(sc.nextInt());
		Print.printMessage("-> 상영 영화 정보를 삭제할 일정 코드를 입력하세요.");
		setSchNo(sc.nextInt());
	}

	private void addScreeningMovieInfo() {
		Screen screen = new Screen();
		Print.printMessage("-> 영화관&상영관 정보");
		screen.getScreenInfoList(); // 상영관 정보 출력
		MovieInfoSetting movie = new MovieInfoSetting();
		Print.printMessage("-> 영화 정보");
		movie.getMovieInfoList(); // 영화 정보 출력
		Schedule schedule = new Schedule();
		Print.printMessage("-> 일정 정보");
		schedule.getScheduleInfoList(); //일정 정보 출력
		Print.printMessage("-> 상영 영화 정보를 입력하려는 영화관 지점의 코드를  입력하세요.");
		setMovieBranchNo(sc.nextInt());
		Print.printMessage("-> 상영 영화 정보를 입력하려는 상영관의 번호를 입력하세요.");
		setScreenNo(sc.nextInt());
		Print.printMessage("-> 상영 영화 정보가 담긴 영화코드를 입력하세요.");
		setMovieNo(sc.nextInt());
		Print.printMessage("-> 상영 영화 정보를 입력할 일정 코드를 입력하세요.");
		setSchNo(sc.nextInt());
	}
	
	/* 저장된 상영 영화 정보 목록 출력 */
	public void getScreeningMovieInfoList() {
		ScreeningMovieInfoSettingDAO screeningmovieinfosettingdao = new ScreeningMovieInfoSettingDAO();
		ArrayList<ScreeningMovieInfoSetting> arrayList = new ArrayList<ScreeningMovieInfoSetting>();
		arrayList = screeningmovieinfosettingdao.getScreenInfo();
		if(arrayList == null) {
			Print.printMessage("아무 정보도 등록되지 않았습니다.");
			Print.printMessage("-----------------------------------------------------");
			return;
		}
		Print.printMessage("상영일정코드 지점코드\t상영관번호\t영화코드\t일정코드");
		int i = 0;
		while(i < arrayList.size()) {
			ScreeningMovieInfoSetting temp = arrayList.get(i);
			Print.printMessage(temp.getScreeningTableNo() + "\t " + temp.getMovieBranchNo() +"\t" + temp.getScreenNo()+"\t" +temp.getMovieNo()+"\t" +temp.getSchNo());
			i++;
		}
		Print.printMessage("총 " + (i) + "개의 상영 영화 정보가 저장되어있습니다.");
		Print.printMessage("-----------------------------------------------------");
	}

	public int getScreeningTableNo() {
		return screeningTableNo;
	}

	public void setScreeningTableNo(int screeningTableNo) {
		this.screeningTableNo = screeningTableNo;
	}
}
