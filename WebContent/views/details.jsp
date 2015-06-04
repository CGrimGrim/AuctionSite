<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="fortress.bid.*"
		 import="java.util.*" 
		 import="fortress.bid.dao.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>BidFortress</title>
	<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="../boostrapadditions.css" rel="stylesheet">
	<link href="../jsadditions.js" rel="text/Javascript">
</head>
<body>
<%
	HttpSession currentSession = request.getSession(false);
%>
	<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="Home.jsp"><span class="glyphicon glyphicon-tower"></span> BidFortress</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <% if(currentSession.getAttribute("CurrentUser") != null){
           out.println("<li><a href=> My Account <span class=sr-only>(current)</span></a></li>");
           out.println("<li><a href=> Sell an Item </a></li>");
           }
         %> 
      </ul>
      <form class="navbar-form navbar-right" role="search">
        <div class="form-group">
          <input type="text" class="form-control" >
        </div>
        <button type="submit" class="btn btn-default">Search</button>
      </form>
      <div class=navbar-right>
      <%
      	if(currentSession.getAttribute("CurrentUser") != null){
			out.println("<button type=button class='btn btn-default navbar-btn' onClick=login.jsp>Logout</button>");
      	}
      	else{
      		out.println("<a href=login.jsp><button type=button class='btn btn-default navbar-btn'>Login</button></a>");
      	}
      %>	
      </div>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div>
	<ul class="nav nav-tabs nav-justified">
	  <li role="presentation"><a href="#">Home & Garden</a></li>
	  <li role="presentation"><a href="#">Leisure</a></li>
	  <li role="presentation"><a href="#">Electricals</a></li>
	  <li role="presentation"><a href="#">Clothing</a></li>
	  <li role="presentation"><a href="#">Jewelery</a></li>
	</ul>
</div>
	<%
		if(session.getAttribute("result") != null){
			switch(session.getAttribute("error").toString()){
			case "bidToLow":
				out.println("<div class='alert alert-danger text-center' role=alert>Your Bid was too low! It must be above the current bid</div>");
				break;
			case "bidOnOwn":
				out.println("<div class='alert alert-danger text-center' role=alert>You cannot bid on your own item!</div>");
				break;
			case "Complete":
				out.println("<div class='alert alert-success text-center' role=alert>Congratulations! You are currently the Highest Bidder</div>");
				break;
			}
		}
		Listing l = null;
		HashMap<Integer, Listing> listings = null;
		Category c = null;
		Condition con = null;
		out.println("<div class= 'col-md-6 col-centered'>");
		if(request.getParameter("origin").equals("Home")){
			listings = (HashMap<Integer, Listing>)currentSession.getAttribute("LatestListings");
			currentSession.setAttribute("origin", "Home");
			
			
		}
		else if(request.getParameter("origin").equals("Results")){
			 listings = (HashMap<Integer, Listing>)currentSession.getAttribute("SearchResults");
			 currentSession.setAttribute("origin", "Results");
		}
		
		String s = request.getParameter("selectedListing");
	    l = listings.get(Integer.parseInt(s));
	    ArrayList<Category> cats = (ArrayList<Category>)currentSession.getAttribute("CategoryList");
		for(Category cat : cats){
			if(cat.getId() == l.getCategory()){
				c = cat;
			}
		}
		ArrayList<Condition> conditions = (ArrayList<Condition>)currentSession.getAttribute("ConditionList");
		for(Condition cons : conditions){
			if(cons.getId() == l.getCondition()){
				con = cons;
			}
		}
		out.println("<a href=results.jsp><p class=text-left><span class='glyphicon glyphicon-chevron-left'></span>Back to Results<p></a>");
		out.println("<div class='col-md-12 col-md-offset-6 panel panel-default'>"+
					"<div class='row'>"+
						"<div class='thumbnail col-md-6'>"+
							"<img src=../defaultImage.jpg>"+
						"</div>"+
						"<div class='form-group panel-body col-md-6'>"+
							"<form action=http://localhost:8080/BidFortress/Controller method=post class='form-inline'>"+
							"<h2>"+l.getItemName()+"</h2>"+
							"<h4>Current bid: £"+l.getCurrentBid()+"<h4>"+
							"<input type=number  step=0.01  min=0 name=enteredbid class='form-control col-md-2'>"+
							"<input type=submit value=Bid class='col-md-4 btn btn-primary'>"+
							"<input type=hidden name=methodcode value=12>"+
							"<input type=hidden name=listingid value="+l.getId()+">"+
							"</form>"+
						"</div></div>"+
					    "<div class='row panel-default  col-mid-8'><div class=panel-heading><strong>Item Description</strong></div>"+
						 "<div class=panel-body>"+l.getDescription()+"</div>"+
					    "<table class='table table-striped text-center'><tr><td><strong>Category</strong></td><td><strong>Condition</strong></td></tr>"+
					    "<tr><td>"+ c.getName() +"</td><td>"+ con.getName() +"</td></tr></table>"+
						"</div></div>"+
					"</div>");
      
       out.println("</div>");
       
	%>
</div>
</body>
</html>