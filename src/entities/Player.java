package entities;

public class Player {

	private String name = "";
	private Account account;
	
	public Player(){
		account = new Account(30000);
	}
	
	public boolean adjustPoints(int dif){
		return account.adjustBalance(dif);
	}
	
	public boolean setBalance(int balance){
		return account.setBalance(balance);
	}
	
	public int getBalance(){
		return account.getBalance();
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
}
