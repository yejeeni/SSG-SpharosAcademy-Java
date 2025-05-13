package use;

class AccountHack{
	public static void main(String[] args){
		Account acc = new Account();
		acc.setBalance(100);
		
		System.out.println(acc.getBalance());
	}
}