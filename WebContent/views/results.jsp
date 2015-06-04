<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="fortress.bid.*"
		 import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>BidFortress</title>
	<link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="../boostrapadditions.css" rel="stylesheet">
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
        <li><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li> 
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
	   if(currentSession.getAttribute("LatestItems") !=null){
		   out.println("<div class=text-center><h4>Search Results</h4></div>");
	   }
	   else{
		   out.println("<div class=text-center><h3>No results found</h3></div>");
	   }
       if(currentSession.getAttribute("LatestItems") != null){
    	   HashMap<Integer, Listing> latest = (HashMap<Integer, Listing>)currentSession.getAttribute("LatestItems");
    	   out.println("<div class='col-md-6 col-md-offset-3'><table class='table table-striped table-hover'><tbody>");
    	   for(Listing l : latest.values()){
	    	   out.println("<a href=details.jsp?origin=Results&selectedListing="+l.getId()+"><tr><div class='row'>"+
	    	     "<td><div class='thumbnail col-md-6 col-md-offset-3'>"+
	    	       "<img src='../defaultImage.jpg' width=160 height=160></div><td>"+
	    	       "<td><div>"+
	    	         "<strong><h4>"+l.getItemName()+"</h4></strong>"+
	    	         "<p> Current Price: £"+l.getCurrentBid()+"</p>"+
	    	     "</div></td>"+
	    	   "</div></tr></a>");
	    	   
    	 	}
    	   out.println("</tbody></table></div>");
       }
      
       
       
	%>
</body>
</html>