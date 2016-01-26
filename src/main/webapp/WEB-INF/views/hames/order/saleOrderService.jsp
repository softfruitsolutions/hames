<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<!-- URL's -->
<c:url value="/saleorder/list" var="saleOrderListUrl" />
<style type="text/css">
	.bold{
		font-weight: bold;
	}
</style>
<script type="text/javascript">
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
		$('#saleOrder').serialize();
		$('#saleOrder').submit();
	}
	
	function showPaymentModal(){
		$('#paymentModal').modal({
			show:true,
			backdrop:'static',
		});
	}
</script>
<div class="col-md-12">
	<h3 class="headline m-top-md">
		Sale Order : ${saleOrder.jobNo}
		<span class="line"></span>
	</h3>
	<div class="panel panel-default no-margin">
		<div class="panel-heading">
			<div class="btn-toolbar no-margin">
				<div class="btn-group">
					<a class="btn btn-sm btn-default" href="${saleOrderListUrl}" title="Back to Sale Order's"><i class="fa fa-reply"></i></a>
				</div>
				<div class="pull-right">
					
				</div>
				
			</div>
		</div>
		<div class="panel-body">
			<form:form modelAttribute="saleOrder" method="POST" action="save">
				<form:hidden path="orderId" />
				<form:hidden path="orderType" />
				
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
										<c:out value="${saleOrder.party.firstName}" />
										<form:hidden path="partyId"/>
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
								<tr>
									<td class="bold">Payment Status</td>
									<td>
										<b><c:out value="${saleOrder.payment.paymentStatus}" /></b>
										<div class="pull-right">
											<a onclick="showPaymentModal()" href="#" class="btn btn-default btn-xs"><i class="fa fa-money"></i></a>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<h5 class="headline">
					Job Details
					<span class="line"></span>
				</h5>
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
				<h5 class="headline">
					Payments
					<span class="line"></span>
				</h5>
				
					<div class="col-xs-6">
						
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
					Payments
					<span class="pull-right"><b><c:out value="${saleOrder.payment.paymentStatus }" /></b></span>
				</h4>
 			</div>
	    	<div class="modal-body">
	    		<div id="bodyContent">
	    			<div class="row">
						<div class="col-xs-4">
							<table class="table table-bordered table-condensed">
								<tbody>
									<tr>
										<td class="bold">Total Amount</td>
										<td>
											<c:out value="${saleOrder.payment.totalAmount}" />
											<form:hidden path="payment.totalAmount"/>
										</td>
									</tr>
									<tr>
										<td class="bold">Paid Amount</td>
										<td>
											<c:out value="${saleOrder.payment.amountPaid}" />
											<form:hidden path="payment.amountPaid"/>
										</td>
									</tr>
									<tr>
										<td class="bold">Discount Amount</td>
										<td>
											<c:out value="${saleOrder.payment.discountAmount}" />
											<form:hidden path="payment.discountAmount"/>
										</td>
									</tr>
									<tr>
										<td class="bold">Balance Due</td>
										<td>
											<c:out value="${saleOrder.payment.balanceDue}" />
											<form:hidden path="payment.balanceDue"/>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="col-xs-8">
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
	    		<c:if test="${saleOrder.payment.paymentStatus != 'PAID' }">
	    		<h5 class="headline">
					Service Bill
					<span class="line"></span>
				</h5>
				<div class="row">
					<c:forEach items="${saleOrder.payment.paymentItems}" var="paymentItems" varStatus="pStatus">
						<c:if test="${pStatus.last}">
							<div class="form-group">
								<label for="jobId" class="col-lg-3 control-label">Payment Date</label>
								<div class="col-lg-9">
									<form:input path="payment.paymentItems[${pStatus.index}].paymentDate" cssClass="form-control input-sm " placeholder="Payment Date" data-required="true" />								 	
								</div><!-- /.col -->
							</div>
							<div class="form-group">
								<label for="jobId" class="col-lg-3 control-label">Payment Amount</label>
								<div class="col-lg-9">
									<form:input path="payment.paymentItems[${pStatus.index}].paymentAmount" cssClass="form-control input-sm" placeholder="Payment Amount" data-required="true"/>								 	
								</div><!-- /.col -->
							</div>
							<div class="form-group">
								<label for="jobId" class="col-lg-3 control-label">Payment Description</label>
								<div class="col-lg-9">
									<form:textarea path="payment.paymentItems[${pStatus.index}].description" cssClass="form-control input-sm" placeholder="Payment Description" data-required="true"/>								 	
								</div><!-- /.col -->
							</div>
						</c:if>
					</c:forEach>
				</div>
				</c:if>
		    	<hr />
				<div class="row">
					<div class="col-xs-6">
					</div>
					<div class="col-xs-6">
						<div class="form-group text-right">
							<c:if test="${saleOrder.payment.paymentStatus == PAID }">
								<a onclick="save()" class="btn btn-success btn-sm" ><i class="fa fa-save"></i> Pay</a>
							</c:if>
			     			<button type="button" class="btn btn-danger btn-sm" class="close" data-dismiss="modal" aria-hidden="true" onclick="">x Close</button>
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
		    	<hr />
				<div class="row">
					<div class="col-xs-6">
					</div>
					<div class="col-xs-6">
						<div class="form-group text-right">
							<a onclick="save()" class="btn btn-success btn-sm" ><i class="fa fa-save"></i> Update</a>
			     			<button type="button" class="btn btn-danger btn-sm" class="close" data-dismiss="modal" aria-hidden="true" onclick="closeStatusUpdateModal()">x Close</button>
						</div>
					</div>
				</div>
			</div>
	    </div>
	 </div>
</div>

