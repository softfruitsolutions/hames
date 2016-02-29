/**
 *  SALE ORDER FUNCTIONS
 */

    $(function(){
    	$("form#saleOrder #partyId").select2();
    	loadDatePipe();
    	//Customer.showModal();
	});
    
    /**
     * Loading date related functions 
     */
    function loadDatePipe(){
    	$('#orderDate').mask("99/99/9999");
		$('#deliveryDate').mask("99/99/9999");
		$("#orderDate").on("dp.change", function (e) {
            $('#deliveryDate').data("DateTimePicker").setMinDate(e.date);
        });
        $("#deliveryDate").on("dp.change", function (e) {
            $('#orderDate').data("DateTimePicker").setMaxDate(e.date);
        });
    }
    
	
    /**
     * Save an order
     */
	function save(){
		var saleOrder = $('#saleOrder').serialize();
		$.ajax({
			type:'POST',
			url:SAVE_SALE_ORDER,
			data:saleOrder,
	        async: false,
			success:function(data){
				SuccessAlert.handleSuccess(data);
				setTimeout(function(){
					window.location.href=VIEW_SALE_ORDER_LIST;
			    },1000);
				
			},
			error:function(data){
				ErrorAlert.handleError(data.responseJSON.message);
			}
		});
	}
	
	/**
	 * Calculate balance due
	 */
	function calculateBalanceDue(){
		var totalAmount = $('#payment\\.totalAmount').val();
		var discountAmount = $('#payment\\.discountAmount').val() == null ? 0 : $('#payment\\.discountAmount').val();
		var paymentAmount = $('#payment\\.paymentItems0\\.paymentAmount').val() == null ? 0 : $('#payment\\.paymentItems0\\.paymentAmount').val();
		
		var balanceDue = totalAmount - discountAmount - paymentAmount;
		$('#balance').val(balanceDue);
	}
	
	/**
	* This function related to customer register modal
	*/
	function setCustomerValues(){
		var data = [];
		
		$("form#saleOrder #partyId").select2("destroy");
		$('form#saleOrder #partyId').find('option').remove().end();
		
		$.ajax({
			type:'GET',
			url:GET_CUSTOMERS,
	        async: false,
			success:function(response){
				$.each(response.datas,function(i,customers){
					$.each(customers,function(c,customer){
						var selectData = {};
						selectData.id = customer.partyId;
						selectData.text = customer.fullName;
						data.push(selectData);
					});
				});
			},
		});
		
		$("form#saleOrder #partyId").select2({
			  data: data
		});
	}