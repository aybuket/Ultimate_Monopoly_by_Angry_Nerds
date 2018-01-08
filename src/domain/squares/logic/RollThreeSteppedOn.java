package domain.squares.logic;

import java.util.Random;

import domain.game.MonopolyTheGame;
import domain.game.Player;

public class RollThreeSteppedOn extends SteppedOn{
	


	public RollThreeSteppedOn(int i) {
		super(i);
	}

	@Override
	public void activate(int i) {
		Player ply = MonopolyTheGame.getInstance().getActivePlayer();
		if (!ply.isMoving()) {
			MonopolyTheGame.getInstance().rollOnce();
			notifyUI();
		}
	}

}
