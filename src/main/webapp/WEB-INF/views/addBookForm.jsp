<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="form" uri="http://www.springframework.org/tags/form"	%>
<%@ page isELIgnored="false" %> <!--  to sie dodaje gdy wąsy wyświetlają się na stronie www -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tweet form</title>
</head>
<body>

<h1>Strona uzytkownika</h1>
<form:form method="post" modelAttribute="tweet">
	
	<form:label path="title">Tytul:</form:label><br/>
	<form:input path="title" id="title" /><br/>
	<form:errors path="title"></form:errors>
	
	<form:label path="tweet_text">Tekst:</form:label><br/>
	<form:textarea path="tweet_text" id="tweet_text" /><br/>
	<form:errors path="tweet_text"></form:errors>
	
	<form:label path="enabled">Liczba stron:</form:label><br/>
	<form:checkbox path="enabled" id="enabled" checked="false"/><br/>
	<form:errors path="enabled"></form:errors>
	
	<input type="submit" value="Wyslij"/>

</form:form>
</body>
</html>