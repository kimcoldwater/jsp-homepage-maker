package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
		
	private Connection conn; //db ����
	private PreparedStatement pstmt; //db sql�� �Է�
	private ResultSet rs; //db���� ���
	
	//db�� jsp���� ����
	public UserDAO() { 
		try {
			String dbURL = "jdbc:mysql://localhost:3307/BBS";
			String dbID = "root";
			String dbPassword = "kim5057";
			Class.forName("com.mysql.jdbc.Driver");//jdbc ����̹� �ε�
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);//db���� ����
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//login�� ����� ����
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL); //preparedStatment ��ü ����
			
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery(); //���� �Է��� sql�� ���� ���(��ȯ)
			
			//�α��� ��� ����
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; //�α��� ����
				}
				else
					return 0;//��й�ȣ ����ġ
			}
			return -1; //���̵� ����
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -2;//�����ͺ��̽�����
	}


	//ȸ������ ��� ����
	public int join(User user) {
		String SQL = "INSERT INTO USER VALUES(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate(); // insert ���� �ݵ�� ��ȯ !!
			}catch(Exception e) {
				e.printStackTrace();
			}
			return -1;
	}
}