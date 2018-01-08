package ui;

public class PlayerUI implements VariablesUI, Observer {

	private int x[];
	private int y[];
	private static PlayerUI instance;

	public static PlayerUI getInstance() {
		if (instance == null) {
			instance = new PlayerUI(new int[] { 705, 725, 705, 725 }, new int[] { 705, 705, 725, 725 });
		}
		return instance;
	}

	private PlayerUI(int x[], int y[]) {
		this.x = x;
		this.y = y;
	}

	public void move(int playerIndex, int squareIndex) {
		int[] pair = coordinates[squareIndex];
		x[playerIndex] = pair[0] + 10 + playerIndex * 5;
		y[playerIndex] = pair[1] + 10 + playerIndex * 5;
	}

	public int[] getXCord() {
		return x;
	}

	public int[] getYCord() {
		return y;
	}

	@Override
	public void update(Object object, String message) {
		// TODO Auto-generated method stub
		if (message.equals("move") || message.equals("playerIndexLocation")) {
			int[] args = (int[]) object;
			move(args[0], args[1]);
		}
	}
	


}
