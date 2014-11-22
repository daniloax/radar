package control;

import java.util.Scanner;

import model.Cell;
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
	private static final String OFF_CELL = "|       |";
	private static final String ON_CELL = "|   o   |";
	
	private static final int INVALID_OPTION = 0;
	private static final int MAKE_CELL_ON = 1;
	private static final int NEXT_GENERATION = 2;
	private static final int HALT = 3; 
	
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
		controller.start();
	}
	
	public void update() {
		engine.setAccounts(getRadarDatabase().getAccounts());
		engine.setCenter(getRadarDatabase().getCell(getAccountNumber()));
		engine.setMap();
		printFirstRow();
		printLine();
		engine.update();
		printOptions();
	}
	
	private void printOptions() {
		int option;
		System.out.println("\n \n");
		
		do {
			System.out.println("Select one of the options: \n \n"); 
			System.out.println("[1] Make a cell on");
			System.out.println("[2] Next generation");
			System.out.println("[3] Halt");
		
			System.out.print("\n \n Option: ");

			option = parseOption(keypad.getLine());
		} while(option == 0);
		
		switch(option) {
			case MAKE_CELL_ON : makeCellOn(); break;
			case NEXT_GENERATION : nextGeneration(); break;
			case HALT : halt();
		}
	}
	
	private void makeCellOn() {
		int i, j = 0;
		Scanner s = new Scanner(System.in);
		
		do {
			
			System.out.print("\n Inform the row number (0 - " + engine.getHeight() + "): " );
			i = s.nextInt();
			System.out.print("\n Inform the column number (0 - " + engine.getWidth() + "): " );
			
			j = s.nextInt();
		
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

	private int parseOption(String option) {
		if(option.equals("1")) {
			return MAKE_CELL_ON;
		}
		else if (option.equals("2")) {
			return NEXT_GENERATION;
		}
		else if (option.equals("3")) {
			return HALT;
		}
		else return INVALID_OPTION;
	}

	/* Imprime uma linha usada como separador das linhas do tabuleiro */
	private void printLine() {
		for (int j = 0; j < dimension; j++) {
			System.out.print(LINE);
		}
		System.out.print("\n");
	}

	/*
	 * Imprime os identificadores das colunas na primeira linha do tabuleiro
	 */
	private void printFirstRow() {
		System.out.println("\n \n");
		for (int j = getRadarDatabase().getCell(getAccountNumber()).getX() - 1; j <= getRadarDatabase().getCell(getAccountNumber()).getX() + 1; j++) {
			System.out.printf("   %3d   ", j * 15);
		}
		System.out.print("\n");
	}

}
