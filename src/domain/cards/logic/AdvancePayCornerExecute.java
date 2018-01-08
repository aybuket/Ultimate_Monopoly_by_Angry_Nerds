package domain.cards.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.squares.Square;

public class AdvancePayCornerExecute implements CardLogic {

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		Player activePlayer = MonopolyTheGame.getInstance().getActivePlayer();
		int currentLocation = activePlayer.getLocation();
		if(currentLocation < 40) {
			activePlayer.setLocation(0);
			Square go = MonopolyTheGame.getInstance().getBoard().getCurrentSquare(0);
			go.steppedOn(0);
		}else if(currentLocation < 64) {
			activePlayer.setLocation(46);
			Square go = MonopolyTheGame.getInstance().getBoard().getCurrentSquare(46);
			go.steppedOn(46);
		}else {
			activePlayer.setLocation(92);
			Square go = MonopolyTheGame.getInstance().getBoard().getCurrentSquare(92);
			activePlayer.setUsedCard(true);
			go.steppedOn(92);
		}
	}

}
