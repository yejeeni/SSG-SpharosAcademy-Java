package com.ssg.shop.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


/**
 * 이미지와 관련된 공통 기능을 제공하는 유틸 클래스
 */
public class ImageUtil {
	
	/**
	 * 클래스패스로부터 이미지를 반환하는 메서드
	 * @param filename
	 * @param width
	 * @param height
	 * @return
	 */
	public Image getImage(String filename, int width, int height) {
		// 패키지 경로로부터 이미지 얻어오기
		// url로 이미지 얻어오기
		URL url = this.getClass().getClassLoader().getResource(filename);
		Image image = null;
		try {
			BufferedImage bufferedImage = ImageIO.read(url);
			image = bufferedImage.getScaledInstance(width, height,	Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}
	
	/**
	 * 클래스패스로부터 아이콘을 반환하는 메서드
	 */
}
