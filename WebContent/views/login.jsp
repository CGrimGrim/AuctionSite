<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="fortress.bid.*" %>
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
 <h1 align=center><span class="glyphicon glyphicon-tower"></span> BidFortress</a></h1>
 <%
	HttpSession currentSession = request.getSession();
    User currentUser = (User)currentSession.getAttribute("CurrentUser");
   	if(currentUser != null){
   		if(!currentUser.isAuthenticated()){
   			out.println("<div class='alert alert-danger' role=alert>Login Failed: Username and/or Password is incorrect</div>");
   		}
   	}
	if(request.getParameter("methodcode") != null){
   		if(request.getParameter("username").equals("")){
   			out.println("<div class='alert alert-warning text-center' role=alert>Oops! looks like you didnt enter your Username</div>");
   		}
   		else if(request.getParameter("password").equals("")){
   			out.println("<div class='alert alert-warning text-center' role=alert>Oops! looks like you didnt enter your Password</div>");
   		   	
   		}
   		
   	}

%><br><br>
<div>
<form class="form-horizontal" method="get">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Username</label>
    <div class="col-sm-4">
      <input name="username" type="text" class="form-control" id="inputEmail3" placeholder="Username">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-4">
      <input name="password" type="password" class="form-control" id="inputPassword3" placeholder="Password">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-4">
      <div class="checkbox">
        <label>
          <input type="checkbox"> Remember me
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-8">
      <button type="submit" class="btn btn-primary col-sm-4">Sign in</button>
    </div>
  </div>
<input type="hidden" name="methodcode" value="2">
</form>
<%


%>
</div>
</body>
</html>