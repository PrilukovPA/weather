<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
	<head>
		<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
		<title>Погода</title>
	</head>
	<body>
			<form class="box login">
				<div class="captHint">${service.serviceName}</div>
				<div class="captHint">${service.cityName}</div>
				<fieldset class="boxBody">
					<label>Температура воздуха: ${service.temperature}</label>
					<label>Относительная влажность: ${service.humidity}</label>
					<label>Атмосферное давление: ${service.pressure}</label>					
				</fieldset>				
				<a href="/weather">Назад</a>
			</form>			
	</body>
</html>
