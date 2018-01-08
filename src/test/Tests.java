package test;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.Controller;
import domain.Translator;
import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.game.Pool;
import domain.squares.Square;

public class Tests {

	MonopolyTheGame gameInstance = MonopolyTheGame.getInstance();
	Controller controllerInstance = Controller.getInstance();

	@Test
	public void moveTest() {
		gameInstance.createNewPlayers();
		gameInstance.rollDice();
		int dice = gameInstance.getDice()[0] + gameInstance.getDice()[1];
		gameInstance.move();
		if (dice % 2 == 0) {
			assertNotEquals(gameInstance.getActivePlayer().getLocation(), dice);
		} else
			assertEquals(gameInstance.getActivePlayer().getLocation(), dice);
	}

	@Test
	public void buyTest() {
		gameInstance.createNewPlayers();
		Player player = gameInstance.getActivePlayer();
		gameInstance.buyDeed(player, 1);
		Square d = gameInstance.getBoard().getCurrentSquare(1);
		assertEquals(player.getBalance(), 3200 - d.getPrice());
		assertTrue(player.getAssets().get(0) == 1);
		assertEquals(d.getOwnerIndex(), gameInstance.getActivePlayerIndex());
	}

	@Test
	public void buildFailedTest() {
		Translator.getInstance().loadGame("empty");
		gameInstance.createNewPlayers();

		Player player = gameInstance.getActivePlayer();
		gameInstance.buyDeed(player, 6);

		Square d = gameInstance.getBoard().getCurrentSquare(6);

		d.buildBuilding();

		assertNotEquals(d.getLevel(), 1);
	}

	@Test
	public void buildCorrectTest() {

		gameInstance.createNewPlayers();
		Player player = gameInstance.getActivePlayer();
		gameInstance.buyDeed(player, 11);
		gameInstance.buyDeed(player, 13);
		Square d = gameInstance.getBoard().getCurrentSquare(11);

		d.buildBuilding();

		assertEquals(d.getLevel(), 1);
	}

	@Test
	public void hotelTest() {
		gameInstance.createNewPlayers();
		Player player = gameInstance.getActivePlayer();
		gameInstance.buyDeed(player, 11);
		gameInstance.buyDeed(player, 13);
		Square d = gameInstance.getBoard().getCurrentSquare(13);
		for (int i = 0; i < 8; i++) {
			d.buildBuilding();
		}
		assertEquals(d.getLevel(), 5);
	}

	@Test
	public void skyscraperFailedTest() {
		gameInstance.createNewPlayers();
		Player player = gameInstance.getActivePlayer();
		gameInstance.buyDeed(player, 16);
		gameInstance.buyDeed(player, 18);
		Square d = gameInstance.getBoard().getCurrentSquare(16);
		for (int i = 0; i < 8; i++) {
			d.buildBuilding();
		}
		assertNotEquals(d.getLevel(), 6);
	}

	@Test
	public void skyscraperCorrectTest() {
		gameInstance.createNewPlayers();
		Player player = gameInstance.getActivePlayer();
		gameInstance.buyDeed(player, 21);
		gameInstance.buyDeed(player, 23);
		gameInstance.buyDeed(player, 24);
		Square d = gameInstance.getBoard().getCurrentSquare(21);
		for (int i = 0; i < 8; i++) {
			d.buildBuilding();
		}
		assertEquals(d.getLevel(), 6);
	}

	@Test
	public void changeDirectionTest() {
		gameInstance.createNewPlayers();
		Player player = gameInstance.getActivePlayer();
		player.changeDirection();
		gameInstance.rollDice();
		int dice = gameInstance.getDice()[0] + gameInstance.getDice()[1];
		gameInstance.move();
		if (dice % 2 == 0) {
			assertNotEquals(gameInstance.getActivePlayer().getLocation(), 40 - dice);
		} else

			assertEquals(player.getLocation(), 40 - dice);
	}

	@Test
	public void doubleDiceTest() {
		// if dice is double, active player does not change
		gameInstance.createNewPlayers();
		for (int i = 0; i < 20; i++) {
			Player player = gameInstance.getActivePlayer();
			controllerInstance.rollDice();
			boolean isDouble = controllerInstance.getDice()[0] == controllerInstance.getDice()[1];
			gameInstance.passTurn();
			Player player2 = gameInstance.getActivePlayer();
			assertEquals(player == player2, isDouble);

		}
	}

	@Test
	public void diceTest() {

		for (int i = 0; i < 20; i++) {

			gameInstance.rollDice();
			assertTrue(gameInstance.diceRepOK());
		}

	}

