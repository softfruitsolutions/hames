<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- URL's -->
<c:url value="/saleorder/view" var="saleOrderViewUrl" />
<c:url value="/saleorder/datatable" var="saleOrderDatatableUrl" />

<script src='<c:url value="/resources/hames/order/js/saleorderlist.js" />' type="text/javascript"></script>

<div class="col-md-12">
	<h3 class="headline m-top-md">
		Sale Orders
		<span class="line"></span>
	</h3>
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<span class="">
				<a class="btn btn-xs btn-primary" href="${saleOrderViewUrl }" title="Create Order "><i class="fa fa-edit"></i> Create Order </a>
			</span>
			<br/>
		</div>
		<div class="panel-body">
			<table id="datatable" class="table table-striped table-hover dataTable">
				<thead>
					<tr>
		                <th>Job No</th>
		                <th>Job Name</th>
		                <th>Order Date</th>
		                <th>Delivery Date</th>
		                <th>Job Status</th>
		                <th>Actions</th>
		            </tr>
				</thead>
			</table>
		</div>
	</div>
</div>

