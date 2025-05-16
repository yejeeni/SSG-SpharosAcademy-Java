package use;

import instrument.Piano;
import instrument.MusicTool;
import instrument.Drum;
import riding.Roller;

public class UseTool {
	public static void main(String[] args) {		
		// 메서드명이 일관적이지 않고, 각 악기가 보유한 메서드가 무엇인지 알 수 없음 -> 가이드라인 필요 -> 추상클래스를 이용
		
		// new MusicTool(); // 추상 클래스는 new를 통해 인스턴스화할 수 없음
		// Piano piano = new Piano();
		// piano.setVolume();
		
		// MusicTool p = new Piano();
		// p.setVolume();
		
		// 인터페이스도 is a 관계로 인정받아, 형변환이 가능
		Roller p2 = new Piano();
		p2.roll();
		
		MusicTool d = new Drum();
		d.setVolume();
		
	}
}
