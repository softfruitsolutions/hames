<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="/WEB-INF/views/hames/tld/functions.tld" %>

<!-- URL's  -->
<c:url value="/expense/view" var="expenseManagerUrl" />
<c:url value="/expense/category" var="categoryViewUrl" />
<c:url value="/expense/category/save" var="categorySaveUrl" />

<script type="text/javascript">
	function save(){
		$('#expenseCategory').serialize();
		$('#expenseCategory').submit();
	}
</script>
<div class="col-md-12">
	<h3 class="headline m-top-md">
		Expense Category
		<span class="line"></span>
	</h3>
	
	<div class="col-md-12">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="btn-toolbar no-margin">
						<div class="btn-group">
							<a class="btn btn-info btn-sm" href="${expenseManagerUrl}" title="Back to Expense Manager"><i class="fa fa-reply"></i></a>
							<a class="btn btn-info btn-sm" href="${categoryViewUrl}" title="Refresh"><i class="fa fa-refresh"></i></a>
							<a class="btn btn-success btn-sm" onclick="save()">
								<i class="fa fa-save"></i>
								<c:if test="${expenseCategory.categoryId == null }" >
									Save
								</c:if>
								<c:if test="${expenseCategory.categoryId != null}" >
									Update
								</c:if>
							</a>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="expenseCategory" method="POST" action="${categorySaveUrl}">
						<div class="row">
							<form:hidden path="categoryId" cssClass="form-control input-sm" placeholder="Category ID" readonly="true"/>
							<div class="col-md-4">
								<div class="form-group">
									<form:label path="categoryName" cssClass="control-label">Category Name</form:label>
									<form:input path="categoryName" cssClass="form-control input-sm" placeholder="Category Name"/>								 	
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<form:label path="categoryDescription" cssClass="control-label">Category Description</form:label>
									<form:textarea path="categoryDescription" cssClass="form-control input-sm" placeholder="Category Description"/>								 	
								</div>
							</div>
						</div>
						<jsp:include page="/WEB-INF/views/hames/audit.jsp" />
					</form:form>
					<h5 class="headline">
						Available Categories
						<span class="line"></span>
					</h5>
					<table class="table table-striped table-hover table-condensed ">
						<thead>
							<tr>
								<td>Category Name</td>
								<td>Category Description</td>
								<td>Actions</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${expenseCategories}" var="ec">
								<tr>
									<td><c:out value="${ec.categoryName }" /></td>
									<td><c:out value="${ec.categoryDescription }" /></td>
									<td>
										<a href="<c:url value="/expense/category?id=${ec.categoryId }"/>" title="Edit"><i class="fa fa-pencil"></i></a>
									</td>
								</tr>
							</c:forEach>
							
						</tbody>
					</table>
				</div>
				
				
			</div>
		</div>
	</div>
</div>