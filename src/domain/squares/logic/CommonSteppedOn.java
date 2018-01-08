package domain.squares.logic;


import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.squares.Square;

// For CabCompany, Property, RailRoad
public class CommonSteppedOn extends SteppedOn{
	public CommonSteppedOn(int index) {
		super(index);
	}

	@Override
	public void activate(int i) {
		Player ply = MonopolyTheGame.getInstance().getActivePlayer();
		if (!ply.isMoving()) {
			Square currentSquare = MonopolyTheGame.getInstance().getBoard().getCurrentSquare(this.getIndex());
			System.out.println("player index" + currentSquare.getOwnerIndex());
			boolean isOwned = currentSquare.isOwned();
			int ownerIndex = currentSquare.getOwnerIndex();
			if (isOwned) {
				if (ownerIndex != i && ownerIndex != -2) {
					currentSquare.setPayRentEnabled(true);
				}else {
					currentSquare.setPayRentEnabled(false);
				}
			}
			//System.out.println("Nowadays, everything started to get common, isn't that kinda boring?");
			notifyUI();
		}
	}
}
