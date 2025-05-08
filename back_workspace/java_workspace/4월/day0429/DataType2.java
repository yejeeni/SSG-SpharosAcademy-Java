class Datatype2
{
	public static void main(String[] args) 
	{
		/*
		문자: char - 한 문자를 담을 수 있음. ex) char x = 'a';
		숫자:  정수 byte < short < int < long
						1			2		  4		8
				 실수	    				float < double
											  4	    8
		논리: boolean 1
				다른 언어와 달리 true=1, false=0으로 적용되지 않음
		*/
		
		byte byte1 = 5; 
		short short1 = 7;
		byte byte2 = 8;
		
		byte1 = byte2; // 대입 연산뿐만 아니라, 일반적으로 연산자가 동작하기 위해서는 연산 대상이 되는 데이터는 자료형이 동일해야 한다.
		short1 = byte1; // 연산 대상이 되는 피연산자들이 동일하지 않기 때문에 java 컴파일러가 자료형을 동일하게 변환시키는 자동 형변환이 발생
								 // 자동 형변환의 조건: 같은 종류의 자료형이고, 작은 자료형을 큰 자료형에 대입
		byte1 = (byte)short1; // 개발자가 데이터의 손실을 감안해서라도 '같은 종류'의 원하는 자료형으로 변환하는 것을 강제 형변환이라고 함.
										  // 이때 사용되는 () 소괄호를 cast 연산자라고 함
		char c = 'B'; // 컴파일 타임에 java 컴파일러가 유니코드에서 문자를 찾음
		char d = '가';
		char k = 96; // 문자의 유니코드를 직접 넣어준 것
		// char m = -30; // char형은 사실상 숫자형은 맞으나, 여기 담을 수 있는 수는 아스키 포함 유니코드를 담기 위한 용도. 키코드 체계에서는 음수 지원 X
		
		short1 = (short)k;
	}
}