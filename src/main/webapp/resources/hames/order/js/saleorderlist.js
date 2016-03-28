/**
*	SALE ORDER LIST JAVASCRIPT 
*/

$(function() {
	loadDatatable();
});

var saleOrderDatatable;
var saleOrderCriteria;
function loadDatatable(){
	
	saleOrderDatatable = $('#saleorderDatatable').dataTable( {
		"bProcessing" : true,
		"bServerSide" : true,
		"bPaginate": true,
        "sAjaxSource": SALEORDER_DATATABLE_URL,
        "fnServerParams": function ( aoData ) {
            aoData.push({ "name": "sortField", "value": "jobNo"});
            aoData.push({ "name": "sortDirection", "value": "desc"});
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
	                        	if(data != null && data != ''){
	                        		return DateUtil.getDate(data);	
	                        	}else{
	                        		return "";
	                        	}
	                            
	                        },
	                    },	
	                    { mDataProp: 'payment.paymentStatus' },
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
        "aLengthMenu": [[10, 20, 25, -1], [10, 20, 25, 50]],
		"iDisplayLength" : 10,
		"buttons": [
		          'colvis',
		          'excel',
		          'print'
		      ],
    });
}

function reloadDatatable(){
	saleOrderCriteria = $('#saleOrderSearchCriteria').serialize();
	saleOrderDatatable.fnReloadAjax(SALEORDER_DATATABLE_URL+'?'+saleOrderCriteria);
}	
