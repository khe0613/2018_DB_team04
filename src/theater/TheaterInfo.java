package theater;

import java.util.Scanner;

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

		
		if((this.branchNo = new TheaterInfoDAO().registerTheater(this)) != -1) {			// ��ȭ�� ��� ����(�����ڵ尡 ���ϵ�)
			return true;
		}else {
			return false;
		}
		

	}
	
	public boolean modifyTheaterInfo(int branchNo, int screenNum, String address, String tel, String branchName) {
		
		return true;
	}
	
	public boolean removeTheater(int branchNo) {
		
		return true;
	}
	
	
}
