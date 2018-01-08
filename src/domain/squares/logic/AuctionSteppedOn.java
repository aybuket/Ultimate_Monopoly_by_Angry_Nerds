package domain.squares.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.squares.Square;

public class AuctionSteppedOn extends SteppedOn {

	public AuctionSteppedOn(int index) {
		super(index);
	}

	@Override
	public void activate(int i) {
		Player ply = MonopolyTheGame.getInstance().getActivePlayer();
		if (!ply.isMoving()) {
			Square currentSquare = MonopolyTheGame.getInstance().getBoard().getCurrentSquare(this.getIndex());
			int ind = currentSquare.getInteraction().getChosenAction();
			if (ind == -1) {
				// TODO en yakındaki en yüksek rentli
			} else {
				// Auction.getInstance().sellSquare(ind);
			}
			notifyUI();
		}
	}

}
