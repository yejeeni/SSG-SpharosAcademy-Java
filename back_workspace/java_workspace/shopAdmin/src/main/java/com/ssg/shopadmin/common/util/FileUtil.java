package com.ssg.shopadmin.common.util;

import java.io.File;

/**
 * 파일 관련 공통 기능 정리
 */
public class FileUtil {
	
	/**
	 * 파일 경로를 반환하는 메서드
	 * @param path
	 * @return
	 */
	public static String getExt(String path) {
		return path.substring(path.lastIndexOf(".")+1, path.length());
	}
	
	/**
	 * 파일명을 시간기준 이름으로 바꿔 새로운 파일을 생성하는 메서드
	 * @param targetDirectory
	 * @param ext
	 * @return
	 */
	public static File createFile(String targetDirectory, String ext) {
		long time = System.currentTimeMillis(); // 현재 시간 반환
		
		String filename = targetDirectory + File.separator + time + "." + ext; // 경로/time.ext
		
		return new File(filename);
	}
}
