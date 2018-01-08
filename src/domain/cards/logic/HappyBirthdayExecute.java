package domain.cards.logic;

import domain.board.Board;
import domain.game.MonopolyTheGame;
import domain.game.Player;

public class HappyBirthdayExecute implements CardLogic {

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		MonopolyTheGame mi = MonopolyTheGame.getInstance();
		Player activePlayer = mi.getActivePlayer();
		for (int i=0; i< mi.getPlayers().length; i++) {
			if (i == mi.getActivePlayerIndex()) {
				activePlayer.incBalance((mi.getPlayers().length -1) * 10);
			}
			else {
				mi.getPlayers()[i].decBalance(10);
			}
		}
		
		activePlayer.setLocation(115);
		Board.getInstance().getCurrentSquare(115).steppedOn(115);
	}

}
