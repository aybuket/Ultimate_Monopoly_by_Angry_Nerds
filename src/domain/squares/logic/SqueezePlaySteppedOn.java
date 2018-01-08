package domain.squares.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;

public class SqueezePlaySteppedOn extends SteppedOn{

	public SqueezePlaySteppedOn(int i) {
		super(i);
	}

	@Override
	public void activate(int i) {

		Player activePlayer = MonopolyTheGame.getInstance().getActivePlayer();
		if (!activePlayer.isMoving()) {
			Player[] players = MonopolyTheGame.getInstance().getPlayers();
			// Rolls a new dice
			MonopolyTheGame.getInstance().rollDice();
			// Gets Dices
			int[] dices = MonopolyTheGame.getInstance().getDice();
			int total = dices[0] + dices[1];

			if (total <= 9 || total >= 5) {
				for (Player ply : players) {
					if (activePlayer != ply) {
						ply.decBalance(50);
					}
				}
				activePlayer.incBalance((players.length - 1) * 50);
			} else if (total == 3 || total == 4 || total == 10 || total == 11) {
				for (Player ply : players) {
					if (activePlayer != ply) {
						ply.decBalance(100);
					}
				}
				activePlayer.incBalance((players.length - 1) * 100);
			} else if (total == 2 || total == 12) {
				for (Player ply : players) {
					if (activePlayer != ply) {
						ply.decBalance(200);
					}
				}
				activePlayer.incBalance((players.length - 1) * 200);
			}
			System.out.println("What is the similarity between you and a lemon right now?");
			notifyUI();
		}
	}

}
