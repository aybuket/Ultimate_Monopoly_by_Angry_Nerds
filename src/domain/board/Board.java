package domain.board;

import java.util.HashMap;

import domain.squares.Square;
import domain.squares.SquareFactory;

/**
 * This class contains graphical features of our squares including names and colors 
 * @author AngryNerds
 */
public class Board {
	static Board gameBoard;
	SquareFactory factory= SquareFactory.getInstance();
	
	/**
	 * @effects return the board instance or creates if it is not instantiated
	 */
	public static Board getInstance(){
		 // 
		if(gameBoard==null){
			gameBoard= new Board();
		}
		return gameBoard;
	}
	
	private Board() {
		generateSquares();
		addDeedColors();
		fillDeedColors();
	};
	
	public void resetBoard(){
		gameBoard=new Board();
	}
	private Square squares[] = new Square[120];
	private HashMap<String, int[]> deedColors = new HashMap<String, int[]>();

	/**
	 * To get the number of deeds of given color group
	 * 
	 * @param color the color group whose size is wanted
	 * @effects returns the number of the properties in that color group. used for determining majority ownership
	 */
	public int getColorGroupSize(String color) {
		if (deedColors.get(color).length == 0 || deedColors.get(color) == null) {
			return 0;
		} else
			return deedColors.get(color).length;
	}

	// These two methods are going to be called by MonopolyTheGame Class
	// MonopolyGame calls getCurrentSquare to use it for moving the player

	/**
	 * @param index the index of the wanted square
	 * @effects returns the current square
	 */
	public Square getCurrentSquare(int index) {
		return squares[index];
	}

	// Called by MonopolyGame with the info of whose turn it is and on which
	// square we are (learned by using the method above)
	// Calls the stepped on function of the related square
	 /**
	  * @param playerIndex the delegate the move for the specific player
	  * @param s the specific square to delegation
	  * @modifies calls the steppedOn function for the active player
	  */
	public void delegateMove(int playerIndex, Square s) {
		s.steppedOn(playerIndex);
		
	}

	// generates the squares indexing them as follows: starting from inner circle,
	// moving to inner and then outer circle,
	// each time starting from bottom right corner
	
