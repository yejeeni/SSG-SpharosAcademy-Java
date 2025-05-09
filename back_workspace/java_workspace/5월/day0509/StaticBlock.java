class StaticBlock{
	static int x;
	
	// static 초기화 블럭. 실행부보다 먼저 초기화 블럭이 실행됨
	static{
		x = 8;
		System.out.println("A");
	}
	
	public static void main(String[] args){
		System.out.println("B");
	}
}
