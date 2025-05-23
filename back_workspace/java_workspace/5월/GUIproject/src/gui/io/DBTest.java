package db;

//MySQL db 연동해보자!!! EClipse와 같은 tool을 사용하지 않고도!!

import java.sql.DriverManager;
import java.sql.*;


class DBTest
{
	public static void main(String[] args){
		//제어하기를 원하는 드라이버 먼저 로드(method 영역)
		PreparedStatement pstmt = null;
		Connection con = null;
		
		String mysqlDriver = "com.mysql.cj.jdbc.Driver";
		String oracleDriver = "oracle.jdbc.driver.OracleDriver";
		
		String mysqlUrl = "jdbc:mysql://localhost:3306/dev";
		String mysqlId = "root";
		String mysqlPwd = "1234";
		
		String oracleUrl = "jdbc:oracle:thin:@localhost:1521:XE";
		String oracleId = "java";
		String oraclePwd = "1234";
		
		
		try{
			//1) 드라이버 로드
			Class.forName(oracleDriver);
			System.out.println("드라이버 로드 성공");
			
			
			String url = oracleUrl;
			String id = oracleId;
			String pass = oraclePwd;
			//2) 접속
			//Connection이란 접속 성공 후 , 그 접속 정보를 가진 인터페이스이다.
			//이 객체가 null이면 접속 실패이다.
			con = DriverManager.getConnection(url,id,pass);
			if(con != null){
				System.out.println("접속 성공");
				
				//접속이 성공한 이후, 원격지의 db서버에 sql문을 네트워크를 통해 전송
				//String sql = "insert into member3 (uid, pwd, email) values ('Sowha', '3333', '3333@daum.net')";
				String oracle = "insert into member3(member3_id, id, pwd,email)";
				oracle += " values(seq_member3.nextval, 'James','3333','333@gmail.com')";
				//JDBC java database connectivity = 자바의 데이터베이스 연동 기술 및 지원 패키지(java sql package에서..)
				//jdbc에서 쿼리문 수행을 담당하는 것은 인터페이스이다. PreparedStatement
				
				
				//접속이 성공된 이후에 쿼리문 수행이 가능하므로, pstmt 객체의 인스턴스는
				//connection 인터페이스로부터 얻어온다.
				pstmt = con.prepareStatement(oracle);
				//준비된 문장으로 쿼리 실행 
				int result = pstmt.executeUpdate(); //DML 수행 시 이 메서드를 수행해야함
				if(result > 0 ){
					System.out.println("등록 성공");
					
				}
				else{
					System.out.println("등록 실패!");
				}
						
			}
			else{
				System.out.println("접속 실패");
			}
		}
		catch(ClassNotFoundException e){
			System.out.println("드라이버를 찾을 수 없습니다.");
		}
		catch(SQLException e){
				System.out.println("url을 찾을 수 없습니다.");
		}
		finally{
			//db와 스트림 같은 자원을 차지하는 기술은 사용 후 반드시 닫아야한다.
			//닫지 않으며 메모리를 계속 확보하고 있다.
			// 만일 웹서버에 이 닫지 않은 코드가 올라간다면.... 동시 자원이 다수생성된다.
			//ㅠㅠ 사 건 발 생
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(con != null){
					con.close();
				}
			}
			catch(SQLException e){
				System.out.println("정상적으로 닫지 못했습니다.");
			}
		}
	}
}