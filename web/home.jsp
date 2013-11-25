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
<input type="submit" value="Logout" >
</form>

<br /><br />
<div><b>Our Inventory up to date:</b></div>
<form method="post">

<table border="2">
<tr>
    <td>Product Title</td>
    <td>Quantity</td>
    <td>Price</td>
</tr>
<%
try
{
Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://localhost/companydb";
String username="root";
String password="Passw0rd";
String query="select prod_Title, prod_Case_Qty, prod_Case_Price from inventory";
Connection conn=DriverManager.getConnection(url,username,password);
Statement stmt=conn.createStatement();
ResultSet rs=stmt.executeQuery(query);
while(rs.next())
{
    String title = rs.getString("prod_Title");
    int qty = rs.getInt("prod_Case_Qty");
    double price = rs.getDouble("prod_Case_Price");
%>
    <tr>
        <td><% out.print(title); %></td>
        <td><% out.print(qty); %></td>
        <td><% out.print(price); %></td>
    </tr>
<%

}
%>
    </table>
    <%
    rs.close();
    stmt.close();
    conn.close();
    }
catch(Exception e)
{
    e.printStackTrace();
}


%>

</form>
    

</body>

</html>