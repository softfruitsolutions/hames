<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/useraccount/view" var="userAccountViewUrl" />
<c:url value="/useraccount/save" var="userAccountSaveUrl" />

<script type="text/javascript">
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
	<div class="col-md-12">
		<div class="row">
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
							<div class="col-xs-5">
								<div class="form-group">
									<label class="col-lg-2 control-label">Username</label>
									<div class="col-lg-10">
										<form:input path="username" cssClass="form-control input-sm" placeholder="Username"/>
									</div><!-- /.col -->
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label">Password</label>
									<div class="col-lg-10">
										<form:password path="password" cssClass="form-control input-sm" placeholder="Password"/>
									</div><!-- /.col -->
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>