package fortress.bid.interfaces;

public interface IDataAccess {
	
<<<<<<< HEAD
	public abstract void insertListing(Listing newListing);
	
	public abstract void getListingInProgress(int userID);
	
	public abstract void getListingSold (int userID);
	
	public abstract void removeListings (int [] listingIDs );
	
	public abstract void searchListings (String searchTerm);
	
	public abstract void insertBid (int listingID, int userID, double bidAmount);
	
	public abstract void getListingsPurchased (int userID);
	
	public abstract void insertUser (User newUser);
	
	public abstract void getUserInfo (String usernameEntered);

=======
	
>>>>>>> 0f1daa55a96e44bcc250f73747f906a7412a1f55
}
