<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Twitter Login</title>
</head>

<body>

	<h1>Twitter Sign In Page</h1>
	<p><a href="http://localhost:8080/Twitter">Home</a></p>
	
	<h4>Please log in to your account:</h4>
	<form action="" method="post">
		
		<label >Your username:</label><br/>
		<input type="text" name="login" /><br/>
		
		<label >Your password:</label><br/>
		<input type="password" name="password" /><br/>
	
		<input type="submit" value="Send"/>
	
	</form>
</body>
</html>