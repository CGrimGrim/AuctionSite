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
<div class="">
	<h3 class=text-center>Sell your item</h3>
	<div class="row col-md-8 col-md-offset-2 form-group panel panel-default" >
	<form class="form-horizontal">
		<div class="col-md-8">
			<label>Item name </label><br>
			<input type=text name=itemname><br>
			<label>Condition </label>
			<% 
			ArrayList<Condition> con = (ArrayList<Condition>)currentSession.getAttribute("ConditionList");
			out.println("<label>Condition </label>");
			out.println("<select name=conditionid  class=form-control>");
			for(Condition c : con){
				out.println("<option value="+c.getId()+">"+c.getName()+"</option>");
			}
		    out.println("</select><br>");
		    out.println("<label>Category </label><br>");
		    ArrayList<Category> cat = (ArrayList<Category>)currentSession.getAttribute("CategoryList");
		    out.println("<select name=categoryid class=form-control>");
		    for(Category c : cat){
		    	out.println("<option value="+c.getId()+">"+c.getName()+"</option>");
		    }
		    out.println("</select>");
		    %>
		    <label>Description</label><br>
		    <textarea name=description rows="3" cols="50"></textarea>
		</div>
		<div class="col-md-4">
	    	<label>Reserve amount </label><input type=number step=any min=0 class=form-control name=reserveamount value=0.00>
	    	<label>Image </label><input type=file><br>
	    	<input type=submit value="Create" class="btn btn-primary">
	    	<input type=hidden name=methodcode value=11>
	    </div>
	</form>
	</div>
</div>
</body> 
</html>