package domain.squares.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;

public class HollandTunnelSteppedOn extends SteppedOn{

	public HollandTunnelSteppedOn(int index) {
		super(index);
	}
	// 58 78

	@Override
	public void activate(int i) {
		Player pl = MonopolyTheGame.getInstance().getActivePlayer();
		if (!pl.isMoving()) {
			if (pl.getLocation() == 58) {
				pl.setLocation(78);
			} else {
				pl.setLocation(58);
			}
			System.out.println("Now you see me, now you don't...)");
			notifyUI();
		}
	}

}
