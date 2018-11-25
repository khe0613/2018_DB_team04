package theater;

import java.util.Scanner;

public class MovieInfoSetting {
	private Scanner sc = new Scanner(System.in);
	String inputMenu = "";
	
	private String movieName;
	private String directorName;
	private String summary;
	private String showTime;
	private String releaseTime;
	private String rating;
	private String perforMername;
	private String genre;
	
	
	public void start() {
		Print.printMessage("------------ 영 화 정 보 관 리 ------------");
		Print.printMessage("-> 원하시는 메뉴를 선택하세요.");
		Print.printMessage("1: 영화 등록   2: 영화정보수정   3: 영화 삭제");
		inputMenu = sc.next();
		menu(inputMenu);
	}
	
	public void MovieInfoSQL(String movieName, String directorName, String summary,
			String showTime, String releaseTime, String rating, String perforMername, String genre,
			int sort) {
		
	}
	private void menu(String menu) {
		switch(menu) {
		case "1":
			Print.printMessage("-> 영화 등록");
			addMovieInfo();
			break;
		case "2":
			Print.printMessage("-> 영화정보수정");
			rewriteMovieInfo();
			break;
		case "3":
			Print.printMessage("-> 영화 삭제");
			deleteMovieInfo();
			break;
			default:
				break;
		}
	}
	
	private void addMovieInfo() {
		Print.printMessage("-> 추가하려는 영화명을 입력하세요.");
		setMovieName(sc.next());
		Print.printMessage("-> 추가하려는 감독명을 입력하세요.");
		setDirectorName(sc.next());
		Print.printMessage("-> 추가하려는 영화주요정보를  입력하세요.");
		setSummary(sc.next());
		Print.printMessage("-> 추가하려는 상영시간을  입력하세요.");
		setShowTime(sc.next());
		Print.printMessage("-> 추가하려는 개봉일을  입력하세요.");
		setReleaseTime(sc.next());
		Print.printMessage("-> 추가하려는 상영등급을  입력하세요.");
		setRating(sc.next());
		Print.printMessage("-> 추가하려는 출연자명을 입력하세요.");
		setPerforMername(sc.next());
		Print.printMessage("-> 추가하려는 장르명을 입력하세요.");
		setGenre(sc.next());
		MovieInfoSQL(getMovieName(), getDirectorName(), getSummary(), getShowTime(), getReleaseTime(),
				getRating(), getPerforMername(), getGenre(), 0);
	}
	
	private void rewriteMovieInfo() {
		Print.printMessage("-> 수정하려는 영화명을 입력하세요.");
		setMovieName(sc.next());
		Print.printMessage("-> 수정하려는 감독명을 입력하세요.");
		setDirectorName(sc.next());
		Print.printMessage("-> 수정하려는 영화주요정보를  입력하세요.");
		setSummary(sc.next());
		Print.printMessage("-> 수정하려는 상영시간을  입력하세요.");
		setShowTime(sc.next());
		Print.printMessage("-> 수정하려는 개봉일을  입력하세요.");
		setReleaseTime(sc.next());
		Print.printMessage("-> 수정하려는 상영등급을  입력하세요.");
		setRating(sc.next());
		Print.printMessage("-> 수정하려는 출연자명을 입력하세요.");
		setPerforMername(sc.next());
		Print.printMessage("-> 수정하려는 장르명을 입력하세요.");
		setGenre(sc.next());
		MovieInfoSQL(getMovieName(), getDirectorName(), getSummary(), getShowTime(), getReleaseTime(),
				getRating(), getPerforMername(), getGenre(), 1);	
	}
	private void deleteMovieInfo() {
		Print.printMessage("-> 삭제하려는 영화코드를 입력하세요.");
		String temp = sc.next();
		// 코드 삭제하는 SQL문.
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

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
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
}
