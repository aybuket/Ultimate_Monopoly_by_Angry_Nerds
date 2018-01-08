package domain.board;

public class Building {
	//type of the building
	public enum BuildingType {
		HOUSE,
	    HOTEL,
	    SKYSCRAPER,
	    CABSTAND,
	    TRAINDEPOT;
	};
	
	public BuildingType type;
	private int price;
	//the rents are going to be calculated according to this info
	private int level = 1;	
	
	public Building (BuildingType type, int price) {
		this.type = type;
		setPrice(price);
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getLevel() {
		return level;
	}
	public void incLevel() {
		this.level++;
	}
	//for mortgage
	public void decLevel() {
		this.level--;
	}
	
	public String toString() {
		String str = "Type: "+type+"\n"
					+"Price: "+getPrice()+"\n"
					+"Level: "+getLevel()+"\n";
		return str;
	}
	
	
}
