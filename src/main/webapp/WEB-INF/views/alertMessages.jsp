<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach items="${successMessages}" var="success">
	<div class="alert alert-success">
		<strong>Well done!</strong><c:out value="${success}" />
	</div>
</c:forEach>