	@Test
	public void playerInitializationTest() {

		gameInstance.createNewPlayers();
		for (int player = 0; player < gameInstance.getNumberOfPlayers(); player++) {
			assertTrue(gameInstance.getPlayers()[player].repOK());
		}

	}

	@Test
	public void playerBalanceTest() {

		Translator.getInstance().loadGame("balanceTest");
		Player player = gameInstance.getActivePlayer();
		gameInstance.buyDeed(player, 1);
		gameInstance.buyDeed(player, 3);
		gameInstance.buyDeed(player, 6);
		gameInstance.buyDeed(player, 8);
		gameInstance.buyDeed(player, 9);
		gameInstance.buyDeed(player, 11);
		gameInstance.buyDeed(player, 13);
		gameInstance.buyDeed(player, 14);
		gameInstance.buyDeed(player, 21);
		gameInstance.buyDeed(player, 23);
		gameInstance.buyDeed(player, 24);
		assertTrue(gameInstance.getActivePlayer().repOK());
		Translator.getInstance().loadGame("empty");
	}

	@Test
	public void locationTest() {
		gameInstance.createNewPlayers();
		Player player = gameInstance.getActivePlayer();

		controllerInstance.rollDice();
		gameInstance.move();
		assertTrue(player.repOK());

	}

	@Test
	public void halvePoolTest() {
		Pool pool = Pool.getInstance();
		pool.takeAllMoney();
		pool.putMoney(2000);
		int money1 = pool.takeHalfMoney();
		int money2 = pool.takeHalfMoney();
		int money3 = pool.takeHalfMoney();
		assertEquals(money1, 1000);
		assertEquals(money2, 500);
		assertEquals(money3, 250);
		assertTrue(pool.repOK());

	}

	@Test
	public void poolNegativeTest() {
		Pool pool = Pool.getInstance();
		pool.takeAllMoney();
		pool.putMoney(1000);
		pool.putMoney(-800);
		assertNotEquals(pool.getBalance(), 200);
		assertEquals(pool.getBalance(), 1000);
		assertTrue(pool.repOK());
		pool.takeAllMoney();
		assertEquals(pool.getBalance(), 0);
		assertTrue(pool.repOK());
	}

	@Test
	public void gameInitTest() {
		Translator.getInstance().loadGame("empty");
		controllerInstance.rollDice();
		assertTrue(gameInstance.repOK());
	}

	@Test
	public void playerIncBalanceTest() {
		gameInstance.createNewPlayers();
		Player player = gameInstance.getActivePlayer();
		player.incBalance(100);
		assertEquals(player.getBalance(), 3300);
	}

	@Test
	public void playerDecBalanceTest() {
		gameInstance.createNewPlayers();
		Player player = gameInstance.getActivePlayer();
		player.decBalance(100);
		assertEquals(player.getBalance(), 3100);
	}

	@Test
	public void propertySteppedOnBuyableTest() {
		gameInstance.createNewPlayers();
		gameInstance.setDiceForTest(1, 0);
		gameInstance.move();
		Square sqr = gameInstance.getBoard().getCurrentSquare(1);
		assertEquals(sqr.isBuyable(), true);
	}

	@Test
	public void bonusSteppedOnNotMovingTest() {
		gameInstance.createNewPlayers();
		Player ply = gameInstance.getActivePlayer();
		ply.setLocation(45);
		gameInstance.setDiceForTest(1, 0);
		gameInstance.move();
		assertEquals(ply.getBalance(), 3500);
	}

	@Test
	public void bonusSteppedOnMovingTest() {
		gameInstance.createNewPlayers();
		Player ply = gameInstance.getActivePlayer();
		ply.setLocation(45);
		gameInstance.setDiceForTest(2, 0);
		gameInstance.move();
		assertEquals(ply.getBalance(), 3450);
	}

	@Test
	public void jailSteppedOnMovingTest() {
		gameInstance.createNewPlayers();
		Player ply = gameInstance.getActivePlayer();
		ply.setLocation(9);
		gameInstance.setDiceForTest(2, 0);
		gameInstance.move();
		assertEquals(ply.isJailed(), false);
	}

	@Test
	public void jailSteppedOnNotMovingTest() {
		gameInstance.createNewPlayers();
		Player ply = gameInstance.getActivePlayer();
		ply.setLocation(9);
		gameInstance.setDiceForTest(1, 0);
		gameInstance.move();
		assertEquals(ply.isJailed(), false);
	}

	@Test
	public void jailGetOutTest() {
		gameInstance.createNewPlayers();
		Player ply = gameInstance.getActivePlayer();
		ply.setLocation(105);
		gameInstance.setDiceForTest(1, 0);
		gameInstance.move();
		gameInstance.setDiceForTest(1, 1);
		gameInstance.move();

		assertEquals(ply.isJailed(), false);
	}

