package collection;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
	public static void main(String[] args) {
		// 자바의 컬렉션 프레임워크는 java.util 패키지에서 지원하고, 그 중 순서 있는 집합을 처리하는 데 대표적인 List에 대해 알아보기
		// list, 배열		공통점 - 순서를 가지며 인덱스로 접근 가능
		// 					차이점 - 배열은 생성 시 크기를 명시해야 하고, 기본 자료형도 담을 수 있음. 
		// 							 컬렉션은 오직 객체만을 대상으로 함
		// 컬렉션 프레임워크는 최상위 인터페이스들의 메서드를 주로 사용하므로, 하위에 어떤 구현 객체들을 사용하더라도 메서드 사용이 일관성있음. 담을 때는 거의 add, 길이는 거의 size
		
		//  자료형을 명시하면 컴파일 타임에 자료형을 체크할 수 있음. 이를 제너릭 타입이라고 함
		List<String> list = new ArrayList<>();
		list.add("apple");
		list.add("banana");

		// 고전적 반복문
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		// improved for
		for (Object obj : list) {
			System.out.println(obj);
		}
		
	}
}
