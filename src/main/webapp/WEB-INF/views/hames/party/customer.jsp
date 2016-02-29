<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- URL's -->
<c:url value="/customer/view" var="customerViewUrl" />
<c:url value="/customer/list" var="customerListUrl" />
<c:url value="/customer/save" var="customerSaveUrl" />

<script type="text/javascript">
	function save(){
		var customer = $('#customer').serialize();
		$.ajax({
			type:'POST',
			url:'${customerSaveUrl}',
			data:customer,
	        async: false,
			success:function(data){
				SuccessAlert.handleSuccess(data);
				setTimeout(function(){
					window.location.href='${customerListUrl}';
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
		    <span class="panel-title panel-title hidden-xs"><i class="imoon imoon-user"></i> CUSTOMER</span>
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
			  	 <a class="btn btn-info" href="${customerListUrl}" title="Back to Available Customer's"><i class="fa fa-reply"></i></a>
				<a class="btn btn-info" href="${customerViewUrl}" title="Refresh"><i class="fa fa-refresh"></i></a>
				<shiro:hasPermission name="party:customer:create">
					<a class="btn btn-success" onclick="save()">
						<i class="fa fa-save"></i>
						<c:if test="${customer.partyId == null }" >
							Save
						</c:if>
						<c:if test="${customer.partyId != null}" >
							Update
						</c:if>
					</a>
				</shiro:hasPermission>
			</div>
		  </div>
		  <form:form modelAttribute="customer" method="POST" action="#">
		  <div class="panel-body">
		  	<div class="tab-content">
				<div id="tab1" class="tab-pane active">
					<form:hidden path="partyId"/>
					<form:hidden path="partyType"/>
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
						<div class="col-md-3">
							<div class="form-group">
								<label class="control-label">E-mail ID</label>
								<div class="input-group">
									<span class="input-group-addon">@</span>
									<form:input path="emailId" cssClass="form-control" placeholder="Email Address" />
								</div>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label class="control-label">Status</label>
								<div class="input-group">
									<span class="input-group-addon">?</span>
									<form:select path="status" cssClass="form-control">
										<form:options items="${customerStatus}" itemLabel="text" />
									</form:select>
								</div>
							</div>
						</div>
					</div>
					<hr />
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