package fortress.bid;

import java.util.HashMap;

public class User {
	private boolean authenticated;
	private int id;
	private String username;
	private String password;
	private HashMap<Integer, Listing> listingsInProgress = null;
	private HashMap<Integer, Listing> listingsExpired = null;
	private HashMap<Integer, Listing> listingsSold = null;
	private HashMap<Integer, Listing> listingsPurchased = null;
	
	public User(String uname, String pword, boolean authStatus){
		username = uname;
		password = pword;
		authenticated = authStatus;
	}
	
	public User(int uid, String uname, String pword){
		id = uid;
		username = uname;
		password = pword;
	}
	
	public User(String uname, String pword){
		username = uname;
		password = pword;
	}
	
	public boolean isAuthenticated(){
		return authenticated;
	}
	
	public int getUserId(){
		return id;
	}
	
	public void setListingsInProgress(HashMap<Integer, Listing>  newListings){
		listingsInProgress = newListings;
	}
	
	public void setListingsExpired(HashMap<Integer, Listing> newListings){
		listingsExpired = newListings;
	}
	
	public void setListingsSold(HashMap<Integer, Listing> newListings){
		listingsSold = newListings;
	}
	
	public void setListingsPurchased(HashMap<Integer, Listing> newListings){
		listingsPurchased = newListings;
	}
	
	public HashMap<Integer, Listing> getListingsInProgress(){
		return listingsInProgress;
	}
	
	public HashMap<Integer, Listing> getListingsExpired(){
		return listingsExpired;
	}
	
	public HashMap<Integer, Listing> getlistingsPurchased(){
		return listingsPurchased;
	}
	
	public HashMap<Integer,Listing> getListingsSold(){
		return listingsSold;
	}
	
	public void authenticate(){
		authenticated = true;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
}
