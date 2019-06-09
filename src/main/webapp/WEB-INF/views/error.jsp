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
				<fieldset class="boxBody">
					<label>Произошла ошибка</label>
					<label>Температура воздуха: не определено</label>
					<label>Относительная влажность: не определено</label>
					<label>Атмосферное давление: не определено</label>
				</fieldset>
				<a href="/weather">Назад</a>
			</form>
	</body>
</html>
