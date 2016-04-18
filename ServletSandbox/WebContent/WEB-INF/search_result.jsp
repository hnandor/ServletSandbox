<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="resources/css/bootstrap.min.css" rel="stylesheet">
		<title>Search result</title>
	</head>
	<body>
		<div class="container-fluid">
			<h3>Search result</h3>
			<br/>
	
			<table class="table table-striped">
				<tr>
					<td>Keyword</td>
					<td>${requestScope.criteria.keyword}</td>
				</tr>
				<tr>
					<td>Directory/file</td>
					<td>${requestScope.criteria.root}</td>
				</tr>
				<tr>
					<td>Extension</td>
					<td>${requestScope.criteria.extension}</td>
				</tr>
				<tr>
					<td>Algorithm</td>
					<td>${requestScope.criteria.algorithm.description}</td>
				</tr>
			</table>
			
			<c:forEach var="filePath" items="${requestScope.filePaths}">
				<c:out value="${filePath}"/>
				<br/>
			</c:forEach>
		</div>
	
		<script src="resources/js/jquery-1.11.3.min.js"></script>
		<script src="resources/js/bootstrap.min.js"></script>
	</body>
</html>