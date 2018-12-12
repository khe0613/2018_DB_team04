package theater;

import java.util.ArrayList;
import java.util.Scanner;

import dao.ScreenDAO;
import dao.SeatDAO;

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
		int branchNo;
		int screenNo;
		int totalSeatNum;
		
		Theater theater = new Theater();
		Print.printMessage("---------------- �� �� �� �� �� �� �� ----------------");
		Print.printMessage("-> ���Ͻô� �޴��� �����ϼ���.");
		Print.printMessage("1: �󿵰� ���    2: �󿵰� ����   3: �󿵰� ����  4: �󿵰� ��ȸ");
		Scanner sc = new Scanner(System.in);
		String menu = sc.next();
		
		switch(menu) {
		case "1":
			theater.theaterList(); // ��ȭ�� ���� ���
			Print.printMessage("-> ����Ϸ��� �����ڵ带 �Է��ϼ���.");
			branchNo = sc.nextInt();
			Print.printMessage("-> ����Ϸ��� �󿵰� ��ȣ�� �Է��ϼ���.");
			screenNo = sc.nextInt();
			Print.printMessage("-> ���Ͻô� �� �󿵰��� ��ü �¼� ���� �Է��ϼ���.");
			totalSeatNum = sc.nextInt();
			if(registerScreen(branchNo, screenNo, totalSeatNum)) 
				Print.printMessage("!! �󿵰� ����� �����Ͽ����ϴ�.");
			else
				Print.printMessage("!! �󿵰� ����� �����Ͽ����ϴ�.");
			break;
		case "2":
			Print.printMessage("-> �����Ϸ��� �����ڵ带 �Է��ϼ���.");
			branchNo = sc.nextInt();
			Print.printMessage("-> �����Ϸ��� �󿵰� ��ȣ�� �Է��ϼ���.");
			screenNo = sc.nextInt();
			Print.printMessage("-> �����Ϸ��� ��ü �¼� ���� �Է��ϼ���.");
			totalSeatNum = sc.nextInt();
			if(modifyScreen(screenNo,branchNo,totalSeatNum)) 
				Print.printMessage("!! �󿵰� ������ �����Ͽ����ϴ�.");
			else
 				Print.printMessage("!! �󿵰� ������ �����Ͽ����ϴ�.");
			break;
		case "3":
			Print.printMessage("-> �����Ϸ��� �����ڵ带 �Է��ϼ���.");
			branchNo = sc.nextInt();
			Print.printMessage("-> �����Ϸ��� �󿵰� ��ȣ�� �Է��ϼ���.");
			screenNo = sc.nextInt();
			if(removeScreen(branchNo,screenNo)) 
				Print.printMessage("!! �󿵰� ������ �����Ͽ����ϴ�.");
			else
				Print.printMessage("!! �󿵰� ������ �����Ͽ����ϴ�.");
			break;
		case "4":
			Print.printMessage("-> ����� �󿵰� ���");
			getScreenInfoList();
			break;
		default:
			break;
		}
	}
	
	// �󿵰� ���
	public boolean registerScreen(int branchNo, int screenNo, int totalSeatNum) {
		ScreenDAO screenDAO = new ScreenDAO();
		SeatDAO seatDAO = new SeatDAO();
		boolean success = true;
		
		System.out.println("---------------- �󿵰� ��� ----------------");
		this.setScreenNo(screenNo); 
		this.setBranchNo(branchNo);
		this.setTotalseatNum(totalSeatNum);
		
		if(screenDAO.registerScreen(this)) {
			for(int seat = 1; seat<= totalSeatNum; seat++) {
				if(!seatDAO.addSeat(seat, screenNo, branchNo)) {
					success = false;
				}
			}
		}else {
			success = false;
		}
		
		if(success) {
			System.out.println("�󿵰� ��Ͽ� �����ϼ̽��ϴ�.");
		}else {
			System.out.println("�󿵰� ��Ͽ� �����ϼ̽��ϴ�.");
		}
		
		return success;
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

	/* ����� �󿵰� ���� ��� ��� */
	public void getScreenInfoList() {
		ScreenDAO screenDAO = new ScreenDAO();
		ArrayList<Screen> arrayList = new ArrayList<Screen>();
		arrayList = screenDAO.getScreenInfo();
		if(arrayList == null) {
			Print.printMessage("�ƹ� ������ ��ϵ��� �ʾҽ��ϴ�.");
			Print.printMessage("-----------------------------------------------------");
			return;
		}
		Print.printMessage("�󿵰���ȣ\t�����ڵ�\t��ü�¼���");
		int i = 0;
		while(i < arrayList.size()) {
			Screen temp = arrayList.get(i);
			Print.printMessage(temp.getScreenNo() + "\t" + temp.getBranchNo() + "\t" + temp.getTotalseatNum());
			i++;
		}
		Print.printMessage("�� " + (i) + "���� �󿵰� ������ ����Ǿ��ֽ��ϴ�.");
		Print.printMessage("-----------------------------------------------------");
	}
}
