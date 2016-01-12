<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- URL's -->
<c:url value="/staffrole/view" var="staffRoleViewUrl" />
<c:url value="/staffrole/datatable" var="staffRoleDatatableUrl" />

<script type="text/javascript">

$(function() {
	$('#datatable').dataTable( {
		"bProcessing" : true,
		"bServerSide" : true,
		"bPaginate": true,
        "sAjaxSource": '${staffRoleDatatableUrl}',
        "fnServerParams": function ( aoData ) {
            aoData.push({ "name": "sortField", "value": "roleId"});
        },
        "aoColumns" : [
						{ mDataProp: 'roleId' },
	                    { mDataProp: 'roleName' },
	                    { mDataProp: 'roleDescription' },
	                    {
	                        "mData": 'roleId',
	                        "bSortable": false,
	                        "mRender": function(data, type, full) {
	                            return '<a href="${staffRoleViewUrl}?id='+data+'" title="Edit"><i class="fa fa-pencil"></i></a> &nbsp'+
	                            	   '<a href="${staffRoleViewUrl}?id='+data+'" title="Edit"><i class="fa fa-trash-o red"></i></a>';
	                        },
	                    },	       
           			  ],
        "bFilter" : false,
        "aLengthMenu": [[5, 20, 25, -1], [5, 15, 20, 30]],
		"iDisplayLength" : 5
    });
});
</script>
<div class="col-md-12">
	<h3 class="headline m-top-md">
		Staff Role
		<span class="line"></span>
	</h3>
	
	<div class="panel panel-default">
		<div class="panel-heading">
			Available Staff Roles
			<span class="pull-right">
				<a class="btn btn-xs btn-info" href="${staffRoleViewUrl }" title="Create Staff Role"><i class="fa fa-edit"></i> Create Staff Role</a>
			</span>
			<br/>
		</div>
		<div class="panel-body">
			<table id="datatable" class="table table-striped table-hover dataTable">
				<thead>
					<tr>
						<th>Role ID</th>
		                <th>Role Name</th>
		                <th>Role Description</th>
		                <th>Actions</th>
		            </tr>
				</thead>
			</table>
		</div>
	</div>
</div>