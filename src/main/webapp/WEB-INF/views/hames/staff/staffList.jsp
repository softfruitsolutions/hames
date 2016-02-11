<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- URL's -->
<c:url value="/staff/view" var="staffViewUrl" />
<c:url value="/staff/datatable" var="staffDatatableUrl" />

<script type="text/javascript">
<c:set var="hasCreatePermission" value="false" />
<shiro:hasPermission name="admin:rolepermission:create">
	<c:set var="hasCreatePermission" value="true" />
</shiro:hasPermission>

$(document).ready(function() {
	$('#staffDatatable').dataTable( {
		"bProcessing" : true,
		"bServerSide" : true,
		"bPaginate": true,
        "sAjaxSource": '${staffDatatableUrl}',
        "fnServerParams": function ( aoData ) {
            aoData.push({ "name": "sortField", "value": "staffId"});
        },
        "aoColumns" : [
	                    { mDataProp: 'fullName' },
	                    { mDataProp: 'phoneNumber'},
	                    { mDataProp: 'city' },
	                    { mDataProp: 'country' },
	                    {
	                        "mData": 'staffId',
	                        "bSortable": false,
	                        "mRender": function(data, type, full) {
	                        	if(${hasCreatePermission}){
	                        		return '<a href="${staffViewUrl}?id='+data+'" title="Edit"><i class="fa fa-pencil"></i></a> &nbsp';
	                        	}else{
	                        		return "";
	                        	}
	                        },
	                    },	       
           			  ],
        "bFilter" : false,
        "aLengthMenu": [[10, 25, 30, -1], [10, 25, 30, 50]],
		"iDisplayLength" : 10
    });
	
});
</script>

<div class="row">
	<div class="col-md-12">
		<div class="panel panel-primary panel-border top">
		  <div class="panel-heading">
		    <span class="panel-title panel-title hidden-xs"><i class="imoon imoon-users"></i>AVAILABLE STAFFS</span>
		  </div>
		  <div class="panel-menu">
		  	 <shiro:hasPermission name="hr:staff:create">
		  	 	<a class="btn btn-primary" href="${staffViewUrl }" title="Create Staff "><i class="fa fa-user"></i> Create Staff </a>
		  	 </shiro:hasPermission>
		  </div>
		  <div class="panel-body ">
			<table id="staffDatatable" class="table table-striped table-hover dataTable">
				<thead>
					<tr>
		                <th>Staff</th>
		                <th>Phone No</th>
		                <th>City</th>
		                <th>Country</th>
		                <th>Actions</th>
		            </tr>
				</thead>
			</table>
		  </div>
		</div>
	</div>
</div>
