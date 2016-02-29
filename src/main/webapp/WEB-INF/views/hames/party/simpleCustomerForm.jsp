<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- URL's -->
<c:url value="/customer/save" var="customerSaveUrl" />

<script type="text/javascript">
	/**
	 * Customer related functions for order
	 * 1. Showing customer modal
	 * 2. Saving a customer
	 * 3. Set customer data to option field after saving a customer
	 */
	var Customer = (function(){
		this.showModal = function(){
			$('#customerModal').modal({
				show:true,
				backdrop:'static',
			});
		};
		this.hideModal = function(){
			$('form#customer')[0].reset();
			$('#customerModal').modal('hide');
		};
		this.postCreate = function(){
			setCustomerValues(null);
			this.hideModal();
		};
		return this;
	})();
	
	function saveCustomer(){
		var customer = $('form#customer').serialize();
		$.ajax({
			type:'POST',
			url:'${customerSaveUrl}',
			data:customer,
	        async: false,
			success:function(data){
				SuccessAlert.handleSuccess(data);
				Customer.postCreate();
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
		        <a href="#customerCreateTab" data-toggle="tab">Create</a>
		      </li>
		    </ul>
		  </div>
		  <%-- <div class="panel-menu">
		  	<div class="btn-group">
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
		  </div>--%>
		  <form:form modelAttribute="customer" method="POST" action="#">
		  <div class="panel-body">
		  	<div class="tab-content">
				<div id="customerCreateTab" class="tab-pane active">
					<form:hidden path="partyId"/>
					<form:hidden path="partyType" value="CUSTOMER"/>
					<form:hidden path="status" value="ACTIVE_CUSTOMER"/>
					<div class="row">
						<div class="col-xs-12">
							<div class="form-group">
								<form:label path="firstName" cssClass="col-lg-3 control-label">First Name</form:label>
								<div class="col-lg-9">
									<form:input path="firstName" cssClass="form-control input-sm" placeholder="First Name" />								 	
								</div><!-- /.col -->
							</div><!-- /form-group -->
							<div class="form-group">
								<form:label path="middleName" cssClass="col-lg-3 control-label">Middle Name</form:label>
								<div class="col-lg-9">
									<form:input path="middleName" cssClass="form-control input-sm" placeholder="Middle Name" />								 	
								</div><!-- /.col -->
							</div><!-- /form-group -->
							<div class="form-group">
								<form:label path="lastName" cssClass="col-lg-3 control-label">Last Name</form:label>
								<div class="col-lg-9">
									<form:input path="lastName" cssClass="form-control input-sm" placeholder="Last Name" />								 	
								</div><!-- /.col -->
							</div><!-- /form-group -->
						</div>
					</div>
					<hr />
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<form:label path="phoneNumber" cssClass="col-xs-3 control-label">Phone Number</form:label>
								<div class="col-lg-9">
									<form:input path="phoneNumber" cssClass="form-control input-sm" placeholder="Phone Number" />								 	
								</div><!-- /.col -->
							</div>
							<div class="form-group">
								<label class="col-xs-3 control-label">Mobile Number</label>
								<div class="col-lg-9">
									<form:input path="mobileNumber" cssClass="form-control input-sm" placeholder="Mobile Number" />
								</div><!-- /.col -->
							</div>
							<div class="form-group">
								<label class="col-xs-3 control-label">E-mail ID</label>
								<div class="col-lg-9">
									<form:input path="emailId" cssClass="form-control input-sm" placeholder="Email Address" />
								</div>
							</div>
						</div>
					</div>
					<hr />
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="col-xs-3 control-label">Permanent Address</label>
								<div class="col-lg-9">
									<form:textarea path="permanentAddress" cssClass="form-control input-sm" placeholder="Permanent Address"/>
								</div>								 	
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