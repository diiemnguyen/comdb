<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.comdb.util.User"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">

<title>Home Page</title>
<link rel="stylesheet" href="css/style.css" type="text/css">

</head>
<body>
<%User user = (User) session.getAttribute("User"); %>
<h3>Hi <%=user.getName() %></h3>
<strong>Email</strong>: <%=user.getEmail() %><br>
<br>
<form action="Logout" method="post">
<input type="submit" value="Admin Logout" >
</form>


<br> <br> <br> <br> <br> <br> <br>

<div class="navigator">
<a id="currenttab" href="adminHome.jsp">Add</a>
<a href="delete.jsp">Delete</a>
</div>

<br> <br> <br> 

<form action="AdminHome" method="post">
    <div><b>Admin wants to add a new customer</b></div><br><br><br>
<strong>Email</strong>:<input type="text" name="email"><br>
<strong>Name</strong>:<input type="text" name="name"><br>
<br>
<input type="submit" value="Admin Add New Customer">
</form>


<br /><br />

</body>

</html>