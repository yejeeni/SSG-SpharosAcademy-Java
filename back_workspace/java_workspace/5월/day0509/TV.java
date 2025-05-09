class  TV{
	String color="black";
	String company="LG";
	int weight=45;

	public void setColor(String c){
		color=c;
	}

	public static void main(String[] args){
		weight=30;  (가)  
		new TV(); (나) 
		String color="blue"; (다)
		System.out.println(color);  (라) 
		int w=new TV().weight;  (마)
		System.out.println("무게는 "+w); (바)
	}