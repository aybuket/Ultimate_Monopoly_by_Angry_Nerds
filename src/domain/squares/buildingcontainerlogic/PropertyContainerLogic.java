package domain.squares.buildingcontainerlogic;

import java.util.ArrayList;

import domain.board.Building;
import domain.board.BuildingPriceFactory;
import domain.board.Building.BuildingType;
import domain.game.MonopolyTheGame;
import domain.squares.Square;

public class PropertyContainerLogic extends BuildingContainer implements BuildingContainerLogic {

	Building house,hotel,skyscraper;
	public PropertyContainerLogic(int i) {
		super(i);
	}

	@Override
	public void build() {
		// 0th House, 1st Hotel, 2nd Skyscraper

		Square currentSquare = MonopolyTheGame.getInstance().getBoard().getCurrentSquare(this.getIndex());
		int price = BuildingPriceFactory.getPrice(currentSquare.getColorGroup());
		//Building house = this.getContainer().get(0);
		//Building hotel = this.getContainer().get(1);
		//Building skyscraper = this.getContainer().get(2);

		if (house == null) {
			this.addBuilding(new Building(BuildingType.HOUSE, price));
			MonopolyTheGame.getInstance().getActivePlayer().decBalance(price);
			house = this.getContainer().get(0);
			System.out.println("House created");
		} else if (house.getLevel() < 4) {
			MonopolyTheGame.getInstance().getActivePlayer().decBalance(price);
			house.incLevel();
		} else if (hotel == null) {
			this.addBuilding(new Building(BuildingType.HOTEL, price));
			hotel = this.getContainer().get(1);
			MonopolyTheGame.getInstance().getActivePlayer().decBalance(price);
			System.out.println("Hotel created");
		} else if (skyscraper == null) {
			this.addBuilding(new Building(BuildingType.SKYSCRAPER, price));
			skyscraper = this.getContainer().get(2);
			MonopolyTheGame.getInstance().getActivePlayer().decBalance(price);
			System.out.println("sky created");
			setEnabler(false);
		}

	}

	@Override
	public void sell() {
		ArrayList<Building> container = this.getContainer();
		int size = container.size();
		if(size == 3) {
			container.remove(0);
			System.out.println("Skyscraper is destroyed");
			setEnabler(true);
		}else if(size == 2) {
			container.remove(0);
			System.out.println("Hotel is destroyed");
		}else {
			if(container.get(0).getLevel()==1){
				container.remove(0);
				System.out.println("You don't have any house anymore");
			}else {
				container.get(0).decLevel();
				System.out.println("House "+(container.get(0).getLevel()+1)+" is destroyed.");
			}
		}
	}

}
