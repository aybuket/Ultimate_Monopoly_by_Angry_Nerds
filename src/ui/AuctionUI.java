package ui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AuctionUI implements VariablesUI{
	
	private static AuctionUI instance = new AuctionUI();
	private JList<String> auctionPlayers;
	private JScrollPane scrollAuction;
	private JLabel currentBid = new JLabel("Current Bid: 0");
	private int givenBid = 0;
	private JLabel givenBidLab = new JLabel("Given: "+givenBid);
	private int crPly = 0;
	
	
	
	private AuctionUI() {}
	
	public static AuctionUI getInstance() {
		if(instance == null) {
			instance = new AuctionUI();
		}
		return instance;
	}
	
	
	public void getAuctionFrame() {
		if(scrollAuction!=null){
			auctionPlayersPanel.remove(scrollAuction);
		}
		auctionFrame.setVisible(true);
		auctionFrame.setLocation(500, 400);
		auctionFrame.setBackground(background);
		auctionFrame.setSize(600, 300);
		auctionPanel.setBackground(background);
		auctionPanel.setLocation(420,30);
		auctionPanel.setSize(110,340);
		auctionPanel.setVisible(true);
		//auctionFrame.setResizable(false);
		JLabel imgLab = new JLabel(new ImageIcon(cardFactory.generateTitleDeedCardImage(controllerInstance.getCurrentSquareName(), false).getScaledInstance(210, 272,  java.awt.Image.SCALE_SMOOTH)));
		imgLab.setLocation(10,10);
		auctionFrame.setContentPane(imgLab);
		auctionPlayers = new JList<String>(controllerInstance.getPlayersName());
		auctionPlayers.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		auctionPlayers.setBackground(background);
		scrollAuction = new JScrollPane(auctionPlayers);
		scrollAuction.setPreferredSize(new Dimension(150,98));
		scrollAuction.setBackground(background);
		auctionPlayersPanel.setBackground(background);
		auctionPlayersPanel.setLocation(30,30);
		auctionPlayersPanel.setSize(150,100);
		auctionPlayersPanel.setVisible(true);
		auctionPlayersPanel.add(scrollAuction);
		auctionFrame.add(auctionPlayersPanel);
		auctionFrame.add(auctionPanel);
		auctionPanel.add(currentBid);
		auctionPanel.add(inc1);
		auctionPanel.add(inc10);
		auctionPanel.add(inc100);
		auctionPanel.add(givenBidLab);
		auctionPanel.add(bid);
		auctionPanel.add(fold);
		inc1.setEnabled(false);
		inc10.setEnabled(false);
		inc100.setEnabled(false);
		bid.setEnabled(false);
		fold.setEnabled(false);
		auctionPlayers.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(auctionPlayers.getSelectedIndex()!=-1) {
					System.out.println(auctionPlayers.getSelectedIndex()+" bids");
					crPly = auctionPlayers.getSelectedIndex();
				}else {
					crPly = 0;
					System.out.println("HATA HATA HATA");
					System.out.println(e);
				}
				inc1.setEnabled(true);
				inc10.setEnabled(true);
				inc100.setEnabled(true);
			}
		});
		auctionPlayers.removeListSelectionListener(null);
		auctionPlayers.removeAll();
	}

	public void setAuctionPlayers(JList<String> jList) {
		auctionPlayers = jList;
	}

	public void incGivenBid(int i) {
		givenBid += i;
	}

	public void setLabel() {
		givenBidLab.setText("Given: "+givenBid);
	}

	public void resetGiven() {
		givenBid = 0;
		setLabel();
		setCurrentLabel();
	}

	public void setCurrentLabel() {
		currentBid.setText("Current: "+givenBid);

	}

	public int getGiven() {
		return givenBid;
	}

	public int getCurrentPlayer() {
		return crPly;
	}
	
	
}
