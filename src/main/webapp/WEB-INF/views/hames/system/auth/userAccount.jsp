<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/useraccount/view" var="userAccountViewUrl" />
<c:url value="/useraccount/save" var="userAccountSaveUrl" />

<script type="text/javascript">
	$(function(){
		$('form,input,select,textarea').attr("autocomplete", "off");
	});
	function save(){
		$('#userAccount').serialize();
		$('#userAccount').submit();
	}
</script>

<div class="col-md-12">
	<h3 class="headline m-top-md">
		User Account
		<span class="line"></span>
	</h3>
	<div class="">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="btn-toolbar no-margin">
					<div class="btn-group">
						<a class="btn btn-default btn-sm" href="${userAccountViewUrl}" title="Refresh"><i class="fa fa-refresh"></i></a>
						<a class="btn btn-default btn-sm" onclick="save()" title="Save"><i class="fa fa-save"></i></a>
					</div>
				</div>
			</div>
			<div class="panel-body">
				<form:form modelAttribute="userAccount" method="POST" action="${userAccountSaveUrl}" cssStyle="form-horizontal">
					<div class="row">
						<div class="col-xs-6">
							<div class="form-group">
								<label class="col-lg-2 control-label">Username</label>
								<div class="col-lg-10">
									<form:input path="username" cssClass="form-control input-sm" placeholder="Username" value=""/>
								</div><!-- /.col -->
							</div>
							<div class="form-group">
								<label class="col-lg-2 control-label">Password</label>
								<div class="col-lg-10">
									<form:password path="password" cssClass="form-control input-sm" placeholder="Password" value=""/>
								</div><!-- /.col -->
							</div>
							<div class="form-group">
								<label for="customerName" class="col-lg-2 control-label">Staff</label>
								<div class="col-lg-10">
									<form:select path="staffId" cssClass="form-control input-sm">
										<form:options items="${staffs }" itemLabel="firstName" itemValue="staffId"/>
									</form:select>
								</div><!-- /.col -->
							</div><!-- /form-group -->
							<div class="form-group">
								<label for="customerName" class="col-lg-2 control-label">Role</label>
								<div class="col-lg-10">
									<form:select path="roleId" cssClass="form-control input-sm">
										<form:options items="${rolePermissions }" itemLabel="roleName" itemValue="roleId"/>
									</form:select>
								</div><!-- /.col -->
							</div><!-- /form-group -->
						</div>
					</div>
					<div class="hide">
						<jsp:include page="/WEB-INF/views/hames/audit.jsp" />
					</div>
				</form:form>
				<hr />
				<div class="row">
					<div class="col-xs-12">
						<table id="userAccountTable" class="table table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<td>Username</td>
									<td>Staff</td>
									<td>Role</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${userAccounts }" var="ua">
									<tr>
										<td><c:out value="${ua.username }"></c:out></td>
										<td><c:out value="${ua.staffId }"></c:out></td>
										<td><c:out value="${ua.roleId }"></c:out></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
