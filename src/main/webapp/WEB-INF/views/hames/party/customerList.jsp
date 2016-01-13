<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- URL's -->
<c:url value="/customer/view" var="customerViewUrl" />

<script type="text/javascript">
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
			<table id="datatable" class="table table-striped table-hover dataTable">
				<thead>
					<tr>
		            </tr>
				</thead>
			</table>
		</div>
	</div>
</div>