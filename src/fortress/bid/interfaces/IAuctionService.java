package fortress.bid.interfaces;

import java.util.ArrayList;

import fortress.bid.*;
import fortress.bid.exceptions.BidLowerThanCurrentBidException;
import fortress.bid.exceptions.BidOnOwnListingException;
import fortress.bid.exceptions.NegativeBidException;

public interface IAuctionService {
	
	public abstract User validateUser (String usernameEntered, String passwordEntered);
	
	public abstract User getListingsInProgress (User currentUser);
	
	public abstract User getListingsSold (User currentUser);
	
	public abstract User getListingsExpired (User currentUser);
	
	public abstract User getListingsPurchased (User currentUser);
	
	public abstract void placeBid (int userID, Listing listing, double bidAmount) throws BidLowerThanCurrentBidException, BidOnOwnListingException, NegativeBidException;
	
	public abstract ArrayList<Listing> searchForListing (String searchTerm);
	
	public abstract void registerUser (String username, String password, String email);
	
	public abstract void removeListings (int[] listingIDs);

}
