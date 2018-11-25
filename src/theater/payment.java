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
	
	public payment(String id) throws SQLException, ClassNotFoundException { // id를 인자로 받아옴
		System.out.println("결제하려는 티켓의 예매번호를 입력해주세요.");
		String resNo = sc.next(); 
		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		sql = "select ispayment from Member where id =?"; // 예매번호의 결제여부 가져오기
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		Boolean isPayment = rs.getBoolean("isPayment");
		if(isPayment == false) { // 결제여부확인
			System.out.println("결제 유형을 선택해주세요. 1. 인터넷 결제	2. 현장 결제");
			int paymentType = sc.nextInt();
			if(paymentType == 1) { // 인터넷 결제
				InternetPayment(id,resNo);
			}else { // 현장 결제
				System.out.println("현장에서 관리자에게 결제해주세요.");
			}			
		}else if(isPayment == null){
			System.out.println("존재하지 않는 예매번호입니다.");
		}else {
			System.out.println("이미 결제가 완료된 티켓입니다.");
		}
	}
	
	public void InternetPayment(String id,String resNo) throws SQLException, ClassNotFoundException { // 인터넷 결제
		int usePoint;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		sql = "select point from Member where id =?"; // point정보 가져오기
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		int memberPoint = rs.getInt("point");
		
		sql = "select price from Reservation where resNo =?"; // 예약정보를 이용해서 가격 가져오기
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, resNo);
		rs = pstmt.executeQuery();
		int ticketPrice = rs.getInt("price");
		int ticketCount = ticketPrice/10000;
		
		System.out.println("포인트를 사용하시겠습니까? 1. 네	2. 아니요");
		int pointType = sc.nextInt(); 
		if(pointType == 1) { // 포인트 사용
			System.out.println("포인트를 얼마나 사용하시겠습니까?");
			usePoint = sc.nextInt();
			if(usePoint > memberPoint) { // 사용할 포인트가 고객포인트보다 크다면
				System.out.println("사용할 포인트가 고객이 가진 포인트보다 크므로 사용하실 수 없습니다.");
			}else if(usePoint < 1000) { // 사용할 포인트가 1000점보다 작으면
				System.out.println("포인트가 1000점보다 작으므로 사용하실 수 없습니다.");
			}else {
				ticketPrice -= usePoint; // 포인트 사용후 티켓 가격
				memberPoint -= usePoint; // 포인트 사용후 고객 포인트
				memberPoint += (ticketCount*100); // 구매시 한장당 100포인트적립
				sql = "update member set point = ? where id = ?"; // 고객의 포인트를 usePoint 만큼 감소하고 테이블에 저장
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, memberPoint);
				pstmt.setString(2,id);
				pstmt.executeUpdate(); 
				System.out.println("고객의 잔여 포인트 : " + memberPoint);
				System.out.println("포인트를 사용하여 결제가 완료되었습니다.");
			}
		}else { // 포인트 미사용
			System.out.println("카드 번호를 입력해 주세요.");
			String cardNum  = sc.next();
			System.out.println("카드 비밀 번호를 입력해 주세요.");
			int cardPassword = sc.nextInt();
			System.out.println("--------결제중입니다--------");
			memberPoint += (ticketCount*100); // 구매시 100포인트적립
			sql = "update member set point = ? where id = ?"; // 고객의 포인트를 usePoint 만큼 감소하고 테이블에 저장
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberPoint);
			pstmt.setString(2,id);
			pstmt.executeUpdate(); 
			System.out.println("고객의 잔여 포인트 : " + memberPoint);
			System.out.println("결제가 완료되었습니다.");
		}
	}
	

}
