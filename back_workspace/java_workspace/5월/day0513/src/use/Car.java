/* 자동차 정의
조건1 핸들 있어야 함
조건2 바퀴 있어야 함
조건3 문 있어야 함
*/

package use;

class Car{
	int price;
	String name;
	
	// 객체가 다른 객체를 멤버 변수로 보유한 관계를 has a 관계라고 함
	Handle handle; // has a 관계. Car has a Handle.
	Wheel wheel; // Car has a wheel 
	Door door; // Car has a Door
	
	// 생성자는 사물을 태어나게 하는 시점에 초기화에 관여하므로, has a 관계에 있는 객체의 인스턴스를 생성할 때 매우 유용함
	public Car(){
		price = 5000;
		name = "K9";
		handle = new Handle();
		wheel = new Wheel();
		door = new Door();
	}
	
}