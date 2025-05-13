/*
객체지향 언어의 장점
1. 캡슐화
2. 상속
3. 추상화
*/

package use;

public class Account{
	private String num = "010-0100-0100";
	private String bank = "하나은행";
	private String owner = "Adams";
	private int balance = 500000;
	
	// 클래스 작성 시 데이터를 보호하고 이 보호된 데이터를 외부에서 사용하게 하려면, 공개된 메서드를 제공해줘야 함
	// 이러한 클래스 정의 기법을 은닉화, 캡슐화(encapsulation)이라고 함
	
	// 데이터 읽기(getter), 쓰기(setter)가 가능하게 하는 메서드 정의
	public String getNum(){
		return this.num;
	}
	public void setNum(String num){
		this.num = num;
	}	
	
	public String getBank(){
		return this.bank;
	}
	public void setBank(String bank){
		this.bank = bank;
	}
	
	public String getOwner(){
		return this.owner;
	}
	public void setOwner(String owner){
		this.owner = owner;
	}
	
	public int getBalance(){
		return this.balance;
	}
	public void setBalance(int balance){
		this.balance = balance;
	}
}