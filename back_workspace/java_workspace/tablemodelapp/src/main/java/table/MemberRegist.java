package table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;


public class MemberRegist extends JFrame implements ActionListener, WindowListener, MouseListener{
	//서쪽 영역 
	JPanel p_west;
	JTextField t_id;
	JTextField t_name;
	JTextField t_tel;
	JButton bt;
	
	//센터영역
	JPanel p_center;
	JTable table;
	JScrollPane scroll;
	
	//센터 하단
	JPanel p_south;
	JLabel la_id;
	JLabel la_value;
	JLabel la_name;
	JLabel la_tel;
	JTextField t_name2;
	JTextField t_tel2;
	JButton bt_edit;
	JButton bt_del;
	
	TableModel model;
	int index=0;
	int selectId = 0; // 현재 선택한 데이터의 pk id 
	String[] selectMember = null;
	
	Connection connection; // 접속은 윈도우 창 생성 시 한 번 시도되며, 창을 닫을 때 접속을 해제
	
	public MemberRegist() {
		//생성
		p_west = new JPanel();
		t_id = new JTextField();
		t_name=new JTextField();
		t_tel = new JTextField();
		
		// 이미지 적용. 이미지의 경로는 class path를 기준으로 가져오기
		Class myClass = this.getClass();
		URL url = myClass.getClassLoader().getSystemResource("handshake.png");
		
		bt = new JButton("가입");
		
		p_center = new JPanel();

		table = new JTable(model =new MyModel(this));  // 생성자의 인수에 이 테이블에서 보여줘야 할 데이터 또는 데이터 처리 객체가 들어가야 함 (MVC패턴에서 모델+컨트롤러의 느낌). 실제 보여질 데이터는 모델이 결정
		scroll = new JScrollPane(table);
		
		//새로 추가된 센터 영역 컴포넌트 
		p_south = new JPanel();
		la_id = new JLabel("ID");
		la_name = new JLabel("Name");
		la_tel = new JLabel("Tel");
		la_value=new JLabel("");
		
		t_name2 = new JTextField();
		t_tel2 = new JTextField();
		bt_edit = new JButton("수정");
		bt_del = new JButton("삭제");
		
		
		// 스타일 적용
		p_west.setBackground(Color.ORANGE);
		p_west.setPreferredSize(new Dimension(150, 500));

		Dimension d = new Dimension(146, 35);
		t_id.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_tel.setPreferredSize(d);
		
		scroll.setPreferredSize(new Dimension(435, 350));
		
		p_south.setPreferredSize(new Dimension(450, 145));
		p_south.setBackground(Color.YELLOW);
		
		Dimension d2 = new Dimension(200, 30);		
		la_id.setPreferredSize(d2);
		la_name.setPreferredSize(d2);
		la_tel.setPreferredSize(d2);
		
		Dimension d3 = new Dimension(370, 30);
		la_value.setPreferredSize(d3);
		t_name2.setPreferredSize(d3);
		t_tel2.setPreferredSize(d3);
		
		
		//조립 
		p_west.add(t_id);
		p_west.add(t_name);
		p_west.add(t_tel);
		p_west.add(bt);
		add(p_west, BorderLayout.WEST);
	
		p_center.add(scroll);
		add(p_center);
		add(p_south, BorderLayout.SOUTH);
		
		//남쪽 패널에 부착 
		p_south.add(la_id);
		p_south.add(la_value);
		p_south.add(la_name);
		p_south.add(t_name2);
		p_south.add(la_tel);
		p_south.add(t_tel2);
		p_south.add(bt_edit);
		p_south.add(bt_del);
		
		
		bt.addActionListener(this); //버튼과 리스너 연결 
		table.addMouseListener(this); // 테이블과 리스너 연결
		bt_del.addActionListener(this); // 삭제 버튼과 리스너 연결
		bt_edit.addActionListener(this); // 수정 버튼과 리스너 연결
		
		//윈도우창과 리스너와의 연결 
		this.addWindowListener(this);
		
		setBounds(300,100, 600, 500);
		setVisible(true);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		 
		// 데이터베이스 접속
		connect();
		selectAll(); // 전체 회원 조회
	}
	
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/dev";
			String id="java";
			String pwd="1234";
			
