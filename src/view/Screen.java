package view;

import model.Cell;

public class Screen {

	public void displayMessage(String message) {
		System.out.print(message);
	}
	
	public void displayMessageLine(String message) {
		System.out.println(message);
	}
	
	public void displayCoordinate(double coordinate) {
		System.out.printf("%,.4fº", coordinate);
	}
	
	public void displayCell(Cell cell) {
		System.out.print(cell.toString());
	}
	
}
