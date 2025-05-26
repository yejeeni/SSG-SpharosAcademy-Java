package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// select문 수행하기
public class SelectTest {
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/dev";
		String user = "java";
		String pass = "1234";
		Connection con = null; // finally에서 닫기 위해서는 try문 내부에 있으면 안 됨
		PreparedStatement pstmt = null; // 쿼리 수행 객체. 쿼리문마다 1:1로 대응해야 함 (쿼리가 3개일 경우, pstmt는 3개)
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로드 성공");
			
			// Connection 객체는 접속이 성공되면 메모리에 올라오는 결과물. 접속 시도가 아니라 접속 정보를 가지고 있음
			con = DriverManager.getConnection(url, user, pass);
			
			if (con != null) {
				System.out.println("접속 성공");
				
				String sql = "select * from emp";
				pstmt = con.prepareStatement(sql); // pstmt 객체 생성
				
				// 쿼리 실행 (DML(insert, create, delete), DDL(create, drop, alter))
				// DML, DDL: executeUpdate(), select executeQuery()
			    rs = pstmt.executeQuery();
			    // rs를 생성한 최초의 시점에는 커서가 첫 번째 레코드보다 위에 위치하여, 커서를 옮기면서 데이터를 찾아야 함
			    
			    while(rs.next()) { // 커서가 앞으로 한 칸 이동	
			    	String ename = rs.getString("ename"); // 현재 커서가 위치한 곳에서의 ename 컬럼값
			    	int sal = rs.getInt("sal");
			    	String job = rs.getString("job");
			    	Date hireDate = rs.getDate("hiredate");
			    	System.out.println("ename = " + ename + ", sal = " + sal + ", job = " + job + ", hireDate = " + hireDate);			    	
			    }
			} else {
				System.out.println("접속 실패");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
		}
	}
}
