package domain.cards;

import domain.cards.interaction.CardInteraction;
import domain.cards.logic.CardLogic;

public class Card {

	private String name;
	private String type;
	private boolean keepable = false;
	//Strategy Object
	private CardLogic crd;
	// Constructor
	public Card(String n, String type,boolean k, CardLogic c) {
		this.name = n;
		this.type = type;
		this.keepable = k;
		this.crd = c;
	}
	
	public void getChoosenAction() {
		
	}
	
	public String getType() {
		return type;
	}

	public void execute(){
		crd.activate();
	}

	public String getName() {
		return name;
	} 

	public boolean isKeepable() {
		return keepable;
	}
	
	public void setColor(String color) {
		crd.interaction.setChosenAction(color);
	}
	
	public String getColor() {
		return crd.interaction.getChosenAction();
	}
	
	public String toString() {
		String str = "Name: "+getName()+"\n"
					+"Is Keepable: "+isKeepable()+"\n"
					+"Card Logic: "+crd.toString()+"\n";
		return str;
	}
}
