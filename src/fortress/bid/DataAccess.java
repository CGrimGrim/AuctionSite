package fortress.bid;

import javax.naming.*;
import org.apache.tomcat.jdbc.pool.*;
import java.sql.*;

import fortress.bid.interfaces.IDataAccess;
import fortress.bid.*;

public class DataAccess implements IDataAccess{

	DataSource ds;
	
	public DataAccess(){
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:comp/env");
			ds = (DataSource)envContext.lookup("jdbc/TestAddressDB");
		}
		catch(Exception e){
			
		}
	}
	
	@Override
	public void insertListing(Listing newListing) {

	}

	@Override
	public ResultSet getListingInProgress(int userID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet getListingSold(int userID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListings(int[] listingIDs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet searchListings(String searchTerm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertBid(int listingID, int userID, double bidAmount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet getListingsPurchased(int userID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUser(User newUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet getUserInfo(String usernameEntered) {
		// TODO Auto-generated method stub
		
	}

}
