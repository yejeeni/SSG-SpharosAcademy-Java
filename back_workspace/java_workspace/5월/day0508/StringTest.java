class StringTest{
	public static void main(String[] args){
		// 모든 프로그래밍 언어에서 사용할 수 있는 기본 자료형은 문자, 숫자, 논리값. 이 중 문자는 문자 1개를 말하므로, 문자열을 표현하려면 배열을 사용해야 한다. 
		// 이 불편을 해소하기 위해 프로그래밍 언어 내부에서는 배열로 처리되지만, 편의를 위해 문자열에 대해서는 객체자료형으로 지원한다.
		
		// new를 명시하지 않아도 내부적으로 문자열 객체를 생성시키는 것을 암시적, 묵시적(implicit) 생성법이라고 함
		// "korea" 문자열이 중복 없이 힙 영역의 상수풀에 만들어지고, s1, s2 모두 같은 "korea"를 가리킴 
		String s1 = "korea";
		String s2 = "korea";
		System.out.println(s1 == s2); // 주소값 비교 true
		
		// new를 명시하여 객체의 생성법을 그대로 따르는 문자열 생성법을 명시적(explicit) 생성법이라고 함
		// 힙 영역에 "hello" 문자열 객체를 각각 생성
		String x1 = new String("hello");
		String x2 = new String("hello");
		System.out.println(x1 == x2); // 주소값 비교 false
	}
}