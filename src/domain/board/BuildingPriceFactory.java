package domain.board;

public class BuildingPriceFactory  {


	public static int getPrice(String color) {
		if(color.equals("Wine") || color.equals("LightBlue") || color.equals("DarkBrown") || color.equals("LightPink") || color.equals("LightGreen")) {

			return 50;
		}

		if(color.equals("DarkPink") || color.equals("Orange") || color.equals("White") || color.equals("LightYellow") || color.equals("DarkGreen")) {

			return 100;
		}

		if(color.equals("Red") || color.equals("DarkYellow") || color.equals("DarkRed")) {

			return 150;
		}

		if(color.equals("GrassGreen") || color.equals("DarkBlue") || color.equals("Black") || color.equals("LightBrown")) {

			return 200;
		}

		if(color.equals("SalmonPink")) {

			return 250;
		}

		if(color.equals("Gray") || color.equals("Brown")) {

			return 300;
		}


		return -1;
	}
}
