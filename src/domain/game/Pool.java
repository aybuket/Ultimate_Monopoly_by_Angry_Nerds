package domain.game;

/**
 * This class is for the pool, the space for money transactions which is placed in the middle of the board 
 * @author AngryNerds
 */
public class Pool{
	
	private static Pool instance = null;
	private int balance;
	/**
	 * @effects returns the pool instance or creates it does not exist
	 */
	public static Pool getInstance() {
		if(instance==null) {
			instance = new Pool();
		}
		return instance;
	}

	private Pool() {
		balance = 0;
	}
	/**
	 * @param amount is the amount that is to be added into the pool
	 * @modifies the money amount in the pool by adding it
	 */
	public void putMoney(int amount) {
		if(amount>0){			
			balance += amount;
		}
	}
	/**
	 * @modifies the money amount in the pool by taking it all
	 */
	public int takeAllMoney() {
		int amount = balance;
		balance = 0;
		return amount;
	}
	/**
	 * @modifies the money amount in the pool by taking the half of it
	 * @effects returns the new money amount
	 */
	public int takeHalfMoney() {
		int amount = balance/2;
		balance = balance-balance/2;
		return amount;
	}

	public boolean repOK(){
		return balance>=0;
	}
	
	public int getBalance(){
		return balance;
	}
	public String toString() {
		String str = "Balance: "+getBalance()+"\n";
		return str;
	}

}
