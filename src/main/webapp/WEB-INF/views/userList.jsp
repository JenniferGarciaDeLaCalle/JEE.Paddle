<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="head.jsp" />

	<p><a href="<c:url value='/createUser'/>">Create user</a></p>

    <H1>List of users</H1>
	<table border="1">
		<thead>
			<tr>
				<th>Username</th>
				<th>Email</th>
				<th>Password</th>
				<th>Birth day</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user">
				<tr>
					<td>${user.username}</td>
					<td>${user.email}</td>
					<td>${user.password}</td>
					<td><fmt:formatDate value="${user.birthDate.time}" type="date" pattern="dd/MM/yyyy" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

<jsp:include page="footer.jsp" />