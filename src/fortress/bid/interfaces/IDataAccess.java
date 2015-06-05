package fortress.bid.interfaces;

import fortress.bid.*;
import java.sql.ResultSet;

public interface IDataAccess {
	

	/**
	 * Adds a new listing to the database
	 * @param newListing the listing to be inserted
	 */
	public abstract void insertListing(Listing newListing);
	
	/**
	 * Gets all of a users listings in which they are acting as the seller that are currently in progress and have not yet sold or expired
	 * @param userID id of the user currently logged in
	 */
	public abstract ResultSet getListingInProgress(int userID);
	
	/**
	 * Gets all of the users listings where they are were acting as the seller which have successfully sold (time has expired and reservation amount was exceeded) 
	 * @param userID id of the user currently logged in
	 */
	public abstract ResultSet getListingSold (int userID);
	
	/**
	 * removes listings which are no longer in progress and have expired (no buyer found for the listing)
	 * @param listingIDs 1 or more listingID's to be removed from the auctions
	 */
	public abstract void removeListings (int [] listingIDs );
	
	/**
	 * searches for all listings where the searchTerm parameter is applicable
	 * @param searchTerm string to search listings for
	 */
	public abstract ResultSet searchListings (String searchTerm);
	
	
	/**
	 * Inserts a bid
	 * @param listingID listing to bid against
	 * @param userID user creating the bid
	 * @param bidAmount the amount to bid against the item
	 */
	public abstract void insertBid (int listingID, int userID, double bidAmount);
	
	/**
	 * Gets all listings where the user has won the auction
	 * @param userID user currently logged in
	 */
	public abstract ResultSet getListingsPurchased (int userID);
	
	/**
	 * adds a new user into the database
	 * @param newUser the user to be registered
	 */
	public abstract void insertUser (User newUser);
	
	/**
	 * gets all information about a user
	 * @param usernameEntered username of the user to retrieve information
	 */
	public abstract ResultSet getUserInfo (String usernameEntered);
	
	public abstract ResultSet getListingHighestBid (int listingID);
	
	public abstract ResultSet getListingsExpired(int userID);

	public abstract ResultSet getLatestListing();
	
	public abstract ResultSet getConditionList();
	
	public abstract ResultSet getCategoryList();
	
	public abstract ResultSet getStatusList();
	
	public abstract ResultSet getListingsByCategory(int categoryID);
	

}
