package mall.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import mall.domain.Product;
import mall.domain.ProductImg;
import mall.exception.UploadException;

/**
 * 컨트롤러에서 업로드라는 모델 영역의 업무를 수행하지 않도록, 업로드 수행을 전담하는 모델 객체 FileManager 클래스
 * 
 * dao, service, controller도 아니므로 스프링의 기본 컴포넌트 이외의 컴포넌트로 등록
 */

@Slf4j
@Component // ComponentScan 대상으로 지정
public class FileManager {
	public void save(Product product, String savePath) throws UploadException {
		// MultipartFile 변수와 html 이름이 동일하면 매핑됨
		MultipartFile[] photo = product.getPhoto();
		log.debug("업로드 파일 수 " + photo.length);

		List<ProductImg> productImgs = new ArrayList<>();
		try {
			for (int i = 0; i < photo.length; i++) {
				// 파일의 확장자 추출
				log.debug(photo[i].getOriginalFilename());
				String ori = (photo[i].getOriginalFilename());
				String ext = ori.substring(ori.lastIndexOf(".") + 1, ori.length());

				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}
				// 파일명 생성
				long time = System.currentTimeMillis();
				String filename = time + "." + ext;
				
				// 생성한 파일명을 데이터베이스에 저장하기 위해 Product의 imgList에 보관
				ProductImg productImg = new ProductImg();
				productImg.setFilename(filename);
				productImgs.add(productImg);
				
				product.setProductImgList(productImgs);

				File file = new File(savePath + File.separator + filename);
				log.debug("업로드 이미지 생성 경로 " + savePath);
				photo[i].transferTo(file); // 메모리상의 파일 정보가 실제 디스크상으로 존재하게 되는 시점
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new UploadException("파일 업로드 실패", e);
		}
	}

	public void save2(MultipartFile photo, String savePath, String filename) throws UploadException {

		// 파일의 확장자 얻어오기
		String ext = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
		log.debug("확장자 " + ext);

		// 파일의 이름은 해시값, 현재 날짜, db index pk값 등으로 설정이 가능
		long time = System.currentTimeMillis();
		String newName = time + "." + ext;

		File file = new File(savePath, newName);
		try {
			photo.transferTo(file);
			log.debug(file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
			throw new UploadException("파일 저장 실패", e);
		}
	}
}
