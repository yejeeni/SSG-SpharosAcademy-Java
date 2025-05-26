package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

// EMP 테이블의 레코드를 모두 가져와서 JTable에 출력
public class EmpLoad extends JFrame {
	JTable table;
	JScrollPane scroll;
	String[][] data; // 건 수를 모르기 때문에 현재 시점에서는 배열 생성 불가
	String[] columns = {
			"empno","ename","job","mgr","hiredate","sal","comm","deptno"
	};
	
	public void loadData() {
		String url = "jdbc:mysql://localhost:3306/dev";
		String user = "java";
		String pass = "1234";

		Connection connection = null; // 접속 정보 객체. 이 객체를 이용하여 접속을 끊을 수도 있음
		PreparedStatement pStatement = null; // 쿼리문 수행 객체
		ResultSet resultSet = null; // 표 데이터를 표현한 객체
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로드 성공");
			
			connection = DriverManager.getConnection(url, user, pass);
			if (connection!=null) {
				System.out.println("데이터베이스 접속 성공");
				
				String sql = "select * from emp order by empno asc";
				
				pStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // 쿼리 수행 객체 생성. 커서가 자유롭게 앞, 뒤, 점프로 움직일 수 있게, 읽기 전용으로
				resultSet = pStatement.executeQuery(); // ResultSet으로 select문의 결과 받음
				
				resultSet.last(); // 커서를 레코드의 마지막 행으로 이동
				int total = resultSet.getRow(); // 데이터 개수를 받아옴
				resultSet.beforeFirst(); // 커서를 맨앞으로 이동
						
				data = new String[total][8];
				
				int idx = 0;
				while (resultSet.next()) {
					// 한 사원에 대한 정보 처리
					data[idx][0] = resultSet.getString("empno"); // empno
					data[idx][1] = resultSet.getString("ename"); // ename
					data[idx][2] = resultSet.getString("job"); // job
					data[idx][3] = resultSet.getString("mgr"); // mgr
					data[idx][4] = resultSet.getString("hiredate"); // hiredate
					data[idx][5] = resultSet.getString("sal"); // sal
					data[idx][6] = resultSet.getString("comm"); // comm
					data[idx][7] = resultSet.getString("deptno"); // deptno		
					
					idx++;
				}
//				System.out.println("resultset now = "+resultSet.getRow());
			} else {
				System.out.println("데이터베이스 접속 실패");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public EmpLoad(){
		// emp 정보 가져오기
		loadData();
		
		table = new JTable(data, columns);
		scroll = new JScrollPane(table);
		
		add(scroll);
		
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new EmpLoad();
	}
}
