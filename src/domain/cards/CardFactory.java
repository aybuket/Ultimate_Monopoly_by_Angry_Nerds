package domain.cards;

import domain.cards.logic.*;
public class CardFactory {

	//////// TO BE IMPLEMENTED WE DONT KNOW HOW TO FACTORY YET :D /////////

	public static Card generateCard(int index) {
		switch(index) {
		case 0: return new Card("Advance to Pay Corner","chance",false, new AdvancePayCornerExecute());
		case 1: return new Card("Advance to Illinois Ave.","chance",false, new AdvanceIllinoisExecute());
		case 2: return new Card("Get Out of Jail Free","chance",true, new GetOutOfJailFreeExecute());
		case 3: return new Card("Go to Jail","chance",false, new GoToJailExecute());
		case 4: return new Card("Traffic Ticket!","chance",false, new TrafficTicketExecute());
		case 5: return new Card("Advance to the Stock Exchange","chance",false, new AdvanceStockExchangeExecute());
		case 6: return new Card("Excellent Accounting","chance",true, new ExcellentAccountingExecute());
		case 7: return new Card("Hurricane Makes Landfall!","chance",false, new HurricaneCardExecute());
		case 8: return new Card("Advance to Squeeze Play","chance",false, new SqueezePlayExecute());
		case 9: return new Card("MARDI GRAS!","chance",false, new MardiGrasExecute());		
		case 10: return new Card("Insurance Premiums Due","communitychest",false, new InsurancePremiumsExecute());
		case 11: return new Card("Happy Birthday!","communitychest",false, new HappyBirthdayExecute());
		case 12: return new Card("Entrepreneur of the Year!","communitychest",false, new EntrepreneurExecute());
		case 13: return new Card("You're getting Married","communitychest",false, new GettingMarriedExecute());
		case 14: return new Card("Bank Error in Your Favor!","communitychest",false, new BankErrorExecute());
		case 15: return new Card("Get Out of Jail Free","communitychest",true, new GetOutOfJailFreeExecute());
		case 16: return new Card("Go to Jail!","communitychest",false, new GoToJailExecute());
		case 17: return new Card("You Win 2nd Place in an Board Game Remix Design Contest!","communitychest",false, new BoardGameRemixExecute());		
		}
		return null;		
	}
}
