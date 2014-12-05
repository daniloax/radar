package model;

import java.util.ArrayList;
import java.util.List;

import control.Database;

public class RadarDatabase {

	private Database database;
	private List<Account> accounts;
	private List<Map> positions;
	
	public RadarDatabase() {
		accounts = new ArrayList<Account>();
		database = new Database();
		positions = new ArrayList<Map>();
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
	
	public List<Account> getAccounts() {
		return accounts;
	}
	
	public List<Map> getMap() {			
		return positions;
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
	
	public void addAccounts() {
		database.addAccounts("account.txt");
	}
	
	public void readAccounts() {
		database.readAccounts("account.txt", accounts);
	}
	
	public void readPositions() {
		database.readPositions("map.txt", positions);
	}
	
	public void setPosition(int account, Double longitude, Double latitude ) {
		getAccount(account).setPosition(longitude, latitude);
	}
	
	public void setPositions() {
		for (Map position : positions)
			position.getAccount().setCell(getCell(position.getAccount().getAccount()));
	}
	
	public void setRadius(int account, Double radius) {
		getAccount(account).setRadius(radius);
	}
	
}
