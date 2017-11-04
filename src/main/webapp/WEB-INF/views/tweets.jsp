<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="form" uri="http://www.springframework.org/tags/form"	%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@	taglib	prefix="form"
				uri="http://www.springframework.org/tags/form"	%>  
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Tweets ${currentUser.username}</title>
</head>

<body>
	<h2>Homepage of @${currentUser.username} </h2>
	<p> 
		<a href="http://localhost:8080/Twitter/user/logout">Log Out</a><br/>
		<a href="http://localhost:8080/Twitter/contact/${currentUser.id}/all">Your contacts</a> <br/>
		<a href="http://localhost:8080/Twitter/tweet/${currentUser.id}/guestTweets">Followed tweets</a>
	</p>
	
	<h5>Tweet if you want:</h5>
	
	<form:form method="post" modelAttribute="tweet">
		
		<form:label path="title">Title:</form:label><br/>
		<form:input path="title" id="title" /><br/>
		<form:errors path="title"></form:errors><br/>
		
		<form:label path="tweet_text">Text:</form:label><br/>
		<form:textarea path="tweet_text" id="tweet_text" /><br/>
		<form:errors path="tweet_text"></form:errors><br/>
		
		<input type="submit" value="Send"/>
	
	</form:form>

	<h5>Your tweets</h5>
	<c:forEach items="${allTweets}" var="item">
		<p><b>${item.title}</b><small>&nbsp;&nbsp;${item.created}</small><br />
		${item.tweet_text}&nbsp;&nbsp;&nbsp;
		<a href="http://localhost:8080/Twitter/tweet/${currentUser.id}/details/${item.id}">Details</a> 
		<a href="http://localhost:8080/Twitter/tweet/${currentUser.id}/delete/${item.id}">Delete </a></p>	
	</c:forEach>

</body>
</html>