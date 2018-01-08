package domain.squares.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.squares.Square;

public class UtilitySteppedOn extends SteppedOn{

	public UtilitySteppedOn(int i) {
		super(i);
	}

	@Override
	public void activate(int i) {
		Player ply = MonopolyTheGame.getInstance().getActivePlayer();
		if (!ply.isMoving()) {
			Square currentSquare = MonopolyTheGame.getInstance().getBoard().getCurrentSquare(this.getIndex());
			boolean isOwned = currentSquare.isOwned();
			int ownerIndex = currentSquare.getOwnerIndex();

			if (isOwned && ownerIndex != i) {
				currentSquare.setPayRentEnabled(true);
			}
			System.out.println("You spend your life maximizing it while spending your time is actually minimizing it.");
			notifyUI();
		}
	}

}
