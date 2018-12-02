package theater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchMovie {
	String jdbcUrl = "jdbc:mysql://localhost:3306/theater";
	String dbId = "parkyoonjung";;
	String dbPass = "qkrdbswjd";

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;

	Scanner sc = new Scanner(System.in);

	public SearchMovie(String id) throws SQLException, ClassNotFoundException { // id를 인자로 받아옴
		System.out.println("");
		
	}
}
