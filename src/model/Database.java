package model;

import java.util.List;

import control.CreateTextFile;
import control.ReadTextFile;

public class Database {
	
	CreateTextFile createTextFile;
	ReadTextFile readTextFile;
	
	public Database() {
		createTextFile = new CreateTextFile();
		readTextFile = new ReadTextFile();
	}
	
	protected void addRecords() {
		createTextFile.openFile();
		createTextFile.addRecords();
		createTextFile.closeFile();
	}
	
	protected void readRecords() {
		readTextFile.openFile();
		readTextFile.readRecords();
		readTextFile.closeFile();
	}
	
	protected void readRecords(List<Account> accounts) {
		readTextFile.openFile();
		readTextFile.readRecords(accounts);
		readTextFile.closeFile();
	}

}
