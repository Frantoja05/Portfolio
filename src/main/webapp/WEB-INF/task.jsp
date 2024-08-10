<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<title>${task.taskName}</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1>Task: ${task.taskName}</h1>
			<p> Creator: ${task.creator.name}</p>
			<p> Assignee: ${task.assignee}</p>
			<p> Priority: ${task.priority}</p>
		</div>
		<c:if test="${task.creator.id == userInSession.id}">
			<a href="/tasks/${task.id}/edit" class="btn btn-success">Edit</a>
			<form action="/tasks/${task.id}/delete" method="post">
				<input type="hidden" name="_method" value="DELETE">
				<input type="submit" value="Delete" class="btn btn-warning">
			</form>
		</c:if>
	</div>
</body>
</html>