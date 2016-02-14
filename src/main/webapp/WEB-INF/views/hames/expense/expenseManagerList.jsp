<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- URL's -->
<c:url value="/expense/view" var="expenseViewUrl" />
<c:url value="/expense/datatable" var="expenseDatatableUrl" />

<script type="text/javascript">
	$(function() {
		$('#datatable').dataTable( {
			"bProcessing" : true,
			"bServerSide" : true,
			"bPaginate": true,
	        "sAjaxSource": 'datatable',
	        "fnServerParams": function ( aoData ) {
	            aoData.push({ "name": "sortField", "value": "expenseDate"});
	        },
	        "aoColumns" : [
		                    {
		                        "mData": 'expenseDate',
		                        "bSortable": false,
		                        "mRender": function(data, type, full) {
		                            return DateUtil.getDate(data);
		                        },
		                    },	
		                    { mDataProp: 'payeeName' },
		                    { mDataProp: 'expenseCategory.categoryName' },
		                    { mDataProp: 'notes' },
		                    { mDataProp: 'payment.totalAmount' },
		                    {
		                        "mData": 'expenseId',
		                        "bSortable": false,
		                        "mRender": function(data, type, full) {
		                            return "<a href='view?id="+data+"'><i class='fa fa-pencil'></i></a>";
		                        },
		                    },	  
	           			  ],
	        "bFilter" : false,
	        "aLengthMenu": [[5, 20, 25, -1], [5, 15, 20, 30]],
			"iDisplayLength" : 5
	    });
	});
</script>

<div class="row">
	<div class="col-md-12">
		<div class="panel panel-primary panel-border top">
		  <div class="panel-heading">
		    <span class="panel-title panel-title hidden-xs"><i class="glyphicon glyphicon-tags"></i>EXPENSES</span>
		  </div>
		  <div class="panel-menu">
		  	 <shiro:hasPermission name="expense:manager:create">
		  	 	<a class="btn btn-primary" href="${expenseViewUrl }" title="Create Expense "><i class="fa fa-edit"></i> Create Expense </a>
		  	 </shiro:hasPermission>
		  </div>
		  <div class="panel-body ">
			<table id="datatable" class="table table-striped table-hover table-condensed dataTable">
				<thead>
					<tr>
		                <th>Expense Date</th>
		                <th>Payee Name</th>
		                <th>Category</th>
		                <th>Notes</th>
		                <th>Total Amount</th>
		                <th>Actions</th>
		            </tr>
				</thead>
			</table>
		  </div>
		</div>
	</div>
</div>

