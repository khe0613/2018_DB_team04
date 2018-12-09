package theater;

import java.util.Scanner;
import dao.ScreeningMovieInfoSettingDAO;

// �󿵿�ȭ��������
public class ScreeningMovieInfoSetting {
	private Scanner sc = new Scanner(System.in);
	String inputMenu = "";
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
		Print.printMessage("---------- �� �� �� ȭ �� �� �� �� ----------");
		Print.printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
		Print.printMessage("1: �󿵿�ȭ ���   2: �󿵿�ȭ ����");
		inputMenu = sc.next();
		menu(inputMenu);
	}

	private void menu(String menu) {
		switch (menu) {
		case "1":
			Print.printMessage("-> �󿵿�ȭ���� ���");
			addScreeningMovieInfo();

			// ����
			if (new ScreeningMovieInfoSettingDAO().addScreeningMovieInfoSQL(getMovieBranchNo(),getScreenNo(),getMovieNo(),getSchNo())) {
				Print.printMessage("!! ��ȭ���� ��� ����");
			} else {
				Print.printMessage("!! ��ȭ���� ��� ����");
			}
			break;
		case "2":
			Print.printMessage("-> �󿵿�ȭ���� ����");
			deleteScreeningMovieInfo();
			if (new ScreeningMovieInfoSettingDAO().deleteScreeningMovieInfoSQL(getMovieBranchNo(),getScreenNo(),getMovieNo(),getSchNo())) {
				Print.printMessage("!! ��ȭ���� ���� ����");
			} else {
				Print.printMessage("!! ��ȭ���� ���� ����");
			}
			break;

		default:
			break;
		}
	}

	private void deleteScreeningMovieInfo() {
		Print.printMessage("-> �� ��ȭ ������ �����Ϸ��� ��ȭ�� ������ �ڵ带  �Է��ϼ���.");
		setMovieBranchNo(sc.nextInt());
		Print.printMessage("-> �� ��ȭ ������ �����Ϸ��� �󿵰��� �ڵ带 �Է��ϼ���.");
		setScreenNo(sc.nextInt());
		Print.printMessage("-> �� ��ȭ ������ ������ ��ȭ�ڵ带 �Է��ϼ���.");
		setMovieNo(sc.nextInt());
		Print.printMessage("-> �� ��ȭ ������ ������ ���� �ڵ带 �Է��ϼ���.");
		setSchNo(sc.nextInt());
	}

	private void addScreeningMovieInfo() {
		Print.printMessage("-> �� ��ȭ ������ �Է��Ϸ��� ��ȭ�� ������ �ڵ带  �Է��ϼ���.");
		setMovieBranchNo(sc.nextInt());
		Print.printMessage("-> �� ��ȭ ������ �Է��Ϸ��� �󿵰��� �ڵ带 �Է��ϼ���.");
		setScreenNo(sc.nextInt());
		Print.printMessage("-> �� ��ȭ ������ ��� ��ȭ�ڵ带 �Է��ϼ���.");
		setMovieNo(sc.nextInt());
		Print.printMessage("-> �� ��ȭ ������ �Է��� ���� �ڵ带 �Է��ϼ���.");
		setSchNo(sc.nextInt());
	}
}
