package domain.squares;

import domain.squares.logic.AuctionSteppedOn;
import domain.squares.logic.BirthdayGiftSteppedOn;
import domain.squares.logic.BonusSteppedOn;
import domain.squares.logic.ChanceSteppedOn;
import domain.squares.logic.CommonSteppedOn;
import domain.squares.logic.CommunityChestSteppedOn;
import domain.squares.logic.FreeParkingSteppedOn;
import domain.squares.logic.GoSteppedOn;
import domain.squares.logic.GoToJailSteppedOn;
import domain.squares.logic.HollandTunnelSteppedOn;
import domain.squares.logic.IncomeTaxSteppedOn;
import domain.squares.logic.JailSteppedOn;
import domain.squares.logic.LuxuryTaxSteppedOn;
import domain.squares.logic.PayDaySteppedOn;
import domain.squares.logic.ReverseDirectionSteppedOn;
import domain.squares.logic.RollThreeSteppedOn;
import domain.squares.logic.SqueezePlaySteppedOn;
import domain.squares.logic.SteppedOnLogic;
import domain.squares.logic.StockExchangeSteppedOn;
import domain.squares.logic.SubwaySteppedOn;
import domain.squares.logic.TaxRefundSteppedOn;
import domain.squares.logic.TransitSteppedOn;
import domain.squares.logic.UtilitySteppedOn;

public class SteppedOnStrategyFactory {

