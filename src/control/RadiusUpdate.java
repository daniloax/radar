package control;

import java.util.InputMismatchException;

import model.RadarDatabase;
import view.Keypad;
import view.Screen;

public class RadiusUpdate extends Transaction {
	
	private int option;
	
	private Double radius;
	private Keypad keypad;
	
	private final static int CANCELED = 3;
	
	public RadiusUpdate(int accountNumber, Screen screen, RadarDatabase radarDatabase, Keypad keypad) {
		super(accountNumber, screen, radarDatabase);
		this.keypad = keypad;
	}
	
	@Override
	public void execute() {
		
		boolean radiusUpdated = false;
		RadarDatabase radarDatabase = getRadarDatabase();
		Screen screen = getScreen();

		do {

			option = displayMenuOfUpdate();

			if (option != CANCELED) {

				radarDatabase.setRadius(getAccountNumber(), radius);
				radiusUpdated = true;

				screen.displayMessageLine("\nYour radius has been updated.");

			} else {

				screen.displayMessageLine("\nCanceling transaction...");
				return;

			}

		} while (!radiusUpdated);
	}
	
	private int displayMenuOfUpdate() {

		int input = 0;
		Screen screen = getScreen();

		while (input == 0) {
			
			screen.displayMessageLine("\nRadius Update Menu:");
			screen.displayMessageLine("[1] Radius Update");
			screen.displayMessageLine("[2] Cancel transaction");
			screen.displayMessage("\nChoose a radius update option: ");

			try {

				input = keypad.getInput();
	
				switch (input) {
	
					case 1:
						setRadius();
						break;
					
					case 2:
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
	
	private void setRadius() {
		Screen screen = getScreen();
		screen.displayMessage("\nRadius value: ");
		radius = getRadius();
	}
	
	private Double getRadius() {
		return keypad.getRadius();
	}

}
