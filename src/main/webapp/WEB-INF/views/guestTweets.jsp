<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@	taglib	prefix="form" uri="http://www.springframework.org/tags/form"	%>
<%@ page isELIgnored="false" %> <!--  to sie dodaje gdy wąsy wyświetlają się na stronie www -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@	taglib	prefix="form"
				uri="http://www.springframework.org/tags/form"	%>    

<title>Tweets ${guestUser.username}</title>

<body>
<p><a href="http://localhost:8080/Twitter/tweet/${currentUser.id}/add">Your homepage </a><br/>
<a href="http://localhost:8080/Twitter/user/logout">Log Out</a> </p>
<h5>Tweets of ${guestUser.username}</h5>

<c:forEach items="${guestTweets}" var="item">
	<p><b>${item.title}</b><small>     ${item.created}</small><br />
	${item.tweet_text}<br />
	<br /></p>
	<p><a href="http://localhost:8080/Twitter/tweet/${currentUser.id}/comment/${item.id}">Comment</a></p>
	
	</c:forEach>

</body>
</html>