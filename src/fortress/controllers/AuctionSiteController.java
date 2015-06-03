package fortress.controllers;

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

public class AuctionSiteController extends HttpServlet{
	
	AuctionService auctionService = new AuctionService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		switch(input){
		case 1:
			getLatestItems(request,response);
		case 2:
			getSearchResults(request,response);
		case 3:
			getListingDetails();
		case 4:
			getListingsPurchased(request,response);
		case 5:
			getListingsExpired(request,response);
		case 6:
			getListingsSold(request,response);
		case 7:
			getListingsInProgress(request,response);
		case 8:
			getCreateListingLists(request,response);
		default:
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		
		switch(input){
		case 9:
			postLogin(request,response);
		case 10:
			postRegisterUser(request,response);
		case 11:
			postCreateListing(request,response);
		case 12:
			postBid();
		case 13:
			postRemoveListing();
		case 14:
			//Empty for now
		default: 
		}
	}
	//******************************************************************************************************************
	private void getLatestItems(HttpServletRequest request, HttpServletResponse response){
		HashMap<Integer,Listing> latestItems = auctionService.getLatestListings();
		
		request.setAttribute("latestItems", latestItems);
		try {
			request.getRequestDispatcher("").forward(request, response);
		}
		catch (ServletException e) {}
		catch (IOException e) {}
	}
	
	private void getSearchResults(HttpServletRequest request, HttpServletResponse response){
		String searchTerm = request.getParameter("search");
		HashMap<Integer,Listing> searchResults = auctionService.searchForListing(searchTerm);
		
		request.setAttribute("searchResults", searchResults);
		try {
			request.getRequestDispatcher("").forward(request, response);
		}
		catch (ServletException e) {}
		catch (IOException e) {}
	}
	
	private User getListingDetails(){
		return null;
	}
	
	private void getListingsPurchased(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		//User newUser = auctionService.getListingsPurchased(currentUser);
		session.setAttribute("currentUser", auctionService.getListingsPurchased(currentUser));

		try {
			request.getRequestDispatcher("").forward(request, response);
		}
		catch (ServletException e) {}
		catch (IOException e) {}

	}
	
	private void getListingsExpired(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		session.setAttribute("currentUser", auctionService.getListingsExpired(currentUser));

		try {
			request.getRequestDispatcher("").forward(request, response);
		}
		catch (ServletException e) {}
		catch (IOException e) {}
	}
	
	private void getListingsSold(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		session.setAttribute("currentUser", auctionService.getListingsSold(currentUser));

		try {
			request.getRequestDispatcher("").forward(request, response);
		}
		catch (ServletException e) {}
		catch (IOException e) {}
	}
	
	private void getListingsInProgress(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		session.setAttribute("currentUser", auctionService.getListingsInProgress(currentUser));

		try {
			request.getRequestDispatcher("").forward(request, response);
		}
		catch (ServletException e) {}
		catch (IOException e) {}
	}
	
	private void getCreateListingLists(HttpServletRequest request, HttpServletResponse response){
		
		request.setAttribute("conditionList",auctionService.getConditionList());
		request.setAttribute("categoryList",auctionService.getCategoryList());
		request.setAttribute("statusList",auctionService.getStatusList());
		
		try {
			request.getRequestDispatcher("").forward(request, response);
		}
		catch (ServletException e) {}
		catch (IOException e) {}
	}
	
	//******************************************************************************************************************
	private void postLogin(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User currentUser = auctionService.validateUser(username, password);
		
		if(currentUser.isAuthenticated()){
			HttpSession session = request.getSession(true);
			session.setAttribute("currentUser", currentUser);
			
			try {
				response.sendRedirect("");
			}
			catch (IOException e) {}
		}else{
			request.setAttribute("authenticated", false);
			try {
				request.getRequestDispatcher("").forward(request, response);
			}
			catch (ServletException e) {}
			catch (IOException e) {}
		}
	}
	
	private void postRegisterUser(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		auctionService.registerUser(username, password, email);
		
		/*try {
			response.sendRedirect("");
		}
		catch (IOException e) {}*/
	}
	
	private void postCreateListing(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(true);
		User currentUser = (User) session.getAttribute("currentUser");
		
		int sellerid = currentUser.getUserId();
		String itemName = request.getParameter("itemname");
		String description = request.getParameter("description");
		/*String condition = request.getParameter("condition");
		String category = request.getParameter("category");
		String reserveAmount = request.getParameter("reserveamount");
		String currentBid = request.getParameter("currentbid");
		String endDate = request.getParameter("enddate");
		String endTime = request.getParameter("endtime");
		String status = request.getParameter("status");*/
	}
	
	private Listing postBid(){
		return null;
	}
	
	private void postRemoveListing(){
		
	}
	
	private void postForgottenPassword(){
		
	}

}
