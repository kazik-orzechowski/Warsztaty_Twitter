<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@	taglib	prefix="form" uri="http://www.springframework.org/tags/form"	%>
 <!--  this should be added when curly brackets are displayed on the web page -->
<%@ page isELIgnored="false" %>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@	taglib	prefix="form"
				uri="http://www.springframework.org/tags/form"	%>    

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>Tweets @${guestUser.username}</title>
</head>

<body>
<h2>Tweets of users followed by @${currentUser.username} </h2>

<p><a href="http://localhost:8080/Twitter/tweet/${currentUser.id}/add">Your homepage </a><br/>
<a href="http://localhost:8080/Twitter/user/logout">Log Out</a> </p>
<h5>List of tweets of @${guestUser.username}</h5>

<c:forEach items="${guestTweets}" var="item">
	<p><b>${item.title}</b><small>     ${item.created}</small><br />
	${item.tweet_text}</p>
	
</c:forEach>

</body>
</html>