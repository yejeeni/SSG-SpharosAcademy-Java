package animal;

public class Duck extends Bird{
	String name = "오리";
	
	public void fly(){
		System.out.println("오리 날다");
		// super.fly(); // 이렇게 super로 부모의 것을 사용하는 것 말고는 부모의 메서드를 사용할 수는 없다
	}
}