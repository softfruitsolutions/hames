<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- URL's -->
<c:url value="/saleorder/view" var="saleOrderViewUrl" />
<c:url value="/saleorder/datatable" var="saleOrderDatatableUrl" />

<script src='<c:url value="/resources/hames/order/js/saleorderlist.js" />' type="text/javascript"></script>

<div class="row">
	<div class="col-md-12">
		<div class="panel panel-primary panel-border top">
		  <div class="panel-heading">
		    <span class="panel-title panel-title hidden-xs"><i class="imoon imoon-drawer2"></i>SALE ORDERS</span>
		  </div>
		  <div class="panel-menu">
		  	 <shiro:hasPermission name="order:saleorder:create">
		  	 	<a class="btn btn-primary" href="${saleOrderViewUrl }" title="Create Order "><i class="fa fa-edit"></i> Create Order </a>
		  	 </shiro:hasPermission>
		  </div>
		  <div class="panel-body ">
			<table id="saleorderDatatable" class="table table-striped table-hover table-condensed dataTable">
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
</div>

