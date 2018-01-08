package domain.squares.buildingcontainerlogic;

import java.util.ArrayList;

import domain.board.Building;

public class BuildingContainer {

	private ArrayList<Building> container = new ArrayList<Building>();
	private int index;
	private boolean enabler=false;
	public BuildingContainer(int i) {
		this.index = i;
	}

	public void addBuilding(Building b) {
		container.add(b);
	}

	public ArrayList<Building> getContainer() {
		return container;
	}

	public int getIndex() {
		return index;
	}

	public boolean isEnabler() {
		return enabler;
	}

	public void setEnabler(boolean enabler) {
		this.enabler = enabler;
	}
	
	public String toString() {
		String str = "Location: "+getIndex()+"\n"
					+"Contains: \n"+container.toString();
		return str;
	}
}
