package fortress.bid;

import java.sql.*;
import java.util.HashMap;

import fortress.bid.interfaces.*;

public class AuctionService implements IAuctionService{
	
	IDataAccess da = null;
	
	public AuctionService(){
		IDataAccess da = new DataAccess();
	}

	@Override
	public User validateUser(String usernameEntered, String passwordEntered) {
		try{
			ResultSet rs = da.getUserInfo(usernameEntered);
			if(!rs.isBeforeFirst()){
				return new User(usernameEntered, passwordEntered, false);
			}
			else{
				rs.next();
				if(passwordEntered.equals(rs.getString(3))){
					return new User(usernameEntered, passwordEntered, true);
				}
			}
			
		}
		catch(SQLException e){
			
		}
		
		return new User(usernameEntered, passwordEntered, false);
		
	}

	@Override
	public User getListingsInProgress(User currentUser) {
		try{
			ResultSet rs = da.getListingInProgress(currentUser.getUserId());
			if(!rs.isBeforeFirst()){
				return currentUser;
			}
			else{
				HashMap<String, Listing> listingsInProgress = new HashMap<>();
				while(rs.next()){
				     Listing l = new Listing(rs.getInt(1), rs.getInt(2),
				    		 				rs.getString(3), rs.getString(4),
				    		 				rs.getByte(5), rs.getByte(6),
				    		 				rs.getDouble(6), rs.getDate(7).toLocalDate(),
				    		 				rs.getTime(8).toLocalTime(), rs.getByte(8));
				     
				}
			
			}
		}
		catch(SQLException e){
			
		}
		
	}

	@Override
	public User getListingsSold(User currentUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getListingsExpired(User currentUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getListingsPurchased(User currentUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void placeBid(int userID, int listingID, double bidAmount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Listing> searchForListing(String searchTerm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerUser(String username, String password, String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListings(int[] listingIDs) {
		// TODO Auto-generated method stub
		
	}
	
	

}
