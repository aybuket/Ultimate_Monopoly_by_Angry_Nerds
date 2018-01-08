package domain.squares.logic;

import ui.Observer;

import java.util.Random;

import domain.game.MonopolyTheGame;
import domain.game.Player;

public class CommunityChestSteppedOn extends SteppedOn{
	Random rgen = new Random();
	private int randomInt = 0;
	public CommunityChestSteppedOn(int index) {
		super(index);
	}

	@Override
	public void activate(int i) {
		Player ply = MonopolyTheGame.getInstance().getActivePlayer();
		if (!ply.isMoving()) {
			System.out.println("There is a quote from Jean Vanier which goes on like this:"
					+ " Community is a sign that love is possible in a materialistic world where people so often either ignore or fight each other. It is a sign that we don't need a lot of money to be happy--in fact, the opposite."
					+ " Ironically enough we only have money in this chest :P");
			randomInt = rgen.nextInt(8);
			ply.addCard(randomInt+10);
			notifyUI();
		}
	}

	@Override
	public void notifyUI() {
		for(Observer obs: observers) {
			obs.update(true, "card");
			obs.update(new String[] {"communitychest", randomInt+""}, "cardIndex");
		}
	}
}
