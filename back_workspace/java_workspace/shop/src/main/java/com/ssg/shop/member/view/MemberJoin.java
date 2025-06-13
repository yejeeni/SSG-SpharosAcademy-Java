package com.ssg.shop.member.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ssg.shop.AppMain;
import com.ssg.shop.common.config.Config;
import com.ssg.shop.common.exception.EmailExpection;
import com.ssg.shop.common.exception.MemberExpection;
import com.ssg.shop.common.util.MailSender;
import com.ssg.shop.common.view.Page;
import com.ssg.shop.member.model.Member;
import com.ssg.shop.member.repository.MemberDAO;

public class MemberJoin extends Page{
	JPanel p_container;
	JTextField t_id;
	JPasswordField t_pwd;
	JTextField t_name;
	JTextField t_email;
	JButton bt;
	
	MemberDAO memberDAO;
	
	MailSender mailSender;
	
	public MemberJoin(AppMain appMain) {
		super(appMain);
		
		// 생성
		p_container = new JPanel();
		t_id = new JTextField();
		t_pwd = new JPasswordField();
		t_name = new JTextField();
		t_email = new JTextField();
		bt = new JButton("가입하기");
		
		memberDAO = new MemberDAO();
		
		mailSender = new MailSender();
		
		// 스타일
		p_container.setPreferredSize(new Dimension(300, 200));
		
		Dimension d = new Dimension(220, 28);
		t_id.setPreferredSize(d);
		t_pwd.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_email.setPreferredSize(d);
		bt.setPreferredSize(d);
					
		// 조립
		p_container.add(t_id);
		p_container.add(t_pwd);
		p_container.add(t_name);
		p_container.add(t_email);
		p_container.add(bt);
		
		add(p_container);
		
		bt.addActionListener(e->{
			join();
		});
				
		setBackground(Color.PINK);
		setPreferredSize(new Dimension(Config.SHOPMAIN_WIDTH, Config.SHOPMAIN_HEIGHT - Config.NAVI_HEIGHT - Config.UTIL_HEIGHT));
	}
	
	public void join() {
		Member member = new Member();
		member.setId(t_id.getText());
		member.setPwd(new String(t_pwd.getPassword()));
		member.setName(t_name.getText());
		member.setEmail(t_email.getText());
		
		try {
			memberDAO.insert(member); // 강제로 try-catch로 감싸라고 하진 않지만, insert()에는 throws가 있어서 try-catch를 하는 것이 안전
			
			// 이메일 전송
			mailSender.sendHtml(member.getEmail(), "회원 가입을 축하드립니다.", "저희 사이트의 회원이 되어주셔서 감사합니다.");
			JOptionPane.showMessageDialog(this, "회원 가입 성공");
		} catch (MemberExpection | EmailExpection e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
}
