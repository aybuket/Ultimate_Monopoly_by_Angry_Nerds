package domain.squares.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;

public class ReverseDirectionSteppedOn extends SteppedOn{

	public ReverseDirectionSteppedOn(int i) {
		super(i);
	}

	@Override
	public void activate(int i) {
		Player ply = MonopolyTheGame.getInstance().getActivePlayer();
		if (!ply.isMoving()) {
			System.out.print(
					"Sometimes in order to move forward you must go backward... Said someone... Probably while he/she was drunk");
			MonopolyTheGame.getInstance().getActivePlayer().changeDirection();
			notifyUI();
		}
	}

}
