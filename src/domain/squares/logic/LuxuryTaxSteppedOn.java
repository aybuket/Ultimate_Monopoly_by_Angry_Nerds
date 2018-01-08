package domain.squares.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;

public class LuxuryTaxSteppedOn extends SteppedOn{

	public LuxuryTaxSteppedOn(int index) {
		super(index);
	}

	@Override
	public void activate(int i) {
		Player pl = MonopolyTheGame.getInstance().getActivePlayer();
		if (!pl.isMoving()) {
			pl.decBalance(75);
			System.out.println(
					"Misfits, outcasts, murderers, a guy who did nothing wrong but still ended up here... You can all find them here take a look");
			notifyUI();
		}
	}
	//TODO para alma cezası var mı yok mu bak


}
