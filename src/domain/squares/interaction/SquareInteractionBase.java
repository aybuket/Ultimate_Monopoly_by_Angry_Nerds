package domain.squares.interaction;

public abstract class SquareInteractionBase {
	int choosenAction = 0;

	public int getAction() {
		return choosenAction;
	}

	public void setAction(int choosenAction) {
		this.choosenAction = choosenAction;
	}
	
}
