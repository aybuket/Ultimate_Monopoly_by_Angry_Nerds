package domain.squares.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;

public class FreeParkingSteppedOn extends SteppedOn{

	public FreeParkingSteppedOn(int index) {
		super(index);
	}

	@Override
	public void activate(int i) {
		Player ply = MonopolyTheGame.getInstance().getActivePlayer();
		if (!ply.isMoving()) {
			System.out.println(
					"Hmm.. seems like a good time to enjoy the beautiful weather outside... Ohh, but you are inside playing this game huh...");
			notifyUI();
		}
	}

}
