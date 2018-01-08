package domain.game;

import java.util.ArrayList;

public class Stock implements Auctionable{

	private String name;
	private int price;
	private ArrayList<Player> owners = new ArrayList<Player>();
	
	

	public int getPrice() {
		return price;
	}

	public ArrayList<Player> getOwners() {
		return owners;
	}
	
	public void addOwner(Player p){
		owners.add(p);
	}

	public String getName() {
		return name;
	}
	
	public Stock(String n, int p){
		this.name = n;
		this.price = p;
	}
	
	public String toString() {
		String str = "Name: "+getName()+"\n"
					+"Price: "+getPrice()+"\n"
					+"Owners: "+getOwners().toString();
	return str;
	}

	@Override
	public void setOwnerIndex(int ownerIndex) {
		Player pl = MonopolyTheGame.getInstance().getPlayers()[ownerIndex];
		addOwner(pl);
		
	}

	@Override
	public int getIndex() {
		return -10;
	}

}
