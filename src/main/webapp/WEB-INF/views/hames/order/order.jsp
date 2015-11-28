<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>


<script type="text/javascript">

	$(function(){
		$('#createdDate').mask("99/99/9999");
		$('#deliveryDate').mask("99/99/9999");
	});
	
	function save(){
		$('#order').serialize();
		$('#order').submit();
	}
	
	function view(id){
		if(id == null){
			window.location.href = "orderview";
		}else{
			window.location.href = "orderview?id="+id;	
		}
	}
	
	function calculate(){
		var totalAmount = $('#totalAmount').val();
		var paymentAmount = $('#paymentAmount').val();
		var balance = totalAmount - paymentAmount;
		console.log(balance);
		$('#balance').val(balance);
	}
	
</script>

<div class="col-md-12">
	<h3 class="headline m-top-md">
		Order
		<span class="line"></span>
	</h3>
	<div class="panel panel-default no-margin">
		<div class="panel-heading">
			<a href="#payment" class="btn btn-success" data-toggle="modal"><i class="fa fa-save"></i> Process</a>
			<div class="pull-right">
				<a class="btn btn-warning">
					<c:if test="${order.orderStatus == 40}">
						DRAFT
					</c:if>
				</a>
			</div>
		</div>
		<div class="panel-body">
			<form:form modelAttribute="order" method="POST" action="ordersave">
				<form:hidden path="orderStatus"/>
				<div class="row">
					<div class="col-xs-8">
						<div class="form-group">
							<label for="jobId" class="col-lg-3 control-label">Job No</label>
							<div class="col-lg-9">
								<form:input path="jobNo" cssClass="form-control input-sm" placeholder="Job No" readonly="readonly"/>								 	
							</div><!-- /.col -->
						</div><!-- /form-group -->
						<div class="form-group">
							<label for="jobName" class="col-lg-3 control-label">Job Name</label>
							<div class="col-lg-9">
								<form:input path="jobName" cssClass="form-control input-sm" placeholder="Job Name" readonly="readonly"/>
							</div><!-- /.col -->
						</div><!-- /form-group -->
						<div class="form-group">
							<label for="jobName" class="col-lg-3 control-label">Job Description</label>
							<div class="col-lg-9">
								<form:textarea path="jobDescription" cssClass="form-control input-sm" placeholder="Job Description" />
							</div><!-- /.col -->
						</div><!-- /form-group -->
						<div class="form-group">
							<label for="customerName" class="col-lg-3 control-label">Customer</label>
							<div class="col-lg-9">
								<form:select path="customerId" cssClass="form-control input-sm">
									<form:options items="${customers }" itemLabel="firstName" itemValue="customerId"/>
								</form:select>
							</div><!-- /.col -->
						</div><!-- /form-group -->
					</div>
					<div class="col-xs-4">
						<div class="form-group">
							<label for="createdDate" class="col-lg-4 control-label">Created Date</label>
							<div class="col-lg-8">
								<form:input path="createdDate" cssClass="form-control input-sm"  type="text"/>
							</div>
						</div><!-- /form-group -->
						<div class="form-group">
							<label for="deliveryDate" class="col-lg-4 control-label">Delivery Date</label>
							<div class="col-lg-8">
								<form:input path="deliveryDate" cssClass="form-control input-sm"  type="text"/>
							</div><!-- /.col -->
						</div><!-- /form-group -->
					</div>
				</div>
				<hr />
				<div class="row">
					<div class="col-xs-6">
						<div class="form-group">
							<label for="colors" class="col-lg-3 control-label">Colors</label>
							<div class="col-lg-9">
								<form:input path="colors" class="form-control input-sm" placeholder="Specify multiple colors here..."/>
							</div><!-- /.col -->
						</div><!-- /form-group -->
						<div class="form-group">
							<label for="proof" class="col-lg-3 control-label">Proof</label>
							<div class="col-lg-9">
								<form:input path="proof" class="form-control input-sm" placeholder="Proof"/>
							</div><!-- /.col -->
						</div><!-- /form-group -->
						<div class="form-group">
							<label for="screenPrinting" class="col-lg-3 control-label">Screen Printing</label>
							<div class="col-lg-9">
								<form:input path="screenPrinting" class="form-control input-sm" placeholder="Screen Printing"/>
							</div><!-- /.col -->
						</div><!-- /form-group -->
					</div>
					<div class="col-xs-6">
						<div class="form-group">
							<label for="film" class="col-lg-3 control-label">Film</label>
							<div class="col-lg-9">
								<form:input path="film" class="form-control input-sm" placeholder="Film"/>
							</div><!-- /.col -->
						</div><!-- /form-group -->
						<div class="form-group">
							<label for="colors" class="col-lg-3 control-label">Paper</label>
							<div class="col-lg-9">
								<form:input path="paper" class="form-control input-sm" placeholder="Paper"/>
							</div><!-- /.col -->
						</div><!-- /form-group -->
						<div class="form-group">
							<label for="plate" class="col-lg-3 control-label">Plate</label>
							<div class="col-lg-9">
								<form:input path="plate" class="form-control input-sm" placeholder="Plate"/>
							</div><!-- /.col -->
						</div><!-- /form-group -->
					</div>
				</div>
				<hr />
				<div class="row">
					<div class="col-xs-4">
						<div class="form-group">
							<label for="printingPaper" class="col-lg-3 control-label">Paper</label>
							<div class="col-lg-9">
							    <form:input path="printingPaper" class="form-control input-sm" placeholder="Paper"/>
							</div><!-- /.col -->
						</div><!-- /form-group -->
						<div class="form-group">
							<label for="printingSize" class="col-lg-3 control-label">Size</label>
							<div class="col-lg-9">
								<form:input path="printingSize" class="form-control input-sm" placeholder="Size"/>
							</div><!-- /.col -->
						</div><!-- /form-group -->
					</div>
					<div class="col-xs-4">
						<div class="form-group">
							<label for="printingQuantity" class="col-lg-3 control-label">Quantity</label>
							<div class="col-lg-9">
								<form:input path="printingQuantity" class="form-control input-sm" placeholder="Quantity"/>
							</div><!-- /.col -->
						</div><!-- /form-group -->
						<div class="form-group">
							<label for="printingInk" class="col-lg-3 control-label">Ink</label>
							<div class="col-lg-9">
								<form:input path="printingInk" class="form-control input-sm" placeholder="Ink"/>
							</div><!-- /.col -->
						</div><!-- /form-group -->
					</div>
					<div class="col-xs-4">
						<div class="form-group">
							<label for="printingColors" class="col-lg-3 control-label">Colors</label>
							<div class="col-lg-9">
								<form:input path="printingColors" class="form-control input-sm" placeholder="Colors"/>
							</div><!-- /.col -->
						</div><!-- /form-group -->
					</div>
				</div>
				<hr />
				<div class="row">
					<div class="col-xs-4">
						<div class="form-group">
							<label for="printing" class="col-lg-3 control-label">Printing</label>
							<div class="col-lg-9">
								<form:input path="printing" class="form-control input-sm" placeholder="Printing"/>
							</div><!-- /.col -->
						</div><!-- /form-group -->
						<div class="form-group">
							<label for="numbering" class="col-lg-3 control-label">Numbering</label>
							<div class="col-lg-9">
								<form:input path="numbering" class="form-control input-sm" placeholder="Numbering"/>
							</div><!-- /.col -->
						</div><!-- /form-group -->
					</div>
					<div class="col-xs-4">
						<div class="form-group">
							<label for="stiching" class="col-lg-3 control-label">Stitching</label>
							<div class="col-lg-9">
								<form:input path="stiching" class="form-control input-sm" placeholder="Stiching"/>
							</div><!-- /.col -->
						</div><!-- /form-group -->
						<div class="form-group">
							<label for="binding" class="col-lg-3 control-label">Binding</label>
							<div class="col-lg-9">
								<form:input path="binding" class="form-control input-sm" placeholder="Binding"/>
							</div><!-- /.col -->
						</div><!-- /form-group -->
						<div class="form-group">
							<label for="other" class="col-lg-3 control-label">Other</label>
							<div class="col-lg-9">
								<form:input path="other" class="form-control input-sm" placeholder="Other"/>
							</div><!-- /.col -->
						</div><!-- /form-group -->
					</div>
					<div class="col-xs-4">
						<div class="form-group">
							<label for="remarks" class="col-lg-3 control-label">Remarks</label>
							<div class="col-lg-9">
								<form:textarea path="remarks" cssClass="form-control input-sm" placeholder="Remarks" />
							</div><!-- /.col -->
						</div><!-- /form-group -->
					</div>
				</div>
				<div class="row no-margin">
					<jsp:include page="/WEB-INF/views/hames/audit.jsp" />
				</div>
			</form:form>
		</div>
	</div>
