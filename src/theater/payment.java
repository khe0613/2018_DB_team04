package theater;
import java.sql.*;
import java.util.Scanner;

import dao.PaymentDAO;
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
	
	public void start(String id) {
		try {
			paymentStart(id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void paymentStart(String id){ // id�� ���ڷ� �޾ƿ�
		ReservationDAO reservationdao = new ReservationDAO();
		Print.printMessage("------------------- �� ȭ �� �� -------------------");
		Print.printMessage("-> �����Ϸ��� Ƽ���� ���Ź�ȣ�� �Է����ּ���.");
		String resNo = sc.next(); 
		String isPayment = reservationdao.isPayment(resNo);
		PaymentDAO paymentdao = new PaymentDAO();
		if(isPayment.equals("false")) { // ��������Ȯ��
			Print.printMessage("-> ���� ������ �������ּ���.");
			Print.printMessage("1. ���ͳ� ���� 2. ���� ����");
			int paymentType = sc.nextInt();
			if(paymentType == 1) { // ���ͳ� ����
				Print.printMessage("-------------- �� �� �� �� �� --------------");
				if(paymentdao.InternetPayment(id,resNo)) {
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
}
