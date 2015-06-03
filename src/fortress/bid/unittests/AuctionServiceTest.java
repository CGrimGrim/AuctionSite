package fortress.bid.unittests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fortress.bid.AuctionService;
import fortress.bid.Listing;
import fortress.bid.User;

public class AuctionServiceTest {
	
	String correctUsername = null;
	String correctPassword = null;
	String incorrectUsername = null;
	String incorrectPassword = null;
	AuctionService as = null;
	User u = null;
	
	@BeforeClass
	public void setUpBeforeClass() throws Exception{
		as = new AuctionService();
		u = new User(3, "SThomas", "SThomas");
	
	}

	@Before
	public void setUp() throws Exception {
		correctUsername = "SThomas";
		correctPassword = "SThomas";
		incorrectUsername = "RFry";
		incorrectPassword = "RFry";
    }

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void validateUserTrueWithCorrectValues(){
	   assertTrue(as.validateUser(correctUsername, correctPassword).isAuthenticated());
	}
	
	@Test
	public void validateUserFalseWithIncorrectPassword(){
		assertFalse(as.validateUser(correctUsername, incorrectPassword).isAuthenticated());
	}
	
	@Test
	public void validateUserFalseWithIncorrectUsername(){
		assertFalse(as.validateUser(incorrectUsername, correctPassword).isAuthenticated());
	}
	
	@Test
	public void validateUserFalseWithBlankUsername(){
		assertFalse(as.validateUser(correctUsername, "").isAuthenticated());
	}
	
	@Test
	public void validateUserFalseWithBlankPassword(){
		assertFalse(as.validateUser("", correctPassword).isAuthenticated());
		
	}
	
	@Test
	public void getListingsInProgressReturnsListingInProgress(){
		u = as.getListingsInProgress(u);
		HashMap<Integer, Listing> results = u.getListingsInProgress();
		assertTrue(results.containsKey(2));
		
	}
	
	@Test
	public void getListingsInProgressReturnsNoListingsInProgress(){
		u = as.getListingsInProgress(u);
		HashMap<Integer, Listing> results = u.getListingsInProgress();
		assertTrue(results.isEmpty());
		
	}
	
	@Test
	public void getListingsCompletedReturnListingsCompleted(){
		u = as.getListingsSold(u);
		HashMap<Integer, Listing> results = u.getListingsCompleted();
		assertTrue(results.containsKey(3));
		
	}
	
	@Test
	public void getListingsCompletedReturnsNoListingsCompleted(){
		u = as.getListingsSold(u);
		HashMap<Integer, Listing> results = u.getListingsCompleted();
		assertTrue(results.isEmpty());
		
	}
	

}
