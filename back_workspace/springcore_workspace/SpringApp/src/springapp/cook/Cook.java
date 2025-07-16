package springapp.cook;

public class Cook {
	Frypan frypan;
	
	public Cook(Frypan pan) {
		this.frypan = pan;
	}
	
	/**
	 * 음식을 만드는 행위의 메서드
	 */
	public void makeFood() {
		frypan.boil();
	}
}
