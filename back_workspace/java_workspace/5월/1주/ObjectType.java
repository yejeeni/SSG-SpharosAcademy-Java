class ObjectType{
	public static void main(String[] args){
		// java에서는 자료형이 총 4개가 지원됨 (기본자료형인 문자, 숫자, 논리값 + 객체자료형)
		int x = 5;
		
		// 개발자가 정의한 클래스를 새로운 자료형으로 인정해주므로, 아래 변수 d 앞에 선언해야 하는 자료형은 내가 정의한 Dog
		Dog d = new Dog();
		d.bark();
		
		Airplane airplane = new Airplane();
		airplane.fly();
		
		Account account = new Account();
		account.deposit(100);
	}
}