package use; // 패키지를 선언하면 javac -d 옵션 사용 시, 선언한 패키지에 해당하는 디렉토리를 자동 생성

import animal.Dog; // classpath 환경변수를 기준으로, 그 밑의 animal 밑의 Dog.class를 import
class UseDog {
	public static void main(String[] args) {
		Dog d = new Dog();
		d.bark();
	}
}
