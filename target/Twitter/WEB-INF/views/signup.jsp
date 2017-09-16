<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="form"
				uri="http://www.springframework.org/tags/form"	%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>

<h1>Sign up</h1>
<p><a href="http://localhost:8080/Twitter">Home page</a></p>
<h2>Create your account</h2>
<form:form method="post" modelAttribute="user">
	
	<form:label path="username">Create your login:</form:label><br/>
	<form:input path="username" id="username" /><br/>
	<form:errors path="username"></form:errors>
	
	<form:label path="password">Password</form:label><br/>
	<form:input path="password" type="password" id="password" /><br/>
	<form:errors path="password"></form:errors>


	<form:label path="email">E-mail:</form:label><br/>
	<form:input path="email" type="email" id="email" /><br/>
	<form:errors path="email"></form:errors>
	
	<form:label path="enabled">Enable ...:</form:label><br/>
	<form:input path="enabled" id="enabled" /><br/>
	<form:errors path="enabled"></form:errors>
	
	<input type="submit" value="Wyslij"/>

</form:form>
</body>
</html>

<%-- </form:form>
<form:form method="post" >
	
	<form:label path="password2">Confirm password</form:label><br/>
	<form:input path="password2" type="password" id="password2" /><br/>
	<form:errors path="password2"></form:errors>
</form:form>
<form:form method="post" modelAttribute="user">
--%>	
