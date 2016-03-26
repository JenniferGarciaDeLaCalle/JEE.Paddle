<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="head.jsp" />

	<p><a href="<c:url value='/createCourt'/>">Create court</a></p>

    <H1>List of courts</H1>
	<table border="1">
		<thead>
			<tr>
				<th>Id</th>
				<th>Active</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${courtList}" var="court">
				<tr>
					<td>${court.courtId}</td>
					<td>${court.active}</td>
					<td><a href="<c:url value='/courtList/${court.courtId}/${court.active}' />">Change activation</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

<jsp:include page="footer.jsp" />