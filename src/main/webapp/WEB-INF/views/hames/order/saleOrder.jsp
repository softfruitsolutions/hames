<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:url value="/saleorder/" var="saleOrdersUrl" />
<c:url value="/saleorder/save" var="saleOrderSaveUrl" />

<script type="text/javascript">

	$(function(){
		$('#orderDate').mask("99/99/9999");
		$('#deliveryDate').mask("99/99/9999");
	});
	
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
					window.location.href='${saleOrdersUrl}';
			    },1000);
				
			},
			error:function(data){
				ErrorAlert.handleError(data.responseJSON.message);
			}
		});
	}
	
	function calculateBalanceDue(){
		var totalAmount = $('#payment\\.totalAmount').val();
		var discountAmount = $('#payment\\.discountAmount').val() == null ? 0 : $('#payment\\.discountAmount').val();
		var paymentAmount = $('#payment\\.paymentItems0\\.paymentAmount').val() == null ? 0 : $('#payment\\.paymentItems0\\.paymentAmount').val();
		
		var balanceDue = totalAmount - discountAmount - paymentAmount;
		$('#balance').val(balanceDue);
	}
	
</script>

<div class="row">
	<div class="col-md-12">
		<div class="panel panel-primary panel-border top">
		  <div class="panel-heading">
		    <span class="panel-title panel-title hidden-xs"><i class="imoon imoon-drawer2"></i> SALE ORDER</span>
		    <ul class="nav panel-tabs">
		      <li class="active">
		        <a href="#tab1" data-toggle="tab">Create</a>
		      </li>
		      <li class="">
		        <a href="#tab2" data-toggle="tab">Audit</a>
		      </li>
		    </ul>
		  </div>
		  <div class="panel-menu">
		  	<div class="btn-group">
		  		<a href="${saleOrdersUrl}" class="btn btn-info"><i class="fa fa-reply"></i></a>
		  		<a href="#" onclick="save()" class="btn btn-primary" data-toggle="modal"><i class="glyphicon glyphicon-floppy-save"></i> Save</a>
		  	</div>
		  	<div class="pull-right">
		  		<a class="btn btn-warning">
					<c:if test="${saleOrder.saleOrderStatus == 'DRAFT'}">
						DRAFT
					</c:if>
				</a>
		  	</div>
		  </div>
		  <form:form modelAttribute="saleOrder" method="POST" action="#">
		  <div class="panel-body">
		  	<div class="tab-content">
				<div id="tab1" class="tab-pane active">
					<form:hidden path="saleOrderStatus"/>
					<form:hidden path="orderId"/>
					<form:hidden path="orderType"/>
					<div class="row">
						<div class="col-xs-7">
							<div class="form-group">
								<label for="jobId" class="col-lg-3 control-label">Job No</label>
								<div class="col-lg-9">
									<form:input path="jobNo" cssClass="form-control input-sm" placeholder="Job No" readonly="true"/>								 	
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
									<form:select path="partyId" cssClass="form-control input-sm">
										<form:options items="${customers }" itemLabel="fullName" itemValue="partyId"/>
									</form:select>
								</div><!-- /.col -->
							</div><!-- /form-group -->
						</div>
						<div class="col-xs-5">
							<div class="form-group">
								<label for="staffConcerned" class="col-lg-4 control-label">Staff Concerned</label>
								<div class="col-lg-8">
									<form:select path="staffConcerned" cssClass="form-control input-sm">
										<form:options items="${staffs }" itemLabel="fullName" itemValue="staffId"/>
									</form:select>
								</div><!-- /.col -->
							</div><!-- /form-group -->
							<div class="form-group">
								<label for="createdDate" class="col-lg-4 control-label">Order Date</label>
								<div class="col-lg-8">
									<form:input path="orderDate" cssClass="form-control input-sm"  type="text"/>
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
					<div class = "row">
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
						</div>
						<div class="col-xs-4">
							<div class="form-group">
								<label for="printingColors" class="col-lg-3 control-label">Colors</label>
								<div class="col-lg-9">
									<form:input path="printingColors" class="form-control input-sm" placeholder="Colors"/>
								</div><!-- /.col -->
							</div><!-- /form-group -->
							<div class="form-group">
								<label for="other" class="col-lg-3 control-label">Other</label>
								<div class="col-lg-9">
									<form:input path="other" class="form-control input-sm" placeholder="Other"/>
								</div><!-- /.col -->
							</div><!-- /form-group -->
							<div class="form-group">
								<label for="remarks" class="col-lg-3 control-label">Remarks</label>
								<div class="col-lg-9">
									<form:textarea path="remarks" cssClass="form-control input-sm" placeholder="Remarks" />
								</div><!-- /.col -->
							</div><!-- /form-group -->
						</div>
					</div>
					<hr />
					<div class="row">
						<div class="col-xs-8">
						</div>
						<div class="col-xs-4">
							<div class="form-group">
								<label class="col-lg-4 control-label">Total Amount</label>
								<div class="col-lg-8">
									<form:input path="payment.totalAmount" class="form-control input-sm" placeholder="" onkeyup="calculateBalanceDue()" style="text-align: right;" autocomplete="off"/>
								</div>
							</div>
							<%-- <div class="form-group">
								<label class="col-lg-4 control-label">Discount</label>
								<div class="col-lg-8">
									<form:input path="payment.discountAmount" class="form-control input-sm" placeholder="" onkeyup="calculateBalanceDue()" style="text-align: right;" autocomplete="off"/>
								</div>
							</div> --%>
							<div class="form-group">
								<label class="col-lg-4 control-label">Payment Amount</label>
								<div class="col-lg-8">
									<form:input path="payment.paymentItems[0].paymentAmount" class="form-control input-sm" placeholder="" onkeyup="calculateBalanceDue()" style="text-align: right;" autocomplete="off"/>
									<form:hidden path="payment.paymentItems[0].paymentDate" />
								</div>
							</div>
							<div class="form-group">
								<label for="balance" class="col-lg-4 control-label">Balance</label>
								<div class="col-lg-8">
									<input type="text" class="form-control input-sm" id="balance" style="font-size: 18px;font-weight: bold;text-align: right;" readonly="readonly">
								</div><!-- /.col -->
							</div>
						</div>
					</div>
				</div>
				<form:hidden path="payment.paymentStatus" />
				<div id="tab2" class="tab-pane">
					<jsp:include page="/WEB-INF/views/hames/audit.jsp" />
				</div>
			</div>
		  </div>
		  </form:form>
		</div>
	</div>
</div>



