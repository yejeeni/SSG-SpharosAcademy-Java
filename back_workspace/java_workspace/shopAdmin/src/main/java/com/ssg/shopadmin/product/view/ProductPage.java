package com.ssg.shopadmin.product.view;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.ssg.common.view.Page;
import com.ssg.shopadmin.AppMain;

/**
 * 상품 등록 페이지
 */
public class ProductPage extends Page {
	JLabel la_topCategory;
	JLabel la_subCategory;
	JLabel la_producnName;
	JLabel la_brand;
	JLabel la_price;
	JLabel la_discount;
	JLabel la_color;
	JLabel la_size;
	JLabel la_photo;
	JLabel la_introduce;
	JLabel la_detail;
	
	JComboBox cb_topCategory;
	JComboBox cb_subCategory;
	JTextField t_productName;
	JTextField t_brand;
	JTextField t_price;
	JTextField t_Color;
	JTextField t_size;
	JTextField bt_photoField;
	JTextArea t_introduce;
	JTextArea t_detail;
	
	
	public ProductPage(AppMain appMain) {
		super(appMain);
		setBackground(Color.CYAN);
	}
}
