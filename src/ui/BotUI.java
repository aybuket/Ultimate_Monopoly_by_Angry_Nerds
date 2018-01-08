package ui;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BotUI extends JInternalFrame implements Observer, VariablesUI {

	boolean moneyGain;
	boolean moneyLose;
	boolean jail;
	boolean inactive;
	boolean bankrupt;
	boolean winner;
	private static BotUI instance;
	private BotIcon icon = new BotIcon(50, 50, 300, 50, "Images/moneyGained.png");;

	//Singleton pattern
	public static BotUI getInstance() {
		if (instance == null) {
			instance = new BotUI();
		}
		return instance;
	}

	//The constructor initializes the frame and adds the bot icon on it
	private BotUI() {
		botFrame();
		Container con = this.getContentPane();
		icon.setLocation(50,50);
		con.add(icon);	
	}

	//Update method for the Observer pattern
	@Override
	public void update(Object object, String message) {
		// TODO Auto-generated method stub
		if (message.equals("animate")) {
			boolean[] args = (boolean[]) object;
			moneyGain = args[0];
			moneyLose = args[1];
			jail = args[2];
			bankrupt = args[3];
			winner = args[4];
			inactive = args[5];
			System.out.println("money lost:" + moneyLose);
			System.out.println("money gained:" + moneyGain);
		}
	}

	//Sets the features of the bot frame
	private void botFrame() {
		this.setLocation(950, 700);
		this.setVisible(true);
		this.setBackground(background);
		this.setSize(400, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	//Paint method of the BotUI, written according to the Animator example we have seen in class
	public void paint(Graphics g) {
		super.paint(g);
		react();
		repaint();
	}

	//A method which checks the booleans and calls changeIcon method accordingly
	public void react() {
		if(moneyLose) {
			icon.changeIcon("Images/moneyLost.png", "moneyLose");
			System.out.println("money is lost :(");
			moneyLose = false;
		}
		if(moneyGain) {
			icon.changeIcon("Images/moneyGained.png", "moneyGain");
			System.out.println("money is gained :)");
			moneyGain = false;
		}
		if(jail) {
			icon.changeIcon("Images/jailed.png", "jail");
			System.out.println("a player is in jail :(");
			jail = false;
		}
		if(inactive) {
			icon.changeIcon("Images/inactive.png", "inactive");
			System.out.println("sıkıldııımm");
			inactive = false;
		}
		if(bankrupt) {
			icon.changeIcon("Images/bankrupt.png", "bankrupt");
			System.out.println("battık");
			bankrupt = false;
		}
		if(winner) {
			icon.changeIcon("Images/winner.png", "winner");
			System.out.println("kazandın!");
			winner = false;
		}
	}

	public static void main() {
		BotUI bui = BotUI.getInstance();
		bui.show();
	}

}

//This class is borrowed from the code that we have seen in the class as an example. The professor has announced that he strongly recommends
//us to use the example code he has given so we took it and modified it for our purposes.
class StraightLinePath {
	int startX, startY, endX, endY, steps;
	int currentStep = -1; // This makes the first step 0
	double deltaX, deltaY;

	/**
	 * Constructor Stores the points, and builds the information needed to construct
	 * the next point. Note that for a path we need the initial point for the path
	 * (X1, Y1), the final point for the path (X2, Y2), and the number of steps in
	 * the path ( numSteps).
	 */
	public StraightLinePath(int X1, int Y1, int X2, int Y2, int numSteps) {
		startX = X1;
		startY = Y1;
		endX = X2;
		endY = Y2;
		steps = numSteps;
		deltaX = ((double) (X2 - X1)) / steps;
		deltaY = ((double) (Y2 - Y1)) / steps;
	}

	/**
	 * Check to see if the path has MoreSteps
	 */
	public boolean hasMoreSteps() {
		if (currentStep > steps)
			return false;
		return true;
	}

	/**
	 * Get the next position. If the path has no more steps, return the current
	 * position.
	 */
	public Point nextPosition() {
		currentStep++;
		if (currentStep > steps)
			return new Point(endX, endY);
		return new Point((int) (startX + (deltaX * currentStep)), (int) (startY + (deltaY * currentStep)));
	}
}

//This class is for creating a bot icon to be displayed in the UI.
@SuppressWarnings("serial")
class BotIcon extends JPanel implements VariablesUI{
	//Color ballColor;
	int xStart, yStart, xLimit, yLimit;
	StraightLinePath myPath;
	protected ImageIcon img = null;
	protected JLabel lbl = null;
	//This flag allows the icon to change only at the ends of the path
	boolean canChange = true;
	boolean canChangeInactive = true;
	//Queue of images to be displayed. This is used for cases in which the bot needs to react multiple things in a row.
	//For example: pay rent causes a player to lose money and another player to gain money. Therefore the bot should react to them sequentially.
	ArrayList<String> imgList = new ArrayList<String>();

	public BotIcon(int xStart, int yStart, int xLimit, int yLimit, String s) {
		this.xStart = xStart;
		this.yStart = yStart;
		this.xLimit = xLimit;
		this.yLimit = yLimit;
		myPath = new StraightLinePath(xStart, yStart, xLimit, yLimit, 50);
		//Creating and simultaneously scaling the image
		img = new ImageIcon(new ImageIcon(s).getImage().getScaledInstance(50, 100, Image.SCALE_DEFAULT));
		lbl = new JLabel(img);
		lbl.setLocation(xStart,yStart);
		add(lbl);
	}
	
	private boolean listContains(ArrayList<String> imgList, String msg) {
		for(String s : imgList) {
			if (s.split("\\|")[1].equals(msg)) return true;
		}
		return false;
	}

	//This method is called by the react() method of BotUI class. It sets the icon according to the message it takes as and input
	 protected void changeIcon(String s, String msg) {
	  //check the definition of canChange above
	  if(msg.equals("inactive")) img = new ImageIcon(new ImageIcon(s).getImage().getScaledInstance(50, 100, Image.SCALE_DEFAULT));
	  if(canChange) {
	   System.out.println(msg);
	   if(msg.equals("moneyLose")) img = new ImageIcon(new ImageIcon(s).getImage().getScaledInstance(50, 70, Image.SCALE_DEFAULT));
	   if(msg.equals("moneyGain")) img = new ImageIcon(new ImageIcon(s).getImage().getScaledInstance(50, 100, Image.SCALE_DEFAULT));
	   if(msg.equals("jail")) img = new ImageIcon(new ImageIcon(s).getImage().getScaledInstance(50, 100, Image.SCALE_DEFAULT));
	   if(msg.equals("bankrupt")) img = new ImageIcon(new ImageIcon(s).getImage().getScaledInstance(50, 70, Image.SCALE_DEFAULT));
	   lbl.setIcon(img);
	   lbl.setLocation(50,50);
	   canChange=false;
	  } else {
	   if(!listContains(imgList, msg)) imgList.add(s + "|" + msg);
	  }
	 }

	 //A modified version of the paint method of Ball example discussed in the class. The aforementioned changing at the ends of the paths
	 //feature is handled in this method. Also queue and dequeue events happen in here.
	 public void paint(Graphics g) {
	  super.paint(g);
	  this.setBackground(background);
	  Point pos = myPath.nextPosition();
	  lbl.setLocation(pos);
	  if (!myPath.hasMoreSteps()) {
	   canChange = true;
	   if(imgList.size()>0){
	    String str[] = imgList.get(0).split("\\|");
	    changeIcon(str[0], str[1]);
	    imgList.remove(0);
	   }
	   if (pos.getX() == xStart)
	    myPath = new StraightLinePath(xStart, yStart, xLimit, yLimit, 50);
	   else
	    myPath = new StraightLinePath(xLimit, yLimit, xStart, yStart, 50);
	  }
	  canChange=false;
	 }


	}
