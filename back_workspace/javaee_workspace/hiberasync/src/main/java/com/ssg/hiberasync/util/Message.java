package com.ssg.hiberasync.util;

import lombok.Data;

/**
 * 응답 메시지를 json으로 주고받기 위한 클래스. 전송 시 json 문자열로 변경함
 */
@Data
public class Message {
	private String result; // success - 200, fail - 에러
	private String msg; // 보내고 싶은 처리 메시지
}
