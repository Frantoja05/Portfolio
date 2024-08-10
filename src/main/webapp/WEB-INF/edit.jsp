<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<title>Edit Task</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Edit: ${task.taskName}</h1>
				<form:form action="/tasks/update" method="post" modelAttribute="task" >
					<div>
						<form:label path="taskName" >Name:</form:label>
						<form:input path="taskName" class="form-control" />
						<form:errors path="taskName" class="text-danger" />
					</div>
					<div>
						<form:label path="assignee">Assignee:</form:label>
						<form:select path="assignee" class="form-select">
							<c:forEach items="${assignees}" var="assignee">
								<form:option value="${assignee.name}">${assignee.name}</form:option>
							</c:forEach>
						</form:select>
					</div>
					<div>
						<form:label path="priority">Priority:</form:label>
						<form:select path="priority" class="form-select">
							<c:forEach items="${priorities}" var="priority">
								<form:option value="${priority}">${priority}</form:option>
							</c:forEach>
						</form:select>
					</div>
					<form:hidden value="${userInSession.id}" path="creator"></form:hidden>
					<form:hidden path="id" value="${task.id}"></form:hidden>
					<input type="hidden" value="put" name="_method">
					<input type="submit" class="btn btn-warning" value="Edit" >
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>