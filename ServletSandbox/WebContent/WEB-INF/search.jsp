<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link href="resources/css/bootstrap.min.css" rel="stylesheet">
		<title>Search page</title>
	</head>
	<body>
		<div class="container-fluid">
			<h3>Search</h3>
			<br/>
			
			<c:if test="${not empty requestScope.errorMessage}" >
				<%= request.getAttribute("errorMessage") %>
			</c:if>
			
			<form action="SearchServlet" method="post">
				<table class="table table-striped">
					<tr>
						<td>Keyword</td>
						<td><input type="text" name="keyword" /></td>
					</tr>
					<tr>
						<td>Directory/file</td>
						<td><input type="text" name="directoryFile" /></td>
					</tr>
					<tr>
						<td>Extension</td>
						<td><input type="text" name="extension" /></td>
					</tr>
					<tr>
						<td>Algorithm</td>
						<td>
							<select name="algorithm" >
								<c:forEach var="algo" items="${requestScope.algorithms}">
									<option value="${algo.key}">
										<c:out value="${algo.description}"/>
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><button type="submit" class="btn btn-default">Search</button></td>
					</tr>
				</table>
			</form>
		</div>
	
		<script src="resources/js/jquery-1.11.3.min.js"></script>
		<script src="resources/js/bootstrap.min.js"></script>
	</body>
</html>