package view;

import java.util.Scanner; 

public class Keypad {
	
	private Scanner input;
	
	public Keypad() {
		input = new Scanner(System.in);
	}
	
	public int getInput() {
		return input.nextInt();
	}
	
	public double getCoodinate() {
		return input.nextDouble();
	}
	
}
