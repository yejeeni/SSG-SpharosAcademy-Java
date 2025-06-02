package collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;

public class SetTest {
	public static void main(String[] args) {
		// 컬렉션 프레임워크가 지원하는 순서 없는 객체 중 하나인 Set
		Set<String> set = new HashSet<>();
		
		set.add("A");
		set.add("B");
		set.add("C");
		set.add("D");
		
		// 순서 없는 Set은 크기는 알지만 직접적으로 for문 수행 불가
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String value = iterator.next(); // 현재 위치에서 다음 요소로 접근
			System.out.println(value);
		}
	}
}
