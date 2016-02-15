<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>


<!-- URL's -->

<c:url value="/saleorder/" var="saleOrdersUrl" />
<c:url value="/saleorder/view" var="saleOrderViewUrl" />
<c:url value="/saleorder/save" var="saleOrderSaveUrl" />

<style type="text/css">
	.bold{
		font-weight: bold;
	}
</style>
<script type="text/javascript">
	var orderId = "${saleOrder.orderId}";
	var jobNo = "${saleOrder.jobNo}";
	var saleOrderStatus = "${saleOrder.saleOrderStatus}";
	
	function showStatusUpdateModal(orderStatus){
		var body = 'You are about to change the status of <b>Job No : '+jobNo+' </b> to <b>'+orderStatus+'</b>. Are you sure you want to continue?';
			
		if(orderStatus === "DELIVERED"){
			body = 'You are about to change the status of <b>Job No : '+jobNo+' </b> to <b>'+orderStatus+'</b>. Are you sure you want to continue? <br /><br/><b style="color:red;">This opeartion can\'t be reverted </b>';
		}
		
		$('#statusUpdateModal #bodyContent').html(body);
		
		//Setting value to Sale Order Status
		$('#saleOrderStatus').val(orderStatus);
		
		$('#statusUpdateModal').modal({
			show:true,
			backdrop:'static'
		});
	}
	
	function closeStatusUpdateModal(){
		$('#saleOrderStatus').val(saleOrderStatus);
	}
	
	function save(){
		var saleOrder = $('#saleOrder').serialize();
		$.ajax({
			type:'POST',
			url:'${saleOrderSaveUrl}',
			data:saleOrder,
	        async: false,
			success:function(data){
				SuccessAlert.handleSuccess(data);
				setTimeout(function(){
					window.location.href='${saleOrderViewUrl}?id='+orderId;
			    },1000);
				
			},
			error:function(data){
				ErrorAlert.handleError(data.responseJSON.message);
			}
		});
	}
	
	function showPaymentModal(){
		$('#paymentModal').modal({
			show:true,
			backdrop:'static',
		});
	}
</script>

