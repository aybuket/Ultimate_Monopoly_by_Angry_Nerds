package ui;

import java.awt.Dimension;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ButtonsUI implements VariablesUI, Observer {

	// JComboBox<String> options;
	private JList<String> options;
	private JList<String> cards;
	private JScrollPane scroll;
	private JScrollPane scrollCard;
	private static ButtonsUI instance;
	private int cardIndex = 0;
	private String cardType = "chance";
	private boolean cardBool = true;
	private static AuctionUI newAuction = AuctionUI.getInstance();
	boolean hurricaneBoolean = false;

	public static ButtonsUI getInstance() {
		if (instance == null) {
			instance = new ButtonsUI();
		}
		return instance;
	}

	private ButtonsUI() {
	};

	public void actionListeners() {
		buy.setEnabled(false);
		payRent.setEnabled(false);
		build.setEnabled(false);
		pass.setEnabled(false);
		drawCard.setEnabled(false);
		mortgage.setEnabled(false);
		sellBuilding.setEnabled(false);
		auctionButton.setEnabled(false);
		// options= new JComboBox<String>();
		options = new JList<String>();
		options.setVisible(true);
		options.setAlignmentX(JComboBox.CENTER_ALIGNMENT);

		options.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		scroll = new JScrollPane(options);
		scroll.setPreferredSize(new Dimension(250, 191));
		// options.setLocation(500,500);
		assets.add(scroll, new Dimension(0, 0));
		options.setBackground(background);
		assets.setBackground(background);

		cards = new JList<String>();
		cards.setVisible(true);
		cards.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
		cards.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollCard = new JScrollPane(cards);
		scrollCard.setPreferredSize(new Dimension(150, 191));
		keepableCardPanel.add(scrollCard, new Dimension(0, 0));
		cards.setBackground(background);
		keepableCardPanel.setBackground(background);

		// rolls the dice, takes the values and moves the player, then repaint
		// the game
		rollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controllerInstance.rollDice();
				// int[] diceValues = controllerInstance.getDice();
				// draw.setDice(diceValues[0],diceValues[1]);
				controllerInstance.move();
				// p1.move(controllerInstance.getActivePlayer(),
				// controllerInstance.getCurrentSquare());
				rollDice.setEnabled(false);
				updateBuildBox();
				if (cardBool) {
					updateCardBox();
				}
				build.setEnabled(false);
				// if square is deed, then player can buy it
				// if(controllerInstance.isPayEnabled()) {
				// payRent.setEnabled(true);
				// pass.setEnabled(false);
				// }else if(controllerInstance.isBuyable()){
				// buy.setEnabled(true);
				// auctionButton.setEnabled(true);
				// pass.setEnabled(true);
				// }else {
				// pass.setEnabled(true);
				// }
				DrawUI.getInstance().repaint();
			}
		});

		// buys the current square
		buy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (controllerInstance.isBuyable()) {
					controllerInstance.buyDeed();
					buy.setEnabled(false);
					auctionButton.setEnabled(false);
					pass.setEnabled(true);
					updateBuildBox();
				}
				// if(controllerInstance.hasAsset()) {
				// build.setEnabled(true);
				// }else {
				// build.setEnabled(false);
				// }
				mainFrame.repaint();
			}
		});

		// pays the rent if the square is owned
		payRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controllerInstance.payRent();
				payRent.setEnabled(false);
				pass.setEnabled(true);
				mainFrame.repaint();
			}
		});

		// pass the turn
		pass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rollDice.setEnabled(true);
				buy.setEnabled(false);
				drawCard.setEnabled(false);
				auctionButton.setEnabled(false);
				controllerInstance.pass();
				pass.setEnabled(false);
				updateBuildBox();
				updateCardBox();
				mortgage.setEnabled(false);
				sellBuilding.setEnabled(false);
				// if(controllerInstance.hasAsset()) {
				// build.setEnabled(true);
				// }else {
				// build.setEnabled(false);
				// }
				mainFrame.repaint();
			}
		});

		// build a building
		build.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// give the available squares, then types
				int toBuild = options.getSelectedIndex();
				System.out.println(toBuild + "");
				controllerInstance.buildBuilding(toBuild);
				if (controllerInstance.isBuildable(options.getSelectedIndex())) {
					build.setEnabled(true);
				} else {
					build.setEnabled(false);
				}
				mortgage.setEnabled(false);
				sellBuilding.setEnabled(false);
				mainFrame.repaint();
			}
		});

		// draw a card
		drawCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controllerInstance.drawCard();
				drawCard.setEnabled(false);
				cardFrame();
				// pass.setEnabled(true);
				updateCardBox();
				cardBool = true;
				mainFrame.repaint();
			}
		});

		// mortgage your property
		mortgage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int m = options.getSelectedIndex();
				controllerInstance.mortgage(m);
				mainFrame.repaint();
			}
		});

		// sell a building
		sellBuilding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int building = options.getSelectedIndex();
				if(controllerInstance.getBuildingLevel(building) == 0) {
					sellBuilding.setEnabled(false);
				}
				controllerInstance.sell(building);
				mortgage.setEnabled(false);
				build.setEnabled(false);
				mainFrame.repaint();
			}
		});

		// save the current game
		saveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controllerInstance.saveGame();
			}
		});

		// load an old game
		loadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openingFrame.dispose();
				MainUI.createFrame();
				mainFrame.add(game);
				game.startGame();
				controllerInstance.loadGame();
				updateBuildBox();
				mainFrame.repaint();
			}
		});

		// close the frame
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openingFrame.dispose();
			}
		});

		useButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pass.setEnabled(true);
				cardFrame.dispose();
				if(hurricaneBoolean) {
					hurricane.getHurricaneFrame();
					hurricaneBoolean = false;
				}else {
					controllerInstance.useCard(cards.getSelectedIndex(),"");
					updateCardBox();
					game.changePoolBalance();
					mainFrame.repaint();
				}
			}
		});
		keepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controllerInstance.keepCard(cards.getSelectedIndex());
				cardFrame.dispose();
				pass.setEnabled(true);
			}
		});

		inc1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newAuction.incGivenBid(1);
				newAuction.setLabel();
				bid.setEnabled(true);
			}
		});
		inc10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newAuction.incGivenBid(10);
				newAuction.setLabel();
				bid.setEnabled(true);
			}
		});
		inc100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newAuction.incGivenBid(100);
				newAuction.setLabel();
				bid.setEnabled(true);
			}
		});
		auctionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controllerInstance.startAuction();
				newAuction.getAuctionFrame();
				auctionButton.setEnabled(false);
				pass.setEnabled(true);
				buy.setEnabled(false);
			}
		});

		bid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newAuction.setCurrentLabel();
				controllerInstance.bid(newAuction.getGiven(), newAuction.getCurrentPlayer());
				newAuction.incGivenBid(1);
				newAuction.setLabel();
				fold.setEnabled(true);
			}
		});
		fold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newAuction.resetGiven();
				auctionFrame.dispose();
				controllerInstance.auction();
				pass.setEnabled(true);
				updateBuildBox();
				mainFrame.repaint();
			}
		});
		letsGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controllerInstance.subway(subway.getIndex());
				subwayFrame.dispose();
				pass.setEnabled(true);
				updateBuildBox();
				mainFrame.repaint();
			}
		});
		tenPer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controllerInstance.incomeTax(1);
				incomeTaxFrame.dispose();
				pass.setEnabled(true);
				mainFrame.repaint();
			}
		});
		tenHunDol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controllerInstance.incomeTax(2);
				incomeTaxFrame.dispose();
				pass.setEnabled(true);
				mainFrame.repaint();
			}
		});
		destroy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controllerInstance.useCard(cards.getSelectedIndex(),hurricane.getColor());
				hurricaneFrame.dispose();
				pass.setEnabled(true);
				mainFrame.repaint();
			}
		});
	}

	void updateCardBox() {
		if(scrollCard!=null){
			keepableCardPanel.remove(scrollCard);
		}
		cards = new JList<String>(controllerInstance.getActivePlayerCards());
		cards.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		cards.setBackground(background);
		scrollCard = new JScrollPane(cards);
		scrollCard.setPreferredSize(new Dimension(150, 191));
		keepableCardPanel.add(scrollCard);
		scrollCard.setBackground(background);
		mainFrame.getRootPane().updateUI();
		// System.out.println("lel"+options.getSelectedIndex());
		cards.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					cardIndex = controllerInstance.getCardIndexNumber(cards.getSelectedIndex());
					if (cardIndex < 10) {
						cardType = "chance";
					} else {
						cardType = "communitychest";
						cardIndex -= 10;
					}
					cardFrame();

				}
			}
		});
	}

	void updateBuildBox() {
		assets.remove(scroll);
		// options= new
		// JComboBox<String>(controllerInstance.getActivePlayerAssets());
		options = new JList<String>(controllerInstance.getActivePlayerAssets());
		options.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		options.setBackground(background);
		scroll = new JScrollPane(options);
		scroll.setPreferredSize(new Dimension(250, 191));
		assets.add(scroll);
		scroll.setBackground(background);
		mainFrame.getRootPane().updateUI();
		// System.out.println("lel"+options.getSelectedIndex());
		options.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(
						options.getSelectedIndex() + " " + controllerInstance.isBuildable(options.getSelectedIndex()));
				if (controllerInstance.isBuildable(options.getSelectedIndex())) {
					build.setEnabled(true);
				} else {
					build.setEnabled(false);
				}
				if(controllerInstance.getBuildingLevel(options.getSelectedIndex()) >= 0) {
					sellBuilding.setEnabled(true);
				}
				if (controllerInstance.getBuildingLevel(options.getSelectedIndex()) == 0) {
					mortgage.setEnabled(true);
				}else {
					mortgage.setEnabled(false);
				}
			}
		});

	}

	private void cardFrame() {
		cardFrame.setLocation(400, 400);
		cardFrame.setVisible(true);
		cardFrame.setBackground(background);
		cardFrame.setSize(400, 350);
		cardFrame.setResizable(false);
		cardFrame.add(cardPanel);
		cardFrame.setContentPane(new JLabel(cardFactory.generateCardImage(cardType, cardIndex)));
		cardPanel.setBackground(background);
		cardPanel.setLocation(500, 305);
		cardPanel.setSize(400, 232);
		cardPanel.setVisible(true);
		cardFrame.add(cardButtonPanel);
		cardButtonPanel.setSize(400, 30);
		cardButtonPanel.setBackground(background);
		cardButtonPanel.add(useButton);
		cardButtonPanel.add(keepButton);
		keepButton.setLocation(200, 0);
		int k = 0; 
		if(cardType.equals("communitychest")) {
			k= 10;
		}
		boolean b = controllerInstance.getCardIsKeepable(cardIndex+k);
		keepButton.setEnabled(b);
		useButton.setEnabled(true);
		useButton.setVisible(true);
	}

	@Override
	public void update(Object object, String message) {
		if (message.equals("card")) {
			drawCard.setEnabled((boolean) object);
			cardBool = !(boolean) object;
			pass.setEnabled(!(boolean) object);
			buy.setEnabled(cardBool);
			auctionButton.setEnabled(cardBool);
			rollDice.setEnabled(false);
		}else if(message.equals("income")) {
			createIncomeTaxFrame();
		} else if (message.equals("buy")) {
			boolean[] msg = (boolean[]) object;
			if (msg[0]) {
				buy.setEnabled(true);
				auctionButton.setEnabled(true);
				pass.setEnabled(false);
				rollDice.setEnabled(false);
			} else if (msg[1]) {
				payRent.setEnabled(true);
				buy.setEnabled(false);
				auctionButton.setEnabled(false);
				pass.setEnabled(false);
				rollDice.setEnabled(false);
			} else {
				pass.setEnabled(true);
				auctionButton.setEnabled(false);
				buy.setEnabled(false);
			}
		} else if (message.equals("subway")) {
			subway.getSubwayFrame();
		} else if (message.equals("cardIndex")) {
			String[] ms = (String[]) object;
			cardType = ms[0];
			cardIndex = Integer.parseInt(ms[1]);
			if(cardIndex==7 && cardType.equals("chance")) {
				hurricaneBoolean = true;
			}
		}else{
			pass.setEnabled(true);
			buy.setEnabled(false);
			auctionButton.setEnabled(false);
		}

		game.changePoolBalance();
		// pass.setEnabled(true);
	}

	public void createIncomeTaxFrame() {
		incomeTaxFrame.setLocation(400, 400);
		incomeTaxFrame.setVisible(true);
		incomeTaxFrame.setBackground(background);
		incomeTaxFrame.setSize(200, 70);
		incomeTaxFrame.setResizable(false);
		incomeTaxFrame.add(incomePanel);
		incomePanel.setBackground(background);
		incomePanel.add(tenPer);
		incomePanel.add(tenHunDol);
	}
}
