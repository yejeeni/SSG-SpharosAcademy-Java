package com.ssg.shopadmin.product.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.ssg.common.view.Page;
import com.ssg.shopadmin.AppMain;
import com.ssg.shopadmin.product.model.SubCategory;
import com.ssg.shopadmin.product.model.TopCategory;
import com.ssg.shopadmin.product.repository.ColorDAO;
import com.ssg.shopadmin.product.repository.SizeDAO;
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
	JList t_color;
	JList t_size;
	JScrollPane scroll_color;
	JScrollPane scroll_size;
	JTextField t_introduce;
	JPanel p_priview; // 선택한 상품 이미지의 미리보기
	JLabel la_introduce;
	JTextArea t_detail;
	JButton bt_regist; // 상품 등록
	JButton bt_list; // 상품 목록
	
	TopCategoryDAO topCategoryDAO;
	SubCategoryDAO subCategoryDAO;
	ColorDAO colorDAO;
	SizeDAO sizeDAO;	
	
	JFileChooser jFileChooser;
	
	Image[] images; // 선택한 파일로부터 생성된 이미지 배열
	File[] files; // 이미지 파일 업로드를 위한 파일 배열. io스트림의 대상은 File이기 때문
	
	
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
		t_color = new JList();
		t_size = new JList();
		scroll_color = new JScrollPane(t_color);
		scroll_size = new JScrollPane(t_size);
		
		p_priview = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				if (images != null) {
					// 선택한 파일 수만큼 반복하면서 이미지 그리기
					for (int i=0; i<images.length; i++) {
						g.drawImage(images[i], 5+(i*45), 5, 45, 45, appMain);					
					}
				}
			}
		}; // 추후 익명 내부 클래스로 전환
		t_introduce = new JTextField();
		la_detail = new JLabel();
		t_detail = new JTextArea();
		bt_regist = new JButton("등록");
		bt_list = new JButton("목록보기");
		
		topCategoryDAO = new TopCategoryDAO();
		subCategoryDAO = new SubCategoryDAO();
		colorDAO = new ColorDAO();
		sizeDAO = new SizeDAO();
		
		jFileChooser = new JFileChooser("C:/lecture_workspace/front_workspace/images");
		jFileChooser.setMultiSelectionEnabled(true); // 다중 선택 가능하도록 설정

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
		scroll_color.setPreferredSize(new Dimension(400, 80));
		scroll_size.setPreferredSize(new Dimension(400, 80));
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
		add(scroll_color);
		add(la_size);
		add(scroll_size);
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
					
					getSubCategory(topCategory);
				}
			}
		});
		
		// 최상위 카테고리 불러오기
		getTopCategory();
		
		getColorList();
		getSizeList();
		
		// 파일탐색기 띄우기
		bt_open.addActionListener(e->{
			int result = jFileChooser.showOpenDialog(ProductPage.this);
			
			if (result == jFileChooser.APPROVE_OPTION) {
				preview();				
			}			
		});
		
		// 등록 버튼과 리스너 연결
		bt_regist.addActionListener(e->{
//			upload();
			regist();
		});
	}
	
	public void preview() {
		// 선택한 이미지 파일에 대한 정보
					files = jFileChooser.getSelectedFiles();
					
					if (files.length > 6) {
						JOptionPane.showMessageDialog(this, "이미지는 최대 6개까지만 가능합니다.");
					} else {
						images = new Image[files.length]; // 선택한 이미지 파일의 수와 동일한 이미지 배열 생성
						
						// 파일을 통해 이미지 생성
						for (int i=0; i<files.length; i++) {
							try {
								BufferedImage bufferedImage = ImageIO.read(files[i]);
								images[i] = bufferedImage.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
								
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
					// 그림 다시 그리기
					p_priview.repaint();
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
	
	/**
	 * topCategory의 하위 카테고리를 얻어오는 메서드
	 * @param topCategory
	 */
	public void getSubCategory(TopCategory topCategory) {
		// 하위 카테고리 목록 가져오기
		List<SubCategory> subCategoryList = subCategoryDAO.selectByTop(topCategory);
		
		// 모든 하위 카테고리 콤보 아이템 삭제
		cb_subCategory.removeAllItems();
		
		SubCategory dummyCategory = new SubCategory();
		dummyCategory.setSub_category_name("하위 카테고리를 선택하세요");
		dummyCategory.setSub_category_id(0);
		cb_subCategory.addItem(dummyCategory);
		
		for (int i=0; i<subCategoryList.size(); i++) {
			cb_subCategory.addItem(subCategoryList.get(i).getSub_category_name()); // 여기서 get이 아니라 toString을 오버라이딩해서 사용하심				
		}
	}
	
	public void getColorList() {
		t_color.setListData(new Vector<>(colorDAO.selectAll()));
	}
	
	public void getSizeList() {
		t_size.setListData(new Vector<>(sizeDAO.selectAll()));
	}
	
	/**
	 *  시각적 효과를 위해 각 이미지의 업로드 진행율을 보여주는 메서드
	 */
	public void upload() {
		UploadDialog dialog = new UploadDialog(this);
	}
	
	public void insert() {
		
	}
	
	/**
	 * 이미지 업로드 및 DB insert
	 */
	public void regist() {
		// 양식을 제대로 입력했을 때
		
		// 상위 카테고리 유효성 체크
		if(cb_topCategory.getSelectedIndex() == 0) {
			JOptionPane.showConfirmDialog(this, "상위 카테고리를 선택해주세요.");
		} else if(cb_subCategory.getSelectedIndex() == 0) {
			JOptionPane.showConfirmDialog(this, "하위 카테고리를 선택해주세요.");
		} else if(t_productName.getText().length() < 1) {
			JOptionPane.showConfirmDialog(this, "상품명을 입력해주세요.");
		} else if(t_brand.getText().length() < 1) {
			JOptionPane.showConfirmDialog(this, "브랜드명을 입력해주세요.");
		} else if(t_price.getText().length() < 1){
			JOptionPane.showConfirmDialog(this, "가격을 입력해주세요.");
		} else if(t_discount.getText().length() < 1){
			JOptionPane.showConfirmDialog(this, "할인가를 입력해주세요.");
		} else if(t_color.getMinSelectionIndex() < 0){
			JOptionPane.showConfirmDialog(this, "색상을 선택해주세요.");
		} else if(t_size.getMinSelectionIndex() < 0){
			JOptionPane.showConfirmDialog(this, "사이즈를 선택해주세요.");
		} else if(files.length < 0){
			JOptionPane.showConfirmDialog(this, "상품 이미지를 선택해주세요.");
		} else if(t_introduce.getText().length() < 1){
			JOptionPane.showConfirmDialog(this, "상품 소개를 입력해주세요.");
		} else if(t_detail.getText().length() < 1){
			JOptionPane.showConfirmDialog(this, "상품 상세 내용을 입력해주세요.");
		} else {
			upload();
			insert(); // db에 insert
		}
		
	}
}
