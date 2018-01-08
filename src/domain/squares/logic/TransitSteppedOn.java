package domain.squares.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;

public class TransitSteppedOn extends SteppedOn{

	public TransitSteppedOn(int i) {
		super(i);
	}

	@Override
	public void activate(int i) {
		Player pl = MonopolyTheGame.getInstance().getActivePlayer();
		int[] dice= MonopolyTheGame.getInstance().getDice();
		if ((dice[0]+dice[1])%2==0) {
			switch(pl.getLocation()){
			case 5: pl.setLocation(71); break;
			case 71: pl.setLocation(5);break;
			case 99: pl.setLocation(25);break;
			case 25:pl.setLocation(99);break;
			case 15:pl.setLocation(49);break;
			case 49:pl.setLocation(15);break;
			case 35:pl.setLocation(61);break;
			case 61:pl.setLocation(35);break;
			}
			if(pl.getDirection()==-1){
				pl.changeDirection();
			}

		}
		if(!pl.isMoving()) {
			notifyUI();
		}
	}

}
