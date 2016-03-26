<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="head.jsp" />

	<H1>Create success</H1>

	<h3>The element (${element}) is create</h3>
	
	 <p><a href="<c:url value='/home' />">Return HOME</a></p>

<jsp:include page="footer.jsp" />