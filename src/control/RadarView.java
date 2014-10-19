package control;

import model.RadarDatabase;
import view.Screen;

public class RadarView extends Transaction {
	
	public RadarView(int accountNumber, Screen screen, RadarDatabase radarDatabase) {
		super(accountNumber, screen, radarDatabase);
	}
	
	@Override
	public void execute() {
		
	}

}
