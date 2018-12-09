package theater;

import java.util.Scanner;

import dao.ScreenDAO;

// �󿵰� ����
public class Screen {
	private int screenNo;		// �󿵰� �ڵ�
	private int branchNo;		// ���� �ڵ�
	private int totalSeatNum; 	// ��ü�¼���
	
	public Screen() {
		
	}
	
	public int getScreenNo() {
		return screenNo;
	}


	public void setScreenNo(int screenNo) {
		this.screenNo = screenNo;
	}


	public int getBranchNo() {
		return branchNo;
	}


	public void setBranchNo(int branchNo) {
		this.branchNo = branchNo;
	}
	
	public int getTotalseatNum() {
		return totalSeatNum;
	}


	public void setTotalseatNum(int totalSeatNum) {
		this.totalSeatNum = totalSeatNum;
	}
	
	public void start() {
		Print.printMessage("---------------- �� �� �� �� �� �� �� ----------------");
		Print.printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
		Print.printMessage("1: �󿵰� ���    2: �󿵰� ����   3: �󿵰� ����");
		Scanner sc = new Scanner(System.in);
		String menu = sc.next();
		
		switch(menu) {
		case "1":
			Print.printMessage("-> ����Ϸ��� �����ڵ带 �Է��ϼ���.");
			setBranchNo(sc.nextInt());
			Print.printMessage("-> ���Ͻô� �� �󿵰��� ��ü �¼� ���� �Է��ϼ���.");
			setTotalseatNum(sc.nextInt());
			if(registerScreen(branchNo,totalSeatNum)) 
				Print.printMessage("!! �󿵰� ����� �����Ͽ����ϴ�.");
			else
				Print.printMessage("!! �󿵰� ����� �����Ͽ����ϴ�.");
			break;
		case "2":
			Print.printMessage("-> �����Ϸ��� �����ڵ带 �Է��ϼ���.");
			setBranchNo(sc.nextInt()); 
			Print.printMessage("-> �����Ϸ��� �󿵰��ڵ带 �Է��ϼ���.");
			setScreenNo(sc.nextInt());
			if(removeScreen(branchNo,screenNo)) 
				Print.printMessage("!! �󿵰� ������ �����Ͽ����ϴ�.");
			else
				Print.printMessage("!! �󿵰� ������ �����Ͽ����ϴ�.");
			break;
		case "3":
			Print.printMessage("-> �����Ϸ��� �����ڵ带 �Է��ϼ���.");
			setBranchNo(sc.nextInt()); 
			Print.printMessage("-> �����Ϸ��� �󿵰��ڵ带 �Է��ϼ���.");
			setScreenNo(sc.nextInt());
			Print.printMessage("-> �����Ϸ��� ��ü �¼� ���� �Է��ϼ���.");
			setTotalseatNum(sc.nextInt());
			if(modifyScreen(screenNo,branchNo,totalSeatNum)) 
				Print.printMessage("!! �󿵰� ������ �����Ͽ����ϴ�.");
			else
 				Print.printMessage("!! �󿵰� ������ �����Ͽ����ϴ�.");
			break;
		default:
			break;
		}
	}
	
	// �󿵰� ���
	public boolean registerScreen(int branchNo, int totalSeatNum) {
		ScreenDAO screenDAO = new ScreenDAO();
		
		System.out.println("---------------- �󿵰� ��� ----------------");
		this.setScreenNo(screenDAO.getScreenNum(branchNo)+1); // dao����  �Լ� ȣ���Ͽ� �ҷ��°ſ� +1���� ��
		this.setBranchNo(branchNo);
		this.setTotalseatNum(totalSeatNum);
		
		if(screenDAO.registerScreen(this)) {
			System.out.println("�󿵰� ��Ͽ� �����ϼ̽��ϴ�.");
			return true;
		}else {
			System.out.println("�󿵰� ��Ͽ� �����ϼ̽��ϴ�.");
			return false;
		}
	}
	
	// �󿵰� ����
	public boolean removeScreen(int branchNo, int screenNo) {
		ScreenDAO screenDAO = new ScreenDAO();
		
		System.out.println("---------------- �󿵰� ���� ----------------");
		if(screenDAO.removeScreen(branchNo,screenNo)) {
			System.out.println("�󿵰� ������ �����ϼ̽��ϴ�.");
			return true;
		}else {
			System.out.println("�󿵰� ������ �����ϼ̽��ϴ�..");
			return false;
		}
	}
	
	// �󿵰� ����
	public boolean modifyScreen(int screenNo, int branchNo, int totalSeatNum) {
		ScreenDAO screenDAO = new ScreenDAO();
		
		System.out.println("---------------- �󿵰� ���� ----------------");
		this.setScreenNo(screenNo);
		this.setBranchNo(branchNo);
		this.setTotalseatNum(totalSeatNum);
		
		if(screenDAO.modifyScreen(this)) {
			System.out.println("�󿵰� ������ �����ϼ̽��ϴ�.");
			return true;
		}else {
			System.out.println("�󿵰� ������ �����ϼ̽��ϴ�.");
			return false;
		}
	}

	
}
