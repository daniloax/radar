package control;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import model.RadarDatabase;
import view.Keypad;
import view.Screen;

public class PositionUpdate extends Transaction {

	private int option;
	private Double longitude, latitude;
	private Keypad keypad;

	private final static int CANCELED = 3;

	public PositionUpdate(int accountNumber, Screen screen, RadarDatabase radarDatabase, Keypad keypad) {
		super(accountNumber, screen, radarDatabase);
		this.keypad = keypad;
	}

	@Override
	public void execute() {

		boolean positionUpdated = false;
		RadarDatabase radarDatabase = getRadarDatabase();
		Screen screen = getScreen();

		do {

			option = displayMenuOfUpdate();

			if (option != CANCELED) {

				radarDatabase.setPosition(getAccountNumber(), longitude, latitude);
				positionUpdated = true;

				screen.displayMessageLine("\nYour position has been updated.");

			} else {

				screen.displayMessageLine("\nCanceling transaction...");
				return;

			}

		} while (!positionUpdated);

	}
	
	private void setLongitude() {
		Screen screen = getScreen();
		screen.displayMessage("\nLongitude coordinate: ");
		longitude = getLongitude();
	}
	
	private void setLatitude() {
		Screen screen = getScreen();
		screen.displayMessage("\nLatitude coordinate: ");
		latitude = getLatitude();
	}

	private int displayMenuOfUpdate() {

		int input = 0;
		Screen screen = getScreen();

		while (input == 0) {
			
			screen.displayMessageLine("\nPosition Update Menu:");
			screen.displayMessageLine("[1] Longitude");
			screen.displayMessageLine("[2] Latitude");
			screen.displayMessageLine("[3] Cancel transaction");
			screen.displayMessage("\nChoose a coordinate update option: ");

			try {

				input = keypad.getInput();
	
				switch (input) {
	
					case 1:
						setLongitude();
						break;
					
					case 2:
						setLatitude();
						break;
						
					case 3:
						break;
					
					default:
						screen.displayMessageLine("\nInvalid selection. Try again.");
		
				}
				
				return input;
				
			} catch ( InputMismatchException inputException ) {
				
				System.err.println( "Invalid input. Please try again." );
				System.exit( 1 );
			}

		}

		return input;

	}
	
	private Double getLongitude() {
		return keypad.getCoodinate();
	}
	
	private Double getLatitude() {
		return keypad.getCoodinate();
	}
		
}
