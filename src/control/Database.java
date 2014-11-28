package control;

import java.util.List;

import model.Account;
import model.Map;

public class Database {
	
	CreateTextFile createTextFile;
	ReadTextFile readTextFile;
	
	public Database() {
		createTextFile = new CreateTextFile();
		readTextFile = new ReadTextFile();
	}
	
	public void addAccounts(String fileName) {
		createTextFile.addAccount(fileName);
	}
	
	public void readAccounts(String fileName) {
		readTextFile.readAccounts(fileName);
	}
	
	public void readAccounts(String fileName, List<Account> accounts) {
		readTextFile.readAccounts(fileName,accounts);
	}
	
	public void readPositions(String fileName, List<Map> positions) {
		readTextFile.readPositions(fileName, positions);
	}

}
