<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="head.jsp" />

	<p><a href="<c:url value='/createTraining'/>">Create training</a></p>
	
    <H1>List of training</H1>
	<table border="1">
		<thead>
			<tr>
				<th style="display:none">Id</th>
				<th>Start date</th>
				<th>Finish date</th>
				<th>Court Id</th>
				<th>Training Id</th>
				<th>Players</th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${trainingList}" var="training">
				<tr>
					<td style="display:none">${training.id}</td>
					<td><fmt:formatDate value="${training.startDate.time}" type="date" pattern="dd/MM/yyyy hh:mm:ss" /></td>
					<td><fmt:formatDate value="${training.finishDate.time}" type="date" pattern="dd/MM/yyyy hh:mm:ss" /></td>
					<td>${training.courtId}</td>
					<td>${training.trainerId}</td>
					<td>
					 	<c:forEach items="${training.players}" var="players">
					 		${players.id},
				    	</c:forEach>
					</td>
					<td><a href="<c:url value='/addUserTraining/${training.id}' />">Add user</a></td>
					<td><a href="<c:url value='/deleteUserTraining/${training.id}' />">Delete user</a></td>
					<td><a href="<c:url value='/deleteTraining/${training.id}' />">Delete training</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

<jsp:include page="footer.jsp" />