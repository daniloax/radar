package control;

import model.RadarDatabase;
import view.Keypad;
import view.Screen;

public class RadiusUpdate extends Transaction {
	
	private Keypad keypad;
	
	public RadiusUpdate(int accountNumber, Screen screen, RadarDatabase radarDatabase, Keypad keypad) {
		super(accountNumber, screen, radarDatabase);
		this.keypad = keypad;
	}
	
	@Override
	public void execute() {
		
	}

}
