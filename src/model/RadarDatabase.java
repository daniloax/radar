package model;

import java.util.ArrayList;
import java.util.List;

import control.Database;

public class RadarDatabase {

	private Database database;
	private List<Account> accounts;
	
	public RadarDatabase() {
		accounts = new ArrayList<Account>();
		database = new Database();
	}
	
	public String getUser(int account) {
		return getAccount(account).getCell().getName();
	}
	
	private Account getAccount(int account) {
		for (Account currentAccount : accounts) {
			if (currentAccount.getAccount() == account)
				return currentAccount;
		}
		return null;
	}
	
	public boolean authenticateUser(int account, int password) {

		Account userAccount = getAccount(account);
		
		if (userAccount != null)
			return userAccount.validatePassword(password);
		else
			return false;		
	}
	
	public Cell getCell(int account) {
		return getAccount(account).getCell();
	}
	
	public double getLongitude(int account) {
		return getAccount(account).getLongitude();
	}
	
	public double getLatitude(int account) {
		return getAccount(account).getLatitude();
	}
	
	public void setPosition(int account, Double longitude, Double latitude ) {
		getAccount(account).setPosition(longitude, latitude);
	}
	
	public void addRecords() {
		database.addRecords();
	}
	
	public void readRecords() {
		database.readRecords(accounts);
	}
	
}
