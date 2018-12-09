package theater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reservation {
	String jdbcUrl = "jdbc:mysql://localhost:3306/theater";
	String dbId = "parkyoonjung";;
	String dbPass = "qkrdbswjd";

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;
	
	Scanner sc = new Scanner(System.in);

	public Reservation(String id) throws SQLException, ClassNotFoundException { // id를 인자로 받아옴
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		
		System.out.println("예매하실 영화를 선택해주세요.");
		int movieNo = selectMovie();
		
		System.out.println("영화관을 선택해주세요.");
		int branchNo = selectTheater(movieNo);
		
		System.out.println("상영날짜를 선택해주세요.");
		int movieSchedule = selectDay(movieNo,branchNo);
		System.out.println("상영관을 선택해주세요.");
		int screenNum = selectScreen();
		System.out.println("시간을 선택해주세요.");
		selectTime();
		System.out.println("몇장을 예매하시겠습니까?");
		int num = sc.nextInt();
		int price = num * 10000;
		System.out.println("좌석을 선택해주세요.");
		String seatNo = selectSeat(num);
		
		
	}
	
	public int selectMovie() throws ClassNotFoundException, SQLException { // 영화 선택
		System.out.println("-----------------상영중인 영화 정보---------------");
		sql = "select Movie.movieName from Movie inner join ScreeningTable on (Movie.movieNo = ScreeningTable.movieNo)"; 
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();			
		while(rs.next()) { // 상영 영화 이름 출력
			ArrayList<String> movieName = new ArrayList<String>();
			if(!movieName.contains(rs.getString("movieName"))) {
				movieName.add(rs.getString("movieName"));
				System.out.print(rs.getString("movieName")+"/t");
			} // 영화이름이 리스트에 없다면 추가	
		}
		System.out.println();
		
		String selectMovie = sc.next(); // 영화 선택
		sql = "select movieNo from Movie where movieName = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, selectMovie);
		rs = pstmt.executeQuery();
		return rs.getInt("movieNo");
	}

	public int selectTheater(int movieNo) throws ClassNotFoundException, SQLException { // 영화관 선택
		System.out.println("-----------------영화가 상영중인 지점---------------");
		
		sql = "select theater.branchName from theater inner join ScreeningTable on (theater.branchNo = ScreeningTable.movieBranchNo) where = ?"; 
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, movieNo);
		rs = pstmt.executeQuery();
		while(rs.next()) { // 상영 지점 출력
			System.out.println(rs.getString("branchName") + "/t");
		}
		String selectTherter = sc.next();
		sql = "select branchNo from theater where branchName = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, selectTherter);
		rs = pstmt.executeQuery();
		return rs.getInt("branchNo");
	}
	
	
	
	public int selectDay(int movieNo, int branchNo) throws ClassNotFoundException, SQLException { // 상영 날짜 선택
		System.out.println("-----------------선택한 지점과 영화에 대한 상영 날짜---------------");
		
		sql = "";	// 날짜 선택하기

		return 0;
	}
	
	public int selectScreen() { // 상영관 선택
		System.out.println("-----------------선택한 지점,영화,상영 날짜에 대한 상영관---------------");


		return 0;
	}
	
	public void selectTime() { // 시간 선택
		
	}
	
	public String selectSeat(int num) { // 좌석 선택
		return null;
	}
}
