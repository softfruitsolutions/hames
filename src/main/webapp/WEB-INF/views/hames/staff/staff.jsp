<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>


<!-- URL's -->
<c:url value="/staff/view" var="staffViewUrl" />
<c:url value="/staff/list" var="staffListUrl" />
<c:url value="/staff/save" var="staffSaveUrl" />

<script type="text/javascript">
	function save(){
		var staff = $('#staff').serialize();
		$.ajax({
			type:'POST',
			url:'${staffSaveUrl}',
			data:staff,
	        async: false,
			success:function(data){
				SuccessAlert.handleSuccess(data);
				setTimeout(function(){
					window.location.href='${staffListUrl}';
			    },1000);
			},
			error:function(data){
				ErrorAlert.handleError(data.responseJSON.message);
			}
		});
	}
	
</script>

<div class="row">
	<div class="col-md-12">
		<div class="panel panel-primary panel-border top">
		  <div class="panel-heading">
		    <span class="panel-title panel-title hidden-xs"><i class="imoon imoon-user"></i> STAFF</span>
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
			  	 <a class="btn btn-info" href="${staffListUrl}" title="Back to Available Staffs"><i class="fa fa-reply"></i></a>
				 <a class="btn btn-info" href="${staffViewUrl}" title="Refresh"><i class="fa fa-refresh"></i></a>
				 <shiro:hasPermission name="hr:staff:create">
				 <a class="btn btn-success" onclick="save()">
					<i class="fa fa-save"></i>
					<c:if test="${staff.staffId == null }" >
						Save
					</c:if>
					<c:if test="${staff.staffId != null}" >
						Update
					</c:if>
				 </a>
				 </shiro:hasPermission>
			</div>
		  </div>
		  <form:form modelAttribute="staff" method="POST" action="#">
		  <div class="panel-body">
		  	<div class="tab-content">
				<div id="tab1" class="tab-pane active">
						<form:hidden path="staffId"/>
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<form:label path="firstName" cssClass="control-label">First Name</form:label>
									<form:input path="firstName" cssClass="form-control input-sm" placeholder="First Name" />								 	
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<form:label path="middleName" cssClass="control-label">Middle Name</form:label>
									<form:input path="middleName" cssClass="form-control input-sm" placeholder="Middle Name" />								 	
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<form:label path="lastName" cssClass="control-label">Last Name</form:label>
									<form:input path="lastName" cssClass="form-control input-sm" placeholder="Last Name" />								 	
								</div>
							</div>
						</div>
						<hr />
						<div class="row">
							<div class="col-md-3">
								<div class="form-group">
									<div class="form-group">
										<form:label path="city" cssClass="control-label">State/City</form:label>
										<form:input path="city" cssClass="form-control input-sm" placeholder="State/City" />								 	
									</div>										
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<form:label path="firstName" cssClass="control-label">Country</form:label>
									<form:input path="country" cssClass="form-control input-sm" placeholder="Country" />								 	
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<form:label path="status" cssClass="control-label">Status</form:label>
									<form:select path="status" cssClass="form-control input-sm">
										<form:options items="${staffStatus }" itemLabel="value" />
									</form:select>	
								</div>
							</div>
						</div>
						<hr />
						<div class="row">
							<div class="col-md-3">
								<div class="form-group">
									<form:label path="phoneNumber" cssClass="control-label">Phone No</form:label>
									<form:input path="phoneNumber" cssClass="form-control" placeholder="Phone No" />								 	
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label class="control-label">Mobile Number</label>
									<div class="input-group">
										<span class="input-group-addon"><i class="fa fa-mobile-phone fa-lg"></i></span>
										<form:input path="mobileNumber" cssClass="form-control" placeholder="Mobile No" />								 	
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label">E-mail ID</label>
									<div class="input-group">
										<span class="input-group-addon">@</span>
										<form:input path="emailId" cssClass="form-control" placeholder="Email Addres" />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label">Permanent Address</label>
									<form:textarea path="permanentAddress" cssClass="form-control input-sm" placeholder="Permanent Address"/>								 	
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label">Temporary Address</label>
									<form:textarea path="temporaryAddress" cssClass="form-control input-sm" placeholder="Temporary Address" />
								</div>
							</div>
						</div>
						<form:hidden path="status" placeholder="status" />
					</div>
				<div id="tab2" class="tab-pane">
					<jsp:include page="/WEB-INF/views/hames/audit.jsp" />
				</div>
			</div>
		  </div>
		  </form:form>
		</div>
	</div>
</div>
