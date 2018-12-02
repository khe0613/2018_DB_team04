package theater;



import java.util.Scanner;

import dao.MemberDAO;

public class Member {
	private String id;
	private String pw;
	private String name;
	private String birth;
	private String address;
	private String phoneNum;
	private int point;
	
	public Member() {
		
	}
	
	public Member(String id, String pw, String name, String birth, String address, String phoneNum, int point) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birth = birth;
		this.address = address;
		this.phoneNum = phoneNum;
		this.point = point;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBirth() {
		return birth;
	}
	
	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	public boolean AdminCheck(String id) {
		if(id.equals("root")) 
			return true;
		else
			return false;
	}
	
	// ȸ�� ����
	public boolean register() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("---------------- ȸ�� ���� ----------------");
		System.out.print("���̵� : ");		this.id = sc.nextLine();
		System.out.print("��й�ȣ : ");	this.pw = sc.nextLine();
		System.out.print("���� : ");		this.name = sc.nextLine();
		System.out.print("������� : ");	this.birth = sc.nextLine();
		System.out.print("�ּ� : ");		this.address = sc.nextLine();
		System.out.print("��ȭ��ȣ : ");	this.phoneNum = sc.nextLine();
		this.point = 0;
		
		sc.close();
		
		if(new MemberDAO().registerMember(this)) {	
			return true;									// ����
		}else {
			return false;									// ����
		}
		
	}
	
	// ȸ�� Ż��
	public boolean delete() {
		return new MemberDAO().deleteMember(this.id);	// ������ ��� true, ������ ��� false�� ���ϵ�
	}
	
	// ȸ�� ���� ����
	public boolean modify() {
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("---------------- ȸ�� ���� ���� ----------------");
		System.out.print("���̵� : ");
		String new_id = sc.nextLine();
		
		System.out.print("��й�ȣ : ");
		String new_pw = sc.nextLine();
		
		System.out.print("���� : ");
		String new_name = sc.nextLine();
		
		System.out.print("������� : ");
		String new_birth = sc.nextLine();
		
		System.out.print("�ּ� : ");
		String  new_address = sc.nextLine();
		
		System.out.print("��ȭ��ȣ : ");
		String new_phoneNum = sc.nextLine();
		
		sc.close();
		
		if(new MemberDAO().modifyMember(new_id, new_pw, new_name, new_birth, new_address, new_phoneNum)) {	// ����
			this.id = new_id;
			this.pw = new_pw;
			this.name = new_name;
			this.birth = new_birth;
			this.address = new_address;
			this.phoneNum = new_phoneNum;
	
			return true;
		}else {
			return false;
		}
	}

	
}
