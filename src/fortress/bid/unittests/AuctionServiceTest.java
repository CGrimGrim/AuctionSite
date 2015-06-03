package fortress.bid.unittests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fortress.bid.AuctionService;
import fortress.bid.Listing;
import fortress.bid.User;
import fortress.bid.exceptions.BidLowerThanCurrentBidException;
import fortress.bid.exceptions.BidOnOwnListingException;
import fortress.bid.exceptions.NegativeBidException;

public class AuctionServiceTest {
	
	String correctUsername = null;
	String correctPassword = null;
	String incorrectUsername = null;
	String incorrectPassword = null;
	static AuctionService as = null;
	static User u = null;
	static User p = null;
	static Listing l = null;
	int[] test1 = {1};
	int[] test2 = {1,2,3};
	static Listing latestListing = null;
	static LocalDate date = null;
	static LocalTime time = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		date = LocalDate.parse("2015-06-03", dtf);
		time = LocalTime.parse("15:00:00");
		u = new User(3, "SThomas", "SThomas");
		p = new User(100, "Barney", "The Dinosaur");
		l = new Listing(1, 2 ,"Chair","Something you sit on", (byte)2, (byte)1, 0.00, date, time, (byte)2);
		latestListing = new Listing(4,5,"Cow", "Goes moo moo!", (byte)3, (byte)2, 0.00, date, time, (byte)2);
	
	}

	@Before
	public void setUp() throws Exception {
		correctUsername = "SThomas";
		correctPassword = "SThomas";
		incorrectUsername = "RFry";
		incorrectPassword = "RFry";
		as = new AuctionService();
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
		HashMap<Integer, Listing> results = u.getListingsSold();
		assertTrue(results.containsKey(3));
		
	}
	
	@Test
	public void getListingsCompletedReturnsNoListingsCompleted(){
		u = as.getListingsSold(u);
		HashMap<Integer, Listing> results = u.getListingsSold();
		assertTrue(results.isEmpty());
		
	}
	
	@Test
	public void getListingsExpiredReturnsListingsExpired(){
		
	}
	
	@Test
	public void getListingsExpiredReturnsNoListingExpired(){
		
	}
	
	@Test
	public void getListingsPurchasedReturnsListingsPurchased(){
		
	}
	
	@Test
	public void getListingsPurchasedReturnsNoListingsPurchased(){
		
	}
	
	@Test
	public void getLatestListings(){
		as.createListing(5, "Cow", "Goes Moo! make delicious burgers", (byte)1, (byte)2, 0.00 , date, time, (byte)1);
		HashMap<Integer, Listing> results = as.getLatestListings();
		for(Listing l : results.values()){
			assertEquals("Cow", l.getItemName());
		}
	}

	@Test (expected=BidLowerThanCurrentBidException.class)
	public void placedBidWherebidislowerThanCurrent() throws BidLowerThanCurrentBidException, BidOnOwnListingException, NegativeBidException{
			as.placeBid(1, l, 1.00);
		
	}
	
	@Test (expected=BidOnOwnListingException.class)
	public void placeBidWhereBidderIsSameAsSeller() throws BidLowerThanCurrentBidException, BidOnOwnListingException, NegativeBidException{
		as.placeBid(2,l, 3000.00);
	}
	
	@Test(expected=NegativeBidException.class)
	public void placeBidWhereBidAmountIsNegative() throws BidLowerThanCurrentBidException, BidOnOwnListingException, NegativeBidException{
		as.placeBid(1,l,-10.00);
	}
	
	@Test
	public void placeBidWhereBidIsValid() throws BidLowerThanCurrentBidException, BidOnOwnListingException, NegativeBidException{
		as.placeBid(1,l,3000.00);
	}

	@Test
	public void registerUser(){
		as.registerUser("Paul", "Byrne", "PaulByrne@dwp.gsi.gov.uk");
	}
	
	@Test
    public void removeListingWhereASingleListingIDIsSupplied(){
		as.removeListings(test1);
		u = as.getListingsExpired(u);
		HashMap<Integer, Listing> results = u.getListingsExpired();
		assertFalse(results.containsKey(test1[0]));
		
	}
	
	@Test
	public void removeListingWhereMultipleListingIDsAreSupplied(){	
		as.removeListings(test2);
		u = as.getListingsExpired(u);
		HashMap<Integer, Listing> results = u.getListingsExpired();
		assertFalse(results.containsKey(test2[0]) && results.containsKey(test2[1]) && results.containsKey(test2[2]));
	}


}
