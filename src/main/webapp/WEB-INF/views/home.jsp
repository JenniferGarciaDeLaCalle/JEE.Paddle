<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="head.jsp" />

	<H1>Home</H1>
	<p><a href="<c:url value='/courtList'/>">Courts</a></p>
	<p><a href="<c:url value='/userList'/>">Users</a></p>
	<p><a href="<c:url value='/trainingList'/>">Trainings</a></p>

<jsp:include page="footer.jsp" />