	@Test
	public void jailCannotGetOutTest() {
		gameInstance.createNewPlayers();
		Player ply = gameInstance.getActivePlayer();
		ply.setLocation(105);
		gameInstance.setDiceForTest(1, 0);
		gameInstance.move();
		gameInstance.setDiceForTest(1, 0);
		gameInstance.move();

		assertEquals(ply.isJailed(), true);
	}

	@Test
	public void goSteppedOnMovingTest() {
		gameInstance.createNewPlayers();
		Player ply = gameInstance.getActivePlayer();
		ply.setLocation(39);
		gameInstance.setDiceForTest(2, 0);
		gameInstance.move();
		assertEquals(ply.getBalance(), 3400);
	}

	@Test
	public void innerCircleMoveFromEndTest() {
		gameInstance.createNewPlayers();
		Player ply = gameInstance.getActivePlayer();
		ply.setLocation(63);
		gameInstance.setDiceForTest(1, 0);
		gameInstance.move();
		assertEquals(ply.getLocation(), 40);
	}

	@Test
	public void middleCircleMoveFromEndTest() {
		gameInstance.createNewPlayers();
		Player ply = gameInstance.getActivePlayer();
		ply.setLocation(39);
		gameInstance.setDiceForTest(1, 0);
		gameInstance.move();
		assertEquals(ply.getLocation(), 0);
	}

	@Test
	public void outerCircleMoveFromEndTest() {
		gameInstance.createNewPlayers();
		Player ply = gameInstance.getActivePlayer();
		ply.setLocation(119);
		gameInstance.setDiceForTest(1, 0);
		gameInstance.move();
		assertEquals(ply.getLocation(), 64);
	}

	@Test
	public void innerCircleReverseMoveFromBeginingTest() {
		gameInstance.createNewPlayers();
		Player ply = gameInstance.getActivePlayer();
		ply.changeDirection();
		ply.setLocation(40);
		gameInstance.setDiceForTest(1, 0);
		gameInstance.move();
		assertEquals(ply.getLocation(), 63);
	}

	@Test
	public void outerCircleReverseMoveFromBeginingTest() {
		gameInstance.createNewPlayers();
		Player ply = gameInstance.getActivePlayer();
		ply.changeDirection();
		ply.setLocation(64);
		gameInstance.setDiceForTest(1, 0);
		gameInstance.move();
		assertEquals(ply.getLocation(), 119);
	}

	@Test
	public void tempGoPastAuctionTest() {
		gameInstance.createNewPlayers();
		Player ply = gameInstance.getActivePlayer();
		ply.setLocation(77);
		gameInstance.setDiceForTest(3, 5);
		gameInstance.move();
		assertEquals(ply.getLocation(), 85);

	}

	@Test
	public void mortgageTest() {
		gameInstance.createNewPlayers();
		Player pl = gameInstance.getActivePlayer();
		Square sq = gameInstance.getBoard().getCurrentSquare(1);
		gameInstance.buyDeed(pl, 1);
		sq.mortgage();
		assertEquals(sq.isMortgaged(), true);
	}

	@Test
	public void mortgageBalanceTest() {
		gameInstance.createNewPlayers();
		Player pl = gameInstance.getActivePlayer();
		Square sq = gameInstance.getBoard().getCurrentSquare(1);
		gameInstance.buyDeed(pl, 1);
		sq.mortgage();
		assertEquals(pl.getBalance(), (3200 - sq.getPrice()) + (sq.getPrice() / 2));
	}

	@Test
	public void treedoubleJailTest() {
		gameInstance.createNewPlayers();
		Player pl = gameInstance.getActivePlayer();
		gameInstance.setDiceForTest(1, 1);
		gameInstance.move();
		gameInstance.passTurn();
		gameInstance.setDiceForTest(1, 1);
		gameInstance.move();
		gameInstance.passTurn();
		gameInstance.setDiceForTest(1, 1);
		gameInstance.move();
		assertEquals(pl.isJailed(), true);

	}

	@Test
	public void passTurnTest() {
		gameInstance.createNewPlayers();
		Player pl = gameInstance.getActivePlayer();
		gameInstance.setDiceForTest(3, 5);
		gameInstance.passTurn();
		assertNotEquals(gameInstance.getActivePlayer(), pl);
	}

	@Test
	public void passTurnTSecondTest() {
		gameInstance.createNewPlayers();
		Player pl = gameInstance.getActivePlayer();
		gameInstance.setDiceForTest(3, 3);
		gameInstance.passTurn();
		assertEquals(gameInstance.getActivePlayer(), pl);
	}
}
