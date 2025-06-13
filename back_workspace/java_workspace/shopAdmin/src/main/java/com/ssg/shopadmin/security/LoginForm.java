package com.ssg.shopadmin.security;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ssg.shopadmin.AppMain;
import com.ssg.shopadmin.common.config.Config;
import com.ssg.shopadmin.common.util.StringUtil;
import com.ssg.shopadmin.common.view.Page;
import com.ssg.shopadmin.security.model.Admin;

public class LoginForm extends Page{

	JLabel la_id;
	JLabel la_pwd;
	JTextField t_id;
	JPasswordField t_pwd;
	JButton bt_login;
	JButton bt_join;
	
	
	public LoginForm(AppMain appMain) {
		super(appMain);
		
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
		
		
		bt_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loginCheck();
			}
		});
		
		bt_join.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appMain.showPage(Config.JOIN_PAGE);
			}
		});
		
		
		this.setPreferredSize(new Dimension(270, 145));
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
			pStatement = appMain.getConnection().prepareStatement(sql);
			// 쿼리문을 수행하기 위해 바인드 변수를 지정
			pStatement.setString(1, id);
			pStatement.setString(2, StringUtil.getSecuredPass(pwd));
			resultSet = pStatement.executeQuery(); // select문은 resultSet을 반환
			
			if(resultSet.next()) { // resultSet이 next가 가능하면 id, pwd가 일치하는 데이터가 있다는 것이므로 로그인 성공
				JOptionPane.showMessageDialog(this, "로그인 성공");	
			
				// 로그인 성공한 사람의 정보 담기
				Admin admin = new Admin(); // empty 상태
				admin.setAdmin_id(resultSet.getInt("admin_id"));
				admin.setId(resultSet.getString("id"));
				admin.setPwd(resultSet.getString("pwd"));
				admin.setName(resultSet.getString("name"));
				
				// AppMain이 보유하고 있는 Admin 모델 객체의 값을 위의 정보로 수정
				appMain.admin = admin;
								
				// 현재 유저가 보고있는 페이지가 MainPage로 교체
				appMain.showPage(Config.MAIN_PAGE);
				
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

}
