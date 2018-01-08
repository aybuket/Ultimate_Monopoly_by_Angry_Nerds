package domain.cards.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;

public class SqueezePlayExecute implements CardLogic {

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		Player activePlayer = MonopolyTheGame.getInstance().getActivePlayer();
		activePlayer.setLocation(40);
		//go'dan geçtiyse para ekleme
	}

}
