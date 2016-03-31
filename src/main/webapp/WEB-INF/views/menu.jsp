<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

	<table border="1">
		<tr>
			<td><a href="<c:url value='/home'/>">Home</a></td>
			<td><a href="<c:url value='/courtList'/>">Courts</a></td>
			<td><a href="<c:url value='/userList'/>">Users</a></td>
			<td><a href="<c:url value='/trainingList'/>">Training</a></td>
		</tr>
	</table>