<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- URL's -->
<c:url value="/saleorder/view" var="saleOrderViewUrl" />
<c:url value="/saleorder/datatable" var="saleOrderDatatableUrl" />

<script src='<c:url value="/resources/hames/order/js/saleorderlist.js" />' type="text/javascript"></script>

<div class="row">
	<div class="col-md-9">
		<div class="panel panel-primary panel-border top">
		  <div class="panel-heading">
		    <span class="panel-title panel-title hidden-xs"><i class="imoon imoon-drawer2"></i>SALE ORDERS</span>
		  </div>
		  <shiro:hasPermission name="order:saleorder:create">
		  <div class="panel-menu">
		  	 <a class="btn btn-primary" href="${saleOrderViewUrl }" title="Create Order "><i class="fa fa-edit"></i> Create Order </a>
		  </div>
		  </shiro:hasPermission>
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
	<div class="col-md-3">
		<div class="panel panel-primary panel-border top">
		  <div class="panel-heading">
		    <span class="panel-title panel-title hidden-xs"><i class="imoon imoon-search"></i>FILTER ORDERS</span>
		  </div>
		  <div class="panel-body well">
		  	<form:form modelAttribute="saleOrderCriteria">
		  	<div class="admin-form">
		  		<div class="section mb15">
	              <label class="field prepend-icon" for="order-id">
	              	<form:input path="jobNo" placeholder="Order Job No" cssClass="gui-input" />
	                <label class="field-icon" for="order-id">
	                  <i class="fa fa-tag"></i>
	                </label>
	              </label>
	            </div>
	            <hr class="short" />
	            <div class="section">
	                <button type="button" class="btn btn-default btn-sm ph25" onclick="loadDatatable()">Search</button>
	            </div>
		  	</div>
		  	</form:form>
		  </div>
		</div>
	</div>
</div>

