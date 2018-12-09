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
	
	public void paymentStart(String id){ // id를 인자로 받아옴
		ReservationDAO reservationdao = new ReservationDAO();
		Print.printMessage("------------------- 영 화 결 제 -------------------");
		Print.printMessage("-> 결제하려는 티켓의 예매번호를 입력해주세요.");
		String resNo = sc.next(); 
		String isPayment = reservationdao.isPayment(resNo);
		PaymentDAO paymentdao = new PaymentDAO();
		if(isPayment.equals("false")) { // 결제여부확인
			Print.printMessage("-> 결제 유형을 선택해주세요.");
			Print.printMessage("1. 인터넷 결제 2. 현장 결제");
			int paymentType = sc.nextInt();
			if(paymentType == 1) { // 인터넷 결제
				Print.printMessage("-------------- 인 터 넷 결 제 --------------");
				if(paymentdao.InternetPayment(id,resNo)) {
					reservationdao.EndPayment(resNo); // true로 변경
					System.out.println("!! 인터넷 결제 완료");
				}else {
					System.out.println("!! 인터넷 결제 실패 다시 시도해주세요.");
				}
				
			}else { // 현장 결제
				System.out.println("!! 현장에서 관리자에게 결제해주세요.");
			}			
		}else if(isPayment.equals("true")) {
			System.out.println("!! 이미 결제가 완료된 티켓입니다.");
		}else {
			System.out.println("!! 존재하지 않는 예매번호 입니다.");
		}
	}
}
