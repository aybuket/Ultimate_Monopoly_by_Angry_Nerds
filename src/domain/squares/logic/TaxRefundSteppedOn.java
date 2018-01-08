package domain.squares.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.game.Pool;

public class TaxRefundSteppedOn extends SteppedOn{

	public TaxRefundSteppedOn(int i) {
		super(i);
	}

	@Override
	public void activate(int i) {

		Player activePly = MonopolyTheGame.getInstance().getActivePlayer();
		if (!activePly.isMoving()) {
			Pool pl = MonopolyTheGame.getInstance().getPool();
			int amount = pl.takeHalfMoney();
			activePly.incBalance(amount);
			System.out.println(
					"Hey remeber the money we took from you a while back as a tax, well here it is, 50% of it anyways");
			notifyUI();
		}
	}
}
