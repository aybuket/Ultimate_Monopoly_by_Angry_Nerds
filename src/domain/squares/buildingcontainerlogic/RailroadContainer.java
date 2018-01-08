package domain.squares.buildingcontainerlogic;

import domain.board.Building;
import domain.board.Building.BuildingType;
import domain.game.MonopolyTheGame;

public class RailroadContainer extends BuildingContainer implements BuildingContainerLogic {

	public RailroadContainer(int i) {
		super(i);
		setEnabler(true);
	}

	@Override
	public void build() {
		// TODO This might cause a bug in the future since it directly sets a new rent
		if (this.getContainer().isEmpty()) {
			this.addBuilding(new Building(BuildingType.TRAINDEPOT, 100));
			MonopolyTheGame.getInstance().getActivePlayer().decBalance(100);
			setEnabler(false);
			//currentSquare.setRent(currentSquare.getRent() * 2);
		} else {
			System.out.println("You cannot bould multiple train depots on a train station");
		}

	}

	@Override
	public void sell() {
		this.getContainer().remove(0);
		setEnabler(true);
		MonopolyTheGame.getInstance().getActivePlayer().incBalance(50);
		System.out.println("Train station is destroyed");
	}

}
