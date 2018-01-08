package domain.squares.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;
import ui.Observer;

public class JailSteppedOn extends SteppedOn {

	public JailSteppedOn(int i) {
		super(i);
	}

	@Override
	public void activate(int i) {
		Player ply = MonopolyTheGame.getInstance().getActivePlayer();
		if (!ply.isMoving()) {
			System.out.println(
					"Misfits, outcasts, murderers, a guy who did nothing wrong but still ended up here... You can all find them here take a look");
			notifyUI();
		}

	}
	
	@Override
	public void notifyUI() {
		for(Observer obs: observers) {
			obs.update("", "");
		}
	}

}
