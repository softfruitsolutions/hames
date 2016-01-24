/**
*	SALE ORDER LIST JAVASCRIPT 
*/

$(function() {
	$('#datatable').dataTable( {
		"bProcessing" : true,
		"bServerSide" : true,
		"bPaginate": true,
        "sAjaxSource": 'datatable',
        "fnServerParams": function ( aoData ) {
            aoData.push({ "name": "sortField", "value": "jobNo"});
        },
        "aoColumns" : [
	                    { mDataProp: 'jobNo' },
	                    { mDataProp: 'jobName' },
	                    {
	                        "mData": 'orderDate',
	                        "bSortable": false,
	                        "mRender": function(data, type, full) {
	                            return DateUtil.getDate(data);
	                        },
	                    },	
	                    {
	                        "mData": 'deliveryDate',
	                        "bSortable": false,
	                        "mRender": function(data, type, full) {
	                            return DateUtil.getDate(data);
	                        },
	                    },	
	                    { mDataProp: 'saleOrderStatus' },
	                    {
	                        "mData": 'orderId',
	                        "bSortable": false,
	                        "mRender": function(data, type, full) {
	                            return statusButtonHTML(full);
	                        },
	                    },	  
           			  ],
        "bFilter" : false,
        "aLengthMenu": [[5, 20, 25, -1], [5, 15, 20, 30]],
		"iDisplayLength" : 5
    });
});

function statusButtonHTML(data){
	var row = '<div class="btn-group">';
		row += '<button class="btn btn-default btn-xs">Status</button>';
		row += '<button data-toggle="dropdown" class="btn btn-default dropdown-toggle btn-xs"><span class="caret"></span></button>';
		row += '<ul class="dropdown-menu slidedown">';
		row += '<li><a onclick="updateOrderStatus(\''+data.orderId+'\',\''+data.jobNo+'\',\'PROOFING\')" data-toggle="modal">Proofing</a></li>';
		row += '<li><a onclick="updateOrderStatus(\''+data.orderId+'\',\''+data.jobNo+'\',\'PROOF_APPROVED\')" data-toggle="modal">Proof Approved</a></li>';
		row += '<li><a onclick="updateOrderStatus(\''+data.orderId+'\',\''+data.jobNo+'\',\'IN_PROGRESS\')" data-toggle="modal">In Progress</a></li>';
		row += '<li><a onclick="updateOrderStatus(\''+data.orderId+'\',\''+data.jobNo+'\',\'COMPLETED\')" data-toggle="modal">Completed</a></li>';
		row += '<li><a onclick="updateOrderStatus(\''+data.orderId+'\',\''+data.jobNo+'\',\'DELIVERED\')" data-toggle="modal">Delivered</a></li>';
		row += '<li class="divider"></li>';
		row += '<li><a onclick="updateOrderStatus(\''+data.orderId+'\',\''+data.jobNo+'\',\'ON_HOLD\')" data-toggle="modal"><b>On Hold</b></a></li>';
		row += '</ul></div>';
		
	return row;
}

function updateOrderStatus(orderId,jobNo,orderStatus){
	var body = 'You are about to change the status of <b>Job No : '+jobNo+' </b> to <b>'+orderStatus+'</b>. Are you sure you want to continue?';
	
	$('#updateStatusModal #bodyContent').html(body);
	
	var href = $("#updateStatusModal #statusUpdateButton").attr("href");
	$("#updateStatusModal #statusUpdateButton").attr("href", href + '?orderId='+orderId+'&jobNo='+jobNo+'&saleorderStatus='+orderStatus+'');
	
	$('#updateStatusModal').modal({
		show:true
	});
	
	
	
}
