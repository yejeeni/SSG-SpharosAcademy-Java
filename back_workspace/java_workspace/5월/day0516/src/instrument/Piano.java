package instrument;

import riding.Roller;
import instrument.MusicTool;

// 최상위 클래스인 MusicTool을 상속받기
// 추상클래스를 상속받는 자식은 반드시 부모의 추상메서드를 구현해야 함
public class Piano extends MusicTool  implements Roller{
						//  is a                           is a
	// 부모의 메서드를 자식이 재정의 -> 오버라이딩
	public void setVolume(){
			System.out.println("피아노 소리 조절");
	}
	
	public void roll(){
		System.out.println("피아노를 타고 감");
	}
}