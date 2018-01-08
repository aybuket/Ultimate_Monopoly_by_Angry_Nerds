package domain.squares.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;

public class GoSteppedOn extends SteppedOn {

	public GoSteppedOn(int index) {
		super(index);
	}

	@Override
	public void activate(int i) {

		Player pl = MonopolyTheGame.getInstance().getActivePlayer();
		if (!pl.isRecentlyJailed()) {
			pl.incBalance(200);
			System.out.println(
					"Look at you now, I remember you passing this square just few seconds ago... Ahhh time, it amazes me how quickly it passes");
		} else {
			pl.setRecentlyJailed(false);
		}
		if(!pl.isMoving()) {
			notifyUI();
		}

	}

}
