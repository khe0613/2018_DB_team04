package theater;

import java.util.ArrayList;
import java.util.Scanner;

import dao.ScreeningMovieInfoSettingDAO;

// �󿵿�ȭ��������
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
		Screen screen = new Screen();
		Print.printMessage("-> ��ȭ��&�󿵰� ����");
		screen.getScreenInfoList(); // �󿵰� ���� ���
		MovieInfoSetting movie = new MovieInfoSetting();
		Print.printMessage("-> ��ȭ ����");
		movie.getMovieInfoList(); // ��ȭ ���� ���
		Schedule schedule = new Schedule();
		Print.printMessage("-> ���� ����");
		schedule.getScheduleInfoList(); //���� ���� ���
		Print.printMessage("-> ��ϵ� �� ��ȭ ����");
		getScreeningMovieInfoList();
		Print.printMessage("-> �� ��ȭ ������ �����Ϸ��� ��ȭ�� ������ �ڵ带  �Է��ϼ���.");
		setMovieBranchNo(sc.nextInt());
		Print.printMessage("-> �� ��ȭ ������ �����Ϸ��� �󿵰��� ��ȣ�� �Է��ϼ���.");
		setScreenNo(sc.nextInt());
		Print.printMessage("-> �� ��ȭ ������ ������ ��ȭ�ڵ带 �Է��ϼ���.");
		setMovieNo(sc.nextInt());
		Print.printMessage("-> �� ��ȭ ������ ������ ���� �ڵ带 �Է��ϼ���.");
		setSchNo(sc.nextInt());
	}

	private void addScreeningMovieInfo() {
		Screen screen = new Screen();
		Print.printMessage("-> ��ȭ��&�󿵰� ����");
		screen.getScreenInfoList(); // �󿵰� ���� ���
		MovieInfoSetting movie = new MovieInfoSetting();
		Print.printMessage("-> ��ȭ ����");
		movie.getMovieInfoList(); // ��ȭ ���� ���
		Schedule schedule = new Schedule();
		Print.printMessage("-> ���� ����");
		schedule.getScheduleInfoList(); //���� ���� ���
		Print.printMessage("-> �� ��ȭ ������ �Է��Ϸ��� ��ȭ�� ������ �ڵ带  �Է��ϼ���.");
		setMovieBranchNo(sc.nextInt());
		Print.printMessage("-> �� ��ȭ ������ �Է��Ϸ��� �󿵰��� ��ȣ�� �Է��ϼ���.");
		setScreenNo(sc.nextInt());
		Print.printMessage("-> �� ��ȭ ������ ��� ��ȭ�ڵ带 �Է��ϼ���.");
		setMovieNo(sc.nextInt());
		Print.printMessage("-> �� ��ȭ ������ �Է��� ���� �ڵ带 �Է��ϼ���.");
		setSchNo(sc.nextInt());
	}
	
	/* ����� �� ��ȭ ���� ��� ��� */
	public void getScreeningMovieInfoList() {
		ScreeningMovieInfoSettingDAO screeningmovieinfosettingdao = new ScreeningMovieInfoSettingDAO();
		ArrayList<ScreeningMovieInfoSetting> arrayList = new ArrayList<ScreeningMovieInfoSetting>();
		arrayList = screeningmovieinfosettingdao.getScreenInfo();
		if(arrayList == null) {
			Print.printMessage("�ƹ� ������ ��ϵ��� �ʾҽ��ϴ�.");
			Print.printMessage("-----------------------------------------------------");
			return;
		}
		Print.printMessage("�������ڵ� �����ڵ�\t�󿵰���ȣ\t��ȭ�ڵ�\t�����ڵ�");
		int i = 0;
		while(i < arrayList.size()) {
			ScreeningMovieInfoSetting temp = arrayList.get(i);
			Print.printMessage(temp.getScreeningTableNo() + "\t " + temp.getMovieBranchNo() +"\t" + temp.getScreenNo()+"\t" +temp.getMovieNo()+"\t" +temp.getSchNo());
			i++;
		}
		Print.printMessage("�� " + (i) + "���� �� ��ȭ ������ ����Ǿ��ֽ��ϴ�.");
		Print.printMessage("-----------------------------------------------------");
	}

	public int getScreeningTableNo() {
		return screeningTableNo;
	}

	public void setScreeningTableNo(int screeningTableNo) {
		this.screeningTableNo = screeningTableNo;
	}
}
