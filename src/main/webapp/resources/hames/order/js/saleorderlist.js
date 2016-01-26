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
	                            return "<a href='view?id="+data+"'><i class='fa fa-eye'></i></a>";
	                        },
	                    },	  
           			  ],
        "bFilter" : false,
        "aLengthMenu": [[5, 20, 25, -1], [5, 15, 20, 30]],
		"iDisplayLength" : 5
    });
});
	
	
