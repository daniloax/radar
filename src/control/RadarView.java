package control;

import java.util.InputMismatchException;

import model.RadarDatabase;
import view.Keypad;
import view.Screen;

public class RadarView extends Transaction {
	
	private int dimension;

	private RadarController controller;
	private RadarEngine engine;
	private Statistics statistics;
	
	private Keypad keypad;
	
	private static final String LINE = "+-------+";
	private final static int CANCELED = 3;
	
	
	public RadarView(int accountNumber, Screen screen, RadarDatabase radarDatabase, Keypad keypad, RadarController radarController, RadarEngine radarEngine, Statistics statistics) {
		super(accountNumber, screen, radarDatabase);
		this.keypad = keypad;
		this.controller = radarController;
		this.engine = radarEngine;
		this.statistics = statistics;
	}
	
	@Override
	public void execute() {
		controller.setRadar(this);
		engine = new RadarEngine(statistics);
		controller.setEngine(engine);
		controller.setStatistics(statistics);
		engine.setAccounts(getRadarDatabase().getAccounts());
		engine.setCenter(getRadarDatabase().getCell(getAccountNumber()));
		getRadarDatabase().setPositions();
		engine.setMap(getRadarDatabase().getMap());
		nextGeneration();
		printFirstRow();
		printLine();
		engine.update();
		update();
	}
	
	public void update() {
		
		int option;
		
		boolean positionUpdated = false;
		Screen screen = getScreen();

		do {

			option = displayMenuOfOption();

			if (option != CANCELED) 
				positionUpdated = true;
			
			else {

				screen.displayMessageLine("\nCanceling transaction...");
				return;

			}

		} while (!positionUpdated);
	}
	
	private int displayMenuOfOption() {
		
		int input = 0;
		Screen screen = getScreen();

		while (input == 0) {
			
			screen.displayMessageLine("\nOption Menu:");
			screen.displayMessageLine("[1] Make a cell on");
			screen.displayMessageLine("[2] Next generation");
			screen.displayMessageLine("[3] Cancel");
			screen.displayMessage("\nChoose a option: ");
			
			try {

				input = keypad.getInput();
	
				switch (input) {
	
					case 1:
						makeCellOn();
						break;
					
					case 2:
						nextGeneration();
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
	
	private void makeCellOn() {
		
		int i = 0;
		int j = 0;
		
		do {
			
			System.out.print("\n Inform the row number (0 - " + engine.getHeight() + "): " );
			i = keypad.getInput();
			
			System.out.print("\n Inform the column number (0 - " + engine.getWidth() + "): " );
			j = keypad.getInput();
		
		} while(!validPosition(i, j));
		
		controller.makeCellOn(i, j);
	}
	
	private void nextGeneration() {
		controller.nextGeneration();
	}
	
	private void halt() {
		controller.halt();
	}
	
	private boolean validPosition(int i, int j) {
		return i >= 0 && i < dimension && j >= 0 && j < dimension;
	}
	
	/* Imprime uma linha usada como separador das linhas do radar */
	private void printLine() {
		for (int j = 0; j < dimension; j++) {
			System.out.print(LINE);
		}
		System.out.print("\n");
	}

	/* Imprime os identificadores das colunas na primeira linha do radar */
	private void printFirstRow() {
		System.out.println("\n");
		for (int j = getRadarDatabase().getCell(getAccountNumber()).getX() - 1; j <= getRadarDatabase().getCell(getAccountNumber()).getX() + 1; j++) {
			System.out.printf("   %3d   ", j * 15);
		}
		System.out.print("\n");
	}

}
