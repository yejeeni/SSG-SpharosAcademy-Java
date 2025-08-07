package mall.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mall.exception.PasswordEncryptException;

@Slf4j
@Component
public class PasswordUtil {

	/**
	 * 솔트 생성
	 */
	public static String generateSalt() {
		// 보안에 사용할 난수 생성기
		SecureRandom sr = new SecureRandom(); // 보안용 난수 생성기
		byte[] salt = new byte[16]; // 16바이트 배열
		sr.nextBytes(salt); // 배열을 무작위 바이트값으로 채움
		log.debug("salt 배열값 {}", salt);
		
		// 문자열로 변환
		return Base64.getEncoder().encodeToString(salt);
	}
	
	/**
	 * 솔트와 비번을 조합한 해시 생성
	 */
	public static String hashPassword(String password, String salt) {
		try {
			// 해시 함수 사용 객체. 문자열을 일정 길이의 고정된 해시값으로 바꿔줌
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(salt.getBytes("UTF-8")); // 해시의 대상이 되는 값 salt를 digest에 바이트로 추가
			byte[] hashedByte = messageDigest.digest(password.getBytes("UTF-8")); // md에 누적된 데이터를 해시화
			
			return Base64.getEncoder().encodeToString(hashedByte); // 해시를 문자로 반환
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new PasswordEncryptException("암호화 실패", e);
		} 
	}
	
	public static void main(String[] args) {
		String salt = generateSalt();
		log.debug("문자열로 변경한 salt {}",salt);
		
		String password = "pw1234";
		String result = hashPassword(password, salt);
		log.debug("암호화 결과 {}", result);
	}
}
