package theater;

import java.util.ArrayList;
// 영화정보관리
import java.util.Scanner;

import dao.MovieDAO;


public class Movie {
	private Scanner sc = new Scanner(System.in);
	String inputMenu = "";
	
	private int movieNO;
	private String movieName;
	private String directorName;
	private String summary;
	private int showTime;
	private String releaseDate;
	private String rating;
	private String perforMername;
	private String genre;
	
	
	public void start() {
		Print.printMessage("--------------- 영 화 정 보 관 리 ---------------");
		Print.printMessage("-> 원하시는 메뉴를 선택하세요.");
		Print.printMessage("1: 영화 등록   2: 영화정보수정   3: 영화 삭제   4: 저장된 영화 목록 보기");
		inputMenu = sc.next();
		menu(inputMenu);
	}
	
	private void menu(String menu) {
		switch(menu) {
		case "1":
			Print.printMessage("-> 영화정보 등록");
			addMovieInfo();
			// 성공
			if(new MovieDAO().addMovieInfoSQL(getMovieName(), getDirectorName(), getSummary(), getShowTime(),
					getReleaseDate(), getRating(), getPerforMername(), getGenre())) {
				Print.printMessage("!! 영화정보 등록 성공");
			}
			else {
				Print.printMessage("!! 영화정보 등록 실패");
			}
			break;
		case "2":
			Print.printMessage("-> 영화정보수정");
			rewriteMovieInfo();
			if(new MovieDAO().rewriteMovieInfoSQL(getMovieNO(), getMovieName(), getDirectorName(), getSummary(), getShowTime(),
					getReleaseDate(), getRating(), getPerforMername(), getGenre())) {
				Print.printMessage("!! 영화정보 수정 성공");
			}
			else {
				Print.printMessage("!! 영화정보 수정 실패");
			}
			break;
		case "3":
			Print.printMessage("-> 영화 삭제");
			deleteMovieInfo();
			if(new MovieDAO().deleteMovieInfoSQL(movieNO)) {
				Print.printMessage("!! 영화정보 삭제 성공");
			}
			else {
				Print.printMessage("!! 영화정보 삭제 실패");
			}
			break;
		case "4":
			Print.printMessage("-> 저장된 영화 목록");
			getMovieInfoList();
			default:
				break;
		}
	}
	
	/* 영화 정보 등록 */
	private void addMovieInfo() {
		Print.printMessage("-> 추가하려는 영화명을 입력하세요.");
		setMovieName(sc.next());
		Print.printMessage("-> 추가하려는 감독명을 입력하세요.");
		setDirectorName(sc.next());
		Print.printMessage("-> 추가하려는 영화주요정보를  입력하세요.");
		setSummary(sc.next());
		Print.printMessage("-> 추가하려는 상영시간을  입력하세요.");
		setShowTime(sc.nextInt());
		Print.printMessage("-> 추가하려는 개봉일을  입력하세요.");
		setReleaseDate(sc.next());
		Print.printMessage("-> 추가하려는 상영등급을  입력하세요.");
		setRating(sc.next());
		Print.printMessage("-> 추가하려는 출연자명을 입력하세요.");
		setPerforMername(sc.next());
		Print.printMessage("-> 추가하려는 장르명을 입력하세요.");
		setGenre(sc.next());
	}
	
	/* 영화 정보 수정 */
	private void rewriteMovieInfo() {
		Print.printMessage("-> 수정하려는 영화정보의 영화코드를 입력하세요.");
		setMovieNO(sc.nextInt());
		Print.printMessage("-> 수정하려는 영화명을 입력하세요.");
		setMovieName(sc.next());
		Print.printMessage("-> 수정하려는 감독명을 입력하세요.");
		setDirectorName(sc.next());
		Print.printMessage("-> 수정하려는 영화주요정보를  입력하세요.");
		setSummary(sc.next());
		Print.printMessage("-> 수정하려는 상영시간을  입력하세요.");
		setShowTime(sc.nextInt());
		Print.printMessage("-> 수정하려는 개봉일을  입력하세요.");
		setReleaseDate(sc.next());
		Print.printMessage("-> 수정하려는 상영등급을  입력하세요.");
		setRating(sc.next());
		Print.printMessage("-> 수정하려는 출연자명을 입력하세요.");
		setPerforMername(sc.next());
		Print.printMessage("-> 수정하려는 장르명을 입력하세요.");
		setGenre(sc.next());	
	}
	
	/* 영화 정보 삭제 */
	private void deleteMovieInfo() {
		Print.printMessage("-> 삭제하려는 영화코드를 입력하세요.");
		setMovieNO(sc.nextInt());
	}

	/* 저장된 영화 정보 목록 출력 */
	public void getMovieInfoList() {
		ArrayList<Movie> arrayList = new ArrayList<Movie>();
		arrayList = new MovieDAO().getMovieInfoListSQL();
		if(arrayList == null) {
			Print.printMessage("아무 정보도 등록되지 않았습니다.");
			Print.printMessage("-----------------------------------------------------");
			return;
		}
		Print.printMessage("영화코드\t영화명\t\t감독명\t\t영화주요정보\t\t상영시간\t개봉일\t\t상영등급\t출연자명\t장르명");
		int i = 0;
		while(i < arrayList.size()) {
			Movie temp = arrayList.get(i);
			Print.printMessage(temp.getMovieNO() + "\t" + temp.getMovieName() + "\t" + temp.getDirectorName()
			+ "\t" + temp.getSummary() + "\t" + temp.getShowTime() + "\t" + temp.getReleaseDate()
			+ "\t" + temp.getRating() + "\t" + temp.getPerforMername() + "\t" + temp.getGenre());
			
			i++;
		}
		Print.printMessage("총 " + (i) + "개의 영화 정보가 저장되어있습니다.");
		Print.printMessage("-----------------------------------------------------");
	}
	
	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getShowTime() {
		return showTime;
	}

	public void setShowTime(int showTime) {
		this.showTime = showTime;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getPerforMername() {
		return perforMername;
	}

	public void setPerforMername(String perforMername) {
		this.perforMername = perforMername;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getMovieNO() {
		return movieNO;
	}

	public void setMovieNO(int movieNO) {
		this.movieNO = movieNO;
	}
}
