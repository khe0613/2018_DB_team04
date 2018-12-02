package theater;

import java.util.Scanner;
import dao.ScreeningMovieInfoSettingDAO;

// �󿵿�ȭ��������
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
		Print.printMessage("---------- �� �� �� ȭ �� �� �� �� ----------");
		Print.printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
		Print.printMessage("1: �󿵿�ȭ ���   2: �󿵿�ȭ ����");
		inputMenu = sc.next();
		menu(inputMenu);
	}
	
	private void menu(String menu) {
		switch(menu) {
		case "1":
			Print.printMessage("-> �󿵿�ȭ���� ���");
			addScreeningMovieInfo();
			
			// ����
			if(new ScreeningMovieInfoSettingDAO().addScreeningMovieInfoSQL(getMovieNo(), getMovieBranchNo())) {
				Print.printMessage("!! ��ȭ���� ��� ����");
			}
			else {
				Print.printMessage("!! ��ȭ���� ��� ����");
			}
			break;
		case "2":
			Print.printMessage("-> �󿵿�ȭ���� ����");
			deleteScreeningMovieInfo();
			if(new ScreeningMovieInfoSettingDAO().deleteScreeningMovieInfoSQL(getMovieNo(), getMovieBranchNo())) {
				Print.printMessage("!! ��ȭ���� ���� ����");
			}
			else {
				Print.printMessage("!! ��ȭ���� ���� ����");
			}
			break;
			
		default:
			break;
		}
	}
	
	private void deleteScreeningMovieInfo() {
		Print.printMessage("-> ��ȭ ������ �����Ϸ��� ��ȭ�� ������ �ڵ带  �Է��ϼ���.");
		setMovieNo(sc.nextInt());
		Print.printMessage("-> ���� �����Ϸ��� ��ȭ�ڵ带 �Է��ϼ���.");
		setMovieBranchNo(sc.nextInt());
	}
	
	private void addScreeningMovieInfo() {
		Print.printMessage("-> ��ȭ ������ �Է��Ϸ��� ��ȭ�� ������ �ڵ带  �Է��ϼ���.");
		setMovieNo(sc.nextInt());
		Print.printMessage("-> ��ȭ ������ ��� ��ȭ�ڵ带 �Է��ϼ���.");
		setMovieBranchNo(sc.nextInt());
	}
}
