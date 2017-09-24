<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@	taglib	prefix="form" uri="http://www.springframework.org/tags/form"	%>
<%@ page isELIgnored="false" %> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@	taglib	prefix="form"
				uri="http://www.springframework.org/tags/form"	%>    

<title>Tweet @${currentUser.username}</title>
</head>
<body>
<h2>Tweet details  </h2>
	
	<p><b>${tweet.id}. ${tweet.title}</b></p>
	<p>Created by: @${currentUser.username}</p>
	<p>Created on: ${tweet.created}</p>
	<p>Text:       ${tweet.tweet_text}</p>
	<p> <a href="http://localhost:8080/Twitter/tweet/${currentUser.id}/add">Return to tweets</a> 
	<a href="http://localhost:8080/Twitter/tweet/${currentUser.id}/delete/${tweet.id}">Delete </a></p>	
</body>
</html>