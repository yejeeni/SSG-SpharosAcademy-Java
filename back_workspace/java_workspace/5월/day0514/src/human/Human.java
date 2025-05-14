package human;

// OOP에서는 클래스 정의 시 중복 코드의 재사용을 위해서 상속이라는 클래스 정의법을 사용할 수 있음
// 
// super: 상속관계에서의 부모 객체 <> sub: 자식 객체

class Human{
	private String country;
	int leg = 2;
	
	public void intellecThink(){
		System.out.println("지적사고력");
	}
}
