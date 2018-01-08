package domain.squares.rentlogic;

import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.squares.Square;

public class UtilityRentLogic implements RentLogic{

	@Override
	public int updateRent(int index) {
		Square currentSqr = MonopolyTheGame.getInstance().getBoard().getCurrentSquare(index);
		Player owner = MonopolyTheGame.getInstance().getPlayers()[currentSqr.getOwnerIndex()];
		int numOfUtil = owner.getNumOfUtils(); 
		int rent= MonopolyTheGame.getInstance().getDice()[0]+MonopolyTheGame.getInstance().getDice()[1];
		// This was written in utility and it seems like never used but just in case
		if (numOfUtil == 1) {
			return rent * 4;
		} else if (numOfUtil == 2) {
			return rent * 10;
		} else if (numOfUtil == 3) {
			return rent * 20;
		} else if (numOfUtil == 4) {
			return rent * 40;
		} else if (numOfUtil == 5) {
			return rent * 80;
		} else if (numOfUtil == 6) {
			return rent * 100;
		} else if (numOfUtil == 7) {
			return rent * 120;
		} else if (numOfUtil == 8) {
			return rent * 150;
		}
		return 0;
	}
	
	public UtilityRentLogic(){
		
	}
}
