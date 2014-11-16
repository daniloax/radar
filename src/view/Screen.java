package view;

import model.Cell;
import model.Coordinate;

public class Screen {

	public void displayMessage(String message) {
		System.out.print(message);
	}
	
	public void displayMessageLine(String message) {
		System.out.println(message);
	}
	
	public void displayCell(Cell cell) {
		System.out.print(cell.toString());
	}
	
	public void displayCoordinate(Coordinate coordinate) {
		System.out.printf("%,.4fº", coordinate.getValue());
	}
	
}
