package control;

import model.RadarDatabase;
import view.Screen;


public class CellView extends Transaction {
	
	public CellView(int accountNumber, Screen screen, RadarDatabase radarDatabase) {
		super(accountNumber, screen, radarDatabase);
	}
	
	@Override
	public void execute() {
		RadarDatabase radarDatabase = getRadarDatabase();
		Screen screen = getScreen();
		
		double currentLatitude = radarDatabase.getLatitudeCoordinate(getAccountNumber());
		double currentLongitude = radarDatabase.getLongitudeCoordinate(getAccountNumber());
		
		screen.displayMessageLine("\nPosition Information:");
		screen.displayMessage(" - Current latitude: ");
		screen.displayCoordenateValue(currentLatitude);
		screen.displayMessage("\n - Current longitude: ");
		screen.displayCoordenateValue(currentLongitude);
		screen.displayMessageLine("");
	}
}
