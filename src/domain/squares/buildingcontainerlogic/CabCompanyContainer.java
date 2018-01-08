package domain.squares.buildingcontainerlogic;

import domain.board.Building;
import domain.board.Building.BuildingType;
import domain.game.MonopolyTheGame;

public class CabCompanyContainer extends BuildingContainer implements BuildingContainerLogic{

	public CabCompanyContainer(int i) {
		super(i);
		setEnabler(true);
	}

	@Override
	public void build() {
		// TODO This might cause a bug in the future since it directly sets a new rent
		if(this.getContainer().isEmpty()){
			this.addBuilding(new Building(BuildingType.CABSTAND, 150));
			MonopolyTheGame.getInstance().getActivePlayer().decBalance(150);
			setEnabler(false);
			//currentSquare.setRent(currentSquare.getRent()*2);
		}else {
			System.out.println("You cannot bould multiple cab stands on a cab company");
		}
		
	}

	@Override
	public void sell() {
		this.getContainer().remove(0);
		setEnabler(true);
		MonopolyTheGame.getInstance().getActivePlayer().incBalance(75);
		System.out.println("Cab company is destroyed");
	}

}
