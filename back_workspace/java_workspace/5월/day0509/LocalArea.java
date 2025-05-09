class LocalArea{
	int k;
	
	// 자바의 생성자는 클래스명과 동일해야 함
	public LocalArea(){
		k = 9;
	}
		
	{
		// 인스턴스 초기화 영역은 해당 클래스로부터 인스턴스를 생성할 때마다 실행되는 영역
		// new LocalArea() 때 이 영역을 건들게 됨
		// 생성자가 이미 객체의 변수들을 초기화하는 역할을 하기 때문에 실전에서는 거의 생성자로 초기화
		System.out.println("인스턴스 생성");
		k = 7;
	}
	public static void main(String[] args){
		new LocalArea();
		new LocalArea();
		new LocalArea();
		
		// int x = 8;
		{
			int x = 5;
		}
		// x = 7;
	}
}
