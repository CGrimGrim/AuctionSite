package fortress.bid;

import java.util.HashMap;

public class User {
	private boolean authenticated;
	private int id;
	private String username;
	private String password;
	private HashMap<String, Listing> ListingsInProgress = null;
	private HashMap<String, Listing> ListingsExpired = null;
	private HashMap<String, Listing> ListingsCompleted = null;
	private HashMap<String, Listing> ListingsPurchased = null;
	
	public User(String uname, String pword, boolean authStatus){
		username = uname;
		password = pword;
		authenticated = authStatus;
	}
	
	public User(int uid, String uname, String pword){
		username = uname;
		password = pword;
	}
	
	public boolean isAuthenticated(){
		return authenticated;
	}
	
	public int getUserId(){
		return id;
	}
	
}
