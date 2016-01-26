<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- URL's -->
<c:url value="/customer/view" var="customerViewUrl" />
<c:url value="/customer/datatable" var="customerDatatableUrl" />

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
			               {mDataProp : 'firstName'	},
			               {mDataProp : 'lastName'},
			               {mDataProp : 'phoneNumber'},
			               {mDataProp : 'emailId'},
			                {
			            	   "mData" : 'partyId' ,
			            	   "bSortable" : false,
			            	   "mRender": function(data, type, full){
			            		   
			            		   return '<a href="${customerViewUrl}?id='+data+ '" title="Edit"><i class="fa fa-pencil"></i></a>&nbsp'+
			            	 			  '<a href="${customerViewUrl}?id='+data+ '" title="Edit"><i class="fa fa-trash-o red"></i></a>';	
			            	   },
			               }, 
			               ],
			"bFilter": false,
			"aLengthMenu":[[5,20,25,-1],[5,15,20,30]],
			"iDisplayLength":5
			
		}); 
	});
</script>


<div class="col-md-12">
	<h3 class="headline m-top-md">
		Customer
		<span class="line"></span>
	</h3>
	
	<div class="panel panel-default">
		<div class="panel-heading">
			Available Customers
			<span class="pull-right">
				<a class="btn btn-xs btn-info" href="${customerViewUrl }" title="Create Customer "><i class="fa fa-edit"></i> Create Customer </a>
			</span>
			<br/>
		</div>
		<div class="panel-body">
			<table id="customerDatatable" class="table table-striped table-hover dataTable">
				<thead>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Phone No</th>
						<th>E-mail Id</th>
						<th>Action</th>
		            </tr>
				</thead>
			</table>
		</div>
	</div>
</div>