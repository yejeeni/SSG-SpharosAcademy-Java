package use;

import animal.Duck;
import animal.Bird;

class  UseDuck{
	public static void main(String[] args) {
		Duck duck = new Duck();
		duck.name = 9; // Duck의 name
		duck.eat(); // Duck에는 eat() 메서드가 없는데, 스택에서 eat() 프레임이 만들어지면서 d가 바인딩되고, eat() 메서드는 Duck의 메서드가 됨 -> 부모에게 상속받은 메서드 호출
							// Duck 클래스에 eat()가 없지만 Bird로부터 상속받았기 때문에 사용할 수 있음.
							// Duck 인스턴스를 통해 호출해도, Bird의 eat()이 실행됨 (정적 바인딩).

		
		Bird bird = new Bird();
		bird = duck; // bird가 부모, duck이 자식이므로 상위에 있는 부모 bird가 자식을 받을 수 있음. 용량을 가리키는 게 아니라, 범위로 접근하는 상위의 개념
							// 자식 객체(Duck)를 부모 타입(Bird) 변수에 대입하는 것 → 업캐스팅 (자동 형변환 가능)
							// 참조 타입이 Bird이므로 Bird의 멤버만 접근 가능하지만, 실제 객체는 Duck

		
		// duck = bird; // 자식이 상위의 부모를 받을 순 없음 
		duck = (Duck)bird; // 상위 자료형으로 캐스팅하면 받을 수 있음
									// bird가 실제로 Duck 객체일 경우에만 다운캐스팅이 가능함 (명시적 형변환 필요)
									// 만약 bird가 Bird 객체였다면 ClassCastException 발생

		
		/*
		Bird b = new Duck(); // 업캐스팅
		b.name = 8; // b는 Duck이지만, 참조형은 Bird로 있어서 Bird로부터 상속받은 name 필드에 접근
		b.fly(); // 오버라이딩된 Duck의 fly() 실행됨 -> 동적 바인딩
		// Duck 객체를 Bird 타입 변수로 참조 → 업캐스팅 (정상)
		// b.name = 8; → Bird의 name 필드에 접근 가능 (상속된 필드)
		// b.fly(); → Bird 타입이지만, 실제 객체는 Duck이므로 Duck의 오버라이딩된 fly()가 실행됨 (동적 바인딩)

		*/
		
		
	}
}
