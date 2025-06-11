package com.ssg.shopadmin.product.view;

import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JDialog;

import com.ssg.common.config.Config;
import com.ssg.shopadmin.common.util.FileUtil;

public class UploadDialog extends JDialog{
	ProductPage page = null;
	
	public UploadDialog(ProductPage page) {
		this.page = page;
		
		JDialog dialog = new JDialog(this, "업로드 정보", true);
		dialog.setLocationRelativeTo(page); // page를 기준으로 상대위치를 잡음
		dialog.setSize(700, 600);
		dialog.setLayout(new FlowLayout());
		
		// 커스텀된 바 테스트
		for (int i=0; i<page.files.length; i++) {
			// 어디에 저장할지 디렉토리를 결정
			File destFile = FileUtil.createFile(Config.PRODUCT_IMAGE_PATH	, FileUtil.getExt(page.files[i].getName()));
			
			MyBar bar = new MyBar(page.files[i], destFile);
			Thread thread = new Thread(bar); // bar의 run() 메서드가 호출
			thread.start();
			
			dialog.add(bar);
		}
		
		dialog.setVisible(true);
	}
}
