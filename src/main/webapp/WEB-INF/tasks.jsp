<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<title>Task Manager</title>
</head>
<body>
	<div class="container">
		<header class="d-flex justify-content-between align-items-center">
		<h1>Welcome, ${userInSession.name}</h1>
			<a href="/logout" >Log out</a>
		</header>
		<div class="row">
			<table class="table table-stripped">
				<thead>
					<tr>
						<th>Task</th>
						<th>Creator</th>
						<th>Assignee</th>
						<th>Priority</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tasks}" var="task">
						<tr>
							<td><a href="/tasks/${task.id}">${task.taskName}</a></td>
							<td>${task.creator.name}</td>
							<td>${task.assignee}</td>
							<td>${task.priority}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div>
		<a class="btn btn-info" href="/tasks/new" >Create Task</a>
		</div>
	</div>
</body>
</html>