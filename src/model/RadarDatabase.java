package model;


public class RadarDatabase {

	private Account[] accounts;
	
	public RadarDatabase() {
		accounts = new Account[2];
		accounts[0] = new Account(100, "Bob", 12345, -35.7345, -57.4532);
		accounts[1] = new Account(200, "Steve", 54321, 47.9876, 153.4857);
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
	
	public Coordinate getLatitude(int account) {
		return getAccount(account).getLatitude();
	}
	
	public Coordinate getLongitudeCoordinate(int account) {
		return getAccount(account).getLongitude();
	}
	
	public void updatePosition(int account, Double longitude, Double latitude ) {
		getAccount(account).setPosition(longitude, latitude);
	}
	
}
