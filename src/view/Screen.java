package view;

import model.Coordinate;

public class Screen {

	public void displayMessage(String message) {
		System.out.print(message);
	}
	
	public void displayMessageLine(String message) {
		System.out.println(message);
	}
	
	public void displayCoordenate(double coordenate) {
		System.out.printf("%,.4fº", coordenate);
	}
	
}
