<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@	taglib	prefix="form" uri="http://www.springframework.org/tags/form"	%>
<%@ page isELIgnored="false" %> <!--  to sie dodaje gdy wąsy wyświetlają się na stronie www -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@	taglib	prefix="form"
				uri="http://www.springframework.org/tags/form"	%>    

<title>Contacts ${currentUser.username}</title>
</head>
<body>
<h2>Contacts of ${currentUser.username} </h2>
<a href="http://localhost:8080/Twitter/tweet/${currentUser.id}/add">Your homepage </a><br/>
<a href="http://localhost:8080/Twitter/user/logout">Log Out</a> </p>
<h5>Manage your contacts:</h5>

<c:forEach items="${hostContacts}" var="item">
	<p><b>${item.guestName}</b> is ${item.guestStatus} by you   
	<a href="http://localhost:8080/Twitter/contact/${currentUser.id}/follow/${item.guestId}" >
	Follow</a> #
	<a href="http://localhost:8080/Twitter/contact/${currentUser.id}/stopFollow/${item.guestId}" >
	Stop following</a> #
	<a href="http://localhost:8080/Twitter/contact/${currentUser.id}/ban/${item.guestId}">Ban </a> #
	<a href="http://localhost:8080/Twitter/tweet/${currentUser.id}/guestTweets/${item.guestId}">See tweets </a>
	</p>
		
</c:forEach>

</body>
</html>