	/**
	 * Square generation
	 * @modifies added the squares of the gameboard
	 */
	private void generateSquares() {
		for(int i=0; i<120; i++){
			squares[i]= factory.createSquare(i);
		}
		
/*
		squares[0] = new Functional("Go", 0);
		squares[1] = new Property("Mediterrenean Avenue", 1, 60, "Wine",2,10,30,90,160,250,750);
		squares[2] = new Functional("Community Chest", 2);
		squares[3] = new Property("Baltic Avenue", 3, 60, "Wine",4,20,60,180,320,450,900);
		squares[4] = new Functional("Income Tax", 4);
		squares[5] = new Functional("Transit Station", 5);
		squares[6] = new Property("Oriental Avenue", 6, 100, "LightBlue",6,30,90,270,400,550,1050);
		squares[7] = new Functional("Chance", 7);
		squares[8] = new Property("Vermont Avenue", 8, 100, "LightBlue",6,30,90,270,400,550,1050);
		squares[9] = new Property("Connecticut Avenue", 9, 120, "LightBlue",8,40,100,300,450,600,1100);
		squares[10] = new Functional("Jail", 10);
		squares[11] = new Property("St. Charles Place", 11, 140, "DarkPink",10,50,150,450,625,750,1250);
		squares[12] = new Utility("Electric Company", 12, 150);
		squares[13] = new Property("States Avenue", 13, 140, "DarkPink",10,50,150,450,625,750,1250);
		squares[14] = new Property("Virginia Avenue", 14, 160, "DarkPink",12,60,180,500,700,900,1400);
		squares[15] = new RailRoad("Pennsylvania Railroad", 15);
		squares[16] = new Property("St. James Place", 16, 180, "Orange",14,70,200,550,750,950,1450);
		squares[17] = new Functional("Community Chest", 17);
		squares[18] = new Property("Tennessee Avenue", 18, 180, "Orange",14,70,200,550,750,950,1450);
		squares[19] = new Property("New York Avenue", 19, 200, "Orange",16,80,220,600,800,1000,1500);
		squares[20] = new Functional("Free Parking", 20);
		squares[21] = new Property("Kentucky Avenue", 21, 220, "Red",18,90,250,700,875,1050,2050);
		squares[22] = new Functional("Chance", 22);
		squares[23] = new Property("Indiana Avenue", 23, 220, "Red",18,90,250,700,875,1050,205);
		squares[24] = new Property("Illinois Avenue", 24, 240, "Red",20,100,300,750,925,1100,2100);
		squares[25] = new Functional("Transit Station", 25);
		squares[26] = new Property("Atlantic Avenue", 26, 260, "DarkYellow",22,110,330,800,975,1150,2150);
		squares[27] = new Property("Ventnor Avenue", 27, 260, "DarkYellow",22,110,330,800,975,1150,2150);
		squares[28] = new Utility("Water Works", 28, 150);
		squares[29] = new Property("Marvin Gardens", 29, 280, "DarkYellow",24,120,350,850,1025,1200,2200);
		squares[30] = new Functional("Roll Three", 30);
		squares[31] = new Property("Pacific Avenue", 31, 300, "GrassGreen",26,130,390,900,1100,1275,2275);
		squares[32] = new Property("North Carolina Avenue", 32, 300, "GrassGreen",26,130,390,900,1100,1275,2275);
		squares[33] = new Functional("Community Chest", 33);
		squares[34] = new Property("Pennsylvania Avenue", 34, 320, "GrassGreen",28,150,450,1000,1200,1400,2400);
		squares[35] = new RailRoad("Short Line", 35, 200);
		squares[36] = new Functional("Chance", 36);
		squares[37] = new Property("Park Place", 37, 350, "DarkBlue",35,175,500,1100,1300,1500,2500);
		squares[38] = new Functional("Luxury Tax", 38);
		squares[39] = new Property("Boardwalk", 39, 400, "DarkBlue",50,200,600,1400,1700,2000,3000);
		// The inner circle
		squares[40] = new Functional("Squeeze Play", 40);
		squares[41] = new Property("The Embarcadero", 41, 210, "White",17,85,240,475,670,1025,1525);
		squares[42] = new Property("Fisherman's Wharf", 42, 250, "White",21,105,320,780,950,1125,1625);
		squares[43] = new Utility("Telephone Company", 43, 150);
		squares[44] = new Functional("Community Chest", 44);
		squares[45] = new Property("Beacon Street", 45, 330, "Black",30,160,470,1050,1250,1500,2500);
		squares[46] = new Functional("Bonus", 46);
		squares[47] = new Property("Boylston Street", 47, 330, "Black",30,160,470,1050,1250,1500,2500);
		squares[48] = new Property("Newbury Street", 48, 380, "Black",40,185,550,1200,1500,1700,2700);
		squares[49] = new Functional("Transit Station", 49);
		squares[50] = new Property("Fifth Avenue", 50, 430, "Gray",60,220,650,1500,1800,2100,3600);
		squares[51] = new Property("Madison Avenue", 51, 430, "Gray",60,220,650,1500,1800,2100,3600);
		squares[52] = new Functional("Stock Exchange", 52);
		squares[53] = new Property("Wall Street", 53, 500, "Gray",80,300,800,1800,2200,2700,4200);
		squares[54] = new Functional("Tax Refund", 54);
		squares[55] = new Utility("Gas Company", 55, 150);
		squares[56] = new Functional("Chance", 56);
		squares[57] = new Property("Florida Avenue", 57, 130, "DarkBrown",9,45,120,350,500,700,1200); 
		squares[58] = new Functional("Holland Tunnel", 58); // Tunnelları functional yaptım haberiniz olsun
		squares[59] = new Property("Miami Avenue", 59, 130, "DarkBrown",9,45,120,350,500,700,1200);
		squares[60] = new Property("Biscayne Avenue", 60, 150, "DarkBrown",11,55,160,475,650,800,1300);
		squares[61] = new Functional("Transit Station", 61);
		squares[62] = new Functional("Reverse Direction", 62);
		squares[63] = new Property("Lombard Street", 63, 210, "White",17,85,240,475,670,1025,1525);
		// The outer circle
		squares[64] = new Functional("Subway", 64);
		squares[65] = new Property("Lake Street", 65, 30, "LightPink",1,5,15,45,80,125,625);
		squares[66] = new Functional("Community Chest", 66);
		squares[67] = new Property("Nicollet Avenue", 67, 30, "LightPink",1,5,15,45,80,125,625);
		squares[68] = new Property("Hennepin Avenue", 68, 60, "LightPink",3,15,45,120,240,350,850);
		squares[69] = new Functional("Bus Ticket", 69);
		squares[70] = new CabCompany("Checker Cab Co.", 70, 300);
		squares[71] = new RailRoad("Reading Railroad", 71, 200);
		squares[72] = new Property("Esplanade Avenue", 72, 90, "LightGreen",5,25,80,225,360,600,1000);
		squares[73] = new Property("Canal Street", 73, 90, "LightGreen",5,25,80,225,360,600,1000);
		squares[74] = new Functional("Chance", 74);
		squares[75] = new Utility("Cable Company", 75, 150);
		squares[76] = new Property("Magazine Street", 76, 120, "LightGreen",8,40,100,300,450,600,1100);
		squares[77] = new Property("Bourbon Street", 77, 120, "LightGreen",8,40,100,300,450,600,1100);
		squares[78] = new Functional("Holland Tunnel", 78);
		squares[79] = new Functional("Auction", 79);
		squares[80] = new Property("Katy Freeway", 80, 150, "LightYellow",11,55,160,475,650,800,1300);
		squares[81] = new Property("Westheimer Road", 81, 150, "LightYellow",11,55,160,475,650,800,1300);
		squares[82] = new Utility("Internet Service Provider", 82, 150);
		squares[83] = new Property("Kirby Drive", 83, 180, "LightYellow",14,70,200,550,750,950,1450);
		squares[84] = new Property("Cullen Boulevard", 84, 180, "LightYellow",14,70,200,550,750,950,1450);
		squares[85] = new Functional("Chance", 85);
		squares[86] = new CabCompany("Black and White Cab Co.", 86, 300);
		squares[87] = new Property("Dekalb Avenue", 87, 210, "DarkGreen",17,85,240,670,840,1025,1525);
		squares[88] = new Functional("Community Chest", 88);
		squares[89] = new Property("Andrew Young Intl Boulevard", 89, 210, "DarkGreen",17,85,240,670,840,1025,1525);
		squares[90] = new Property("Decatur Street", 90, 2400, "DarkGreen",20,100,300,750,925,1100,1600);
		squares[91] = new Property("Peachtree Street", 91, 240, "DarkGreen",20,100,300,750,925,1100,1600);
		squares[92] = new Functional("Pay Day", 92);
		squares[93] = new Property("Randrolph Street", 93, 270, "DarkRed",23,115,345,825,1010,1180,2180);
		squares[94] = new Functional("Chance", 94);
		squares[95] = new Property("Lake Shore Drive", 95, 270, "DarkRed",23,115,345,825,1010,1180,2180);
		squares[96] = new Property("Wacker Drive", 96, 300, "DarkRed",26,130,390,900,1100,1275,2275);
		squares[97] = new Property("Michigan Avenue", 97, 300, "DarkRed",26,130,390,900,1100,1275,2275);
		squares[98] = new CabCompany("Yellow Cab Co.", 98, 300);
		squares[99] = new RailRoad("B&O Railroad", 99, 200);
		squares[100] = new Functional("Community Chest", 100);
		squares[101] = new Property("South Temple", 101, 330, "LightBrown",32,160,470,1050,1250,1500,2500);
		squares[102] = new Property("West Temple", 102, 330, "LightBrown",32,160,470,1050,1250,1500,2500);
		squares[103] = new Utility("Trash Collector", 103, 150);
		squares[104] = new Property("North Temple", 104, 360, "LightBrown",38,170,525,1125,1425,1275,1650);
		squares[105] = new Property("Temple Square", 105, 360, "LightBrown",38,170,525,1125,1425,1275,1650);
		squares[106] = new Functional("Go to jail", 106);
		squares[107] = new Property("South Street", 107, 390, "SalmonPink",45,210,575,1300,1600,1800,3300);
		squares[108] = new Property("Broad Street", 108, 390, "SalmonPink",45,210,575,1300,1600,1800,3300);
		squares[109] = new Property("Walnut Street", 109, 420, "SalmonPink",55,225,630,1450,1750,2050,3550);
		squares[110] = new Functional("Community Chest", 110);
		squares[111] = new Property("Market Street", 111, 420, "SalmonPink",55,225,630,1450,1750,2050,3550);
		squares[112] = new Functional("Bus Ticket", 112);
		squares[113] = new Utility("Sewage System", 113, 150);
		squares[114] = new CabCompany("Ute Cab Co.", 114, 300);
		squares[115] = new Functional("Birthday Gift", 115);
		squares[116] = new Property("Mulholland Drive", 116, 450, "Brown",70,350,750,1600,1850,2100,3600);
		squares[117] = new Property("Ventura Boulevard", 117, 480, "Brown",80,400,825,1800,2175,2550,4050);
		squares[118] = new Functional("Chance", 118);
		squares[119] = new Property("Rodeo Drive", 119, 510, "Brown",90,450,900,2000,2500,3000,4500);
*/
	}

	
	
