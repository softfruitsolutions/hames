<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- URL's -->
<c:url value="/staff/view" var="staffViewUrl" />
<c:url value="/staff/datatable" var="staffDatatableUrl" />

<script type="text/javascript">

$(document).ready(function() {
	$('#datatable').dataTable( {
		"bProcessing" : true,
		"bServerSide" : true,
		"bPaginate": true,
        "sAjaxSource": '${staffDatatableUrl}',
        "fnServerParams": function ( aoData ) {
            aoData.push({ "name": "sortField", "value": "staffId"});
        },
        "aoColumns" : [
	                    { mDataProp: 'firstName' },
	                    { mDataProp: 'lastName' },
	                    { mDataProp: 'city' },
	                    { mDataProp: 'country' },
	                    {
	                        "mData": 'staffId',
	                        "bSortable": false,
	                        "mRender": function(data, type, full) {
	                            return '<a href="${staffViewUrl}?id='+data+'" title="Edit"><i class="fa fa-pencil"></i></a> &nbsp'+
	                            	   '<a href="${staffViewUrl}?id='+data+'" title="Edit"><i class="fa fa-trash-o red"></i></a>';
	                        },
	                    },	       
           			  ],
        "bFilter" : true,
        "aLengthMenu": [[5, 20, 25, -1], [5, 15, 20, 30]],
		"iDisplayLength" : 5
    });
	
});
</script>
<div class="col-md-12">
	<h3 class="headline m-top-md">
		Staff
		<span class="line"></span>
	</h3>
	
	<div class="panel panel-default">
		<div class="panel-heading">
			Available Staffs
			<span class="pull-right">
				<a class="btn btn-xs btn-info" href="${staffViewUrl }" title="Create Staff "><i class="fa fa-edit"></i> Create Staff </a>
			</span>
			<br/>
		</div>
		<div class="panel-body">
			<table id="datatable" class="table table-striped table-hover dataTable">
				<thead>
					<tr>
		                <th>First Name</th>
		                <th>Last Name</th>
		                <th>City</th>
		                <th>Country</th>
		                <th>Actions</th>
		            </tr>
				</thead>
			</table>
		</div>
	</div>
</div>
