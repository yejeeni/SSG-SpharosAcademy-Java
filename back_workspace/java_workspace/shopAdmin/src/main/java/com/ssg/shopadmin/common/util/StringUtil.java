package com.ssg.shopadmin.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {

	public static String getSecuredPass(String str) {
		
		StringBuffer stringBuffer = new StringBuffer(); // String의 불변 특징을 없앤 객체
		String passString = str;
		try {
			// 사용할 암호화 지정
			MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
			
			// 문자열을 바이트로 잘게 쪼갬
			byte[] hash = mDigest.digest(passString.getBytes("UTF-8"));
			

			// 잘게 쪼갠 바이트를 16진수 문자열로 반환
			for (int i=0; i<hash.length; i++) {
				// byte 데이터를 16진수로 변경하는 과정에서, byte값 안에 음수가 존재할 경우 byte 데이터형이 int형으로 변경되면서 부호비트가 자동으로 생성되어버림
				// 부호비트는 암호화에 사용되지 않으므로 제거해야 함
				// 16진수의 경우 0xff를 and하여 처리
				String hex = Integer.toHexString(0xff & hash[i]);
//				System.out.println(hex);
				
				if (hex.length() < 2) {
					stringBuffer.append("0"); // 한자리수일 경우 두자리로 늘려줘야 함
				}
				stringBuffer.append(hex); // 스트링 누적
			}
//			System.out.println(stringBuffer.toString()); // StringBuffer는 String이 아니므로 형변환해야 함
			
		} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return stringBuffer.toString();
	}

}
