<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- URL's -->
<c:url value="/party/supplier" var="supplierUrl" />
<c:url value="/party/supplier/view" var="supplierViewUrl" />
<c:url value="/party/supplier/create" var="supplierCreateUrl" />
<c:url value="/party/supplier/datatable" var="supplierDatatableUrl" />

<c:set var="hasCreatePermission" value="false" />
<shiro:hasPermission name="party:supplier:create">
	<c:set var="hasCreatePermission" value="true" />
</shiro:hasPermission>

<script type="text/javascript">

	/* VARIABLES */
	var HAS_CREATE_PERMISSION = '${hasCreatePermission}';
	
	$(document).ready(function() {
		$('#supplierDatatable').dataTable (	{
			"bProcessing" : true,
			"bServerSide" : true,
			"bPaginate": true,
			"sAjaxSource":	'${supplierDatatableUrl}',
			"fnServerParams": function (aoData) {
				aoData.push({"name":"sortField" ,"value":"partyId"});
			},
			"aoColumns" : [
			               {mDataProp : 'name'},
			               {mDataProp : 'contactNo'},
			               {mDataProp : 'status'},
			               {mDataProp : 'address'},
			               {
			            	   "mData" : 'partyId' ,
			            	   "bSortable" : false,
			            	   "mRender": function(data, type, full){
			            		   if(HAS_CREATE_PERMISSION){
			            			   return '<a href="${supplierUrl}/'+data+ '" title="Edit"><i class="fa fa-pencil"></i></a>&nbsp';
		                           }else{
		                        	   return "";
		                           }
			            	   },
			                } 
			               ],
			"bFilter": false,
			"aLengthMenu":[[10,20,25,-1],[10,20,30,40]],
			"iDisplayLength":10
			
		}); 
	});
</script>

<div class="row">
	<div class="col-md-12">
		<div class="panel panel-primary panel-border top">
		  <div class="panel-heading">
		    <span class="panel-title panel-title hidden-xs"><i class="octicon octicon-package"></i> AVAILABLE SUPPLIERS</span>
		  </div>
		  <div class="panel-menu">
		  	 <shiro:hasPermission name="party:supplier:create">
		  	 	<a class="btn btn-primary btn-sm" href="${supplierCreateUrl }" title="Create Supplier "><i class="fa fa-edit"></i> Create Supplier </a>
		  	 </shiro:hasPermission>
		  </div>
		  <div class="panel-body ">
			<table id="supplierDatatable" class="table table-striped table-hover table-condensed dataTable">
				<thead>
					<tr>
						<th>Supplier Name</th>
						<th>Contact No </th>
						<th>Status</th>
						<th>Address</th>
						<th>Action</th>
		            </tr>
				</thead>
			</table>
		  </div>
		</div>
	</div>
</div>