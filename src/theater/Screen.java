package theater;

import java.util.Scanner;

import dao.ScreenDAO;

// �󿵰� ����
public class Screen {
	private int screenNo;		// �󿵰� �ڵ�
	private int branchNo;		// ���� �ڵ�
	private int totalSeatNum; 	// ��ü�¼���
	private int leftSeatNum;	// �ܿ���
	
	public Screen() {
		
	}
	
	public int getScreenNo() {
		return screenNo;
	}
	
	public int getBranchNo() {
		return branchNo;
	}
	

	
	public int getTotalSeatNum() {
		return totalSeatNum;
	}
	
	public int getLeftSeatNum() {
		return leftSeatNum;
	}
	
	public boolean registerScreen(int screenNo, int branchNo) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("---------------- �󿵰� ��� ----------------");
		this.screenNo = screenNo;
		this.branchNo = branchNo;
		System.out.print("��ü�¼��� : ");	this.totalSeatNum = sc.nextInt();
		this.leftSeatNum = this.totalSeatNum;
		
		if(new ScreenDAO().registerScreen(this))
		
		return true;
	}
	
	public boolean removeScreen() {
		return true;
	}
	
	public boolean modifyScreen() {
		return true;
	}
	

	
	
	
	
	
	
}
