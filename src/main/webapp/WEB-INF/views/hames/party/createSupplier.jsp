<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- URL's -->
<c:url value="/party/supplier/view" var="supplierViewUrl" />
<c:url value="/party/supplier/create" var="supplierCreateUrl" />
<c:url value="/party/supplier/save" var="supplierSaveUrl" />

<script type="text/javascript">
	function save(){
		var supplier = $('#supplier').serialize();
		console.log(supplier);
		$.ajax({
			type:'POST',
			url:'${supplierSaveUrl}',
			data:supplier,
	        async: false,
			success:function(data){
				SuccessAlert.handleSuccess(data);
				setTimeout(function(){
					window.location.href='${supplierViewUrl}';
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
		    <span class="panel-title panel-title hidden-xs"><i class="imoon imoon-drawer2"></i>CREATE SUPPLIER</span>
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
		  		<a href="${supplierViewUrl}" class="btn btn-info btn-sm"><i class="fa fa-reply"></i></a>
				<a class="btn btn-info btn-sm" href="${supplierCreateUrl}" title="Refresh"><i class="fa fa-refresh"></i></a>
				<shiro:hasPermission name="party:supplier:create">
				<a class="btn btn-primary btn-sm" onclick="save()">
					<i class="fa fa-save"></i>
					<c:if test="${supplier.partyId == null }" >
						Save
					</c:if>
					<c:if test="${supplier.partyId != null}" >
						Update
					</c:if>
				</a>
				</shiro:hasPermission>
		  	</div>
		  </div>
		  <form:form modelAttribute="supplier" method="POST" action="#">
		  <div class="panel-body ">
		  	<div class="tab-content">
				<div id="tab1" class="tab-pane active">
					<div class="row">
						<div class="col-xs-6">
							<form:hidden path="partyId"/>
							<form:hidden path="partyType"/>
							
							<div class="form-group">
								<form:label path="name" cssClass="col-xs-3 control-label">Supplier Name</form:label>
								<div class="col-xs-9">
									<form:input path="name" cssClass="form-control input-sm" placeholder="Supplier Name"/>
								</div>								 	
							</div>
							<div class="form-group">
								<form:label path="type" cssClass="col-xs-3 control-label">Supplier Type</form:label>
								<div class="col-xs-9">
									<form:select path="type" cssClass="form-control input-sm">
										<form:options items="${supplierTypes}" itemValue="typeId" itemLabel="typeName"/>
									</form:select>
								</div>								 	
							</div>
							<div class="form-group">
								<form:label path="status" cssClass="col-xs-3 control-label">Supplier Status</form:label>
								<div class="col-xs-9">
									<form:select path="status" cssClass="form-control input-sm">
										<form:options items="${supplierStatus}" itemLabel="text"/>
									</form:select>
								</div>								 	
							</div> 
							<div class="form-group">
								<form:label path="address" cssClass="col-xs-3 control-label">Supplier Address</form:label>
								<div class="col-xs-9">
									<form:textarea path="address" cssClass="form-control input-sm" placeholder="Supplier Address"/>
								</div>								 	
							</div> 
						</div>
						
						<div class="col-xs-6">
							<div class="form-group">
								<form:label path="contactNo" cssClass="col-xs-3 control-label">Contact No</form:label>
								<div class="col-xs-9">
									<form:input path="contactNo" cssClass="form-control input-sm" placeholder="Contact No"/>
								</div>								 	
							</div>
							<div class="form-group">
								<form:label path="emailId" cssClass="col-xs-3 control-label">E-Mail</form:label>
								<div class="col-xs-9">
									<form:input path="emailId" cssClass="form-control input-sm" placeholder="Email"/>
								</div>								 	
							</div>
							<div class="form-group">
								<form:label path="website" cssClass="col-xs-3 control-label">Website</form:label>
								<div class="col-xs-9">
									<form:input path="website" cssClass="form-control input-sm" placeholder="Website"/>
								</div>								 	
							</div>
							<div class="form-group">
								<form:label path="description" cssClass="col-xs-3 control-label">Description</form:label>
								<div class="col-xs-9">
									<form:textarea path="description" cssClass="form-control input-sm" placeholder="Statutory info and other general information about Supplier"/>
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