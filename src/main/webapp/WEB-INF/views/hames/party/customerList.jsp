<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- URL's -->
<c:url value="/customer/view" var="customerViewUrl" />
<c:url value="/customer/datatable" var="customerDatatableUrl" />

<c:set var="hasCreatePermission" value="false" />
<shiro:hasPermission name="party:customer:create">
	<c:set var="hasCreatePermission" value="true" />
</shiro:hasPermission>

<script type="text/javascript">

	$(document).ready(function() {
		$('#customerDatatable').dataTable (	{
			"bProcessing" : true,
			"bServerSide" : true,
			"bPaginate": true,
			"sAjaxSource":	'${customerDatatableUrl}',
			"fnServerParams": function (aoData) {
				aoData.push({"name":"sortField" ,"value":"partyId"});
			},
			"aoColumns" : [
			               {mDataProp : 'fullName'},
			               {mDataProp : 'phoneNumber'},
			               {mDataProp : 'mobileNumber'},
			               {mDataProp : 'emailId'},
			                {
			            	   "mData" : 'partyId' ,
			            	   "bSortable" : false,
			            	   "mRender": function(data, type, full){
			            		   if(${hasCreatePermission}){
			            			   return '<a href="${customerViewUrl}?id='+data+ '" title="Edit"><i class="fa fa-pencil"></i></a>&nbsp';
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
		    <span class="panel-title panel-title hidden-xs"><i class="glyphicons glyphicons-parents"></i>AVAILABLE CUSTOMERS</span>
		  </div>
		  <div class="panel-menu">
		  	 <shiro:hasPermission name="party:customer:create">
		  	 	<a class="btn btn-primary" href="${customerViewUrl }" title="Create Staff "><i class="fa fa-edit"></i> Create Customer </a>
		  	 </shiro:hasPermission>
		  </div>
		  <div class="panel-body ">
			<table id="customerDatatable" class="table table-striped table-hover table-condensed dataTable">
				<thead>
					<tr>
						<th>Customer</th>
						<th>Phone No</th>
						<th>Mobile No</th>
						<th>E-mail Id</th>
						<th>Action</th>
		            </tr>
				</thead>
			</table>
		  </div>
		</div>
	</div>
</div>