/* 수식자 */
package use;

class ModifierStudy{
	public static void main(String[] args) 
	{
		/* java의 수식자의 종류
		1. static(정적인) - 변수에 적용하면 클래스 변수, 메서드에 적용하면 클래스 메서드가 됨
		2. final(마지막) - 변수에 붙이면 변수의 값을 변경할 수 없음, 메서드에 붙이면 자식이 부모의 메서드를 재정의할 수 없음
		3. abstract(추상적인)
		*/		
		
		final int x = 5;
		x = 8;
	}
}