<div class="row">
	<div class="col-md-12">
		<div class="panel panel-primary panel-border top">
		  <div class="panel-heading">
		    <span class="panel-title panel-title"><i class="imoon imoon-drawer2"></i>SALE ORDER : ${saleOrder.jobNo}</span>
		    <ul class="nav panel-tabs">
		      <li class="active">
		        <a href="#tab1" data-toggle="tab">View</a>
		      </li>
		      <li class="">
		        <a href="#tab2" data-toggle="tab">Audit</a>
		      </li>
		    </ul>
		  </div>
		  <div class="panel-menu">
		  	<div class="btn-group">
		  		<a class="btn btn-sm btn-primary" href="${saleOrdersUrl}" title="Back to Sale Orders"><i class="fa fa-reply"></i></a>
		  	</div>
		  </div>
		  <div class="panel-body ">
		  	<form:form modelAttribute="saleOrder" method="POST" action="#">
		  	<div class="tab-content">
			  <div id="tab1" class="tab-pane active">
				<div class="row">
					<div class="col-xs-6">
						<table class="table table-bordered table-condensed ">
							<tbody>
								<tr>
									<td class="bold">Job No</td>
									<td>
										<c:out value="${saleOrder.jobNo }" />
										<form:hidden path="jobNo"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Job Name</td>
									<td>
										<c:out value="${saleOrder.jobName }" />
										<form:hidden path="jobName"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Job Description</td>
									<td>
										<c:out value="${saleOrder.jobDescription}" />
										<form:hidden path="jobDescription"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Customer</td>
									<td>
										<c:out value="${saleOrder.party.fullName}" />
										<form:hidden path="partyId"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Staff Concerned</td>
									<td>
										<c:out value="${saleOrder.staffConcernedText}" />
										<form:hidden path="staffConcerned"/>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="col-xs-6">
						<table class="table table-bordered table-condensed ">
							<tbody>
								<tr>
									<td class="bold">Created Date</td>
									<td>
										<joda:format value="${saleOrder.orderDate}" pattern="dd/MM/yyyy" />
										<form:hidden path="orderDate"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Delivery Date</td>
									<td>
										<joda:format value="${saleOrder.deliveryDate}" pattern="dd/MM/yyyy" />
										<form:hidden path="deliveryDate"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Order Status</td>
									<td>
										<b><c:out value="${saleOrder.saleOrderStatus}" /></b>
										<c:if test="${saleOrder.saleOrderStatus != 'DELIVERED' }">
											<div class="btn-group pull-right">
												<button class="btn btn-default btn-xs"><i class="fa fa-cog"></i></button>
												<button data-toggle="dropdown" class="btn btn-default dropdown-toggle btn-xs"><span class="caret"></span></button>
												<ul class="dropdown-menu slidedown">
													<li><a onclick="showStatusUpdateModal('PROOFING')" data-toggle="modal">Proofing</a></li>
													<li><a onclick="showStatusUpdateModal('PROOF_APPROVED')" data-toggle="modal">Proof Approved</a></li>
													<li><a onclick="showStatusUpdateModal('IN_PROGRESS')" data-toggle="modal">In Progress</a></li>
													<li><a onclick="showStatusUpdateModal('COMPLETED')" data-toggle="modal">Completed</a></li>
													<li><a onclick="showStatusUpdateModal('DELIVERED')" data-toggle="modal">Delivered</a></li>
													<li class="divider"></li>
													<li><a onclick="showStatusUpdateModal('ON_HOLD')" data-toggle="modal"><b>On Hold</b></a></li>
												</ul>
											</div>
										</c:if>
										<form:hidden path="saleOrderStatus"/>
									</td>
								</tr>
								<shiro:hasPermission name="order:saleorder:payment:view">
									<tr>
										<td class="bold">Payment Status</td>
										<td>
											<b><c:out value="${saleOrder.payment.paymentStatus}" /></b>
											<div class="pull-right">
												<a onclick="showPaymentModal()" href="#" class="btn btn-default btn-xs"><i class="fa fa-money"></i></a>
											</div>
										</td>
									</tr>
								</shiro:hasPermission>
							</tbody>
						</table>
					</div>
				</div>
				<hr />
				<form:hidden path="orderId" />
				<form:hidden path="orderType" />
				<div class="row">
					<div class="col-xs-4">
						<table class="table table-bordered table-condensed ">
							<tbody>
								<tr>
									<td class="bold">Colors</td>
									<td>
										<c:out value="${saleOrder.colors}" />
										<form:hidden path="colors"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Proof</td>
									<td>
										<c:out value="${saleOrder.proof}" />
										<form:hidden path="proof"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Screen Printing</td>
									<td>
										<c:out value="${saleOrder.screenPrinting}" />
										<form:hidden path="screenPrinting"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Film</td>
									<td>
										<c:out value="${saleOrder.film}" />
										<form:hidden path="film"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Paper</td>
									<td>
										<c:out value="${saleOrder.paper}" />
										<form:hidden path="paper"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Plate</td>
									<td>
										<c:out value="${saleOrder.plate}" />
										<form:hidden path="plate"/>
									</td>
								</tr>
							</tbody>
						</table>			
					</div>
					<div class="col-xs-4">
						<table class="table table-bordered table-condensed ">
							<tbody>
								<tr>
									<td class="bold">Paper</td>
									<td>
										<c:out value="${saleOrder.printingPaper}" />
										<form:hidden path="printingPaper"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Size</td>
									<td>
										<c:out value="${saleOrder.printingSize}" />
										<form:hidden path="printingSize"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Quantity</td>
									<td>
										<c:out value="${saleOrder.printingQuantity}" />
										<form:hidden path="printingQuantity"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Ink</td>
									<td>
										<c:out value="${saleOrder.printingInk}" />
										<form:hidden path="printingInk"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Colors</td>
									<td>
										<c:out value="${saleOrder.printingColors}" />
										<form:hidden path="printingColors"/>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="col-xs-4">
						<table class="table table-bordered table-condensed">
							<tbody>
								<tr>
									<td class="bold">Printing</td>
									<td>
										<c:out value="${saleOrder.printing}" />
										<form:hidden path="printing"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Numbering</td>
									<td>
										<c:out value="${saleOrder.numbering}" />
										<form:hidden path="numbering"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Stiching</td>
									<td>
										<c:out value="${saleOrder.stiching}" />
										<form:hidden path="stiching"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Binding</td>
									<td>
										<c:out value="${saleOrder.binding}" />
										<form:hidden path="binding"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Other</td>
									<td>
										<c:out value="${saleOrder.other}" />
										<form:hidden path="other"/>
									</td>
								</tr>
								<tr>
									<td class="bold">Remarks</td>
									<td>
										<c:out value="${saleOrder.remarks}" />
										<form:hidden path="remarks"/>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			  </div>
			  <div id="tab2" class="tab-pane">
			  	<jsp:include page="/WEB-INF/views/hames/audit.jsp" />
			  </div>
			</div>
		  </div>
		</div>
	</div>
