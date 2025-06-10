package com.ssg.shopadmin.member.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ssg.common.config.Config;
import com.ssg.common.view.Page;
import com.ssg.shopadmin.AppMain;
import com.ssg.shopadmin.common.util.StringUtil;

/**
 * 관리자 등록 페이지
 */
public class MemberJoin extends Page{
	JLabel la_id;
	JLabel la_pwd;
	JLabel la_name;
	JLabel la_email;
	
	JTextField t_id;
	JPasswordField t_pwd;
	JTextField t_name;
	JTextField t_email;
	
	JButton bt_login;
	JButton bt_join;
	
	public MemberJoin(AppMain appMain) {
		super(appMain);
		
		la_id = new JLabel("ID");
		la_pwd = new JLabel("PW");
		la_name = new JLabel("이름");
		la_email = new JLabel("이메일");
		
		t_id = new JTextField();
		t_pwd = new JPasswordField();
		t_name = new JTextField();
		t_email = new JTextField();
		
		bt_login = new JButton("로그인");
		bt_join = new JButton("회원가입");
		
		Dimension dimension = new Dimension(200, 35);
		
		la_id.setPreferredSize(dimension);
		la_pwd.setPreferredSize(dimension);
		la_name.setPreferredSize(dimension);
		la_email.setPreferredSize(dimension);
		
		t_id.setPreferredSize(dimension);
		t_pwd.setPreferredSize(dimension);
		t_name.setPreferredSize(dimension);
		t_email.setPreferredSize(dimension);
		
		this.setPreferredSize(new Dimension(450, 300));
		
		add(la_id);
		add(t_id);
		add(la_pwd);
		add(t_pwd);
		add(la_name);
		add(t_name);
		add(la_email);
		add(t_email);
		add(bt_login);
		add(bt_join);
		
		// 로그인 버튼에 리스너 연결
		bt_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appMain.showPage(Config.LOGIN_PAGE);
			}
		});		
		
		// 가입 버튼에 리스너 연결
		bt_join.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});		
	}
	
	// 가입한 관리자 정보를 데이터에 추가
	public void regist() {
		// 데이터베이스에 넣기 전에 폼 양식을 제대로 작성했는지 유효성 체크
		if (t_id.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
		} else if(t_pwd.getPassword().length < 1) {
			JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요");
		} else if(t_name.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "이름을 입력하세요");
		} else if(t_email.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "이메일을 입력하세요");
		} else {
			// mysql에 insert
			StringBuffer sql = new StringBuffer();
			sql.append("insert into admin(id, pwd, name, email)");
			sql.append(" values(?, ?, ?, ?)");
			
			PreparedStatement pStatement = null;
			try {
				pStatement = appMain.getConnection().prepareStatement(sql.toString());
				pStatement.setString(1, t_id.getText()); // 사용자가 입력한 id
				pStatement.setString(2, StringUtil.getSecuredPass(new String(t_pwd.getPassword()))); // 사용자가 입력한 비밀번호
				pStatement.setString(3, t_name.getText());
				pStatement.setString(4, t_email.getText());
				
				int result = pStatement.executeUpdate(); // DML 실행
				if (result > 0) {
					JOptionPane.showConfirmDialog(this, "관리자 가입 성공");
				} else {
					JOptionPane.showConfirmDialog(this, "관리자 가입 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
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
}
