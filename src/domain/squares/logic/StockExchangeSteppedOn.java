package domain.squares.logic;

import domain.game.Auction;
import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.game.Stock;
import domain.squares.Square;

public class StockExchangeSteppedOn extends SteppedOn{
	private Stock[] Stocks = new Stock[6];

	public StockExchangeSteppedOn(int i) {
		super(i);
		Stocks[0] = new Stock("Acme Motors", 150);
		Stocks[1] = new Stock("Untd. Railways", 140);
		Stocks[2] = new Stock("Gen. Radio", 130);
		Stocks[3] = new Stock("Not. Utilities", 120);
		Stocks[4] = new Stock("Alld. Steamship", 110);
		Stocks[5] = new Stock("Motion Pictures", 100);
	}

	@Override
	public void activate(int i) {
		Player pl = MonopolyTheGame.getInstance().getActivePlayer();
		if (!pl.isMoving()) {
			Square currentSquare = MonopolyTheGame.getInstance().getBoard().getCurrentSquare(this.getIndex());
			int ind = currentSquare.getInteraction().getChosenAction();
			if (ind > 0) {
				if (ind == 1) {
					pl.decBalance(Stocks[0].getPrice());
					Stocks[0].addOwner(pl);
				}
				if (ind == 2) {
					pl.decBalance(Stocks[1].getPrice());
					Stocks[1].addOwner(pl);
				}
				if (ind == 3) {
					pl.decBalance(Stocks[2].getPrice());
					Stocks[2].addOwner(pl);
				}
				if (ind == 4) {
					pl.decBalance(Stocks[3].getPrice());
					Stocks[3].addOwner(pl);
				}
				if (ind == 5) {
					pl.decBalance(Stocks[4].getPrice());
					Stocks[4].addOwner(pl);
				}
				if (ind == 6) {
					pl.decBalance(Stocks[5].getPrice());
					Stocks[5].addOwner(pl);
				}
				// TODO en yakındaki en yüksek rentli
			} else {
				Auction.getInstance().startAuction(Stocks[ind * (-1)]);
			}

			for (i = 0; i < Stocks.length; i++) {
				for (int j = 0; j < Stocks[i].getOwners().size(); j++) {
					Stocks[i].getOwners().get(j).incBalance(Stocks[i].getPrice());
				}
			}
			System.out.println("Well, gentlemen, lets make some money!");
			notifyUI();
		}
	}
}
