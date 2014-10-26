package model;

import java.util.ArrayList;
import java.util.List;

import control.CreateTextFile;
import control.ReadTextFile;
import control.UserRequest;

public class RadarDatabase {

	private List<Account> accounts;
	private ReadTextFile database;
	private UserRequest userRequest;
	
	public RadarDatabase() {
		accounts = new ArrayList<Account>();
		database = new ReadTextFile();
		userRequest = new UserRequest();
	}
	
	public String getUser(int account) {
		return getAccount(account).getUser();
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
	
	public double getLongitude(int account) {
		return getAccount(account).getLongitude();
	}
	
	public double getLatitude(int account) {
		return getAccount(account).getLatitude();
	}
	
	public void setPosition(int account, Double longitude, Double latitude ) {
		getAccount(account).setPosition(longitude, latitude);
	}
	
	public void getUserRequest() {
		userRequest.processRequests();
	}
	
	public void readRecords() {
		database.openFile();
		database.readRecords(accounts);
		database.closeFile();
	}
	
}