	static SteppedOnStrategyFactory instance;
	public static SteppedOnStrategyFactory getInstance() {
		if(instance==null){
			instance=new SteppedOnStrategyFactory();
		}
		return instance;
	}

	
	private SteppedOnStrategyFactory(){}
	
	
	public SteppedOnLogic createSteppedOn(int index) {
		// TODO Auto-generated method stub
		switch(index){
		case 0 : return new GoSteppedOn(index);
		case 1 : return new CommonSteppedOn(index);
		case 2 : return new CommunityChestSteppedOn(index);
		case 3 : return new CommonSteppedOn(index);
		case 4 : return new IncomeTaxSteppedOn(index);
		case 5 : return new TransitSteppedOn(index);  
		case 6 : return new CommonSteppedOn(index);
		case 7 : return new ChanceSteppedOn(index);
		case 8 : return new CommonSteppedOn(index);
		case 9 : return new CommonSteppedOn(index);
		case 10 : return new JailSteppedOn(index);
		case 11 : return new CommonSteppedOn(index);
		case 12 : return new UtilitySteppedOn(index);
		case 13 : return new CommonSteppedOn(index);
		case 14 : return new CommonSteppedOn(index);
		case 15 : return new TransitSteppedOn(index);
		case 16 : return new CommonSteppedOn(index);
		case 17 : return new CommunityChestSteppedOn(index);
		case 18 : return new CommonSteppedOn(index);
		case 19 : return new CommonSteppedOn(index);
		case 20 : return new FreeParkingSteppedOn(index);
		case 21 : return new CommonSteppedOn(index);
		case 22 : return new ChanceSteppedOn(index);
		case 23 : return new CommonSteppedOn(index);
		case 24 : return new CommonSteppedOn(index);
		case 25 : return new TransitSteppedOn(index);  
		case 26 : return new CommonSteppedOn(index);
		case 27 : return new CommonSteppedOn(index);
		case 28 : return new UtilitySteppedOn(index);
		case 29 : return new CommonSteppedOn(index);
		case 30 : return new RollThreeSteppedOn(index);
		case 31 : return new CommonSteppedOn(index);
		case 32 : return new CommonSteppedOn(index);
		case 33 : return new CommunityChestSteppedOn(index);
		case 34 : return new CommonSteppedOn(index);
		case 35 : return new TransitSteppedOn(index);
		case 36 : return new ChanceSteppedOn(index);
		case 37 : return new CommonSteppedOn(index);
		case 38 : return new LuxuryTaxSteppedOn(index);
		case 39 : return new CommonSteppedOn(index);
		// The inner circle
		case 40 : return new SqueezePlaySteppedOn(index);
		case 41 : return new CommonSteppedOn(index);
		case 42 : return new CommonSteppedOn(index);
		case 43 : return new UtilitySteppedOn(index);
		case 44 : return new CommunityChestSteppedOn(index);
		case 45 : return new CommonSteppedOn(index);
		case 46 : return new BonusSteppedOn(index); 
		case 47 : return new CommonSteppedOn(index);
		case 48 : return new CommonSteppedOn(index);
		case 49 : return new TransitSteppedOn(index);
		case 50 : return new CommonSteppedOn(index);
		case 51 : return new CommonSteppedOn(index);
		case 52 : return new StockExchangeSteppedOn(index);
		case 53 : return new CommonSteppedOn(index);
		case 54 : return new TaxRefundSteppedOn(index);
		case 55 : return new UtilitySteppedOn(index);
		case 56 : return new ChanceSteppedOn(index);
		case 57 : return new CommonSteppedOn(index);
		case 58 : return new HollandTunnelSteppedOn(index); 
		case 59 : return new CommonSteppedOn(index);
		case 60 : return new CommonSteppedOn(index);
		case 61 : return new TransitSteppedOn(index); 
		case 62 : return new ReverseDirectionSteppedOn(index);
		case 63 : return new CommonSteppedOn(index);
		// The outer circle
		case 64 : return new SubwaySteppedOn(index);
		case 65 : return new CommonSteppedOn(index);
		case 66 : return new CommunityChestSteppedOn(index);
		case 67 : return new CommonSteppedOn(index);
		case 68 : return new CommonSteppedOn(index);
		case 69 : return new CommonSteppedOn(index); //BUS TICKET
		case 70 : return new CommonSteppedOn(index);
		case 71 : return new TransitSteppedOn(index);
		case 72 : return new CommonSteppedOn(index);
		case 73 : return new CommonSteppedOn(index);
		case 74 : return new ChanceSteppedOn(index);
		case 75 : return new UtilitySteppedOn(index);
		case 76 : return new CommonSteppedOn(index);
		case 77 : return new CommonSteppedOn(index);
		case 78 : return new HollandTunnelSteppedOn(index);
		case 79 : return new AuctionSteppedOn(index);
		case 80 : return new CommonSteppedOn(index);
		case 81 : return new CommonSteppedOn(index);
		case 82 : return new UtilitySteppedOn(index);
		case 83 : return new CommonSteppedOn(index);
		case 84 : return new CommonSteppedOn(index);
		case 85 : return new ChanceSteppedOn(index);
		case 86 : return new CommonSteppedOn(index);
		case 87 : return new CommonSteppedOn(index);
		case 88 : return new CommunityChestSteppedOn(index);
		case 89 : return new CommonSteppedOn(index);
		case 90 : return new CommonSteppedOn(index);
		case 91 : return new CommonSteppedOn(index);
		case 92 : return new PayDaySteppedOn(index);
		case 93 : return new CommonSteppedOn(index);
		case 94 : return new ChanceSteppedOn(index);
		case 95 : return new CommonSteppedOn(index);
		case 96 : return new CommonSteppedOn(index);
		case 97 : return new CommonSteppedOn(index);
		case 98 : return new CommonSteppedOn(index);
		case 99 : return new TransitSteppedOn(index);
		case 100 : return new CommunityChestSteppedOn(index);
		case 101 : return new CommonSteppedOn(index);
		case 102 : return new CommonSteppedOn(index);
		case 103 : return new UtilitySteppedOn(index);
		case 104 : return new CommonSteppedOn(index);
		case 105 : return new CommonSteppedOn(index);
		case 106 : return new GoToJailSteppedOn(index);
		case 107 : return new CommonSteppedOn(index);
		case 108 : return new CommonSteppedOn(index);
		case 109 : return new CommonSteppedOn(index);
		case 110 : return new CommunityChestSteppedOn(index);
		case 111 : return new CommonSteppedOn(index);
		case 112 : return new CommonSteppedOn(index);  // BUS TICKET
		case 113 : return new UtilitySteppedOn(index);
		case 114 : return new CommonSteppedOn(index);
		case 115 : return new BirthdayGiftSteppedOn(index);
		case 116 : return new CommonSteppedOn(index);
		case 117 : return new CommonSteppedOn(index);
		case 118 : return new ChanceSteppedOn(index);
		case 119 : return new CommonSteppedOn(index);
		default : return null;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
