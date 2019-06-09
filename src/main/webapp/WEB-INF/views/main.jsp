<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
	<head>
		<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
		<script src="<c:url value="/resources/js/jquery-1.7.2.min.js" />"></script>
		<script src="<c:url value="/resources/js/stored-select2.js" />"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Погода</title>
	</head>
	
	<body>
		<form:form method="POST" commandName="settings" action="current" class="box login">
			<fieldset class="boxBody">
				<label>Сервис</label>
				<form:select path="serviceId" items="${serviceList}" />
				<label>Город</label>
				<form:select path="cityId" items="${cityList}" />			
			</fieldset>
			<footer> 
				<input type="submit" class="btnLogin" value="Показать погоду">
			</footer>
		</form:form>
	</body>
</html>