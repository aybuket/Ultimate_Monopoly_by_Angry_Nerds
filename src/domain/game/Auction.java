package domain.game;

import java.util.Arrays;
import java.util.HashMap;
public class Auction {

	private static Auction instance;
	private Auctionable auctionItem;
	private HashMap<Integer, Player> bids = new HashMap<Integer, Player>();
//	private int passCount = 0;
	private int maxBid = 0;

	public static Auction getInstance() {
		if (instance == null) {
			instance = new Auction();
		}
		return instance;
	}

	private Auction() {
		//HMMM HMMM HMMM HMMM HMMM -.-
	}

	public void registerBid(Player p, int value) {
		if (value > maxBid) {
			maxBid = value;
			bids.put(value, p);
		} else {
			System.out.println(
					"“Are you an idiot, or an idiot?' Gargarin hissed.'The first one. I really resent being called the second.” Replied the man. Which lefts you with only the second option");
		}

	}
//	
//	public boolean isFinished(){
//		int playerCount = MonopolyTheGame.getInstance().getPlayers().length;
//		if(passCount == playerCount){
//			return true;
//		}
//		return false;
//	}
	
	public void finishAuction(){
//		if(isFinished()){
			Player[] plyArr = MonopolyTheGame.getInstance().getPlayers();
			int index = Arrays.asList(plyArr).indexOf(bids.get(maxBid));
			MonopolyTheGame.getInstance().getPlayers()[index].decBalance(maxBid);
			MonopolyTheGame.getInstance().getPlayers()[index].addAsset(auctionItem.getIndex());
			auctionItem.setOwnerIndex(index);
			
//		}else {
//			System.out.println("Patience is a virtue, sadly you dont seem to have it");
//		}
	}
//
//	public void setPassCount(int passCount) {
//		this.passCount = passCount;
//	}

	public int getMaxBid() {
		return maxBid;
	}

	public void startAuction(Auctionable a){
		this.auctionItem = a;
		maxBid = 0;
		bids = new HashMap<Integer, Player>();
	}

}
