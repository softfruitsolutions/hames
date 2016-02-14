<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- URL's  -->
<c:url value="/expense/view" var="expenseManagerUrl" />
<c:url value="/expense/category" var="categoryViewUrl" />
<c:url value="/expense/category/save" var="categorySaveUrl" />

<script type="text/javascript">
	function save(){
		var expenseCategory = $('#expenseCategory').serialize();
		$.ajax({
			type:'POST',
			url:'${categorySaveUrl}',
			data:expenseCategory,
	        async: false,
			success:function(data){
				SuccessAlert.handleSuccess(data);
				setTimeout(function(){
					window.location.href='${categoryViewUrl}';
			    },1000);
				
			},
			error:function(data){
				ErrorAlert.handleError(data.responseJSON.message);
			}
		});
	}
</script>

<div class="row">
	<div class="col-md-6">
		<div class="panel panel-primary panel-border top">
		  <div class="panel-heading">
		    <span class="panel-title panel-title hidden-xs"><i class="imoon imoon-drawer2"></i>EXPENSE CATEGORY</span>
		     <ul class="nav panel-tabs">
		      <li class="active">
		        <a href="#tab1" data-toggle="tab">Create</a>
		      </li>
		      <li class="">
		        <a href="#tab2" data-toggle="tab">Audit</a>
		      </li>
		    </ul>
		  </div>
		  <div class="panel-menu">
		  	<div class="btn-group">
				<a class="btn btn-info btn-sm" href="${categoryViewUrl}" title="Refresh"><i class="fa fa-refresh"></i></a>
				<shiro:hasPermission name="expense:category:create">
				<a class="btn btn-primary btn-sm" onclick="save()">
					<i class="fa fa-save"></i>
					<c:if test="${expenseCategory.categoryId == null }" >
						Save
					</c:if>
					<c:if test="${expenseCategory.categoryId != null}" >
						Update
					</c:if>
				</a>
				</shiro:hasPermission>
		  	</div>
		  </div>
		  <form:form modelAttribute="expenseCategory" method="POST" action="#">
		  <div class="panel-body ">
		  	<div class="tab-content">
				<div id="tab1" class="tab-pane active">
					<div class="row">
						<form:hidden path="categoryId" cssClass="form-control input-sm" placeholder="Category ID" readonly="true"/>
						<div class="col-md-6">
							<div class="form-group">
								<form:label path="categoryName" cssClass="control-label">Category Name</form:label>
								<form:input path="categoryName" cssClass="form-control input-sm" placeholder="Category Name"/>								 	
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<form:label path="categoryDescription" cssClass="control-label">Category Description</form:label>
								<form:textarea path="categoryDescription" cssClass="form-control input-sm" placeholder="Category Description"/>								 	
							</div>
						</div>
					</div>
				</div>
				<div id="tab2" class="tab-pane">
					<jsp:include page="/WEB-INF/views/hames/audit.jsp" />
				</div>
			</div>
		  </div>
		  </form:form>
		</div>
	</div>
	<div class="col-md-6">
		<div class="panel panel-primary panel-border top">
		  <div class="panel-heading">
		    <span class="panel-title panel-title hidden-xs"><i class="imoon imoon-drawer2"></i>AVAILABLE CATEGORIES</span>
		  </div>
		  <div class="panel-body">
		  	 <table class="table table-hover table-condensed">
				<thead>
					<tr>
						<th>Category Name</th>
						<th>Category Description</th>
						<th>Actions</th>
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