<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="head.jsp" />

    <H1>Create user</H1>
	<form:form action="createUser" modelAttribute="user">
		<p>Username:
			<form:input path="username" placeholder="Username" required="required"/>
			<form:errors path="username" cssClass="error" />
		</p>
		<p>Email:
			<form:input path="email" placeholder="Email" required="required"/>
			<form:errors path="email" cssClass="error" />
		</p>
		<p>Password:
			<form:input path="password" placeholder="Password" required="required"/>
			<form:errors path="password" cssClass="error" />
		</p>
		<p>Birth date:
			<form:input path="birthDate" placeholder="dd/mm/yyyy" required="required" />
			<form:errors path="birthDate" cssClass="error" />
		</p>
		<p><input type="submit" value="Create"></p>
	</form:form>
	
	<p><a href="<c:url value='/userList' />">Return User list</a></p>
	
<jsp:include page="footer.jsp" />