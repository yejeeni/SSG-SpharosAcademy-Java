/*
비슷한 기능의 메서드임에도 불구하고, 메서드명 중복 금지라는 원칙으로 인해 메서드를 여러 개 만들어야 하는 비효율적인 상황이 생길 수 있음
OOP에서는 기능상 크게 차이가 없는 경우 기존의 메서드명을 중복 정의하는 기법을 지원하는데, 이를 메서드 오버로딩이라고 함
오버로딩 조건
1. 메서드의 이름이 동일
2. 매개변수의 자료형이나 개수를 다르게
*/

class Duck 
{
	public void fly(){
	}
	
	// 오버로딩
	public void fly(int height){
	}
	
	// 매개변수의 이름만 다르기 때문에 오버로딩 X
	// public void fly(int x){
	// }
	
	// 함수명은 같으나 반환형만 다르기 때문에 오버로딩 X
	// public String fly(){
	// }
	
	// 오버로딩
	public void fly(int height,  boolean b){
	}
}