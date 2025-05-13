package use;

public class Wheel{
	String brand = "한국타이어";
	int price = 1700000;
	
	public void roll(){
		System.out.println("바퀴굴러가유");	
	}
	
	public String getBrand(){
		return this.brand;	
	}
}