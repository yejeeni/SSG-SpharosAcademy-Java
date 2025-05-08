class Account{
	String bank = "하나은행";
	String owner = "Adams";
	int money = 1000;
	
	public void deposit(int m){
		this.money += m;
		System.out.println(m+"원을 입금하여 총액은 "+this.money);
	}
	
	public void withdraw(int m){
		this.money -= m;
	}
}