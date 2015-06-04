package fortress.bid.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;

import fortress.bid.AuctionService;
import fortress.bid.Listing;
import fortress.bid.User;
import fortress.bid.exceptions.BidLowerThanCurrentBidException;
import fortress.bid.exceptions.BidOnOwnListingException;
import fortress.bid.exceptions.NegativeBidException;

public class AuctionSiteController extends HttpServlet{
	

	AuctionService auctionService = null;
	@Override
	public void init(ServletConfig sc) throws ServletException{
		auctionService = new AuctionService();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String s = request.getParameter("methodcode");
		int input = 0;
		if(request.getParameter("methodcode") == null){
		    input = 9999;
		}else{
			input = Integer.parseInt(s);
		}
		switch(input){
		case 1:
			getLatestItems(request,response);
			break;
		case 2:
			getSearchResults(request,response);
			break;
		case 3:
			getListingDetails(request,response);
			break;
		case 4:
			getListingsPurchased(request,response);
			break;
		case 5:
			getListingsExpired(request,response);
			break;
		case 6:
			getListingsSold(request,response);
			break;
		case 7:
			getListingsInProgress(request,response);
			break;
		case 8:
			getCreateListingLists(request,response);
			break;
		default:
			getLatestListings(request,response);
			break;
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		
		switch(Integer.parseInt(request.getParameter("methodcode"))){
		case 9:
			postLogin(request,response);
			break;
		case 10:
			postRegisterUser(request,response);
			break;
		case 11:
			postCreateListing(request,response);
			break;
		case 12:
			postBid(request,response);
			break;
		case 13:
			postRemoveListing(request,response);
			break;
		case 14:
			//Empty for now
		default:
			getLatestListings(request,response);
			break;
		}
	}
	//******************************************************************************************************************
	private void getLatestListings(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.setAttribute("LatestListings",auctionService.getLatestListings());
		getCreateListingLists(request, response);
		try {
			response.sendRedirect("views/Home.jsp");
		}
		catch (IOException e) {}
	}
	
	private void getLatestItems(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		HashMap<Integer,Listing> latestItems = auctionService.getLatestListings();
		
		session.setAttribute("LatestItems", latestItems);
		
		try {
			response.sendRedirect("");
		}
		catch (IOException e) {}
		
		/*try {
			request.getRequestDispatcher("").forward(request, response);
		}
		catch (ServletException e) {}
		catch (IOException e) {}*/
	}
	
	private void getSearchResults(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		String searchTerm = request.getParameter("search");
		HashMap<Integer,Listing> searchResults = auctionService.searchForListing(searchTerm);
		
		session.setAttribute("SearchResults", searchResults);
		
		try {
			response.sendRedirect("");
		}
		catch (IOException e) {}
		
		/*try {
			request.getRequestDispatcher("").forward(request, response);
		}
		catch (ServletException e) {}
		catch (IOException e) {}*/
	}
	
	private void getListingDetails(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(true);
		HashMap<Integer,Listing> searchResults = (HashMap<Integer, Listing>) session.getAttribute("SearchResults");
		Listing listing = searchResults.get(Integer.parseInt(request.getParameter("listingid")));
	}
	
	private void getListingsPurchased(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		//User newUser = auctionService.getListingsPurchased(currentUser);
		session.setAttribute("CurrentUser", auctionService.getListingsPurchased(currentUser));
		
		try {
			response.sendRedirect("");
		}
		catch (IOException e) {}

		/*try {
			request.getRequestDispatcher("").forward(request, response);
		}
		catch (ServletException e) {}
		catch (IOException e) {}*/

	}
	
	private void getListingsExpired(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		session.setAttribute("CurrentUser", auctionService.getListingsExpired(currentUser));
		
		try {
			response.sendRedirect("");
		}
		catch (IOException e) {}

		/*try {
			request.getRequestDispatcher("").forward(request, response);
		}
		catch (ServletException e) {}
		catch (IOException e) {}*/
	}
	
	private void getListingsSold(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		session.setAttribute("CurrentUser", auctionService.getListingsSold(currentUser));
		
		try {
			response.sendRedirect("");
		}
		catch (IOException e) {}

		/*try {
			request.getRequestDispatcher("").forward(request, response);
		}
		catch (ServletException e) {}
		catch (IOException e) {}*/
	}
	
	private void getListingsInProgress(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		session.setAttribute("CurrentUser", auctionService.getListingsInProgress(currentUser));
		
		try {
			response.sendRedirect("");
		}
		catch (IOException e) {}

		/*try {
			request.getRequestDispatcher("").forward(request, response);
		}
		catch (ServletException e) {}
		catch (IOException e) {}*/
	}
	
	private void getCreateListingLists(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.setAttribute("ConditionList",auctionService.getConditionList());
		session.setAttribute("CategoryList",auctionService.getCategoryList());
		session.setAttribute("StatusList",auctionService.getStatusList());
	
		
		/*try {
			request.getRequestDispatcher("").forward(request, response);
		}
		catch (ServletException e) {}
		catch (IOException e) {}*/
	}
	
	//******************************************************************************************************************
	private void postLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession(true);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User currentUser = auctionService.validateUser(username, password);
		System.out.println("currentUser auth status : " + currentUser.isAuthenticated());
		
		if(currentUser.isAuthenticated()){
			session.setAttribute("CurrentUser", currentUser);
			response.sendRedirect("views/Home.jsp");
			return;
		}else{
			session.setAttribute("Authenticated", false);
			response.sendRedirect("views/login.jsp");
			return;
		}

	}
	
