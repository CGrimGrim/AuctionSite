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
<div>
	<h3>Sell your item</h3>
	<div class="row">
	<form action="controller" method="post">
		<div>
			<label>Item name </label><input type=text name=itemname>
			<label>Condition </label>
			<% 
			ArrayList<Condition> con = (ArrayList<Condition>)currentSession.getAttribute("Conditions");
			out.println("<label>Condition </label>");
			out.println("<select name=conditionid  class=form-control>");
			for(Condition c : con){
				out.println("<option value="+c.getId()+">"+c.getName()+"</option>");
			}
		    out.println("</select>");
		    out.println("<label>Category </label>");
		    ArrayList<Category> cat = (ArrayList<Category>)currentSession.getAttribute("Categories");
		    out.println("<select name=categoryid class=form-control>");
		    for(Category c : cat){
		    	out.println("<option value="+c.getId()+">"+c.getName()+"</option>");
		    }
		    out.println("</select>");
		    %>
		    <label>Description</label>
		    <textarea name=description rows="3"></textarea>
		</div>
		<div>
	    	<label>Reserve amount </label><input type=text class=form-control name=reserveamount>
	    	<label>Image </label><input type=file>
	    	<input type=submit value="Create" class="btn btn-primary">
	    	<input type=hidden value=11>
	    </div>
	</form>
	</div>
</div>
</body> 
</html>