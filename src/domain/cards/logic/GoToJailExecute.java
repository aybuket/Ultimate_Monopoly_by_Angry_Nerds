package domain.cards.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.squares.Square;

public class GoToJailExecute implements CardLogic {

	@Override
	public void activate() {
		Player activePlayer = MonopolyTheGame.getInstance().getActivePlayer();
		activePlayer.setLocation(10);
		activePlayer.setJailed(true);
		Square currentSquare = MonopolyTheGame.getInstance().getBoard().getCurrentSquare(activePlayer.getLocation());
		currentSquare.steppedOn(activePlayer.getLocation());
//		
	}

}
