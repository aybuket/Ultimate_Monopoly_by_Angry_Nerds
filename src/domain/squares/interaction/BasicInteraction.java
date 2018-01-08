package domain.squares.interaction;

public class BasicInteraction extends SquareInteractionBase implements SquareInteraction{
//	
//	private String[] buttons;
//
//	@Override
//	public String[] getButtons() {
//		return buttons;
//	}

	@Override
	public int getChosenAction() {
		return this.getAction();
	}

	@Override
	public void setChoosenAction(int a) {
		this.setAction(a);	
	}
//
//	public BasicInteraction(String[] s){
//		this.buttons = s;
//	}

}
