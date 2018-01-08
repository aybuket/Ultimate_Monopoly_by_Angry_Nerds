package ui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.Controller;

public interface VariablesUI {
	// background color
	static Color background = new Color(196,255,231);
	// main frame
	static JFrame mainFrame = new JFrame("Ultimate Monopoly");
	static JFrame openingFrame = new JFrame("Powered by Angry Nerds");
	static JFrame registerPlayers = new JFrame("Choose Players");
	static JFrame cardFrame = new JFrame("You drew a card");
	static JFrame auctionFrame = new JFrame("Auction time!");
	static JFrame incomeTaxFrame = new JFrame("Give your taxes!");
	static JFrame stockExchangeFrame = new JFrame("Stock Exchange");
	static JFrame subwayFrame = new JFrame("Take the Subway!");
	static JFrame hurricaneFrame = new JFrame("Hurricane Makes Landfall!");
	// main UI objects
	static BoardUI board = new BoardUI();
	static DrawUI draw = DrawUI.getInstance();
	// game
	static GameUI game = new GameUI();
	//button panels
	static JPanel rollPanel = new JPanel();
	static JPanel buttonPanel = new JPanel();
	static JPanel savePanel = new JPanel();
	static JPanel exitPanel = new JPanel();
	static JPanel loadPanel= new JPanel();
	static JPanel assets = new JPanel();
	static JPanel play = new JPanel();
	static JPanel cardPanel = new JPanel();
	static JPanel keepableCardPanel = new JPanel();
	static JPanel cardButtonPanel = new JPanel();
	static JPanel auctionPanel = new JPanel();
	static JPanel auctionPlayersPanel = new JPanel();
	static JPanel incomePanel = new JPanel();
	static JPanel subwayPanel = new JPanel();
	static JPanel hurricanePanel = new JPanel();
	//bot panel and frame
	static JPanel botPanel = new JPanel();
	static JFrame botFrame = new JFrame("Inside Out Bot");
	//buttons
	static JButton playGame = new JButton("I want to play a game!");
	static JButton newGame = new JButton("New Game");
	static JButton loadGame = new JButton("Load Game");
	static JButton saveGame = new JButton("Save");
	static JButton exitGame = new JButton("Exit");
	static JButton exit = new JButton("Exit");
	static JButton rollDice = new JButton("Roll Dice");
	static JButton buy = new JButton("Buy");
	static JButton payRent = new JButton("Pay Rent");
	static JButton pass = new JButton("Pass");
	static JButton drawCard= new JButton("Draw Card");
	static JButton build = new JButton("Build");
	static JButton mortgage = new JButton("Mortgage");
	static JButton sellBuilding = new JButton("Sell");
	static JButton useButton = new JButton("Use");
	static JButton keepButton = new JButton("Keep");
	static JButton bid = new JButton("Bid");
	static JButton fold = new JButton("Finish");
	static JButton inc1 = new JButton("+1");
	static JButton inc10 = new JButton("+10");
	static JButton inc100 = new JButton("+100");
	static JButton auctionButton = new JButton("Auction");
	static JButton letsGo = new JButton("Let's Go");
	static JButton tenPer = new JButton("%10");
	static JButton tenHunDol = new JButton("$100");
	static JButton destroy = new JButton("Destroy");
	//board variables
	final int BOARD_WIDTH = 900;
	final int BOARD_HEIGHT = 900;
	final int offset_X = 20;
	final int offset_Y = 20;
	final int square_height = 104;
	final int square_width = 52;
	static int[][] coordinates = new int[120][2]; 

	// This can be improved for sure
	static PlayerUI p1 = PlayerUI.getInstance();

	// Controller Instance
	static Controller controllerInstance = Controller.getInstance();

	// Card Factory 
	static CardFactoryUI cardFactory = new CardFactoryUI();
	
	// Subway
	static SubwayUI subway = new SubwayUI();
	
	//Hurricane 
	static HurricaneUI hurricane = new HurricaneUI();
}
