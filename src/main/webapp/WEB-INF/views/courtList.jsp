<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<title>Spring MVC. User List</title>
</head>

<body>
    <H1>List of courts</H1>
	<table border="1">
		<thead>
			<tr>
				<th>Id</th>
				<th>Active</th>
				<!-- <th>#</th> -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${courtList}" var="court">
				<tr>
					<td>${court.id}</td>
					<td>${court.active}</td>
					<!-- <td><a href="<c:url value='/delete-user/${user.id}' />">delete</a></td> -->
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>