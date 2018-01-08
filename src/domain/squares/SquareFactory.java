package domain.squares;



public class SquareFactory {


	static SquareFactory instance;
	public static SquareFactory getInstance(){
		if(instance==null){
			instance= new SquareFactory();
		}
		return instance;
	}

	private SquareFactory() {};

	public Square createSquare(int index){
		switch(index){
		case 0 : return new Square("Go", 0);
		case 1 : return new Square("Mediterranean Avenue", 1, 60, "Wine",2,10,30,90,160,250,750);
		case 2 : return new Square("Community Chest", 2);
		case 3 : return new Square("Baltic Avenue", 3, 60, "Wine",4,20,60,180,320,450,900);
		case 4 : return new Square("Income Tax", 4);
		case 5 : return new Square("Transit Station", 5);
		case 6 : return new Square("Oriental Avenue", 6, 100, "LightBlue",6,30,90,270,400,550,1050);
		case 7 : return new Square("Chance", 7);
		case 8 : return new Square("Vermont Avenue", 8, 100, "LightBlue",6,30,90,270,400,550,1050);
		case 9 : return new Square("Connecticut Avenue", 9, 120, "LightBlue",8,40,100,300,450,600,1100);
		case 10 : return new Square("Jail", 10);
		case 11 : return new Square("St. Charles Place", 11, 140, "DarkPink",10,50,150,450,625,750,1250);
		case 12 : return new Square("Electric Company", 12, 150);
		case 13 : return new Square("States Avenue", 13, 140, "DarkPink",10,50,150,450,625,750,1250);
		case 14 : return new Square("Virginia Avenue", 14, 160, "DarkPink",12,60,180,500,700,900,1400);
		case 15 : return new Square("Pennsylvania Railroad", 15,200);
		case 16 : return new Square("St. James Place", 16, 180, "Orange",14,70,200,550,750,950,1450);
		case 17 : return new Square("Community Chest", 17);
		case 18 : return new Square("Tennessee Avenue", 18, 180, "Orange",14,70,200,550,750,950,1450);
		case 19 : return new Square("New York Avenue", 19, 200, "Orange",16,80,220,600,800,1000,1500);
		case 20 : return new Square("Free Parking", 20);
		case 21 : return new Square("Kentucky Avenue", 21, 220, "Red",18,90,250,700,875,1050,2050);
		case 22 : return new Square("Chance", 22);
		case 23 : return new Square("Indiana Avenue", 23, 220, "Red",18,90,250,700,875,1050,205);
		case 24 : return new Square("Illinois Avenue", 24, 240, "Red",20,100,300,750,925,1100,2100);
		case 25 : return new Square("Transit Station", 25);
		case 26 : return new Square("Atlantic Avenue", 26, 260, "DarkYellow",22,110,330,800,975,1150,2150);
		case 27 : return new Square("Ventnor Avenue", 27, 260, "DarkYellow",22,110,330,800,975,1150,2150);
		case 28 : return new Square("Water Works", 28, 150);
		case 29 : return new Square("Marvin Gardens", 29, 280, "DarkYellow",24,120,350,850,1025,1200,2200);
		case 30 : return new Square("Roll Three", 30);
		case 31 : return new Square("Pacific Avenue", 31, 300, "GrassGreen",26,130,390,900,1100,1275,2275);
		case 32 : return new Square("North Carolina Avenue", 32, 300, "GrassGreen",26,130,390,900,1100,1275,2275);
		case 33 : return new Square("Community Chest", 33);
		case 34 : return new Square("Pennsylvania Avenue", 34, 320, "GrassGreen",28,150,450,1000,1200,1400,2400);
		case 35 : return new Square("Short Line", 35, 200);
		case 36 : return new Square("Chance", 36);
		case 37 : return new Square("Park Place", 37, 350, "DarkBlue",35,175,500,1100,1300,1500,2500);
		case 38 : return new Square("Luxury Tax", 38);
		case 39 : return new Square("Boardwalk", 39, 400, "DarkBlue",50,200,600,1400,1700,2000,3000);
		// The inner circle
		case 40 : return new Square("Squeeze Play", 40);
		case 41 : return new Square("The Embarcadero", 41, 210, "White",17,85,240,475,670,1025,1525);
		case 42 : return new Square("Fisherman's Wharf", 42, 250, "White",21,105,320,780,950,1125,1625);
		case 43 : return new Square("Telephone Company", 43, 150);
		case 44 : return new Square("Community Chest", 44);
		case 45 : return new Square("Beacon Street", 45, 330, "Black",30,160,470,1050,1250,1500,2500);
		case 46 : return new Square("Bonus", 46);
		case 47 : return new Square("Boylston Street", 47, 330, "Black",30,160,470,1050,1250,1500,2500);
		case 48 : return new Square("Newbury Street", 48, 380, "Black",40,185,550,1200,1500,1700,2700);
		case 49 : return new Square("Transit Station", 49);
		case 50 : return new Square("Fifth Avenue", 50, 430, "Gray",60,220,650,1500,1800,2100,3600);
		case 51 : return new Square("Madison Avenue", 51, 430, "Gray",60,220,650,1500,1800,2100,3600);
		case 52 : return new Square("Stock Exchange", 52);
		case 53 : return new Square("Wall Street", 53, 500, "Gray",80,300,800,1800,2200,2700,4200);
		case 54 : return new Square("Tax Refund", 54);
		case 55 : return new Square("Gas Company", 55, 150);
		case 56 : return new Square("Chance", 56);
		case 57 : return new Square("Florida Avenue", 57, 130, "DarkBrown",9,45,120,350,500,700,1200); 
		case 58 : return new Square("Holland Tunnel", 58); // Tunnelları Square yaptım haberiniz olsun
		case 59 : return new Square("Miami Avenue", 59, 130, "DarkBrown",9,45,120,350,500,700,1200);
		case 60 : return new Square("Biscayne Avenue", 60, 150, "DarkBrown",11,55,160,475,650,800,1300);
		case 61 : return new Square("Transit Station", 61);
		case 62 : return new Square("Reverse Direction", 62);
		case 63 : return new Square("Lombard Street", 63, 210, "White",17,85,240,475,670,1025,1525);
		// The outer circle
		case 64 : return new Square("Subway", 64);
		case 65 : return new Square("Lake Street", 65, 30, "LightPink",1,5,15,45,80,125,625);
		case 66 : return new Square("Community Chest", 66);
		case 67 : return new Square("Nicollet Avenue", 67, 30, "LightPink",1,5,15,45,80,125,625);
		case 68 : return new Square("Hennepin Avenue", 68, 60, "LightPink",3,15,45,120,240,350,850);
		case 69 : return new Square("Bus Ticket", 69);
		case 70 : return new Square("Checker Cab Co.", 70, 300);
		case 71 : return new Square("Reading Railroad", 71, 200);
		case 72 : return new Square("Esplanade Avenue", 72, 90, "LightGreen",5,25,80,225,360,600,1000);
		case 73 : return new Square("Canal Street", 73, 90, "LightGreen",5,25,80,225,360,600,1000);
		case 74 : return new Square("Chance", 74);
		case 75 : return new Square("Cable Company", 75, 150);
		case 76 : return new Square("Magazine Street", 76, 120, "LightGreen",8,40,100,300,450,600,1100);
		case 77 : return new Square("Bourbon Street", 77, 120, "LightGreen",8,40,100,300,450,600,1100);
		case 78 : return new Square("Holland Tunnel", 78);
		case 79 : return new Square("Auction", 79);
		case 80 : return new Square("Katy Freeway", 80, 150, "LightYellow",11,55,160,475,650,800,1300);
		case 81 : return new Square("Westheimer Road", 81, 150, "LightYellow",11,55,160,475,650,800,1300);
		case 82 : return new Square("Internet Service Provider", 82, 150);
		case 83 : return new Square("Kirby Drive", 83, 180, "LightYellow",14,70,200,550,750,950,1450);
		case 84 : return new Square("Cullen Boulevard", 84, 180, "LightYellow",14,70,200,550,750,950,1450);
		case 85 : return new Square("Chance", 85);
		case 86 : return new Square("Black and White Cab Co.", 86, 300);
		case 87 : return new Square("Dekalb Avenue", 87, 210, "DarkGreen",17,85,240,670,840,1025,1525);
		case 88 : return new Square("Community Chest", 88);
		case 89 : return new Square("Andrew Young Intl Boulevard", 89, 210, "DarkGreen",17,85,240,670,840,1025,1525);
		case 90 : return new Square("Decatur Street", 90, 2400, "DarkGreen",20,100,300,750,925,1100,1600);
		case 91 : return new Square("Peachtree Street", 91, 240, "DarkGreen",20,100,300,750,925,1100,1600);
		case 92 : return new Square("Pay Day", 92);
		case 93 : return new Square("Randrolph Street", 93, 270, "DarkRed",23,115,345,825,1010,1180,2180);
		case 94 : return new Square("Chance", 94);
		case 95 : return new Square("Lake Shore Drive", 95, 270, "DarkRed",23,115,345,825,1010,1180,2180);
		case 96 : return new Square("Wacker Drive", 96, 300, "DarkRed",26,130,390,900,1100,1275,2275);
		case 97 : return new Square("Michigan Avenue", 97, 300, "DarkRed",26,130,390,900,1100,1275,2275);
		case 98 : return new Square("Yellow Cab Co.", 98, 300);
		case 99 : return new Square("B&O Railroad", 99, 200);
		case 100 : return new Square("Community Chest", 100);
		case 101 : return new Square("South Temple", 101, 330, "LightBrown",32,160,470,1050,1250,1500,2500);
		case 102 : return new Square("West Temple", 102, 330, "LightBrown",32,160,470,1050,1250,1500,2500);
		case 103 : return new Square("Trash Collector", 103, 150);
		case 104 : return new Square("North Temple", 104, 360, "LightBrown",38,170,525,1125,1425,1275,1650);
		case 105 : return new Square("Temple Square", 105, 360, "LightBrown",38,170,525,1125,1425,1275,1650);
		case 106 : return new Square("Go to jail", 106);
		case 107 : return new Square("South Street", 107, 390, "SalmonPink",45,210,575,1300,1600,1800,3300);
		case 108 : return new Square("Broad Street", 108, 390, "SalmonPink",45,210,575,1300,1600,1800,3300);
		case 109 : return new Square("Walnut Street", 109, 420, "SalmonPink",55,225,630,1450,1750,2050,3550);
		case 110 : return new Square("Community Chest", 110);
		case 111 : return new Square("Market Street", 111, 420, "SalmonPink",55,225,630,1450,1750,2050,3550);
		case 112 : return new Square("Bus Ticket", 112);
		case 113 : return new Square("Sewage System", 113, 150);
		case 114 : return new Square("Ute Cab Co.", 114, 300);
		case 115 : return new Square("Birthday Gift", 115);
		case 116 : return new Square("Mulholland Drive", 116, 450, "Brown",70,350,750,1600,1850,2100,3600);
		case 117 : return new Square("Ventura Boulevard", 117, 480, "Brown",80,400,825,1800,2175,2550,4050);
		case 118 : return new Square("Chance", 118);
		case 119 : return new Square("Rodeo Drive", 119, 510, "Brown",90,450,900,2000,2500,3000,4500);
		default : return null;
		}
	}
}