	/**
	 * Color creation for properties
	 */
	private void addDeedColors() {
		 // @modifies  Adding deeds to the hashmap according to their color groups 
		deedColors.put("Wine", new int[2]);
		deedColors.put("LightBlue", new int[3]);
		deedColors.put("DarkPink", new int[3]);
		deedColors.put("Orange", new int[3]);
		deedColors.put("Red", new int[3]);
		deedColors.put("DarkYellow", new int[3]);
		deedColors.put("GrassGreen", new int[3]);
		deedColors.put("DarkBlue", new int[2]);
		deedColors.put("DarkBrown", new int[3]);
		deedColors.put("White", new int[3]);
		deedColors.put("Black", new int[3]);
		deedColors.put("Gray", new int[3]);
		deedColors.put("LightPink", new int[3]);
		deedColors.put("LightGreen", new int[4]);
		deedColors.put("LightYellow", new int[4]);
		deedColors.put("DarkGreen", new int[4]);
		deedColors.put("DarkRed", new int[4]);
		deedColors.put("LightBrown", new int[4]);
		deedColors.put("SalmonPink", new int[4]);
		deedColors.put("Brown", new int[3]);
	}
	
	/**
	 * Color specification for properties
	 */
	private void fillDeedColors() {
		// @modifies  Filling deeds to the hashmap according to their color groups 
		
		// The middle circle
		deedColors.get("Wine")[0] = 1;
		deedColors.get("Wine")[1] = 3;
		deedColors.get("LightBlue")[0] = 6;
		deedColors.get("LightBlue")[1] = 8;
		deedColors.get("LightBlue")[2] = 9;
		deedColors.get("DarkPink")[0] = 11;
		deedColors.get("DarkPink")[1] = 13;
		deedColors.get("DarkPink")[2] = 14;
		deedColors.get("Orange")[0] = 16;
		deedColors.get("Orange")[1] = 18;
		deedColors.get("Orange")[2] = 19;
		deedColors.get("Red")[0] = 21;
		deedColors.get("Red")[1] = 23;
		deedColors.get("Red")[2] = 24;
		deedColors.get("DarkYellow")[0] = 26;
		deedColors.get("DarkYellow")[1] = 27;
		deedColors.get("DarkYellow")[2] = 29;
		deedColors.get("GrassGreen")[0] = 31;
		deedColors.get("GrassGreen")[1] = 32;
		deedColors.get("GrassGreen")[2] = 34;
		deedColors.get("DarkBlue")[0] = 37;
		deedColors.get("DarkBlue")[1] = 39;
		// The inner circle
		deedColors.get("White")[0] = 41;
		deedColors.get("White")[1] = 42;
		deedColors.get("White")[2] = 63;
		deedColors.get("Black")[0] = 45;
		deedColors.get("Black")[1] = 47;
		deedColors.get("Black")[2] = 48;
		deedColors.get("Gray")[0] = 50;
		deedColors.get("Gray")[1] = 51;
		deedColors.get("Gray")[2] = 53;
		deedColors.get("DarkBrown")[0] = 57;
		deedColors.get("DarkBrown")[1] = 59;
		deedColors.get("DarkBrown")[2] = 60;
		// The outer circle
		deedColors.get("LightPink")[0] = 65;
		deedColors.get("LightPink")[1] = 67;
		deedColors.get("LightPink")[2] = 68;
		deedColors.get("LightGreen")[0] = 72;
		deedColors.get("LightGreen")[1] = 73;
		deedColors.get("LightGreen")[2] = 76;
		deedColors.get("LightGreen")[3] = 77;
		deedColors.get("LightYellow")[0] = 80;
		deedColors.get("LightYellow")[1] = 81;
		deedColors.get("LightYellow")[2] = 83;
		deedColors.get("LightYellow")[3] = 84;
		deedColors.get("DarkGreen")[0] = 87;
		deedColors.get("DarkGreen")[1] = 89;
		deedColors.get("DarkGreen")[2] = 90;
		deedColors.get("DarkGreen")[3] = 91;
		deedColors.get("DarkRed")[0] = 93;
		deedColors.get("DarkRed")[1] = 95;
		deedColors.get("DarkRed")[2] = 96;
		deedColors.get("DarkRed")[3] = 97;
		deedColors.get("LightBrown")[0] = 101;
		deedColors.get("LightBrown")[1] = 102;
		deedColors.get("LightBrown")[2] = 104;
		deedColors.get("LightBrown")[3] = 105;
		deedColors.get("SalmonPink")[0] = 107;
		deedColors.get("SalmonPink")[1] = 108;
		deedColors.get("SalmonPink")[2] = 109;
		deedColors.get("SalmonPink")[3] = 111;
		deedColors.get("Brown")[0] = 116;
		deedColors.get("Brown")[1] = 117;
		deedColors.get("Brown")[2] = 119;

	}
	
	public int[] getColorGroup(String color) {
		return deedColors.get(color);
	}

	public boolean repOK(){
		for(int i=0; i<120; i++){
			if(!squares[i].repOK()){
				System.out.println("hmm"+i);
				return false;
			}
		}
		return true;
	}
	
	public String toString() {
		String str = "Squares: \n";
		for(int i=0; i<squares.length; i++) {
			str = str + squares[i].toString();
		}
		return str;
	}
}
