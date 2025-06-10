package com.ssg.shopadmin.config.view;

import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.ssg.common.config.Config;
import com.ssg.common.view.Page;
import com.ssg.shopadmin.AppMain;
import com.ssg.shopadmin.product.repository.ColorDAO;
import com.ssg.shopadmin.product.repository.SizeDAO;
import com.ssg.shopadmin.product.model.Color;
import com.ssg.shopadmin.product.model.Size;

public class ConfigPage extends Page{
	
	JPanel p_color;
	JLabel la_color;
	JTextField t_color;
	JButton bt_color;
	JList list_color;
	JScrollPane scroll_color;
	
	JPanel p_size;
	JLabel la_size;
	JTextField t_size;
	JButton bt_size;
	JList list_size;
	JScrollPane scroll_size;
	
	ColorDAO colorDAO;
	SizeDAO sizeDAO;
	
	public ConfigPage(AppMain appMain) {
		super(appMain);
		setBackground(java.awt.Color.GREEN);
		
		// 생성
		p_color =new JPanel();
		la_color = new JLabel("색상 정보");
		t_color = new JTextField();
		bt_color = new JButton("등록");
		list_color = new JList<>();
		scroll_color = new JScrollPane(list_color);
		
		p_size =new JPanel();
		la_size = new JLabel("사이즈 정보");
		t_size = new JTextField();
		bt_size = new JButton("등록");
		list_size = new JList<>();
		scroll_size = new JScrollPane(list_size);
		
		colorDAO = new ColorDAO();
		sizeDAO = new SizeDAO();
		
		// 스타일
		Dimension dimension = new Dimension(150, 30);
		
		la_color.setPreferredSize(dimension);
		t_color.setPreferredSize(dimension);
		la_size.setPreferredSize(dimension);
		t_size.setPreferredSize(dimension);
		
		scroll_color.setPreferredSize(new Dimension(200, 300));
		scroll_size.setPreferredSize(new Dimension(200, 300));
		
		p_color.setPreferredSize(new Dimension(Config.ADMIN_MAIN_WIDTH - 200, 350));
		p_size.setPreferredSize(new Dimension(Config.ADMIN_MAIN_WIDTH - 200, 350));
		
		p_color.setBorder(new TitledBorder("색상 정보 등록"));
		p_size.setBorder(new TitledBorder("사이즈 정보 등록"));
		
		// 조립
		p_color.add(la_color);
		p_color.add(t_color);
		p_color.add(bt_color);
		p_color.add(scroll_color);
		
		p_size.add(la_size);
		p_size.add(t_size);
		p_size.add(bt_size);
		p_size.add(scroll_size);
		
		add(p_color);
		add(p_size);
		
		getColorList();
		
		// 색상 등록 버튼에 이벤트 연결
		// 람다  함수형 인터페이스에서 사용할 수 있음
		bt_color.addActionListener((e)->{ 
			// e는 actionPerformed()가 됨
//			System.out.println("click");
			registColor();
			getColorList();
			}
		);
		
		bt_size.addActionListener((e)->{ 
			registSize();
			getSizeList();
			}
		);
	}
	
	/**
	 * 색상 등록
	 */
	public void registColor() {
		Color color = new Color();
		color.setColor_name(t_color.getText());
		
		int result = colorDAO.insert(color);
		if (result > 0) {
			JOptionPane.showConfirmDialog(this, "등록 성공");
		} else {
			JOptionPane.showConfirmDialog(this, "등록 실패");
		}
	}

	public void getColorList() {
		ArrayList<Color> colorList = colorDAO.selectAll();
		
		Vector<Color> vector = new Vector<>(colorList);
		
		list_color.setListData(vector);
		
	}
	
	/**
	 * 사이즈 등록
	 */
	public void registSize() {
		Size size = new Size();
		size.setSize_name(t_size.getText());
		
		int result = sizeDAO.insert(size);
		if (result > 0) {
			JOptionPane.showConfirmDialog(this, "등록 성공");
		} else {
			JOptionPane.showConfirmDialog(this, "등록 실패");
		}
	}

	public void getSizeList() {
		ArrayList<Size> sizeList = sizeDAO.selectAll();
		
		Vector<Size> vector = new Vector<>(sizeList);
		
		list_size.setListData(vector);
		
	}	
	
}
