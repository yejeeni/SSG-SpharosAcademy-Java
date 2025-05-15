public class Bird {
	int age=3;
	public void fly() {
		System.out.println("새가 날아요");
	}	
}
public class Sparrow extends Bird{
    int age=5;
	public void fly() {
		System.out.println("참새가 날아요");
	}	
}
public class Duck extends Bird{	
	public void fly() {
		System.out.println("오리가 날아요");
	}	
}


public class UseBird {
	public static void main(String[] args) {
		Bird bird = new Bird();
		System.out.println(bird.age);//(마)에서 Bird 의 멤버변수 age를 출력하게 되므로 3이 출력된다 -> ㅇㅇ
		bird.fly(); //(가)에서 Bird 의 멤버 메서드가 호출되므로, “새가 날아요” 가 출력된다 ㅇㅇ
		
		bird=new Sparrow();
		bird.fly(); //(나)의 실행 결과는 “새가 날아요” 이다. ㄴㄴ 오버라이딩. 메모리구조로 말하면 fly가 만들어지면서 this에는 b, b는 Sparrow를 가리키는 주소값이 됨
	    
		bird=new Duck();
        bird.fly(); //(다)에서 “오리가 날아요”가 출력된다.	 ㅇㅇ
	    
		Bird b=new Bird();
	    Duck d=(Duck)b; // (라)에서 Bird 와 Duck은 같은 자료형이므로 다운캐스팅에 아무런 문제가 없으나, 실행 시점에는 에러가 발생할 것이다.
								   // 컴파일시점 에러 X 문법상 에러가 없음. 그러나 실행시점에 에러 발생. 메모리에 Duck 인스턴스가 없어서 (??)
	}
	
	// bird 변수는 자료형이 Bird 형 이지만 (가)에서는 그냥 새로,  (나)에서는 참새로 동작했으며 (다)에서는 오리로 동작했으므로, 
	// 결국 같은 자료형이 여러 가지 객체의 모습으로 동작했다고 볼 수 있으며, 
	// 이렇게 상위 객체의 자료형으로 자식객체의 메서드를 호출할 수 있는 기법을 가리켜 OOP 언어는 다형성(Polymorphism) 이라 한다.
}
