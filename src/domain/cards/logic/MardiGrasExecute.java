package domain.cards.logic;
import domain.game.MonopolyTheGame;
import domain.game.Player;

public class MardiGrasExecute implements CardLogic  {

	@Override
	public void activate() {
		// 73 TODO Auto-generated method stub
		for(int i = 0; i< MonopolyTheGame.getInstance().getPlayers().length; i++){
			MonopolyTheGame.getInstance().getPlayers()[i].setLocation(73);
			MonopolyTheGame.getInstance().getBoard().getCurrentSquare(73).steppedOn(73);
		}
	}	

}
