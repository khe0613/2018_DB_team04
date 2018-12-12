package theater;

import java.util.ArrayList;
// ��ȭ��������
import java.util.Scanner;

import dao.MovieInfoSettingDAO;

public class MovieInfoSetting {
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
		Print.printMessage("--------------- �� ȭ �� �� �� �� ---------------");
		Print.printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
		Print.printMessage("1: ��ȭ ���   2: ��ȭ��������   3: ��ȭ ����   4: ����� ��ȭ ��� ����");
		inputMenu = sc.next();
		menu(inputMenu);
	}
	
	private void menu(String menu) {
		switch(menu) {
		case "1":
			Print.printMessage("-> ��ȭ���� ���");
			addMovieInfo();
			// ����
			if(new MovieInfoSettingDAO().addMovieInfoSQL(getMovieName(), getDirectorName(), getSummary(), getShowTime(),
					getReleaseDate(), getRating(), getPerforMername(), getGenre())) {
				Print.printMessage("!! ��ȭ���� ��� ����");
			}
			else {
				Print.printMessage("!! ��ȭ���� ��� ����");
			}
			break;
		case "2":
			Print.printMessage("-> ��ȭ��������");
			rewriteMovieInfo();
			if(new MovieInfoSettingDAO().rewriteMovieInfoSQL(getMovieNO(), getMovieName(), getDirectorName(), getSummary(), getShowTime(),
					getReleaseDate(), getRating(), getPerforMername(), getGenre())) {
				Print.printMessage("!! ��ȭ���� ���� ����");
			}
			else {
				Print.printMessage("!! ��ȭ���� ���� ����");
			}
			break;
		case "3":
			Print.printMessage("-> ��ȭ ����");
			deleteMovieInfo();
			if(new MovieInfoSettingDAO().deleteMovieInfoSQL(movieNO)) {
				Print.printMessage("!! ��ȭ���� ���� ����");
			}
			else {
				Print.printMessage("!! ��ȭ���� ���� ����");
			}
			break;
		case "4":
			Print.printMessage("-> ����� ��ȭ ���");
			getMovieInfoList();
			default:
				break;
		}
	}
	
	/* ��ȭ ���� ��� */
	private void addMovieInfo() {
		Print.printMessage("-> �߰��Ϸ��� ��ȭ���� �Է��ϼ���.");
		setMovieName(sc.next());
		Print.printMessage("-> �߰��Ϸ��� �������� �Է��ϼ���.");
		setDirectorName(sc.next());
		Print.printMessage("-> �߰��Ϸ��� ��ȭ�ֿ�������  �Է��ϼ���.");
		setSummary(sc.next());
		Print.printMessage("-> �߰��Ϸ��� �󿵽ð���  �Է��ϼ���.");
		setShowTime(sc.nextInt());
		Print.printMessage("-> �߰��Ϸ��� ��������  �Է��ϼ���.");
		setReleaseDate(sc.next());
		Print.printMessage("-> �߰��Ϸ��� �󿵵����  �Է��ϼ���.");
		setRating(sc.next());
		Print.printMessage("-> �߰��Ϸ��� �⿬�ڸ��� �Է��ϼ���.");
		setPerforMername(sc.next());
		Print.printMessage("-> �߰��Ϸ��� �帣���� �Է��ϼ���.");
		setGenre(sc.next());
	}
	
	/* ��ȭ ���� ���� */
	private void rewriteMovieInfo() {
		Print.printMessage("-> �����Ϸ��� ��ȭ������ ��ȭ�ڵ带 �Է��ϼ���.");
		setMovieNO(sc.nextInt());
		Print.printMessage("-> �����Ϸ��� ��ȭ���� �Է��ϼ���.");
		setMovieName(sc.next());
		Print.printMessage("-> �����Ϸ��� �������� �Է��ϼ���.");
		setDirectorName(sc.next());
		Print.printMessage("-> �����Ϸ��� ��ȭ�ֿ�������  �Է��ϼ���.");
		setSummary(sc.next());
		Print.printMessage("-> �����Ϸ��� �󿵽ð���  �Է��ϼ���.");
		setShowTime(sc.nextInt());
		Print.printMessage("-> �����Ϸ��� ��������  �Է��ϼ���.");
		setReleaseDate(sc.next());
		Print.printMessage("-> �����Ϸ��� �󿵵����  �Է��ϼ���.");
		setRating(sc.next());
		Print.printMessage("-> �����Ϸ��� �⿬�ڸ��� �Է��ϼ���.");
		setPerforMername(sc.next());
		Print.printMessage("-> �����Ϸ��� �帣���� �Է��ϼ���.");
		setGenre(sc.next());	
	}
	
	/* ��ȭ ���� ���� */
	private void deleteMovieInfo() {
		Print.printMessage("-> �����Ϸ��� ��ȭ�ڵ带 �Է��ϼ���.");
		setMovieNO(sc.nextInt());
	}

	/* ����� ��ȭ ���� ��� ��� */
	public void getMovieInfoList() {
		ArrayList<MovieInfoSetting> arrayList = new ArrayList<MovieInfoSetting>();
		arrayList = new MovieInfoSettingDAO().getMovieInfoListSQL();
		if(arrayList == null) {
			Print.printMessage("�ƹ� ������ ��ϵ��� �ʾҽ��ϴ�.");
			Print.printMessage("-----------------------------------------------------");
			return;
		}
		Print.printMessage("��ȭ�ڵ�\t��ȭ��\t\t������\t\t��ȭ�ֿ�����\t\t�󿵽ð�\t������\t\t�󿵵��\t�⿬�ڸ�\t�帣��");
		int i = 0;
		while(i < arrayList.size()) {
			MovieInfoSetting temp = arrayList.get(i);
			Print.printMessage(temp.getMovieNO() + "\t" + temp.getMovieName() + "\t" + temp.getDirectorName()
			+ "\t" + temp.getSummary() + "\t" + temp.getShowTime() + "\t" + temp.getReleaseDate()
			+ "\t" + temp.getRating() + "\t" + temp.getPerforMername() + "\t" + temp.getGenre());
			
			i++;
		}
		Print.printMessage("�� " + (i) + "���� ��ȭ ������ ����Ǿ��ֽ��ϴ�.");
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
