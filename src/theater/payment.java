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
	
	public void Start(String id){ // id를 인자로 받아옴
		ReservationDAO reservationdao = new ReservationDAO();
		Print.printMessage("------------------- 영 화 결 제 -------------------");
		Print.printMessage("-> 결제하려는 티켓의 예매번호를 입력해주세요.");
		String resNo = sc.next(); 
		String isPayment = reservationdao.isPayment(resNo);
		payment payment = new payment();
		if(isPayment.equals("false")) { // 결제여부확인
			Print.printMessage("-> 결제 유형을 선택해주세요.");
			Print.printMessage("1. 인터넷 결제 2. 현장 결제");
			int paymentType = sc.nextInt();
			if(paymentType == 1) { // 인터넷 결제
				Print.printMessage("-------------- 인 터 넷 결 제 --------------");
				if(payment.InternetPayment(id,resNo)) {
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
	
	public boolean InternetPayment(String id,String resNo) { // 인터넷 결제
		MemberDAO memberdao = new MemberDAO();
		int usePoint;
		int memberPoint = memberdao.getPoint(id);
		int ticketPrice = 10000;
		System.out.println("!! 포인트를 사용하시겠습니까?");
		Print.printMessage("1: 네  2: 아니오");
		int pointType = sc.nextInt(); 
		
		if(pointType == 1) { // 포인트 사용
			System.out.println("포인트를 얼마나 사용하시겠습니까?");
			usePoint = sc.nextInt();
			if(usePoint > memberPoint) { // 사용할 포인트가 고객포인트보다 크다면
				System.out.println("사용할 포인트가 고객이 가진 포인트보다 크므로 사용하실 수 없습니다.");
				return false;
			}else if(usePoint < 1000) { // 사용할 포인트가 1000점보다 작으면
				System.out.println("포인트가 1000점보다 작으므로 사용하실 수 없습니다.");
				return false;
			}else {
				ticketPrice -= usePoint; // 포인트 사용후 티켓 가격
				System.out.println("카드 번호를 입력해 주세요.");
				String cardNum  = sc.next();
				System.out.println("카드 비밀 번호를 입력해 주세요.");
				int cardPassword = sc.nextInt();
				System.out.println("카드로 포인트 결제 후 남은 가격인 "+ticketPrice+"원을 결제 하였습니다.");
				memberPoint -= usePoint; // 포인트 사용후 고객 포인트
				memberPoint += 100; // 구매시 한장당 100포인트적립
				
				if(memberdao.usePoint(memberPoint, id)) {
					System.out.println("고객의 잔여 포인트 : " + memberPoint);
					System.out.println("포인트를 사용하여 결제가 완료되었습니다.");	
					return true;
				}else {
					System.out.println("포인트를 사용한 결제가 실패하였습니다.");
					return false;
				}
			}
		}else { // 포인트 미사용
			System.out.println("카드 번호를 입력해 주세요.");
			String cardNum  = sc.next();
			System.out.println("카드 비밀 번호를 입력해 주세요.");
			int cardPassword = sc.nextInt();
			System.out.println("--------결제중입니다--------");
			System.out.println("카드로 " +ticketPrice+ "원을 결제 하였습니다.");
			memberPoint += 100; // 구매시 100포인트적립
			if(memberdao.usePoint(memberPoint, id)) {
				System.out.println("고객의 잔여 포인트 : " + memberPoint);
				System.out.println("결제가 완료되었습니다.");	
				return true;
			}else {
				System.out.println("결제가 실패하였습니다.");
				return false;
			}
		}
	}
	
	public boolean onSitePayment(String id,String resNo) { // 현장 결제
		MemberDAO memberdao = new MemberDAO();
		int usePoint;
		int memberPoint = memberdao.getPoint(id);
		int ticketPrice = 10000;
		System.out.println("!! 포인트를 사용하시겠습니까?");
		Print.printMessage("1: 네  2: 아니오");
		int pointType = sc.nextInt(); 
		
		if(pointType == 1) { // 포인트 사용
			System.out.println("포인트를 얼마나 사용하시겠습니까?");
			usePoint = sc.nextInt();
			if(usePoint > memberPoint) { // 사용할 포인트가 고객포인트보다 크다면
				System.out.println("사용할 포인트가 고객이 가진 포인트보다 크므로 사용하실 수 없습니다.");
				return false;
			}else if(usePoint < 1000) { // 사용할 포인트가 1000점보다 작으면
				System.out.println("포인트가 1000점보다 작으므로 사용하실 수 없습니다.");
				return false;
			}else {
				ticketPrice -= usePoint; // 포인트 사용후 티켓 가격
				System.out.println("티켓가격은 "+ticketPrice+"입니다.");
				System.out.println("--------결제중입니다--------");
				System.out.println("현금으로 " +ticketPrice+ "원을 결제 하였습니다.");
				memberPoint -= usePoint; // 포인트 사용후 고객 포인트
				memberPoint += 100; // 구매시 한장당 100포인트적립
				
				if(memberdao.usePoint(memberPoint, id)) {
					System.out.println("고객의 잔여 포인트 : " + memberPoint);
					System.out.println("포인트를 사용하여 결제가 완료되었습니다.");	
					return true;
				}else {
					System.out.println("포인트를 사용한 결제가 실패하였습니다.");
					return false;
				}
			}
		}else { // 포인트 미사용
			System.out.println("티켓가격은 "+ticketPrice+"입니다.");
			System.out.println("--------결제중입니다--------");
			System.out.println("현금으로 " +ticketPrice+ "원을 결제 하였습니다.");
			memberPoint += 100; // 구매시 100포인트적립
			if(memberdao.usePoint(memberPoint, id)) {
				System.out.println("고객의 잔여 포인트 : " + memberPoint);
				System.out.println("결제가 완료되었습니다.");	
				return true;
			}else {
				System.out.println("결제가 실패하였습니다.");
				return false;
			}
		}
	}
}
