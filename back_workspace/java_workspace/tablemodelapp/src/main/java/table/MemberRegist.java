package table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class MemberRegist extends JFrame implements ActionListener, WindowListener{
	// 서쪽 영역
	JPanel p_west;
	JTextField t_id;
	JTextField t_name;
	JTextField t_tel;
	JButton bt;
	
	// 센터 영역
	JPanel p_center;
	JTable table;
	JScrollPane scroll;
	
	TableModel model;
	
	
	Connection connection; // 접속은 윈도우 창 생성 시 한 번 시도되며, 창을 닫을 때 접속을 해제
	
	public MemberRegist() {
		p_west = new JPanel();
		t_id = new JTextField();
		t_name = new JTextField();
		t_tel = new JTextField();
		bt = new JButton("가입");
		
		p_center = new JPanel();
				
		// model을 부모인 TableModel로 정의해놓고, 사용할 테이블의 자식 클래스로 다운캐스팅
		model = new MyModel();
		table = new JTable(model); // 생성자의 인수에 이 테이블에서 보여줘야 할 데이터 또는 데이터 처리 객체가 들어가야 함 (MVC패턴에서 모델+컨트롤러의 느낌). 실제 보여질 데이터는 모델이 결정
		scroll = new JScrollPane(table);
		
		// 스타일 적용
		p_west.setBackground(Color.ORANGE);
		p_west.setPreferredSize(new Dimension(150, 500));
		
		Dimension dimension = new Dimension(146, 35);
		t_id.setPreferredSize(dimension);
		t_name.setPreferredSize(dimension);
		t_tel.setPreferredSize(dimension);

		scroll.setPreferredSize(new Dimension(430, 450));
		
		bt.addActionListener(this);
		
		// 윈도우 창과 리스너와의 연결
		addWindowListener(this);
		
		// 조립
		p_west.add(t_id);
		p_west.add(t_name);
		p_west.add(t_tel);
		p_west.add(bt);
		add(p_west, BorderLayout.WEST); 


		p_center.add(scroll);
		add(p_center);
		
		setBounds(300, 100, 600, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 데이터베이스 접속
		connect();
		selectAll(); // 전체 회원 조회
	}
	
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/dev";
			String id = "java";
			String pwd = "1234";
			
			connection = DriverManager.getConnection(url, id, pwd);
			if (connection != null) {
				// 윈도우창 제목에 "접속 성공"을 출력
				this.setTitle("접속 성공");
				System.out.println("데이터베이스 접속 성공");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 회원 한 명 등록
	 * 모델이 보유한 2차원 배열에 저장
	 */
	public void regist() {
		String sql = "insert into member4(id, name, tel)";
		sql += " values('"+t_id.getText()+"', '"+t_name.getText()+"', '"+t_tel.getText()+"')";
		System.out.println(sql);
		
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			int result = preparedStatement.executeUpdate(); // DML
			if(result > 0) {
				JOptionPane.showMessageDialog(this, "등록 성공");
				selectAll();
			} else {
				JOptionPane.showMessageDialog(this, "등록 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// 메모리에서 관리
		// 회원 1명을 1차원 배열에 담아서, 이 배열을 모델이 2차원 배열의 가장 최근 위치에 저장
//		String[] member = {t_id.getText(), t_name.getText(), t_tel.getText()};
//		((MyModel)model).rows[index++] = member;
//		table.updateUI();
	}
	
	// 데이터 가져오기
	public void selectAll() {
		String sql = "select * from member4";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement =	connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, resultSet.CONCUR_READ_ONLY);
			resultSet = preparedStatement.executeQuery(); // select문 executeQuery(). 결과로 표가 반환되므로, 해당 표를 제어할 ResultSet 객체가 반환
			resultSet.last(); // 레코드의 마지막으로 커서 이동
			int total = resultSet.getRow(); // 현재 행 수 조회
			System.out.println("총 회원 수: "+total);
			
			// resultSet 자체는 Model이 보유하고 있는 2차원 배열 자체가 아니므로, resultSet의 데이터를 2차원 배열로 변환하여 Model에서 배열 대신 사용해야 함
			// total의 값으로 2차원 배열 재정의
			((MyModel)model).rows = new String[total][3];
			
			// 레코드의 마지막으로 이동했던 커서를 맨 앞으로 다시 복귀
			resultSet.beforeFirst();
			
			int index = 0; // 회원 인덱스
			while(resultSet.next()) {
				// 레코드 한 건 조회
				String[] record = {
						resultSet.getString("id"), 
						resultSet.getString("name"),  
						resultSet.getString("tel")
						};
				
				((MyModel)model).rows[index++] = record;
			}
			table.updateUI();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		regist();
	}
	
	public static void main(String[] args) {
		new MemberRegist();
	}

	@Override
	public void windowOpened(WindowEvent e) {
//		System.out.println("windowOpened");
	}

	/**
	 * 윈도우 창을 닫는 순간 호출되는 메서드
	 * 연결되어있던 자원을 해제하는 용도로 사용 가능
	 */
	@Override
	public void windowClosing(WindowEvent e) {
//		System.out.println("windowClosing");
		
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
//		System.out.println("windowClosed");
	}

	@Override
	public void windowIconified(WindowEvent e) {
//		System.out.println("windowIconified");
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
//		System.out.println("windowDeiconified");
	}

	@Override
	public void windowActivated(WindowEvent e) {
//		System.out.println("windowActivated");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
//		System.out.println("windowDeactivated");
	}

	
}
