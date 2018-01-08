package domain.squares.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;
import java.util.Random;
import ui.Observer;

public class ChanceSteppedOn extends SteppedOn{
	Random rgen = new Random();
	private int randomInt = 0;
	public ChanceSteppedOn(int index) {

		super(index);
	}

	@Override
	public void activate(int i) {
		Player ply = MonopolyTheGame.getInstance().getActivePlayer();
		if (!ply.isMoving()) {
			System.out.println("Let's say everybody deserves a CHANCE, right ? I mean before the second one");
			randomInt = rgen.nextInt(10);
			ply.addCard(randomInt);
//			ply.addCard(7);
			notifyUI();
		}
	}

	@Override
	public void notifyUI() {
		for(Observer obs: observers) {
			obs.update(true, "card");
			obs.update(new String[] {"chance", randomInt+""}, "cardIndex");
//			obs.update(new String[] {"chance", 7+""}, "cardIndex");

		}
	}
}
