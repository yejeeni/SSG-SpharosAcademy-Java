/* JVM은 OS로부터 메모리를 할당받으면, 해당 메모리를 3개의 영역으로 나누어서 관리한다.
1️. Method (메서드 영역)  
- 클래스의 바이트 코드, static 변수, 상수 풀, 메서드 코드 등이 저장
- 클래스가 처음 로드될 때 (java.exe 실행 시) 메서드 영역에 올라감

2️. Stack (스택 영역)  
- 각 스레드마다 별도로 할당
- 메서드 호출 시 프레임이 생성되고, 그 안에 지역변수, 매개변수가 저장

3️. Heap (힙 영역)  
- new로 생성된 인스턴스(객체)가 저장
- 멤버 변수도 힙에 저장되지만, 멤버 메서드는 메서드 영역에 이미 올라가 있음
*/

class Cat{
	String name = "tomcat"; // 멤버 변수 → heap에 저장 (객체 생성 후)
	int age = 5; // 멤버 변수 → heap에 저장 (객체 생성 후)
	
	public int getAge(){ // 멤버 메서드 → method에 저장
		return age;	
	}
	
	public static void main(String[] args){ // args도 null로 stack에 저장 (main 메서드의 지역변수)
		int x = 7; // stack에 저장 (main 메서드의 지역변수)
		Cat c = new Cat(); // c → stack (참조(레퍼런스) 변수), new Cat() → heap (객체)
		System.out.println(c.getAge());
		
		/*
		// java.exe 호출 시 main() 메서드는 자동으로 호출되는데, 이때 main() 메서드의 매개변수인 String 배열의 값을 넘길 수 있음
		// cmd > java 문자열 문자열2
		System.out.println("넘긴 배열의 수는"+args.length);
		for(int i=0; i<args.length; i++){
			System.out.println(args[i]);	
			*/
		
		}
	}
}
		