package com.ssg.shopadmin.security;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ssg.shopadmin.AppMain;
import com.ssg.shopadmin.security.model.Admin;

public class LoginForm extends JFrame{

	JLabel la_id;
	JLabel la_pwd;
	JTextField t_id;
	JPasswordField t_pwd;
	JButton bt_login;
	JButton bt_join;
	
	Connection connection;
	
	public LoginForm() {
		la_id = new JLabel("ID");
		la_pwd = new JLabel("PW");
		t_id = new JTextField();
		t_pwd = new JPasswordField();
		bt_login = new JButton("로그인");
		bt_join = new JButton("가입");
		
		
		// 스타일
		Dimension t_dimension = new Dimension(150, 20);
		t_id.setPreferredSize(t_dimension);
		t_pwd.setPreferredSize(t_dimension);
				
		Dimension la_dimension = new Dimension(70, 20);
		la_id.setPreferredSize(la_dimension);
		la_pwd.setPreferredSize(la_dimension);

		// 조립
		this.setLayout(new FlowLayout());
		add(la_id);
		add(t_id);
		add(la_pwd);
		add(t_pwd);
		add(bt_login);
		add(bt_join);
		
		connect();
		
		bt_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loginCheck();
			}
		});
		
		setSize(270, 140);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * 데이터베이스 연결
	 */
	public void connect() {
		String url = "jdbc:mysql://localhost:3306/shop";
		String user = "shop";
		String pw = "1234";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(url, user, pw);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (connection != null) {
				this.setTitle("접속중");
			} else{
				this.setTitle("접속실패");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 로그인
	 */
	public void loginCheck() {
		String id = t_id.getText(); // 일반 텍스트 컴포넌트의 스트링 값 얻기
		String pwd = new String(t_pwd.getPassword()); // 명시적 String 생성법으로 비밀번호 반환
		
		String sql = "select * from admin where id = ? and pwd = ?";
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		
		try {
			pStatement = connection.prepareStatement(sql);
			// 쿼리문을 수행하기 위해 바인드 변수를 지정
			pStatement.setString(1, id);
			pStatement.setString(2, pwd);
			resultSet = pStatement.executeQuery(); // select문은 resultSet을 반환
			
			if(resultSet.next()) { // resultSet이 next가 가능하면 id, pwd가 일치하는 데이터가 있다는 것이므로 로그인 성공
				JOptionPane.showMessageDialog(this, "로그인 성공");	
				
				// 로그인 성공한 사람의 정보 담기
				Admin admin = new Admin(); // empty 상태
				admin.setAdmin_id(resultSet.getInt("admin_id"));
				admin.setId(resultSet.getString("id"));
				admin.setPwd(resultSet.getString("pwd"));
				admin.setName(resultSet.getString("name"));
								
				AppMain appMain = new AppMain(connection, admin); // 관리자페이지 메인화면 띄우기
				this.setVisible(false); // 로그인폼 숨기기
				
			} else {
				JOptionPane.showMessageDialog(this, "로그인 실패");
			}
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
			if (pStatement != null) {				
				try {
					pStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new LoginForm();
	}

}
