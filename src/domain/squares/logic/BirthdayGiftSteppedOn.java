package domain.squares.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.squares.logic.SteppedOn;

public class BirthdayGiftSteppedOn extends SteppedOn{

	public BirthdayGiftSteppedOn(int index) {
		super(index);
	}

	public void activate(int i) {

		Player pl = MonopolyTheGame.getInstance().getActivePlayer();

		if (!pl.isMoving()) {
			pl.incBalance(100);
			System.out.println("For your birthday, I wanted to give you something that was both funny and charming, but then I remembered you already have me in your life.");
			notifyUI();
		}
	}

}
