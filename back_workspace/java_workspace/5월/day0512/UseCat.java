/* Cat의 실행부 */

class UseCat 
{
	public static void main(String[] args) 
	{
		new Cat(); // Cat클래스에 생성자가 없어도 컴파일러에 의한 디폴트 생성자로 인해 new 연산자 뒤에서 에러가 나지 않음
	}
}