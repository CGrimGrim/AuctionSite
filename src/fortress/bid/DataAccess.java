package fortress.bid;

import javax.naming.*;

import org.apache.tomcat.jdbc.pool.*;

import java.sql.*;

import fortress.bid.interfaces.IDataAccess;

public class DataAccess implements IDataAccess{

	DataSource ds;
	Connection connection = null;
	
	public DataAccess(){
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:comp/env");
			ds = (DataSource)envContext.lookup("jdbc/TestAddressDB");
		}
		catch(Exception e){}
	}
	
	@Override
	public void insertListing(Listing newListing) {
		try{
			connection = ds.getConnection();
			
			int userID = newListing.getId();
			String itemName = newListing.getItemName();
			String desc = newListing.getDescription();
			byte condID = newListing.getCondition();
			byte catID = newListing.getCategory();
			double reserveAmount = newListing.getReserveAmount();
			Date endDate = Date.valueOf(newListing.getEndDate());
			Time endTime = Time.valueOf(newListing.getEndTime());
			byte statusID = newListing.getStatus();
			
			PreparedStatement statement = connection.prepareStatement("Call newListing(?,?,?,?,?,?,?,?,?);");
				statement.setInt(1, userID);
				statement.setString(2, itemName);
				statement.setString(3, desc);
				statement.setByte(4, condID);
				statement.setByte(5, catID);
				statement.setDouble(6, reserveAmount);
				statement.setDate(7, endDate);
				statement.setTime(8, endTime);
				statement.setByte(9, statusID);
				
				statement.execute();
		}
		catch(SQLException e){}
	}

	@Override
	public ResultSet getListingInProgress(int userID) {
		ResultSet rs = null;
		
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement("select * from saleitems where user_id=?;");
			statement.setInt(1, userID);
			rs = statement.executeQuery();
		}
		catch(SQLException e){}
		
		return rs;
	}

	@Override
	public ResultSet getListingSold(int userID) {
		ResultSet rs = null;
		
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement("select * from solditems where user_id=?;");
			statement.setInt(1, userID);
			rs = statement.executeQuery();
		}
		catch(SQLException e){}
		
		return rs;
	}

	@Override
	public void removeListings(int[] listingIDs) {
		try{
			connection = ds.getConnection();
			for(int i = 0; i<listingIDs.length;i++){
				PreparedStatement statement = connection.prepareStatement("call removelisting(?);");
				
				statement.setInt(1,listingIDs[i]);
				statement.execute();
			}
		}
		catch(SQLException e){}
	}

	@Override
	public ResultSet searchListings(String searchTerm) {
		ResultSet rs = null;
		
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement("call search(?);");
			statement.setString(1, searchTerm);
			rs = statement.executeQuery();
		}
		catch(SQLException e){}
		
		return rs;
	}

	@Override
	public void insertBid(int listingID, int userID, double bidAmount) {
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement("call placebid(?,?,?)");
			
			statement.setInt(1, listingID);
			statement.setInt(2, userID);
			statement.setDouble(3, bidAmount);
			
			statement.execute();
		}
		catch(SQLException e){}
	}

	@Override
	public ResultSet getListingsPurchased(int userID) {
		ResultSet rs = null;
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement("call purchasehistory(?)");
			statement.setInt(1, userID);
			rs = statement.executeQuery();
		}
		catch(SQLException e){}
		return rs;
	}

	@Override
	public void insertUser(User newUser) {
		try{
			connection = ds.getConnection();
			String userName = newUser.getUsername();
			String password = newUser.getPassword();
			//String email = newUser.getEmail();
			
			PreparedStatement statement = connection.prepareStatement("call newuser(?,?,?)");
			
			statement.setString(1, userName);
			statement.setString(2, password);
			statement.setString(3, "null");
			
			statement.execute();
		}
		catch(SQLException e){}
	}

	@Override
	public ResultSet getUserInfo(String usernameEntered) {
		ResultSet rs = null;
		
		try{
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement("select * from user where username=?");
			statement.setString(1, usernameEntered);
			statement.execute();
		}
		catch(SQLException e){}
		
		return rs;
	}

	
	@Override
	public ResultSet getListingHighestBid(int listingID) {
	   try{
		connection = ds.getConnection();
		CallableStatement cs = connection.prepareCall("call listinghighestbidbylistingid(?)");
		cs.setInt(1, listingID);
		return cs.executeQuery();
	   }
	   catch(SQLException e){
	   }
	   return null;
	}
	

	@Override
	public ResultSet getListingsExpired(int userID) {
		try{
			connection = ds.getConnection();
			PreparedStatement ps = connection.prepareStatement("Select * from ");
		    ps.setInt(1, userID);
	    return ps.executeQuery();
		}
		catch(SQLException e){
			
		}
		
		return null;
	}

}
