<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="head.jsp" />

    <H1>Create training</H1>
	<form:form action="createTraining" modelAttribute="training">
		<p>Start date:
			<form:input path="startDate" placeholder="dd/mm/yyyy hh:mm:ss" required="required" />
			<form:errors path="startDate" cssClass="error" />
		</p>
		<p>Finish date:
			<form:input path="finishDate" placeholder="dd/mm/yyyy hh:mm:ss" required="required" />
			<form:errors path="finishDate" cssClass="error" />
		</p>
		<p>Court Id:
			<select name="courtId">
			    <c:forEach items="${courtIdList}" var="courtId">
			        <option value="${courtId}">${courtId}</option>
			    </c:forEach>
			</select>
		</p>
		<p>Trainer Id:
			<select name="trainerId">
			    <c:forEach items="${trainerIdList}" var="trainerId">
			        <option value="${trainerId}">${trainerId}</option>
			    </c:forEach>
			</select>
		</p>
		
		<p><input type="submit" value="Create"></p>
	</form:form>
	
	<p><a href="<c:url value='/trainingList' />">Return Training list</a></p>
	
<jsp:include page="footer.jsp" />