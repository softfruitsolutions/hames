<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
	function save(){
		$('#staffRole').serialize();
		$('#staffRole').submit();
	}
	
	function view(id){
		window.location.href = "staffroleview?id="+id;
	}
</script>
<div class="col-md-12">
	<h3 class="headline m-top-md">
		Staff Role
		<span class="line"></span>
	</h3>
	
	<div class="col-md-12">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="btn-toolbar no-margin">
						<div class="btn-group">
							<a class="btn btn-info" href="staffroleview" title="Back to Available Roles"><i class="fa fa-reply"></i></a>
							<a class="btn btn-info" href="staffrole" title="Refresh"><i class="fa fa-refresh"></i></a>
							<a class="btn btn-success" onclick="save()">
								<i class="fa fa-save"></i>
								<c:if test="${staffRole.roleId == null }" >
									Save
								</c:if>
								<c:if test="${staffRole.roleId > 0 }" >
									Update
								</c:if>
							</a>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="staffRole" method="POST" action="staffrolesave">
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<form:label path="roleId" cssClass="control-label">Role ID</form:label>
									<form:input path="roleId" cssClass="form-control input-sm" placeholder="Role ID" readonly="true"/>								 	
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<form:label path="roleName" cssClass="control-label">Role Name</form:label>
									<form:input path="roleName" cssClass="form-control input-sm" placeholder="Role Name"/>								 	
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<form:label path="roleDescription" cssClass="control-label">Role Description</form:label>
									<form:textarea path="roleDescription" cssClass="form-control input-sm" placeholder="Role Description"/>								 	
								</div>
							</div>
						</div>
						<form:hidden path="status" placeholder="status" />
						<jsp:include page="/WEB-INF/views/hames/audit.jsp" />
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>