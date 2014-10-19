package view;

public class Screen {

	public void displayMessage(String message) {
		System.out.print(message);
	}
	
	public void displayMessageLine(String message) {
		System.out.println(message);
	}
	
	public void displayCoordenateValue(double coordenate) {
		System.out.printf("%,.4fº", coordenate);
	}
	
}
