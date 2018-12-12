package theater;
import java.sql.*;
import java.util.Scanner;

import dao.MemberDAO;
import dao.ReservationDAO;

public class payment {
	String jdbcUrl = "jdbc:mysql://localhost:3306/theater?useSSL=false"; 
	String dbId = "parkyoonjung";;
	String dbPass = "qkrdbswjd";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;
	
	Scanner sc = new Scanner(System.in);
	
	public void Start(String id){ // id�� ���ڷ� �޾ƿ�
		ReservationDAO reservationdao = new ReservationDAO();
		Print.printMessage("------------------- �� ȭ �� �� -------------------");
		Print.printMessage("-> �����Ϸ��� Ƽ���� ���Ź�ȣ�� �Է����ּ���.");
		String resNo = sc.next(); 
		String isPayment = reservationdao.isPayment(resNo);
		payment payment = new payment();
		if(isPayment.equals("false")) { // ��������Ȯ��
			Print.printMessage("-> ���� ������ �������ּ���.");
			Print.printMessage("1. ���ͳ� ���� 2. ���� ����");
			int paymentType = sc.nextInt();
			if(paymentType == 1) { // ���ͳ� ����
				Print.printMessage("-------------- �� �� �� �� �� --------------");
				if(payment.InternetPayment(id,resNo)) {
					reservationdao.EndPayment(resNo); // true�� ����
					System.out.println("!! ���ͳ� ���� �Ϸ�");
				}else {
					System.out.println("!! ���ͳ� ���� ���� �ٽ� �õ����ּ���.");
				}
				
			}else { // ���� ����
				System.out.println("!! ���忡�� �����ڿ��� �������ּ���.");
			}			
		}else if(isPayment.equals("true")) {
			System.out.println("!! �̹� ������ �Ϸ�� Ƽ���Դϴ�.");
		}else {
			System.out.println("!! �������� �ʴ� ���Ź�ȣ �Դϴ�.");
		}
	}
	
	public boolean InternetPayment(String id,String resNo) { // ���ͳ� ����
		MemberDAO memberdao = new MemberDAO();
		int usePoint;
		int memberPoint = memberdao.getPoint(id);
		int ticketPrice = 10000;
		System.out.println("!! ����Ʈ�� ����Ͻðڽ��ϱ�?");
		Print.printMessage("1: ��  2: �ƴϿ�");
		int pointType = sc.nextInt(); 
		
		if(pointType == 1) { // ����Ʈ ���
			System.out.println("����Ʈ�� �󸶳� ����Ͻðڽ��ϱ�?");
			usePoint = sc.nextInt();
			if(usePoint > memberPoint) { // ����� ����Ʈ�� ������Ʈ���� ũ�ٸ�
				System.out.println("����� ����Ʈ�� ���� ���� ����Ʈ���� ũ�Ƿ� ����Ͻ� �� �����ϴ�.");
				return false;
			}else if(usePoint < 1000) { // ����� ����Ʈ�� 1000������ ������
				System.out.println("����Ʈ�� 1000������ �����Ƿ� ����Ͻ� �� �����ϴ�.");
				return false;
			}else {
				ticketPrice -= usePoint; // ����Ʈ ����� Ƽ�� ����
				System.out.println("ī�� ��ȣ�� �Է��� �ּ���.");
				String cardNum  = sc.next();
				System.out.println("ī�� ��� ��ȣ�� �Է��� �ּ���.");
				int cardPassword = sc.nextInt();
				System.out.println("ī��� ����Ʈ ���� �� ���� ������ "+ticketPrice+"���� ���� �Ͽ����ϴ�.");
				memberPoint -= usePoint; // ����Ʈ ����� �� ����Ʈ
				memberPoint += 100; // ���Ž� ����� 100����Ʈ����
				
				if(memberdao.usePoint(memberPoint, id)) {
					System.out.println("���� �ܿ� ����Ʈ : " + memberPoint);
					System.out.println("����Ʈ�� ����Ͽ� ������ �Ϸ�Ǿ����ϴ�.");	
					return true;
				}else {
					System.out.println("����Ʈ�� ����� ������ �����Ͽ����ϴ�.");
					return false;
				}
			}
		}else { // ����Ʈ �̻��
			System.out.println("ī�� ��ȣ�� �Է��� �ּ���.");
			String cardNum  = sc.next();
			System.out.println("ī�� ��� ��ȣ�� �Է��� �ּ���.");
			int cardPassword = sc.nextInt();
			System.out.println("--------�������Դϴ�--------");
			System.out.println("ī��� " +ticketPrice+ "���� ���� �Ͽ����ϴ�.");
			memberPoint += 100; // ���Ž� 100����Ʈ����
			if(memberdao.usePoint(memberPoint, id)) {
				System.out.println("���� �ܿ� ����Ʈ : " + memberPoint);
				System.out.println("������ �Ϸ�Ǿ����ϴ�.");	
				return true;
			}else {
				System.out.println("������ �����Ͽ����ϴ�.");
				return false;
			}
		}
	}
	
