package control;

import model.RadarDatabase;
import view.Screen;


public abstract class Transaction {
	
	private int accountNumber;
	private Screen screen;
	private RadarDatabase radarDatabase;
	
	public Transaction(int accountNumber, Screen screen, RadarDatabase radarDatabase) {
		this.accountNumber = accountNumber;
		this.screen = screen;
		this.radarDatabase = radarDatabase;
	} 
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public RadarDatabase getRadarDatabase() {
		return radarDatabase;
	}
	
	public Screen getScreen() {
		return screen;
	}
	
	public abstract void execute();
	
}
