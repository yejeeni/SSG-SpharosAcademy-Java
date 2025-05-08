class StringTest2 
{
	public static void main(String[] args) 
	{
		// String은 불변, 즉 변경될 수 없음 (immutable)
		String x = "korea";
		for (int i=1;i<=100;i++){ // 반복문을 수행할 때마다 문자열이 새로 만들어짐
			x=x+i;
			System.out.println(x);
		}
		
		// x = "korea fighting";	
	}
}
