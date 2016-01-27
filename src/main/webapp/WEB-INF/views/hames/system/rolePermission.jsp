<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- URL's  -->
<c:url value="/role/list" var="roleListUrl" />
<c:url value="/role/view" var="roleViewUrl" />
<c:url value="/role/save" var="roleSaveUrl" />

<script type="text/javascript">
	function save(){
		$('#rolePermission').serialize();
		$('#rolePermission').submit();
	}
</script>
<div class="col-md-12">
	<h3 class="headline m-top-md">
		Role Permission
		<span class="line"></span>
	</h3>
	
	<div class="col-md-12">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="btn-toolbar no-margin">
						<div class="btn-group">
							<a class="btn btn-info" href="${roleListUrl}" title="Back to Available Roles"><i class="fa fa-reply"></i></a>
							<a class="btn btn-info" href="${roleViewUrl}" title="Refresh"><i class="fa fa-refresh"></i></a>
							<a class="btn btn-success" onclick="save()">
								<i class="fa fa-save"></i>
								<c:if test="${rolePermission.roleId == null }" >
									Save
								</c:if>
								<c:if test="${rolePermission.roleId != null}" >
									Update
								</c:if>
							</a>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="rolePermission" method="POST" action="${roleSaveUrl}">
						<div class="row">
							<form:hidden path="roleId" cssClass="form-control input-sm" placeholder="Role ID" readonly="true"/>
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
							<div class="col-md-4">
								<div class="form-group">
									<form:label path="status" cssClass="control-label">Role Status</form:label>
									<form:select path="status" cssClass="form-control input-sm">
										<form:options items="${rolePermissionStatus }" itemLabel="value" />
									</form:select>	
								</div>
							</div>
							
						</div>
						<h5 class="headline">
							Permissions
							<span class="line"></span>
						</h5>
						<div class="row">
							<c:forEach items="${permissions }" var="p" varStatus="pStatus">
								<div class="checkbox">
									<label class="label-checkbox">
										<input type="checkbox" id="permission${pStatus}" name="permissions" value="${p.permission}">
										<span class="custom-checkbox"></span>
										<c:out value="${p.text}" />
									</label>
								</div>
							</c:forEach>
						</div>
						<form:hidden path="status" placeholder="status" />
						<jsp:include page="/WEB-INF/views/hames/audit.jsp" />
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>