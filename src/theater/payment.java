package theater;
import java.sql.*;
import java.util.Scanner;

public class payment {
	String jdbcUrl = "jdbc:mysql://localhost:3306/theater"; 
	String dbId = "parkyoonjung";;
	String dbPass = "qkrdbswjd";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;
	
	Scanner sc = new Scanner(System.in);
	
	public payment(String id) throws SQLException, ClassNotFoundException { // id�� ���ڷ� �޾ƿ�
		System.out.println("�����Ϸ��� Ƽ���� ���Ź�ȣ�� �Է����ּ���.");
		String resNo = sc.next(); 
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		sql = "select ispayment from Member where id =?"; // ���Ź�ȣ�� �������� ��������
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		Boolean isPayment = rs.getBoolean("isPayment");
		if(isPayment == false) { // ��������Ȯ��
			System.out.println("���� ������ �������ּ���. 1. ���ͳ� ����	2. ���� ����");
			int paymentType = sc.nextInt();
			if(paymentType == 1) { // ���ͳ� ����
				InternetPayment(id,resNo);
			}else { // ���� ����
				System.out.println("���忡�� �����ڿ��� �������ּ���.");
			}			
		}else if(isPayment == null){
			System.out.println("�������� �ʴ� ���Ź�ȣ�Դϴ�.");
		}else {
			System.out.println("�̹� ������ �Ϸ�� Ƽ���Դϴ�.");
		}
	}
	
	public void InternetPayment(String id,String resNo) throws SQLException, ClassNotFoundException { // ���ͳ� ����
		int usePoint;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		sql = "select point from Member where id =?"; // point���� ��������
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		int memberPoint = rs.getInt("point");
		
		sql = "select price from Reservation where resNo =?"; // ���������� �̿��ؼ� ���� ��������
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, resNo);
		rs = pstmt.executeQuery();
		int ticketPrice = rs.getInt("price");
		int ticketCount = ticketPrice/10000;
		
		System.out.println("����Ʈ�� ����Ͻðڽ��ϱ�? 1. ��	2. �ƴϿ�");
		int pointType = sc.nextInt(); 
		if(pointType == 1) { // ����Ʈ ���
			System.out.println("����Ʈ�� �󸶳� ����Ͻðڽ��ϱ�?");
			usePoint = sc.nextInt();
			if(usePoint > memberPoint) { // ����� ����Ʈ�� ������Ʈ���� ũ�ٸ�
				System.out.println("����� ����Ʈ�� ���� ���� ����Ʈ���� ũ�Ƿ� ����Ͻ� �� �����ϴ�.");
			}else if(usePoint < 1000) { // ����� ����Ʈ�� 1000������ ������
				System.out.println("����Ʈ�� 1000������ �����Ƿ� ����Ͻ� �� �����ϴ�.");
			}else {
				ticketPrice -= usePoint; // ����Ʈ ����� Ƽ�� ����
				memberPoint -= usePoint; // ����Ʈ ����� �� ����Ʈ
				memberPoint += (ticketCount*100); // ���Ž� ����� 100����Ʈ����
				sql = "update member set point = ? where id = ?"; // ���� ����Ʈ�� usePoint ��ŭ �����ϰ� ���̺� ����
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, memberPoint);
				pstmt.setString(2,id);
				pstmt.executeUpdate(); 
				System.out.println("���� �ܿ� ����Ʈ : " + memberPoint);
				System.out.println("����Ʈ�� ����Ͽ� ������ �Ϸ�Ǿ����ϴ�.");
			}
		}else { // ����Ʈ �̻��
			System.out.println("ī�� ��ȣ�� �Է��� �ּ���.");
			String cardNum  = sc.next();
			System.out.println("ī�� ��� ��ȣ�� �Է��� �ּ���.");
			int cardPassword = sc.nextInt();
			System.out.println("--------�������Դϴ�--------");
			memberPoint += (ticketCount*100); // ���Ž� 100����Ʈ����
			sql = "update member set point = ? where id = ?"; // ���� ����Ʈ�� usePoint ��ŭ �����ϰ� ���̺� ����
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberPoint);
			pstmt.setString(2,id);
			pstmt.executeUpdate(); 
			System.out.println("���� �ܿ� ����Ʈ : " + memberPoint);
			System.out.println("������ �Ϸ�Ǿ����ϴ�.");
		}
	}
	

}
