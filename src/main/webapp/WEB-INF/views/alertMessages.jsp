<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<br />
<c:forEach items="${successMessages}" var="success">
	<div class="alert alert-success">
		<strong>Done! </strong><c:out value="${success}" />
	</div>
</c:forEach>
<c:forEach items="${errorMessages}" var="error">
	<div class="alert alert-danger">
		<strong>Oops! </strong><c:out value="${error}" />
	</div>
</c:forEach>
