package fortress.bid;

import javax.annotation.Resource;
import javax.naming.*;

import org.apache.tomcat.jdbc.pool.*;

import java.sql.*;

import fortress.bid.interfaces.IDataAccess;

public class DataAccess implements IDataAccess{

	
	Connection connection = null;
	
	public DataAccess(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){}
	}
	
	public void getConnection(){
		try{
			System.out.println("Setting Connection");
			
		}
		catch(Exception e){
			
		}
	}
	
	@Override
	public void insertListing(Listing newListing) {
		try{
			
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

			PreparedStatement statement = connection.prepareStatement("call placebid(?,?,?)");
			
			statement.setInt(1, listingID);
			statement.setInt(2, userID);
			statement.setDouble(3, bidAmount);
			
			statement.execute();
		}
		catch(SQLException e){
			System.out.println("Exception occured placing bid");
		}
	}

	@Override
	public ResultSet getListingsPurchased(int userID) {
		ResultSet rs = null;
		try{

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
			connection = DriverManager.getConnection("jdbc:mysql://192.168.1.13/auctionsite", "newuser", "password");
			PreparedStatement statement = connection.prepareStatement("select * from users where username=?");
			statement.setString(1, usernameEntered);
			rs = statement.executeQuery();
		}
		catch(SQLException e){ System.out.println("Exception : " + e.getMessage());}
		
		return rs;
	}

	@Override
	public ResultSet getListingHighestBid(int listingID) {
	   try{
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

			PreparedStatement ps = connection.prepareStatement("Select * from ");
		    ps.setInt(1, userID);
	    return ps.executeQuery();
		}
		catch(SQLException e){
			
		}
		
		return null;
	}

	public ResultSet getLatestListing(){
		try{
			connection = DriverManager.getConnection("jdbc:mysql://192.168.1.13/auctionsite", "newuser", "password");
			return connection.prepareStatement("Select * from latestitems").executeQuery();
		}
		catch(SQLException e){
			System.out.println("Exception : " + e.getMessage());
			System.out.println("Exception : " + e.getCause());
		}
		return null;
	}
	
	public ResultSet getConditionList(){
		try{
			return connection.prepareStatement("Select * from conditions").executeQuery();
		}
		catch(SQLException e){
			
		}
		return null;
	}
	
	public ResultSet getCategoryList(){
		try{
			return connection.prepareStatement("Select * from category").executeQuery();
		}
		catch(SQLException e){
			
		}
		return null;
	}
	
	public ResultSet getStatusList(){
		try{
			return connection.prepareStatement("Select * from status").executeQuery();
		}
		catch(SQLException e){
			
		}
		return null;
	}

	public ResultSet getListingsByCategory(int categoryID){
		   try{
				CallableStatement cs = connection.prepareCall("call listinghighestbidbycategoryID(?)");
				cs.setInt(1, categoryID);
				return cs.executeQuery();
			   }
			   catch(SQLException e){
			   }
			   return null;
			}


	}
	
