package com.ssg.shopadmin.product.view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.ssg.shopadmin.AppMain;
import com.ssg.shopadmin.common.config.Config;
import com.ssg.shopadmin.common.view.Page;

public class ProductListPage extends Page{
	
	JTable table;
	JScrollPane scroll;
	JPanel p_content;
	JButton bt_regist;
	ProductModel productModel;
	
	public ProductListPage(AppMain appMain) {
		super(appMain);
		
		table = new JTable(productModel = new ProductModel());
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(Config.ADMIN_MAIN_WIDTH - Config.SIDE_WIDTH, 400));
		add(scroll);
	}
}