</div>

<div class="modal fade in" id="payment" aria-hidden="false">
	<div class="modal-dialog">
 		<div class="modal-content">
   			<div class="modal-header">
     			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4>Payment</h4>
 			</div>
	    	<div class="modal-body">
	    		<div class="row">
		    		<div class="col-xs-6">
		    			<div class="form-group">
							<label class="control-label">Balance</label>
							<input type="text" class="form-control input-sm" id="balance" style="font-size: 16px;font-weight: bold;" readonly="readonly">
						</div>
		    		</div>
		    		<div class="col-xs-6">
						<div class="form-group">
							<label class="control-label">Total Amount</label>
							<input type="text" class="form-control input-sm" id="totalAmount" onblur="calculate()">
							<%-- <form:textarea path="permanentAddress" cssClass="form-control input-sm" placeholder="Permanent Address"/> --%>								 	
						</div>
						<div class="form-group">
							<label class="control-label">Payment</label>
							<input type="text" class="form-control input-sm" id="paymentAmount" onblur="calculate()" >
							<%-- <form:textarea path="permanentAddress" cssClass="form-control input-sm" placeholder="Permanent Address"/> --%>								 	
						</div>
					</div>
				</div>
				<hr />
				<div class="row">
					<div class="col-xs-6">
					</div>
					<div class="col-xs-6">
						<div class="form-group text-right">
							<a href="#" class="btn btn-success" onclick="save()"><i class="fa fa-save"></i> Create Order</a>
						</div>
					</div>
				</div>
		    </div>
	  	</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div>