	public boolean onSitePayment(String id,String resNo) { // ���� ����
		MemberDAO memberdao = new MemberDAO();
		int usePoint;
		int memberPoint = memberdao.getPoint(id);
		int ticketPrice = 10000;
		System.out.println("!! ����Ʈ�� ����Ͻðڽ��ϱ�?");
		Print.printMessage("1: ��  2: �ƴϿ�");
		int pointType = sc.nextInt(); 
		
		if(pointType == 1) { // ����Ʈ ���
			System.out.println("����Ʈ�� �󸶳� ����Ͻðڽ��ϱ�?");
			usePoint = sc.nextInt();
			if(usePoint > memberPoint) { // ����� ����Ʈ�� ������Ʈ���� ũ�ٸ�
				System.out.println("����� ����Ʈ�� ���� ���� ����Ʈ���� ũ�Ƿ� ����Ͻ� �� �����ϴ�.");
				return false;
			}else if(usePoint < 1000) { // ����� ����Ʈ�� 1000������ ������
				System.out.println("����Ʈ�� 1000������ �����Ƿ� ����Ͻ� �� �����ϴ�.");
				return false;
			}else {
				ticketPrice -= usePoint; // ����Ʈ ����� Ƽ�� ����
				System.out.println("Ƽ�ϰ����� "+ticketPrice+"�Դϴ�.");
				System.out.println("--------�������Դϴ�--------");
				System.out.println("�������� " +ticketPrice+ "���� ���� �Ͽ����ϴ�.");
				memberPoint -= usePoint; // ����Ʈ ����� �� ����Ʈ
				memberPoint += 100; // ���Ž� ����� 100����Ʈ����
				
				if(memberdao.usePoint(memberPoint, id)) {
					System.out.println("���� �ܿ� ����Ʈ : " + memberPoint);
					System.out.println("����Ʈ�� ����Ͽ� ������ �Ϸ�Ǿ����ϴ�.");	
					return true;
				}else {
					System.out.println("����Ʈ�� ����� ������ �����Ͽ����ϴ�.");
					return false;
				}
			}
		}else { // ����Ʈ �̻��
			System.out.println("Ƽ�ϰ����� "+ticketPrice+"�Դϴ�.");
			System.out.println("--------�������Դϴ�--------");
			System.out.println("�������� " +ticketPrice+ "���� ���� �Ͽ����ϴ�.");
			memberPoint += 100; // ���Ž� 100����Ʈ����
			if(memberdao.usePoint(memberPoint, id)) {
				System.out.println("���� �ܿ� ����Ʈ : " + memberPoint);
				System.out.println("������ �Ϸ�Ǿ����ϴ�.");	
				return true;
			}else {
				System.out.println("������ �����Ͽ����ϴ�.");
				return false;
			}
		}
	}
}