	private void postRegisterUser(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		auctionService.registerUser(username, password, email);
		
		try {
			response.sendRedirect("");
		}
		catch (IOException e) {}
	}
	
	private void postCreateListing(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(true);
		User currentUser = (User) session.getAttribute("CurrentUser");
		
		int sellerID = currentUser.getUserId();
		String itemName = request.getParameter("itemname");
		String description = request.getParameter("description");
		byte condition = Byte.parseByte(request.getParameter("condition"));
		byte category = Byte.parseByte(request.getParameter("category"));
		double reserveAmount = Double.parseDouble(request.getParameter("reserveamount"));
		LocalDate endDate = LocalDate.now().plusDays(1);
		LocalTime endTime = LocalTime.now().plusHours(24);
		byte status = Byte.parseByte(request.getParameter("status"));
		
		auctionService.createListing(sellerID, itemName, description, condition, category, reserveAmount, endDate, endTime, status);
		
		try {
			response.sendRedirect("");
		}
		catch (IOException e) {}
	}
	
	private void postBid(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession(true);
		User currentUser = (User) session.getAttribute("CurrentUser");
		if(currentUser == null){
			response.sendRedirect("views/login.jsp");
			return;
		}
		int userID = currentUser.getUserId();
		Listing listing = null;
		String redirectString = null;
		if(session.getAttribute("origin").equals("Home")){
			HashMap<Integer,Listing> LatestListings = (HashMap<Integer, Listing>) session.getAttribute("LatestListings");
			listing = LatestListings.get(Integer.parseInt(request.getParameter("listingid")));
			redirectString = "details.jsp?origin=Home&selectedListing="+request.getParameter("listingid");
		}
		else if(session.getAttribute("origin").equals("Results")){
			HashMap<Integer,Listing> searchResults = (HashMap<Integer, Listing>) session.getAttribute("SearchResults");
			listing = searchResults.get(Integer.parseInt(request.getParameter("listingid")));
			redirectString = "details.jsp?origin=Results&selectedListing="+request.getParameter("listingid");
		}
		double bidAmount = Double.parseDouble(request.getParameter("bidamount"));
		try {
			auctionService.placeBid(userID, listing, bidAmount);
			listing.setCurrentBid(bidAmount);
		}
		catch (BidLowerThanCurrentBidException e1) { session.setAttribute("error", "bidToLow"); response.sendRedirect(redirectString); }
		catch (BidOnOwnListingException e1) { session.setAttribute("error", "bitOnOwn"); response.sendRedirect(redirectString);}
		catch (NegativeBidException e1) {}
		
		try {
			session.setAttribute("error", "Complete");
			response.sendRedirect(redirectString);
		}
		catch (IOException e) {}

	}
	
	private void postRemoveListing(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(true);
		int [] listingIDs = (int[]) session.getAttribute("");
		auctionService.removeListings(listingIDs);
		
		try {
			response.sendRedirect("");
		}
		catch (IOException e) {}
	}
	
	private void postForgottenPassword(){
		
	}

}
