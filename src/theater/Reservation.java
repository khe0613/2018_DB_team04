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

	public Reservation(String id) throws SQLException, ClassNotFoundException { // id�� ���ڷ� �޾ƿ�
		System.out.println("�����Ͻ� ��ȭ�� �������ּ���.");
		int movieNo = selectMovie();
		System.out.println("��ȭ���� �������ּ���.");
		int branchNo = selectTherter(movieNo);
		System.out.println("�󿵳�¥�� �������ּ���.");
		int movieSchedule = selectDay(movieNo,branchNo);
		System.out.println("�󿵰��� �������ּ���.");
		int screenNum = selectScreen();
		System.out.println("�ð��� �������ּ���.");
		selectTime();
		System.out.println("������ �����Ͻðڽ��ϱ�?");
		int num = sc.nextInt();
		int price = num * 10000;
		System.out.println("�¼��� �������ּ���.");
		String seatNo = selectSeat(num);
		
		
	}
	
	public int selectMovie() throws ClassNotFoundException, SQLException { // ��ȭ ����
		System.out.println("-----------------������ ��ȭ ����---------------");
		sql = "select Movie.movieName from Movie inner join listScreenToBranch on (Movie.movieNo = listScreenToBranch.movieNo)"; 
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();			
		while(rs.next()) { // �� ��ȭ �̸� ���
			ArrayList<String> movieName = new ArrayList<String>();
			if(!movieName.contains(rs.getString("movieName"))) {
				movieName.add(rs.getString("movieName"));
				System.out.println(rs.getString("movieName")+"/t");
			} // ��ȭ�̸��� ����Ʈ�� ���ٸ� �߰�	
		}
		String selectMovie = sc.next(); // ��ȭ ����
		sql = "select movieNo from Movie where movieName = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, selectMovie);
		rs = pstmt.executeQuery();
		return rs.getInt("movieNo");
	}

	public int selectTherter(int movieNo) throws ClassNotFoundException, SQLException { // ��ȭ�� ����
		System.out.println("-----------------��ȭ�� ������ ����---------------");
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		sql = "select theater.branchName from theater inner join listScreenToBranch on (theater.branchNo = listScreenToBranch.movieBranchNo) where = ?"; 
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, movieNo);
		rs = pstmt.executeQuery();
		while(rs.next()) { // �� ���� ���
			System.out.println(rs.getString("branchName") + "/t");
		}
		String selectTherter = sc.next();
		sql = "select branchNo from theater where branchName = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, selectTherter);
		rs = pstmt.executeQuery();
		return rs.getInt("branchNo");
	}
	
	public int selectDay(int movieNo, int branchNo) throws ClassNotFoundException, SQLException { // �� ��¥ ����
		System.out.println("-----------------������ ������ ��ȭ�� ���� �� ��¥---------------");


		return 0;
	}
	
	public int selectScreen() { // �󿵰� ����
		System.out.println("-----------------������ ����,��ȭ,�� ��¥�� ���� �󿵰�---------------");


		return 0;
	}
	
	public void selectTime() { // �ð� ����
		
	}
	
	public String selectSeat(int num) { // �¼� ����
		return null;
	}
}
