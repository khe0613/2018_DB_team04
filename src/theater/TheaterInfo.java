package theater;

import java.util.Scanner;

import dao.TheaterInfoDAO;

// ��ȭ����������
public class TheaterInfo {
	private int branchNo;
	private int screenNum;
	private String address;
	private String tel;
	private String branchName;
	
	
	public TheaterInfo() {
		
	}
	
	public TheaterInfo(int branchNo, int screenNum, String address, String tel, String branchName) {
		this.branchNo = branchNo;
		this.screenNum = screenNum;
		this.address = address;
		this.tel = tel;
		this.branchName = branchName;
	}
	
	public int getBranchNo() {
		return branchNo;
	}
	
	public void setBranchNo(int branchNo) {
		this.branchNo = branchNo;
	}
	
	public int getScreenNum() {
		return screenNum;
	}
	
	public void setScreenNum(int screenNum) {
		this.screenNum = screenNum;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getBranchName() {
		return branchName;
	}
	
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	
	// ��ȭ�� ���
	public boolean addTheater() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("---------------- ��ȭ�� ��� ----------------");
		System.out.print("�󿵰� �� : ");	this.screenNum = sc.nextInt();
		System.out.print("�ּ� : ");		this.address = sc.nextLine();
		System.out.print("��ȭ��ȣ : ");	this.tel = sc.nextLine();
		System.out.print("������ : ");		this.branchName = sc.nextLine();

		sc.close();
		
		if((this.branchNo = new TheaterInfoDAO().registerTheater(this)) != -1) {			// ��ȭ�� ��� ����(�����ڵ尡 ���ϵ�)
			return true;
		}else {
			return false;
		}
		

	}
	
	
	// ��ȭ�� ���� ����
	public boolean modifyTheaterInfo() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("---------------- ��ȭ�� ���� ���� ----------------");
		System.out.print("�󿵰� �� : ");
		int new_screenNum = sc.nextInt();
		
		System.out.print("�ּ� : ");
		String new_address = sc.nextLine();
		
		System.out.print("��ȭ��ȣ : ");
		String new_tel = sc.nextLine();
		
		System.out.print("������ : ");	
		String new_branchName = sc.nextLine();
		
		sc.close();
		
		if(new TheaterInfoDAO().modifyTheaterInfo(this.branchNo, new_screenNum, new_address, new_tel, new_branchName)) {
			this.screenNum = new_screenNum;
			this.address = new_address;
			this.tel = new_tel;
			this.branchName = new_branchName;
			
			return true;
		}else {
			return false;
		}
	

	}
	
	// ��ȭ�� ����
	public boolean removeTheater() {
		return new TheaterInfoDAO().removeTheater(this.branchNo);		// ������ ��� true, ������ ��� false�� ���ϵ�
	}
	
	
}
