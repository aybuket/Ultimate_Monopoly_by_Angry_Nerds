package ui;

public class BoardUI implements VariablesUI{
	private int index = 0;
	
	protected void calculateCoord() {
		index = 0;
		middleCircle();
		innerCircle();
		outerCircle();

	}
	public void middleCircle() {
		int offset = 29;
		int x_coord = offset+9*square_width+2*square_height;
		int y_coord = offset+9*square_width+2*square_height;
		for(int i=0; i<4; i++) {
			if(i==1) {
				x_coord -= square_width*2;
				int[]  coords = {x_coord,y_coord};
				coordinates[index++] = coords;
			}else if(i==2) {
				y_coord -= square_width*2;
				int[]  coords = {x_coord,y_coord};
				coordinates[index++] = coords;
				x_coord += square_width;
			}else if(i==3) {
				x_coord += square_width;
				int[]  coords = {x_coord,y_coord};
				coordinates[index++] = coords;
				y_coord += square_width;
			}else {
				int[]  coords = {x_coord,y_coord};
				coordinates[index++] = coords;
			}
			for(int j=2; j<11; j++) {
				if(i==0) {
					x_coord -= square_width;
				}else if(i==1) {
					y_coord -= square_width;
				}else if(i==2) {
					x_coord += square_width;
				}else {
					y_coord += square_width;
				}
				int[]  coords = {x_coord,y_coord};
				coordinates[index++] = coords;
			}
		}
	}

	public void innerCircle() {
		int offset = 29;
		int x_coord = offset+5*square_width+3*square_height;
		int y_coord = offset+5*square_width+3*square_height;
		for(int i=0; i<4; i++) {
			if(i==1) {
				x_coord -= square_width*2;
				int[]  coords = {x_coord,y_coord};
				coordinates[index++] = coords;
			}else if(i==2) {
				y_coord -= square_width*2;
				int[]  coords = {x_coord,y_coord};
				coordinates[index++] = coords;
				x_coord += square_width;
			}else if(i==3) {
				x_coord += square_width;
				int[]  coords = {x_coord,y_coord};
				coordinates[index++] = coords;
				y_coord += square_width;
			}else {
				int[]  coords = {x_coord,y_coord};
				coordinates[index++] = coords;
			}
			for(int j=2; j<7; j++) {
				if(i==0) {
					x_coord -= square_width;

				}else if(i==1) {
					y_coord -= square_width;

				}else if(i==2) {
					x_coord += square_width;
				}else {
					y_coord += square_width;
				}
				int[]  coords = {x_coord,y_coord};
				coordinates[index++] = coords;
			}
		}
	}

	public void outerCircle() {
		int offset = 29;
		int x_coord = offset+15*square_width;
		int y_coord = offset+15*square_width;
		for(int i=0; i<4; i++) {

			if(i==1) {
				x_coord -= square_width*2;
				int[]  coords = {x_coord,y_coord};
				coordinates[index++] = coords;
			}else if(i==2) {
				y_coord -= square_width*2;
				int[]  coords = {x_coord,y_coord};
				coordinates[index++] = coords;
				x_coord += square_width;
			}else if(i==3) {
				x_coord += square_width;
				int[]  coords = {x_coord,y_coord};
				coordinates[index++] = coords;
				y_coord += square_width;
			}else {
				int[]  coords = {x_coord,y_coord};
				coordinates[index++] = coords;
			}
			for(int j=2; j<15; j++) {
				if(i==0) {
					x_coord -= square_width;
				}else if(i==1) {
					y_coord -= square_width;
				}else if(i==2) {
					x_coord += square_width;
				}else {
					y_coord += square_width;
				}
				int[]  coords = {x_coord,y_coord};
				coordinates[index++] = coords;
			}
		}
	}
}
