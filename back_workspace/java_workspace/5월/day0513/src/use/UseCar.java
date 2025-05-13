package use;

public class UseCar{
	public static void main(String[] args) {
		Car car = new Car();
		
		String handleColor = car.handle.getColor();
		System.out.println(handleColor);
		
		String wheelBrand = car.wheel.getBrand();
		System.out.println(wheelBrand);
		
		car.door.open();
	}
}