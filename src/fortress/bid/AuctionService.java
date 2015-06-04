package fortress.bid;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

import fortress.bid.exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;

import fortress.bid.interfaces.*;
import fortress.bid.dao.*;

public class AuctionService implements IAuctionService{
	
	IDataAccess da = null;
	
	public AuctionService(){
		da = new DataAccess();
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
				HashMap<Integer, Listing> listingsInProgress = new HashMap<>();
				while(rs.next()){
				     Listing l = new Listing(rs.getInt(1), rs.getInt(2),
				    		 				rs.getString(3), rs.getString(4),
				    		 				rs.getByte(5), rs.getByte(6),
				    		 				rs.getDouble(6), rs.getDate(7).toLocalDate(),
				    		 				rs.getTime(8).toLocalTime(), rs.getByte(8));
				     Integer i = new Integer(rs.getInt(1));
				     ResultSet hb = da.getListingHighestBid(rs.getInt(1));
				     hb.next();
				     l.setCurrentBid(hb.getDouble(3));
				     listingsInProgress.put(i, l);
				     
				}
				
			currentUser.setListingsInProgress(listingsInProgress);
				
			}
		}
		catch(SQLException e){
			
		}
		
		return currentUser;
	}

	@Override
	public User getListingsSold(User currentUser) {
		try{
		ResultSet rs = da.getListingSold(currentUser.getUserId());
		if (!rs.isBeforeFirst()){
			return currentUser;
		}
		else{
			HashMap<Integer, Listing> ListingsSold = new HashMap<>();
			while (rs.next()){
			Listing l = new Listing (rs.getInt(1), rs.getInt(2),rs.getString(3),
									rs.getString(4), rs.getByte(5), rs.getByte(6),
									rs.getDouble(7), rs.getDate(8).toLocalDate(), rs.getTime(9).toLocalTime(),
									rs.getByte(10));
			Integer i = new Integer(rs.getInt(1));
			ResultSet hb = da.getListingHighestBid(rs.getInt(1));
		     hb.next();
		     l.setCurrentBid(hb.getDouble(3));
			ListingsSold.put(i, l);
			
			}
			
			currentUser.setListingsSold(ListingsSold);
		}
		
		}
		catch(SQLException e){
			
			
		}
		
		return currentUser;
	}

	@Override
	public User getListingsExpired(User currentUser) {
		try{
		ResultSet rs = da.getListingsExpired(currentUser.getUserId());
		if (!rs.isBeforeFirst()){
			return currentUser;
		}
		else{
			HashMap<Integer, Listing> ListingsExpired = new HashMap<>();
			while (rs.next()){
			Listing l = new Listing (rs.getInt(1), rs.getInt(2),rs.getString(3),
									rs.getString(4), rs.getByte(5), rs.getByte(6),
									rs.getDouble(7), rs.getDate(8).toLocalDate(), rs.getTime(9).toLocalTime(),
									rs.getByte(10));
			Integer i = new Integer(rs.getInt(1));
			ResultSet hb = da.getListingHighestBid(rs.getInt(1));
		     hb.next();
		     l.setCurrentBid(hb.getDouble(3));
			ListingsExpired.put(i, l);
			
			}
			
			currentUser.setListingsExpired(ListingsExpired);
		}
		
		}
		
		catch(SQLException e){
			
		}
		
		return currentUser;
	}

	@Override
	public User getListingsPurchased(User currentUser) {
		try{
		ResultSet rs = da.getListingsPurchased(currentUser.getUserId());
		if (!rs.isBeforeFirst()){
			return currentUser;
		}
		else{
			HashMap<Integer, Listing> ListingsPurchased = new HashMap<>();
			while (rs.next()){
			Listing l = new Listing (rs.getInt(1), rs.getInt(2),rs.getString(3),
									rs.getString(4), rs.getByte(5), rs.getByte(6),
									rs.getDouble(7), rs.getDate(8).toLocalDate(), rs.getTime(9).toLocalTime(),
									rs.getByte(10));
			Integer i = new Integer(rs.getInt(1));
			ResultSet hb = da.getListingHighestBid(rs.getInt(1));
		    hb.next();
		    l.setCurrentBid(hb.getDouble(3));
			ListingsPurchased.put(i, l);
			
			}
			
			currentUser.setListingsPurchased(ListingsPurchased);
		}
		
		}
		
		catch(SQLException e){
			
		}
		
		return currentUser;
	}

	@Override
	public void placeBid(int userID, Listing listing, double bidAmount) throws BidLowerThanCurrentBidException, BidOnOwnListingException, NegativeBidException {
		
		if(bidAmount <= 0){
			throw new NegativeBidException(userID + "attempting to insert invalid bid on : " + listing.getId());
		}
		else if(listing.getSellerid() == userID){
			throw new BidOnOwnListingException(userID + " attempting to bid on listing : " + listing.getId());
		}
		else if(listing.getCurrentBid() >= bidAmount ){
			throw new BidLowerThanCurrentBidException(userID + "attempting to insert invalid bid on : " + listing.getId());
		}		
		else {
			da.insertBid(listing.getId(), userID, bidAmount);
		}
	}

	@Override
	public HashMap<Integer, Listing> searchForListing(String searchTerm) {
		try{
			ResultSet rs = da.searchListings(searchTerm);
			HashMap<Integer, Listing> searchResult = new HashMap<>();
			if(!rs.isBeforeFirst()){
				return null;
			}
			else{
				while(rs.next()){
					Listing l = new Listing (rs.getInt(1), rs.getInt(2),rs.getString(3),
							rs.getString(4), rs.getByte(5), rs.getByte(6),
							rs.getDouble(7), rs.getDate(8).toLocalDate(), rs.getTime(9).toLocalTime(),
							rs.getByte(10));
					ResultSet hb = da.getListingHighestBid(rs.getInt(1));
				    hb.next();
				    l.setCurrentBid(hb.getDouble(3));
				    Integer i = new Integer(rs.getInt(1));
				    searchResult.put(i, l);
				}
				
			}
			
			return searchResult;
		}
		catch(Exception e){
		}
		
		return null;
	}

	@Override
	public void registerUser(String username, String password, String email) {
		User newUser = new User(username, password);
		da.insertUser(newUser);
		
	}

	@Override
	public void removeListings(int[] listingIDs) {
		da.removeListings(listingIDs);
	}

	
	@Override
	public HashMap<Integer, Listing> getLatestListings() {
		try{
			ResultSet rs = da.getLatestListing();
			HashMap<Integer, Listing> latestListings = new HashMap<>();
			if(!rs.isBeforeFirst()){
				return null;
			}
			else{
				while(rs.next()){
					Listing l = new Listing (rs.getInt(1), rs.getInt(2),rs.getString(3),
											rs.getString(4), rs.getByte(5), rs.getByte(6),
											rs.getDouble(7), rs.getDate(8).toLocalDate(), rs.getTime(9).toLocalTime(),
											rs.getByte(10));
					Integer i = new Integer(rs.getInt(1));
					ResultSet hb = da.getListingHighestBid(rs.getInt(1));
					hb.next();
					l.setCurrentBid(hb.getDouble(3));
					latestListings.put(i, l);
				}
				
				return latestListings;
			}
		}
		catch(SQLException e){
			
		}
		return null;
		
	}

	@Override
	public void createListing(int sellerId, String name, String desc, int condition, int category, double reserveAmount, LocalDate endDate, LocalTime endTime, byte status) {
		Listing l = new Listing(sellerId, name, desc, (byte)condition, (byte)category, reserveAmount, endDate, endTime, (byte)status);
		da.insertListing(l);
	}
	
	public ArrayList getConditionList(){
		ResultSet rs = da.getConditionList();
		ArrayList conditionList = new ArrayList();
		if(!rs.isBeforeFirst()){
			return null;
		}
		else{
			while(rs.next()){
				Condition c = new Condition(rs.getByte(1),rs.getString(2));
				conditionList.add(c);
				return conditionList;
			}
		}
	}
	
	public ArrayList getCategoryList(){
		ResultSet rs = da.getCategoryList();
		ArrayList categoryList = new ArrayList();
		if(!rs.isBeforeFirst()){
			return null;
		}
		else{
			while(rs.next()){
				Category c = new Category(rs.getByte(1),rs.getString(2));
				categoryList.add(c);
				return categoryList;
			}
		}
	}
	
	public ArrayList getStatusList(){
		ResultSet rs = da.getStatusList();
		ArrayList statusList = new ArrayList();
		if(!rs.isBeforeFirst()){
			return null;
		}
		else{
			while(rs.next()){
				Status c = new Status(rs.getByte(1),rs.getString(2));
				statusList.add(c);
				return statusList;
			}
		}
	}
}
