<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- URL's -->
<c:url value="/inventory/product" var="productUrl" />
<c:url value="/inventory/product/create" var="productCreateUrl" />
<c:url value="/inventory/product/datatable" var="productDatatableUrl" />

<c:set var="hasCreatePermission" value="false" />
<shiro:hasPermission name="inventory:product:create">
	<c:set var="hasCreatePermission" value="true" />
</shiro:hasPermission>

<script type="text/javascript">

	$(document).ready(function() {
		$('#productDatatable').dataTable (	{
			"bProcessing" : true,
			"bServerSide" : true,
			"bPaginate": true,
			"sAjaxSource":	'${productDatatableUrl}',
			"fnServerParams": function (aoData) {
				aoData.push({"name":"sortField" ,"value":"productCode"});
			},
			"aoColumns" : [
			               {mDataProp : 'productCode'},
			               {mDataProp : 'productName'},
			               {mDataProp : 'productType'},
			               {mDataProp : 'productGroup'},
			               {mDataProp : 'uom'},
			               {
			            	   "mData" : 'productId' ,
			            	   "bSortable" : false,
			            	   "mRender": function(data, type, full){
			            		   if(${hasCreatePermission}){
			            			   return '<a href="${productUrl}/'+data+ '" title="Edit"><i class="fa fa-pencil"></i></a>&nbsp';
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
		    <span class="panel-title panel-title hidden-xs"><i class="octicon octicon-package"></i> AVAILABLE PRODUCTS</span>
		  </div>
		  <div class="panel-menu">
		  	 <shiro:hasPermission name="inventory:product:create">
		  	 	<a class="btn btn-primary btn-sm" href="${productCreateUrl }" title="Create Product "><i class="fa fa-edit"></i> Create Product </a>
		  	 </shiro:hasPermission>
		  </div>
		  <div class="panel-body ">
			<table id="productDatatable" class="table table-striped table-hover table-condensed dataTable">
				<thead>
					<tr>
						<th>Product Code</th>
						<th>Product Name</th>
						<th>Product Type</th>
						<th>Product Group</th>
						<th>UOM</th>
						<th>Action</th>
		            </tr>
				</thead>
			</table>
		  </div>
		</div>
	</div>
</div>