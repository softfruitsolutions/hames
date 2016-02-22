<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url value="/useraccount/view" var="userAccountViewUrl" />
<c:url value="/useraccount/save" var="userAccountSaveUrl" />

<script type="text/javascript">
	$(function(){
		$('form,input,select,textarea').attr("autocomplete", "off");
	});
	function save(){
		var useraccount = $('#userAccount').serialize();
		$.ajax({
			type:'POST',
			url:'${userAccountSaveUrl}',
			data:useraccount,
	        async: false,
			success:function(data){
				SuccessAlert.handleSuccess(data);
				setTimeout(function(){
					window.location.href='${userAccountViewUrl}';
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
		    <span class="panel-title">USER ACCOUNT</span>
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
			  <a href="${userAccountViewUrl }" class="btn btn-primary light">
			    <i class="glyphicon glyphicon-refresh"></i>
			  </a>
			  <a href="#" class="btn btn-primary" onclick="save()">
			    <i class="fa fa-save fa-lg"></i>
			  </a>
			</div>
		  </div>
		  <form:form modelAttribute="userAccount" action="#" >
		  	<form:hidden path="accountId"/>
		    <div class="panel-body">
			    <div class="tab-content">
			      <div id="tab1" class="tab-pane active">
   	              	<div class="row">
						<div class="col-xs-10">
							<div class="form-group">
								<label class="col-lg-3 control-label">Username</label>
								<div class="col-lg-7">
									<form:input path="username" cssClass="form-control input-sm" placeholder="Username" autocomplete="off"/>
								</div><!-- /.col -->
							</div>
							<div class="form-group">
								<label class="col-lg-3 control-label">Password</label>
								<div class="col-lg-7">
									<form:password path="password" cssClass="form-control input-sm" placeholder="Password" autocomplete="off"/>
								</div><!-- /.col -->
							</div>
							<div class="form-group">
								<label for="customerName" class="col-lg-3 control-label">Staff</label>
								<div class="col-lg-7">
									<form:select path="staffId" cssClass="form-control input-sm">
										<form:options items="${staffs }" itemLabel="fullName" itemValue="staffId"/>
									</form:select>
								</div><!-- /.col -->
							</div><!-- /form-group -->
							<div class="form-group">
								<label for="customerName" class="col-lg-3 control-label">Role</label>
								<div class="col-lg-7">
									<form:select path="roleId" cssClass="form-control input-sm">
										<form:options items="${rolePermissions }" itemLabel="roleName" itemValue="roleId"/>
									</form:select>
								</div><!-- /.col -->
							</div><!-- /form-group -->
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
		    <span class="panel-title">Available User Accounts</span>
		  </div>
 	      <div class="panel-body">
 	       <table class="table table-hover table-condensed">
			  <thead>
			    <tr>
			      <th>Staff Name</th>
			      <th>Username</th>
			      <th>Role Name</th>
			      <th>Actions</th>
			    </tr>
			  </thead>
			  <tbody>
			 	<c:forEach items="${userAccounts }" var="ua">
					<tr>
						<td><c:out value="${ua.staff.fullName }"></c:out></td>
						<td><c:out value="${ua.username }"></c:out></td>
						<td><c:out value="${ua.rolePermission.roleName}"></c:out></td>
						<td></td>
					</tr>
				</c:forEach>
			  </tbody>
			</table>
 	      </div>
 	    </div>
   	</div>
</div>