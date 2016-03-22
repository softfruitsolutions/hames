<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- URL's  -->
<c:url value="/party/supplier/type/create" var="supplierTypeUrl" />
<c:url value="/party/supplier/type/save" var="supplierTypeSaveUrl" />

<script type="text/javascript">
	function save(){
		var supplierType = $('#supplierType').serialize();
		$.ajax({
			type:'POST',
			url:'${supplierTypeSaveUrl}',
			data:supplierType,
	        async: false,
			success:function(data){
				SuccessAlert.handleSuccess(data);
				setTimeout(function(){
					window.location.href='${supplierTypeUrl}';
			    },1000);
				
			},
			error:function(data){
				ErrorAlert.handleError(data.responseJSON.message);
			}
		});
	}
</script>

<div class="row">
	<shiro:hasPermission name="party:supplier:type:view">
	<div class="col-md-6">
		<div class="panel panel-primary panel-border top">
		  <div class="panel-heading">
		    <span class="panel-title panel-title hidden-xs"><i class="imoon imoon-drawer2"></i>SUPPLIER TYPE</span>
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
				<a class="btn btn-info btn-sm" href="${supplierTypeUrl}" title="Refresh"><i class="fa fa-refresh"></i></a>
				<shiro:hasPermission name="party:supplier:type:create">
				<a class="btn btn-primary btn-sm" onclick="save()">
					<i class="fa fa-save"></i>
					<c:if test="${supplierType.typeId == null }" >
						Save
					</c:if>
					<c:if test="${supplierType.typeId != null}" >
						Update
					</c:if>
				</a>
				</shiro:hasPermission>
		  	</div>
		  </div>
		  <form:form modelAttribute="supplierType" method="POST" action="#">
		  <div class="panel-body ">
		  	<div class="tab-content">
				<div id="tab1" class="tab-pane active">
					<div class="row">
						<form:hidden path="typeId"/>
						<div class="col-md-6">
							<div class="form-group">
								<form:label path="typeName" cssClass="control-label">Type Name</form:label>
								<form:input path="typeName" cssClass="form-control input-sm" placeholder="Supplier Type Name"/>								 	
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<form:label path="typeDescription" cssClass="control-label">Type Description</form:label>
								<form:textarea path="typeDescription" cssClass="form-control input-sm" placeholder="Supplier Type Description"/>								 	
							</div>
						</div>
					</div>
				</div>
				<div id="tab2" class="tab-pane">
					<%-- <jsp:include page="/WEB-INF/views/hames/audit.jsp" /> --%>
				</div>
			</div>
		  </div>
		  </form:form>
		</div>
	</div>
	</shiro:hasPermission>
	<div class="col-md-6">
		<div class="panel panel-primary panel-border top">
		  <div class="panel-heading">
		    <span class="panel-title panel-title hidden-xs"><i class="imoon imoon-drawer2"></i>AVAILABLE SUPPLIER TYPES</span>
		  </div>
		  <div class="panel-body">
		  </div>
		</div>
	</div>
</div>