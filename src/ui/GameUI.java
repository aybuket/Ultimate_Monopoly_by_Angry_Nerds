package ui;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameUI extends JPanel implements VariablesUI{
	
	private static JLabel poolBalance = new JLabel("Pool: 0");
	
	// add images and drawings
	public static void addDrawings() {
		DrawUI.fillImages();
		draw.setVisible(true);
		mainFrame.add(draw);
	}

	//add roll button
	public static void addRollPanel() {
		mainFrame.add(rollPanel);
		rollPanel.setBackground(background);
		rollPanel.setVisible(true);
		rollPanel.setSize(100, 30);
		rollPanel.setLocation(950,30);
		rollPanel.add(rollDice);

	}

	// add button panel
	public static void addButtonPanel() {
		mainFrame.add(buttonPanel);
		buttonPanel.setBackground(background);
		buttonPanel.setLocation(950, 130);
		buttonPanel.setVisible(true);
		buttonPanel.setSize(100, 270);
		buttonPanel.add(buy);
		buttonPanel.add(auctionButton);
		buttonPanel.add(payRent);
		buttonPanel.add(drawCard);
		buttonPanel.add(pass);
		buttonPanel.add(build);
		buttonPanel.add(mortgage);
		buttonPanel.add(sellBuilding);
	}

	// add button panels and the board
	protected void startGame() {
		board.calculateCoord();
		addPool();
		mainFrame.add(BotUI.getInstance());
		BotUI.getInstance().setLocation(950, 700);
		BotUI.getInstance().setSize(400,200);
		addRollPanel();
		addButtonPanel();
		addSave();
		addExit();
		addAssets();
		addCards();
		addDrawings();

	}

	private static void addAssets() {
		mainFrame.add(assets);
		assets.setBackground(background);
		assets.setLocation(950,475);
		assets.setVisible(true);
		assets.setSize(250,200);
	}
	
	private static void addCards() {
		mainFrame.add(keepableCardPanel);
		keepableCardPanel.setBackground(background);
		keepableCardPanel.setLocation(1200,475);
		keepableCardPanel.setVisible(true);
		keepableCardPanel.setSize(150,200);
	}

	// add exit button
	private static void addExit() {
		mainFrame.add(exitPanel);
		exitPanel.setBackground(background);
		exitPanel.setLocation(1300, 30);
		exitPanel.setVisible(true);
		exitPanel.setSize(100,30);
		exitPanel.add(exitGame);
	}

	// add save button
	private static void addSave() {
		mainFrame.add(savePanel);
		savePanel.setBackground(background);
		savePanel.setLocation(1230, 30);
		savePanel.setVisible(true);
		savePanel.setSize(70,30);
		savePanel.add(saveGame);
	}
	private static void addPool() {
		mainFrame.add(loadPanel);
		loadPanel.setBackground(background);
		loadPanel.setLocation(1050, 35);
		loadPanel.setVisible(true);
		loadPanel.setSize(105,30);
		loadPanel.add(poolBalance);
	}
	
	public void changePoolBalance() {
		poolBalance.setText("Pool: "+controllerInstance.getPoolBalance());
	}

}
