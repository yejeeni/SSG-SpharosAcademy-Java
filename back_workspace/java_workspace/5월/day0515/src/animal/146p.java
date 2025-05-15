public class Fish {
	String name="난 물고기";
      private int age=2; //(마)
}

public class Shark extends Fish{
	String name="난 상어";
}

public class UseAnimal {
	public static void main(String[] args) {
		Fish f = new Fish();
		Shark s = new Shark();
		f = s; //(가)에서 f는 Fish 형 객체를 대입해야 함에도, Shark 형인 s 를 대입하였으므로 자료형에 대한 컴파일 에러가 발생할 것이다.
				 // -> 문제 없음
				 
		s = f; //(나)에서 Shark는 Fish 형과 같은 종류로 인정되므로,  s에 f 를 대입하는 것은 아무런 문제가 없다.
				 // -> 문제 있음 (형변환을 안함)
				// (나)에서 코드를  s = (Shark)f 로 다운 캐스팅 할 경우 정상적으로 컴파일 될 것이다
				// -> 맞음
		
		Fish fish = new Shark(); //(다)는 상속 관계에 있는 Shark 자식형을 부모형인 Fish로 가리키고 있으므로 아무런 문제 없이 컴파일 될 것이다.
											 // -> 맞음. 근데 Fish의 인스턴스는 메모리에 올라가진 않음.
											 
		Shark shark = new Fish(); //(라)
	}
	
	// 부모 자료형에서 자식 자료형으로 다운캐스팅 할 경우, 데이터의 손실이 발생한다 -> 아님. 상속간 캐스팅은 데이터 범위의 문제 X
	// Shar의 인스턴스가 생성될 때, (마)는 그 안에 포함되지 못한다. -> 아님. 들어가지만, private이므로 사용할 수 없을 뿐
}