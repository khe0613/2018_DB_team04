package theater;

import java.util.Scanner;

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
}
