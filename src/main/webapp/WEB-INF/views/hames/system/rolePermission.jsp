<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="/WEB-INF/views/hames/tld/functions.tld" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>


<!-- URL's  -->
<c:url value="/role/view" var="roleViewUrl" />
<c:url value="/role/save" var="roleSaveUrl" />
<c:url value="/role/datatable" var="roleDatatableUrl" />

<!-- Variables -->
<c:set var="hasCreatePermission" value="false" />
<shiro:hasPermission name="admin:rolepermission:create">
	<c:set var="hasCreatePermission" value="true" />
</shiro:hasPermission>

<script type="text/javascript">
	
	$(function(){
		loadRoleDatatable();	
	});
	
	function save(){
		var role = $('#rolePermission').serialize();
		$.ajax({
			type:'POST',
			url:'${roleSaveUrl}',
			data:role,
	        async: false,
			success:function(data){
				SuccessAlert.handleSuccess(data);
				setTimeout(function(){
					window.location.href='${roleViewUrl}';
			    },1000);
				
			},
			error:function(data){
				ErrorAlert.handleError(data.responseJSON.message);
			}
		});
	}
	
	function loadRoleDatatable(){
		$('#roleDatatable').dataTable( {
			"bProcessing" : true,
			"bServerSide" : true,
			"bPaginate": true,
	        "sAjaxSource": '${roleDatatableUrl}',
	        "fnServerParams": function ( aoData ) {
	            aoData.push({ "name": "sortField", "value": "roleId"});
	        },
	        "aoColumns" : [
		                    { mDataProp: 'roleName' },
		                    { mDataProp: 'roleDescription' },
		                    {
		                        "mData": 'roleId',
		                        "bSortable": false, 
		                        "mRender": function(data, type, full) {
		                        	if(${hasCreatePermission}){
		                        		return '<a href="${roleViewUrl}?id='+data+'" title="Edit"><i class="fa fa-pencil"></i></a> &nbsp';
		                        	}else{
		                        		return "";
		                        	}
		                        },
		                    },	       
	           			  ],
	        "bFilter" : false,
	        "aLengthMenu": [[5, 20, 25, -1], [5, 15, 20, 30]],
			"iDisplayLength" : 5
	    });
	}
</script>


<div class="row">
	<div class="col-md-6">
		<div class="panel panel-primary panel-border top">
		  <div class="panel-heading">
		    <span class="panel-title">ROLE</span>
		    <ul class="nav panel-tabs">
		      <li class="active">
		        <a href="#tab1" data-toggle="tab">Create</a>
		      </li>
		      <li class="">
		        <a href="#tab2" data-toggle="tab">Permissions</a>
		      </li>
		      <li class="">
		        <a href="#tab3" data-toggle="tab">Audit</a>
		      </li>
		    </ul>
		  </div>
		  <div class="panel-menu">
			<div class="btn-group">
			  <a href="${roleViewUrl }" class="btn btn-primary light">
			    <i class="glyphicon glyphicon-refresh"></i>
			  </a>
			  <shiro:hasPermission name="admin:rolepermission:create">
				  <a id="roleSaveBtn" href="#" class="btn btn-primary" onclick="save()">
				    <i class="fa fa-save fa-lg"></i>
				  </a>
			  </shiro:hasPermission>
			</div>
		  </div>
		  <form:form modelAttribute="rolePermission">
		    <div class="panel-body">
			    <div class="tab-content">
			      <div id="tab1" class="tab-pane active">
   	              	<div class="row">
						<form:hidden path="roleId" cssClass="form-control input-sm" placeholder="Role ID" readonly="true"/>
						<div class="col-md-6">
							<div class="form-group">
								<form:label path="roleName" cssClass="control-label">Role Name</form:label>
								<form:input path="roleName" cssClass="form-control input-sm" placeholder="Role Name"/>								 	
							</div>
							<div class="form-group">
								<form:label path="status" cssClass="control-label">Role Status</form:label>
								<form:select path="status" cssClass="form-control input-sm">
									<form:options items="${rolePermissionStatus }" itemLabel="value" />
								</form:select>	
							</div>
						</div>
			      		<div class="col-md-6">
							<div class="form-group">
								<form:label path="roleDescription" cssClass="control-label">Role Description</form:label>
								<form:textarea path="roleDescription" cssClass="form-control input-sm" placeholder="Role Description" style="height:100px;"/>								 	
							</div>
						</div>
			      	</div>
			      </div>
			      <div id="tab2" class="tab-pane">
			        <div class="row">
			        	<div class="col-md-12">
				        	<c:forEach items="${permissions }" var="p" varStatus="pStatus">
								<div class="checkbox">
									<label class="label-checkbox">
										<c:if test="${rolePermission.permissions == null}">
											<input type="checkbox" id="permission${pStatus.index}" name="permissions" value="${p.permission}">
										</c:if>
										<c:if test="${rolePermission.permissions != null}">
											<input type="checkbox" id="permission${pStatus.index}" name="permissions" value="${p.permission}" <c:if test="${fn:contains(rolePermission.permissions,p.permission) }">checked="checked"</c:if>>
										</c:if>
										<span class="custom-checkbox"></span>
										<c:out value="${p.text}" />
									</label>
								</div>
							</c:forEach>
						</div>
			        </div>
			      </div>
			      <div id="tab3" class="tab-pane">
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
		    <span class="panel-title">Available Roles</span>
		  </div>
 	      <div class="panel-body">
 	      	<table id="roleDatatable" class="table table-striped table-hover dataTable">
				<thead>
					<tr>
		                <th>Role Name</th>
		                <th>Role Description</th>
		                <th>Actions</th>
		            </tr>
				</thead>
			</table>
 	      </div>
 	    </div>
	</div>
</div>

