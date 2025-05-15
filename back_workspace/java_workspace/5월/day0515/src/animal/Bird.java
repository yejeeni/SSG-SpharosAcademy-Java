package animal;


public class Bird{
	String name = "부모새";
	int age = 5;
	
	/*
	public Bird(){
		super(); // 최상위 부모클래스 Object()를 초기화		
	}
	*/
	
	public void fly(){
		System.out.println("부모새가 날아요");
	}
	
	public void eat(){
		System.out.println("밥먹어요");
	}
}