			connection=DriverManager.getConnection(url, id, pwd);
			if(connection!=null) {
				this.setTitle("접속 성공");
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
	
	/**
	 * 모든 회원 정보 가져오기
	 */
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
			((MyModel)model).rows = new String[total][4];
			
			// 레코드의 마지막으로 이동했던 커서를 맨 앞으로 다시 복귀
			resultSet.beforeFirst();
			
			int index = 0; // 회원 인덱스
			while(resultSet.next()) {
				// 레코드 한 건 조회
				String[] record = {
						resultSet.getString("id"), 
						resultSet.getString("name"),  
						resultSet.getString("tel"),
						resultSet.getString("member4_id"),
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
	
	/**
	 * 선택한 회원 정보 가져오기
	 */
	public void select(int member4_id) {
//		System.out.println("사원 선택");
		String sql = "select * from member4 where member4_id = " + member4_id;
		System.out.println(sql);
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery(); // 레코드 결과 반환
			
			// 화면에 출력
			if(resultSet.next()) { // 레코드가 존재하는 경우
				String id = resultSet.getString("id");
				this.la_value.setText(id);
				
				String name = resultSet.getString("name");
				this.t_name2.setText(name);
				
				String tel = resultSet.getString("tel");
				this.t_tel2.setText(tel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(resultSet != null) {
					try {
						resultSet.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				
			}
		}
	}
	
	/**
	 * 선택한 회원 1명 삭제
	 */
	public void delete(int member4_id) {
		String sql = "delete from member4 where member4_id = " + member4_id;
//		System.out.println(sql);
		
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql); // 쿼리 객체
			int result = preparedStatement.executeUpdate(); // DML 수행. 쿼리가 수행된 레코드 수를 반환한다. 반한값이 0인 경우는 실행이 되지 않은 것
			if(result > 0) {
				JOptionPane.showMessageDialog(this, "삭제 성공");
				selectAll();
			} else {
				JOptionPane.showMessageDialog(this, "삭제에 실패했습니다.");
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
	}
	
	/**
	 * 선택한 회원 1명의 정보 수정
	 * @param record 회원 1명의 정보 배열
	 */
	public void edit(String[] record) {
		String sql = "update member4 set id= '" + record[0] + "', name= '"+ record[1]+ "', tel='" + record[2] + "'";
		sql += " where member4_id= " + record[3];
		System.out.println(sql);
		
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			int result = preparedStatement.executeUpdate();
			if(result > 0) {
				JOptionPane.showMessageDialog(this, "수정되었습니다.");
				selectAll();
			} else {
				JOptionPane.showConfirmDialog(this, "수정에 실패했습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if(object == bt) {
			regist();			
		}else if(object == bt_edit) {
			int result = JOptionPane.showConfirmDialog(this, "수정하시겠습니까?");
			if (result == JOptionPane.OK_OPTION) {
				selectMember[1] = t_name2.getText();
				selectMember[2] = t_tel2.getText();
				edit(selectMember);
			}
		}else if(object == bt_del) {
			int result = JOptionPane.showConfirmDialog(this, "삭제하시겠습니까?");
			if (result == JOptionPane.OK_OPTION) {
				delete(selectId);
			}
		}
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

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int row = table.getSelectedRow(); // 선택한 행의 값
		int col = table.getSelectedColumn(); // 선택한 열의 값
//		// 행, 열 값을 통해 데이터 베열에 접근해서 값을 가져오는 방식
		// JTable에 출력하지 않은 숨겨진 데이터도 접근 가능
		System.out.println(((MyModel)model).rows[row][3]);
		
		String[][] rows = ((MyModel)model).rows;
		
		// JTable에서 자신의 셀의 정보를 반환하는 것을 통해 값을 가져오는 방식
//		Object value = table.getValueAt(row, 1);
//		System.out.println(value);
		
		selectId = Integer.parseInt(((MyModel)model).rows[row][3]);
		selectMember = rows[row];
		select(Integer.parseInt(((MyModel)model).rows[row][3]));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	
}