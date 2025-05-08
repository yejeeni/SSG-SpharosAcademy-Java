class DataType{
	public static void main(String[] args){
		boolean b = true;
		int x = 3;
		short y = 9;
		char c = 'K';
		
		int r1 = b+x; // 서로 다른 자료형이므로 연산 불가
		short r2 = y+c; // x는 정수, c는 문자이므로 연산 불가
		int r3 = x+c; //  c는 int로 형변환 되므로 정상 컴파일
		boolean r4 = b+c; // c는 boolean형으로 변환되지 않음. 연산 불가
	}
}