package ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawUI extends JPanel implements VariablesUI, Observer{

	private int x_coord = 950;
	private int y_coord = 70;
	private static Image[] dieSides = new Image[8];
	private int die1 = 1;
	private int die2 = 1;
	private int die3 = 1;
	private 	ImageObserver observer = null;
	private BufferedImage img=null;
	private static DrawUI instance = null;

	private DrawUI() {};

	public static DrawUI getInstance(){
		if(instance==null){
			instance= new DrawUI();
		}
		return instance;
	}
	public void paint(Graphics g) {
		// draw the dice
		g.drawImage(dieSides[die1-1], x_coord-20, y_coord, 50, 50, observer);
		g.drawImage(dieSides[die2-1], x_coord+30, y_coord, 50, 50, observer);
		g.drawImage(dieSides[die3-1], x_coord+80, y_coord, 50, 50, observer);
		// import the board
		try {
			img = ImageIO.read(new File("Images/board.png"));
		} catch (IOException e) {
		}
		ImageObserver observer = null;
		//draw the board
		g.drawImage(img, offset_X, offset_Y,BOARD_WIDTH,BOARD_HEIGHT, observer);

		//draw the players
		int[] xpos= p1.getXCord();
		int[] ypos= p1.getYCord();
		Color[] colors=new Color[]{new Color(255,246,187),new Color(32,204,187),new Color(255,152,189), new Color(172,143,255)};
		for(int i=0; i<xpos.length; i++){
			g.setColor(colors[i]);
			g.fillOval(xpos[i], ypos[i], 20,20);
			g.setColor(Color.black);
			g.drawOval(xpos[i], ypos[i], 20,20);
		}
		//player info
		g.setColor(colors[controllerInstance.getActivePlayer()]);
		g.fillRect(x_coord, y_coord+350, 400, 30);
		g.setColor(Color.black);
		g.drawRect(x_coord, y_coord+350, 400, 30);
		g.drawRect(x_coord, y_coord+380, 400, 30);
		//		g.drawRect(x_coord, y_coord+410, 400, 390);
		g.setFont(new Font("Courier New", 1, 12));
		g.drawString("Player "+controllerInstance.getActivePlayer()+" Info", x_coord+10, y_coord+367);
		g.drawString("Balance: $"+controllerInstance.getActivePlayerBalance(), x_coord+290, y_coord+367);
		g.drawString("Position: "+controllerInstance.getActivePlayerPosition(), x_coord+10, y_coord+397);
		//		int i = 0;
		//		for(String str: controllerInstance.getActivePlayerAssets()) {
		//			g.drawString(str+"    " +controllerInstance.getAssetLevel(i),x_coord+10,y_coord+427+i*15);
		//			i++;
		//		}
		BufferedImage img = cardFactory.generateTitleDeedCardImage(controllerInstance.getCurrentSquareName(), controllerInstance.getCurrentSquareIsMortgage());
		g.drawImage(img, 1090, 70, 210, 272, observer);
		if(img!=null) {
			g.drawString("Owner", 1310, 100);
			if(!controllerInstance.getCurrentSquareOwner().equals("Player -1")) {
				g.drawString(controllerInstance.getCurrentSquareOwner(), 1310, 115);
			}
			g.drawString("Buildings", 1310, 150);
			if(controllerInstance.getSquareLevel()!=0) {
				if(controllerInstance.getSquareLevel()<5) {
					if(controllerInstance.getSquareType().equals("Property")) {
						g.drawString("House: "+controllerInstance.getSquareLevel(), 1310, 165);
					}else if(controllerInstance.getSquareType().equals("Railroad")) {
						g.drawString("Train Depot: 1", 1310, 165);
					}else {
						g.drawString("Cab Stand: 1", 1310, 165);
					}
				}else if(controllerInstance.getSquareLevel()<6) {
					g.drawString("Hotel: 1", 1310, 165);
				}else {
					g.drawString("Skyscraper: 1", 1310, 165);
				}
			}

		}


	}

	// change the die value
	public void setDice(int d1, int d2, int d3) {
		die1 = d1;
		die2 = d2;
		if(d3==4 || d3==5) {
			die3 = 7;
		}else if(d3==6) {
			die3 = 8;
		}else {
			die3 = d3;
		}
	}


	// imports the sides of die
	public static void fillImages() {
		try {
			dieSides[0] = ImageIO.read(new File("sidesOfDie/side1.png"));
		} catch (IOException e) {
		}
		try {
			dieSides[1] = ImageIO.read(new File("sidesOfDie/side2.png"));
		} catch (IOException e) {
		}
		try {
			dieSides[2] = ImageIO.read(new File("sidesOfDie/side3.png"));
		} catch (IOException e) {
		}
		try {
			dieSides[3] = ImageIO.read(new File("sidesOfDie/side4.png"));
		} catch (IOException e) {
		}
		try {
			dieSides[4] = ImageIO.read(new File("sidesOfDie/side5.png"));
		} catch (IOException e) {
		}
		try {
			dieSides[5] = ImageIO.read(new File("sidesOfDie/side6.png"));
		} catch (IOException e) {
		}
		try {
			dieSides[6] = ImageIO.read(new File("sidesOfDie/side7.png"));
		} catch (IOException e) {
		}
		try {
			dieSides[7] = ImageIO.read(new File("sidesOfDie/side8.png"));
		} catch (IOException e) {
		}

	}

	@Override
	public void update(Object object, String message) {
		// TODO Auto-generated method stub
		if(message.equals("rollDice")) {
			int[] values = (int[]) object;
			//			System.out.println(values[0]+" "+values[1]);
			setDice(values[0], values[1], values[2]);
		}
	}


}
