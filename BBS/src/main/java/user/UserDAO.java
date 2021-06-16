package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
		
	private Connection conn; //db 연결
	private PreparedStatement pstmt; //db sql문 입력
	private ResultSet rs; //db값을 출력
	
	//db와 jsp서버 연결
	public UserDAO() { 
		try {
			String dbURL = "jdbc:mysql://localhost:3307/BBS";
			String dbID = "root";
			String dbPassword = "kim5057";
			Class.forName("com.mysql.jdbc.Driver");//jdbc 드라이버 로딩
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);//db서버 접속
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//login의 기능을 구현
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL); //preparedStatment 객체 생성
			
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery(); //위에 입력한 sql문 값을 출력(반환)
			
			//로그인 기능 구현
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; //로그인 성공
				}
				else
					return 0;//비밀번호 불일치
			}
			return -1; //아이디가 없음
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -2;//데이터베이스오류
	}


	//회원가입 기능 구현
	public int join(User user) {
		String SQL = "INSERT INTO USER VALUES(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate(); // insert 문은 반드시 반환 !!
			}catch(Exception e) {
				e.printStackTrace();
			}
			return -1;
	}
}