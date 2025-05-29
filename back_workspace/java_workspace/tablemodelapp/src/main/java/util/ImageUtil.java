package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * 이미지와 관련된 유용한 기능을 제공하는 클래스
 */
public class ImageUtil {
	
	/**
	 * url 이미지를 아이콘으로 반환하는 메서드
	 * @param filename 아이콘 파일명 (확장자까지)
	 * @param width
	 * @param height
	 * @return
	 */
	public Icon getIcon(String filename, int width, int height) {
		Class class1 = this.getClass();
		
		// 패키지 안에 들어있는 이미지 자원의 이름을 명시하면, url을 반환
		URL url = class1.getClassLoader().getResource(filename);
		
		// ImangeIcon은 크기를 지정할 수 없어, 크기 조절이 가능한 이미지를 얻어오기 위해 BufferedImage 이용
		// Toolkit을 통해 Image 객체는 픽셀 정보까지 접근할 수 없기 때문에 이용할 가치가 있음. 회사의 이미지로고의 워터마크 처리 등 보다 기술적인 이미지 제어를 하고싶은 경우, BufferedImage를 이용해야 함
		BufferedImage bufferedImage = null;
		Image image = null;
		ImageIcon icon = null;
		
		try {
			bufferedImage = ImageIO.read(url);
			image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			icon = new ImageIcon(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return icon;
	}
	
}
