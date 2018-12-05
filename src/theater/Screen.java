package theater;

import java.util.Scanner;

import dao.ScreenDAO;

// �󿵰� ����
public class Screen {
	private int screenNo;		// �󿵰� �ڵ�
	private int branchNo;		// ���� �ڵ�
	private int scheduleCode;	// �����ڵ�
	private int totalSeatNum; 	// ��ü�¼���
	private int leftSeatNum;	// �ܿ���
	
	public Screen() {
		
	}
	
	public int getScreenNo() {
		return screenNo;
	}
	
	public int getBranchNo() {
		return this.branchNo;
	}
	
	public int getScheduleCode() {
		return this.scheduleCode;
	}
	

	public int getTotalSeatNum() {
		return totalSeatNum;
	}
	
	public int getLeftSeatNum() {
		return leftSeatNum;
	}
	
	public void start() {
		Print.printMessage("---------------- �� �� �� �� �� �� �� ----------------");
		Print.printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
		Print.printMessage("1: �󿵰� ���    2: �󿵰� ����   3: �󿵰� ����");
		Scanner sc = new Scanner(System.in);
		String menu = sc.next();
		
		int branchno, screenno;
		switch(menu) {
		case "1":
			Print.printMessage("-> ���Ͻô� screenNo�� �Է��ϼ���.");
			screenno = sc.nextInt();
			Print.printMessage("-> ���Ͻô� branchNo�� �Է��ϼ���.");
			branchno = sc.nextInt();
			if(registerScreen(screenno, branchno)) 
				Print.printMessage("!! �󿵰� ����� �����Ͽ����ϴ�.");
			else
				Print.printMessage("!! �󿵰� ����� �����Ͽ����ϴ�.");
			break;
		case "2":
			Print.printMessage("-> ���Ͻô� screenNo�� �Է��ϼ���.");
			screenno = sc.nextInt();
			Print.printMessage("-> ���Ͻô� branchNo�� �Է��ϼ���.");
			branchno = sc.nextInt();
			if(removeScreen(screenno, branchno)) 
				Print.printMessage("!! �󿵰� ������ �����Ͽ����ϴ�.");
			else
				Print.printMessage("!! �󿵰� ������ �����Ͽ����ϴ�.");
			break;
		case "3":
			
			break;
			default:
				break;
		}
	}
	
	// �󿵰� ���
	public boolean registerScreen(int screenNo, int branchNo) {
		ScreenDAO screenDAO = new ScreenDAO();
		Scanner sc = new Scanner(System.in);
		boolean success;
		
		System.out.println("---------------- �󿵰� ��� ----------------");
		this.screenNo = screenNo;
		this.branchNo = branchNo;
		System.out.print("��ü�¼��� : ");	this.totalSeatNum = sc.nextInt();
		this.leftSeatNum = this.totalSeatNum;
		
		
		if(screenDAO.registerScreen(this)) {
			System.out.println("�󿵰� ��Ͽ� �����ϼ̽��ϴ�.");
			success = true;
		}else {
			System.out.println("�󿵰� ��Ͽ� �����ϼ̽��ϴ�.");
			success = false;
		}
		
		screenDAO.disconnectDB();
		return success;
	}
	
	// �󿵰� ����
	public boolean removeScreen(int screenNo, int branchNo) {
		ScreenDAO screenDAO = new ScreenDAO();
		boolean success;
		
		System.out.println("---------------- �󿵰� ���� ----------------");
		if(screenDAO.removeScreen(screenNo, branchNo)) {
			System.out.println("�󿵰� ������ �����ϼ̽��ϴ�.");
			success = true;
		}else {
			System.out.println("�󿵰� ������ �����ϼ̽��ϴ�..");
			success = false;
		}
		
		screenDAO.disconnectDB();
		
		return success;
	}
	
	// �󿵰� ����
	public boolean modifyScreen(Screen screen) {
		ScreenDAO screenDAO = new ScreenDAO();
		boolean success;
		
		System.out.println("---------------- �󿵰� ���� ----------------");
		if(screenDAO.modifyScreen(screen)) {
			this.screenNo = screen.getScreenNo();
			this.branchNo = screen.getBranchNo();
			this.scheduleCode = screen.getScheduleCode();
			this.totalSeatNum = screen.getTotalSeatNum();
			this.leftSeatNum =  screen.getLeftSeatNum();
			
			System.out.println("�󿵰� ������ �����ϼ̽��ϴ�.");
			success = true;
		}else {
			System.out.println("�󿵰� ������ �����ϼ̽��ϴ�.");
			success = false;
		}
		
		return success;
	}
	

	
	
	
	
	
	
}
