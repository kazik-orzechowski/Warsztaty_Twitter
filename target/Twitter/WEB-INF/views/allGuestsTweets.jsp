<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@	taglib	prefix="form" uri="http://www.springframework.org/tags/form"	%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@	taglib	prefix="form"
				uri="http://www.springframework.org/tags/form"	%>    

<title>Followed tweets</title>


<body>
<h2>Tweets of users followed by @${currentUser.username} </h2>
<p><a href="http://localhost:8080/Twitter/tweet/${currentUser.id}/add">Your homepage </a><br/>
<a href="http://localhost:8080/Twitter/user/logout">Log Out</a> </p>

<c:forEach items="${guestTweets}" var="item">
	<u>@<c:out value="${item.user.username}"></c:out></u> <br/>
	<b>${item.title}</b><small>     ${item.created}</small><br />
	${item.tweet_text}     <a href="http://localhost:8080/Twitter/tweet/${currentUser.id}/comment/${item.id}/post">Post a comment</a>
	<a href="http://localhost:8080/Twitter/tweet/${currentUser.id}/comment/${item.id}/show">Show comments</a></p>
	<c:set var="inputDisplay" value="${inputDisplayNumber}" /> <!-- This same as your request attribute -->
	<c:set var="tweetNumber" value="${item.id}" />
	<c:choose>
	    <c:when test="${displayNumber == tweetNumber}">
			<c:forEach items="${tweetComments}" var="comment">  
				<p>&nbsp;&nbsp;&nbsp;<small><u>@${comment.user.username }</u>  ${comment.comment_text }
						<small> ${comment.created }</small></small></p>
			</c:forEach>
	    </c:when>
	    <c:otherwise></c:otherwise>      
	</c:choose>

	<c:choose>
	    <c:when test="${inputDisplayNumber == tweetNumber}">
			<form:form method="post" modelAttribute="comment">
				<form:label path="comment_text">Your comment: </form:label>
				<form:input path="comment_text" id="comment_text" />   <input type="submit" value="Send"/><br/>
				<form:errors path="comment_text"></form:errors>
			</form:form>
	    </c:when>
	    <c:otherwise></c:otherwise>      
	</c:choose>
</c:forEach>	
</body>
</html>