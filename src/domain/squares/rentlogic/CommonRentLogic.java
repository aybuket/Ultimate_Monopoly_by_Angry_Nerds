package domain.squares.rentlogic;

import domain.game.MonopolyTheGame;

public class CommonRentLogic implements RentLogic{
	
	private int[] rent;

	@Override
	public int updateRent(int index) {
		System.out.println("No need to change anything ready to go");
		int buildingLevel = MonopolyTheGame.getInstance().getBoard().getCurrentSquare(index).getLevel();
		return rent[buildingLevel];
	}
	
	public CommonRentLogic(int[] a){
		this.rent = a;
	}
	

}
