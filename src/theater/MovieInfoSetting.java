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
		Print.printMessage("------------ �� ȭ �� �� �� �� ------------");
		Print.printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
		Print.printMessage("1: ��ȭ ���   2: ��ȭ��������   3: ��ȭ ����");
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
			Print.printMessage("-> ��ȭ ���");
			addMovieInfo();
			break;
		case "2":
			Print.printMessage("-> ��ȭ��������");
			rewriteMovieInfo();
			break;
		case "3":
			Print.printMessage("-> ��ȭ ����");
			deleteMovieInfo();
			break;
			default:
				break;
		}
	}
	
	private void addMovieInfo() {
		Print.printMessage("-> �߰��Ϸ��� ��ȭ���� �Է��ϼ���.");
		setMovieName(sc.next());
		Print.printMessage("-> �߰��Ϸ��� �������� �Է��ϼ���.");
		setDirectorName(sc.next());
		Print.printMessage("-> �߰��Ϸ��� ��ȭ�ֿ�������  �Է��ϼ���.");
		setSummary(sc.next());
		Print.printMessage("-> �߰��Ϸ��� �󿵽ð���  �Է��ϼ���.");
		setShowTime(sc.next());
		Print.printMessage("-> �߰��Ϸ��� ��������  �Է��ϼ���.");
		setReleaseTime(sc.next());
		Print.printMessage("-> �߰��Ϸ��� �󿵵����  �Է��ϼ���.");
		setRating(sc.next());
		Print.printMessage("-> �߰��Ϸ��� �⿬�ڸ��� �Է��ϼ���.");
		setPerforMername(sc.next());
		Print.printMessage("-> �߰��Ϸ��� �帣���� �Է��ϼ���.");
		setGenre(sc.next());
		MovieInfoSQL(getMovieName(), getDirectorName(), getSummary(), getShowTime(), getReleaseTime(),
				getRating(), getPerforMername(), getGenre(), 0);
	}
	
	private void rewriteMovieInfo() {
		Print.printMessage("-> �����Ϸ��� ��ȭ���� �Է��ϼ���.");
		setMovieName(sc.next());
		Print.printMessage("-> �����Ϸ��� �������� �Է��ϼ���.");
		setDirectorName(sc.next());
		Print.printMessage("-> �����Ϸ��� ��ȭ�ֿ�������  �Է��ϼ���.");
		setSummary(sc.next());
		Print.printMessage("-> �����Ϸ��� �󿵽ð���  �Է��ϼ���.");
		setShowTime(sc.next());
		Print.printMessage("-> �����Ϸ��� ��������  �Է��ϼ���.");
		setReleaseTime(sc.next());
		Print.printMessage("-> �����Ϸ��� �󿵵����  �Է��ϼ���.");
		setRating(sc.next());
		Print.printMessage("-> �����Ϸ��� �⿬�ڸ��� �Է��ϼ���.");
		setPerforMername(sc.next());
		Print.printMessage("-> �����Ϸ��� �帣���� �Է��ϼ���.");
		setGenre(sc.next());
		MovieInfoSQL(getMovieName(), getDirectorName(), getSummary(), getShowTime(), getReleaseTime(),
				getRating(), getPerforMername(), getGenre(), 1);	
	}
	private void deleteMovieInfo() {
		Print.printMessage("-> �����Ϸ��� ��ȭ�ڵ带 �Է��ϼ���.");
		String temp = sc.next();
		// �ڵ� �����ϴ� SQL��.
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
