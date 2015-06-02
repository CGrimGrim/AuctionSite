package fortress.bid.unittests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fortress.bid.AuctionService;

public class AuctionServiceTest {
	
	String correctUsername = null;
	String correctPassword = null;
	String incorrectUsername = null;
	String incorrectPassword = null;
	AuctionService as = null;
	
	@BeforeClass
	public void setUpBeforeClass() throws Exception{
		as = new AuctionService();
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
	
	

}
