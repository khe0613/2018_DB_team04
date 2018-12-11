package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import theater.Print;

// ��ȭ DAO
public class PaymentDAO {
	String jdbcUrl = "jdbc:mysql://localhost:3306/theater?useSSL=false";
	String dbId = "parkyoonjung";;
	String dbPass = "qkrdbswjd";

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	Scanner sc = new Scanner(System.in);
	
	private void connectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}
	
	private void disConnectDB() {
		if(rs!= null) try {rs.close();}catch (Exception e) {}
		if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
		if(conn != null) try{conn.close();}catch(SQLException sqle){}
	}
	
	public boolean InternetPayment(String id,String resNo) { // ���ͳ� ����
		connectDB();
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
		connectDB();
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

