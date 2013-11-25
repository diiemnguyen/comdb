<!DOCTYPE html>
<html>

<head>
<meta charset="US-ASCII">
<title>Login Page</title>
<link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<h3>Existing Customer Login</h3>
<form action="Login" method="post">
    <strong>Email</strong>:<input type="text" name="email"><br>
    <input type="submit" value="Login">
</form>

<br>
Not yet a customer? please <a href="register.jsp">register</a>

<br><br><br><br><br><br><br><br><br>
<h3>Administrator Login</h3>
<form action="AdminLogin" method="post">
    <strong>Admin Email</strong>:<input type="text" name="admin_email"><br>
    <input type="submit" value="AdminLogin">
</form>

</body>
</html>