package com.ssg.ioproject;

public class ExceptionTest {
	/*
	 * java가 예외처리를 강요하는 체크예외 vs 언체크 예외
	 * 공통점: 코드로 해결할 수 있는 에러
	 * 체크예외: 중요한 것들만 강요
	 * 언체크예외: 강요 X. 비정상 종료되지 않으려면 적극적으로 예외처리가 필요
	 * */
	
	public static void main(String[] args) {
		
		// 직접 만든 예외를 일부러 일으켜보기
		try {
			throw new MyArrayException("배열 관련 에러 발생"); // 출력: Exception in thread "main" com.ssg.ioproject.MyArrayException: 배열 관련 에러 발생			
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("도달");
		
		/*
		int[] arr = new int[3];
		
		
		try {
			arr[0] = 1;
			arr[1] = 3;
			arr[2] = 3;		
			arr[3] = 3; // ArrayIndexOutOfBoundsException 비정상종료 발생			
		} catch (MyArrayException e) {
			System.out.println(e.getMessage());
		}			  
		 */

	}
}