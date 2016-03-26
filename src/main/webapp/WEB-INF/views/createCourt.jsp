<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="head.jsp" />

    <H1>Create court</H1>
	<form:form action="createCourt" modelAttribute="court">
		<p>Id:
			<form:input path="courtId" placeholder="Id" required="required"/>
			<form:errors path="courtId" cssClass="error" />
		</p>
		<p>Active:
			<select name="active">
			    <option value="true">true</option>
			    <option value="false">false</option>
			  </select>
		</p>
		<p><input type="submit" value="Create"></p>
	</form:form>
	
	<p><a href="<c:url value='/courtList' />">Return Court list</a></p>
	
<jsp:include page="footer.jsp" />