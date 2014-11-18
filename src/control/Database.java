package control;

import java.util.List;

import model.Account;

public class Database {
	
	CreateTextFile createTextFile;
	ReadTextFile readTextFile;
	
	public Database() {
		createTextFile = new CreateTextFile();
		readTextFile = new ReadTextFile();
	}
	
	public void addRecords() {
		createTextFile.openFile();
		createTextFile.addRecords();
		createTextFile.closeFile();
	}
	
	public void readRecords() {
		readTextFile.openFile();
		readTextFile.readRecords();
		readTextFile.closeFile();
	}
	
	public void readRecords(List<Account> accounts) {
		readTextFile.openFile();
		readTextFile.readRecords(accounts);
		readTextFile.closeFile();
	}

}
