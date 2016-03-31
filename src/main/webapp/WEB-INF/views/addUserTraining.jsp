<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="head.jsp" />

    <H1>Add user training</H1>
	<form:form action="addUserTraining/${trainingId}" modelAttribute="user">
		<p>Player Id:
			<select name="id">
			    <c:forEach items="${userIdList}" var="userId">
			        <option value="${userId}">${userId}</option>
			    </c:forEach>
			</select>
		</p>
		
		<p><input type="submit" value="Add"></p>
	</form:form>
	
	<p><a href="<c:url value='/trainingList' />">Return Training list</a></p>
	
<jsp:include page="footer.jsp" />