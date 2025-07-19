package mall.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import mall.exception.UploadException;

/**
 * 컨트롤러에서 업로드라는 모델 영역의 업무를 수행하지 않도록, 업로드 수행을 전담하는 모델 객체 FileManager 클래스
 * 
 * dao, service, controller도 아니므로 스프링의 기본 컴포넌트 이외의 컴포넌트로 등록
 */

@Slf4j
@Component // ComponentScan 대상으로 지정
public class FileManager {
	public void save(MultipartFile photo, String savePath, String filename) throws UploadException {

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
