package model;

public class Longitude extends Coordinate {
	
	public Longitude() {
		super();
	}
	
	public Longitude(double value) {
		super((value >= -180.0 && value <= 180.0) ? value : 0.0);
	}
	
	public void setValue(double value) {
		super.setValue((value >= -180.0 && value <= 180.0) ? value : 0.0);
	}

}
