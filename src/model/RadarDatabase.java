package model;


public class RadarDatabase {

	private Account[] accounts;
	
	public RadarDatabase() {
		accounts = new Account[2];
		accounts[0] = new Account(100, "Bob", 12345, -35.7345, -57.4532);
		accounts[1] = new Account(200, "Steve", 54321, 47.9876, 153.4857);
	}
	
	private Account getAccount(int accountNumber) {
		for (Account currentAccount : accounts) {
			if (currentAccount.getAccountNumber() == accountNumber)
				return currentAccount;
		}
		return null;
	}
	
	public boolean authenticateUser(int accountNumber, int password) {

		Account userAccount = getAccount(accountNumber);
		
		if (userAccount != null)
			return userAccount.validatePassword(password);
		else
			return false;		
	}
	
	public double getLatitudeCoordinate(int accountNumber) {
		return getAccount(accountNumber).getLatitude();
	}
	
	public double getLongitudeCoordinate(int accountNumber) {
		return getAccount(accountNumber).getLongitude();
	}
	
	public void updatePosition(int accountNumber, Double latitude, Double longitude) {
		getAccount(accountNumber).updatePosition(latitude, longitude);
	}
	
}
