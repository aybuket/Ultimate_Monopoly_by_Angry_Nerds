package test;


import domain.Translator;
import domain.game.MonopolyTheGame;

public class Test {

	static Translator translator;
	static MonopolyTheGame game;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		translator=  Translator.getInstance();


		//game.createNewPlayers();

		game.createNewPlayers();
		translator.saveGame("First");
		(MonopolyTheGame.getInstance().getBoard().getCurrentSquare(1)).buildBuilding();
		(MonopolyTheGame.getInstance().getBoard().getCurrentSquare(1)).buildBuilding();
		(MonopolyTheGame.getInstance().getBoard().getCurrentSquare(1)).buildBuilding();
		(MonopolyTheGame.getInstance().getBoard().getCurrentSquare(1)).buildBuilding();
		(MonopolyTheGame.getInstance().getBoard().getCurrentSquare(1)).buildBuilding();
		//((Property)(game.getInstance().getBoard().getCurrentSquare(1))).buildBuilding();

		//((CabCompany)(game.getInstance().getBoard().getCurrentSquare(114))).buildBuilding();

	}

}
