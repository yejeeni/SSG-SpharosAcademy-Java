package com.ssg.shopadmin.product.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.ssg.common.view.Page;
import com.ssg.shopadmin.AppMain;
import com.ssg.shopadmin.product.model.SubCategory;
import com.ssg.shopadmin.product.model.TopCategory;
import com.ssg.shopadmin.product.repository.SubCategoryDAO;
import com.ssg.shopadmin.product.repository.TopCategoryDAO;

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
	JButton bt_open; // 파일탐색기 버튼
		
	JLabel la_photo;
	JLabel la_detail;
	
	
	JComboBox<TopCategory> cb_topCategory;
	JComboBox cb_subCategory;
	JTextField t_productName;
	JTextField t_brand;
	JTextField t_price;
	JTextField t_discount;
	JTextField t_color;
	JTextField t_size;
	JTextField t_introduce;
	JPanel p_priview; // 선택한 상품 이미지의 미리보기
	JLabel la_introduce;
	JTextArea t_detail;
	JButton bt_regist; // 상품 등록
	JButton bt_list; // 상품 목록
	
	TopCategoryDAO topCategoryDAO;
	SubCategoryDAO subCategoryDAO;
	
	
	public ProductPage(AppMain appMain) {
		super(appMain);
		setBackground(Color.CYAN);

		// 생성
		la_topCategory = new JLabel("최상위 카테고리");
		la_subCategory = new JLabel("하위 카테고리");
		la_producnName = new JLabel("제품명");
		la_brand = new JLabel("브랜드명");
		la_price = new JLabel("가격");
		la_discount = new JLabel("할인");
		la_color = new JLabel("색상");
		la_size = new JLabel("사이즈");
		bt_open = new JButton("상품 이미지 등록");
		la_introduce = new JLabel("소개");
		
		cb_topCategory = new JComboBox();
		cb_subCategory = new JComboBox();
		t_productName = new JTextField();
		t_brand = new JTextField();
		t_price = new JTextField();
		t_discount = new JTextField();
		t_color = new JTextField();
		t_size = new JTextField();
		p_priview = new JPanel(); // 추후 익명 내부 클래스로 전환
		t_introduce = new JTextField();
		la_detail = new JLabel();
		t_detail = new JTextArea();
		bt_regist = new JButton("등록");
		bt_list = new JButton("목록보기");
		
		topCategoryDAO = new TopCategoryDAO();
		subCategoryDAO = new SubCategoryDAO();

		// 스타일
		Dimension d = new Dimension(400, 30);
		la_topCategory.setPreferredSize(d);
		la_subCategory.setPreferredSize(d);
		la_producnName.setPreferredSize(d);
		la_brand.setPreferredSize(d);
		la_price.setPreferredSize(d);
		la_discount.setPreferredSize(d);
		la_color.setPreferredSize(d);
		la_size.setPreferredSize(d);
		bt_open.setPreferredSize(d);
		la_introduce.setPreferredSize(d);
		cb_topCategory.setPreferredSize(d);
		cb_subCategory.setPreferredSize(d);
		t_productName.setPreferredSize(d);
		t_brand.setPreferredSize(d);
		t_price.setPreferredSize(d);
		t_discount.setPreferredSize(d);
		t_color.setPreferredSize(d);
		t_size.setPreferredSize(d);
		p_priview.setPreferredSize(d);
		t_introduce.setPreferredSize(d);
		t_detail.setPreferredSize(d);
		bt_regist.setPreferredSize(new Dimension(130, 30));
		bt_list.setPreferredSize(new Dimension(130, 30));

		p_priview.setPreferredSize(new Dimension(400, 80));
		t_introduce.setPreferredSize(new Dimension(400, 50));
		t_detail.setPreferredSize(new Dimension(400, 60));

		// 조립
		add(la_topCategory);
		add(cb_topCategory);
		add(la_subCategory);
		add(cb_subCategory);
		add(la_producnName);
		add(t_productName);
		add(la_brand);
		add(t_brand);
		add(la_price);
		add(t_price);
		add(la_discount);
		add(t_discount);
		add(la_size);
		add(la_color);
		add(t_color);
		add(la_size);
		add(t_size);
		add(bt_open);
		add(p_priview);
		add(t_introduce);
		add(la_detail);
		add(t_detail);
		add(bt_regist);
		add(bt_list);

		setPreferredSize(new Dimension(880, 750));

		// 최상위 카테고리에 이벤트 연결
		cb_topCategory.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println("다른아이템 선택");
					
					// 선택한 아이템의 pk 출력
					TopCategory topCategory = (TopCategory)cb_topCategory.getSelectedItem();

					// 하위 카테고리 목록 가져오기
					List<SubCategory> subCategoryList = subCategoryDAO.selectByTop(topCategory);
					
					// 모든 하위 카테고리 콤보 아이템 삭제
					cb_subCategory.removeAllItems();
					
					for (int i=0; i<subCategoryList.size(); i++) {
						cb_subCategory.addItem(subCategoryList.get(i).getSub_category_name());						
					}
					
					
				}
			}
		});
		
		// 최상위 카테고리 불러오기
		getTopCategory();
	}
	
	// DAO를 통해 얻어온 list를 이용하여 콤보박스에 카테고리 값 채우기
	public void getTopCategory() {
		List<TopCategory> list = topCategoryDAO.selectAll();
		
		// 안내문구를 담은 더미 카테고리를 콤보박스에 추가
		TopCategory dummyCategory = new TopCategory();
		dummyCategory.setTop_category_name("최상위 카테고리를 선택하세요");
		dummyCategory.setTop_category_id(0);
		cb_topCategory.addItem(dummyCategory);
		
		for (int i=0; i<list.size(); i++) {
			cb_topCategory.addItem(list.get(i));
			
		}
	}
}