</div>



<!-- 
	PAYMENT MODAL	
 -->
<div class="modal fade in" id="paymentModal" aria-hidden="false">
	<div class="modal-dialog">
 		<div class="modal-content" style="width:700px;">
   			<div class="modal-header">
				<h4>
					<i class="fa fa-money"></i>
					PAYMENTS
				</h4>
 			</div>
	    	<div class="modal-body">
	    		<div id="bodyContent">
	    			<div class="row">
	    				<div class="col-xs-7">
	    					<c:if test="${saleOrder.payment.paymentStatus != 'PAID' }">
								<c:forEach items="${saleOrder.payment.paymentItems}" var="paymentItems" varStatus="pStatus">
									<c:if test="${pStatus.last}">
										<div class="form-group">
											<label for="paymentDate" class="col-lg-5 control-label">Payment Date</label>
											<div class="col-lg-7">
												<form:input path="payment.paymentItems[${pStatus.index}].paymentDate" cssClass="form-control input-sm " placeholder="Payment Date" data-required="true" />								 	
											</div><!-- /.col -->
										</div>
										<div class="form-group">
											<label for="paymentAmount" class="col-lg-5 control-label">Payment Amount</label>
											<div class="col-lg-7">
												<form:input path="payment.paymentItems[${pStatus.index}].paymentAmount" cssClass="form-control input-sm" placeholder="Payment Amount" data-required="true"/>								 	
											</div><!-- /.col -->
										</div>
										<div class="form-group">
											<label for="discountAmount" class="col-lg-5 control-label">Discount</label>
											<div class="col-lg-7">
												<form:input path="payment.discountAmount" cssClass="form-control input-sm" placeholder="Discount Amount" data-required="true"/>								 	
											</div><!-- /.col -->
										</div>
										<div class="form-group">
											<label for="jobId" class="col-lg-5 control-label">Payment Description</label>
											<div class="col-lg-7">
												<form:textarea path="payment.paymentItems[${pStatus.index}].description" cssClass="form-control input-sm" placeholder="Payment Description" data-required="true"/>								 	
											</div><!-- /.col -->
										</div>
									</c:if>
								</c:forEach>
							</c:if>
	    				</div>
	    				<div class="col-xs-5">
							<table class="table table-condensed">
							  <thead>
							  	<c:set var="statusTableHeaderColor" value="success" />
							  	<c:if test="${saleOrder.payment.paymentStatus != 'PAID'}">
							  		<c:set var="statusTableHeaderColor" value="danger" />
							  	</c:if>
							    <tr class="${statusTableHeaderColor }">
							      <th><c:out value="${saleOrder.payment.paymentStatus }" /></th>
							      <th></th>
							    </tr>
							  </thead>
							  <tbody>
							    <tr>
							      <td>Total Amount</td>
							      <td style="text-align: right;">
							      	<b>
							      		<c:out value="${saleOrder.payment.totalAmount}" />
										<form:hidden path="payment.totalAmount"/>
									</b>
								  </td>
							    </tr>
							    <tr>
							      <td>Amount Paid</td>
							      <td style="text-align: right;">
							      	<b>
							      		<c:out value="${saleOrder.payment.amountPaid}" />
										<form:hidden path="payment.amountPaid"/>
							      	</b>
							      </td>
							    </tr>
							    <c:if test="${saleOrder.payment.discountAmount != null}">
								<tr>
									<td>Discount Amount</td>
									<td style="text-align: right;">
										<b><c:out value="${saleOrder.payment.discountAmount}" /></b>
									</td>
								</tr>
								</c:if>
							    <tr>
							      <td>Balance Due</td>
							      <td style="text-align: right;">
							      	<b>
							      		<c:out value="${saleOrder.payment.balanceDue}" />
										<form:hidden path="payment.balanceDue"/>
							      	</b>
							      </td>
							    </tr>
							  </tbody>
							  <form:hidden path="payment.paymentId"/>
							  <form:hidden path="payment.paymentNotes"/>
							  <form:hidden path="payment.paymentDate"/>
							</table>
	    				</div>
	    			</div>
	    			<b>Payment Details</b>
	    			<div class="row">
						<div class="col-xs-12">
							<table class="table table-bordered table-condensed table-hover">
								<thead>
									<tr>
										<th> Date </th>
										<th> Amount </th>
										<th> Type </th>
										<th> Description </th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${saleOrder.payment.paymentItems}" var="paymentItems" varStatus="pStatus">
										<c:if test="${!pStatus.last}">
											<tr>
												<td><joda:format value="${paymentItems.paymentDate}" pattern="dd/MM/yyyy" /></td>
												<td><c:out value="${paymentItems.paymentAmount }" /></td>
												<td><c:out value="${paymentItems.paymentType }" /></td>
												<td><c:out value="${paymentItems.description }" /></td>
											</tr>
											<form:hidden path="payment.paymentItems[${pStatus.index}].paymentAmount"/>
											<form:hidden path="payment.paymentItems[${pStatus.index}].paymentDate" />
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
	    		</div>
	    		
			</div>
			<div class="modal-footer">
				<div class="row">
					<div class="col-xs-6">
					</div>
					<div class="col-xs-6">
						<div class="form-group text-right">
							<c:if test="${saleOrder.payment.paymentStatus != 'PAID' }">
								<a onclick="save()" class="btn btn-primary" ><i class="fa fa-save"></i> Pay</a>
							</c:if>
			     			<button type="button" class="btn btn-danger" class="close" data-dismiss="modal" aria-hidden="true" onclick="">x Close</button>
						</div>
					</div>
				</div>			
			</div>
	    </div>
	 </div>
</div>
</form:form>

<!-- 
	STATUS UPDATE MODAL
 -->
<div class="modal fade in" id="statusUpdateModal" aria-hidden="false">
	<div class="modal-dialog">
 		<div class="modal-content">
   			<div class="modal-header">
				<h4>Update</h4>
 			</div>
	    	<div class="modal-body">
	    		<div id="bodyContent">
	    		</div>
			</div>
			<div class="modal-footer">
				<div class="row">
					<div class="col-xs-6">
					</div>
					<div class="col-xs-6">
						<div class="form-group text-right">
							<a onclick="save()" class="btn btn-primary" ><i class="fa fa-save"></i> Update</a>
			     			<button type="button" class="btn btn-danger" class="close" data-dismiss="modal" aria-hidden="true" onclick="closeStatusUpdateModal()">x Close</button>
						</div>		
					</div>
				</div>
			</div>
			
	    </div>
	 </div>
</div>

