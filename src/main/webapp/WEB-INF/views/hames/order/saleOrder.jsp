<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- URL's  -->
<c:url value="/saleorder/" var="saleOrdersUrl" />
<c:url value="/saleorder/save" var="saleOrderSaveUrl" />
<c:url value="/customer/all" var="getCustomers" />

<script src='<c:url value="/resources/hames/order/js/saleorder.js" />' type="text/javascript"></script>
<script type="text/javascript">
<!--
//-->
	var GET_CUSTOMERS = '${getCustomers}';
	var VIEW_SALE_ORDER_LIST = '${saleOrdersUrl}';
	var SAVE_SALE_ORDER = '${saleOrderSaveUrl}';
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
		  		<a href="${saleOrdersUrl}" class="btn btn-info btn-sm"><i class="fa fa-reply"></i></a>
		  		<a href="#" onclick="save()" class="btn btn-primary btn-sm" data-toggle="modal"><i class="glyphicon glyphicon-floppy-save"></i> Save</a>
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
									<div class="input-group">
										<form:select path="partyId" cssClass="form-control input-sm">
			                        		<form:options items="${customers }" itemLabel="fullName" itemValue="partyId"/>
			                        	</form:select>
									   <span class="input-group-btn">
									        <button class="btn btn-primary light" type="button" style="height: 34px;" onclick="Customer.showModal()">
									        	<i class="glyphicons glyphicons-user_add" style="top:-4px;"></i>
									        </button>
									   </span>
									</div>
		                        </div>
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
									<form:input path="orderDate" cssClass="form-control input-sm datepicker"  type="text"/>
								</div>
							</div><!-- /form-group -->
							<div class="form-group">
								<label for="deliveryDate" class="col-lg-4 control-label">Delivery Date</label>
								<div class="col-lg-8">
									<form:input path="deliveryDate" cssClass="form-control input-sm datepicker"  type="text"/>
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


<!-- 
	CUSTOMER MODAL 
 -->
<div class="modal fade in" id="customerModal" aria-hidden="false">
	<div class="modal-dialog">
 		<div class="modal-content">
   			<!-- <div class="modal-header">
				<h4>CUSTOMER</h4>
 			</div> -->
	    	<div class="modal-body">
	    		<div id="bodyContent">
	    			<jsp:include page="/WEB-INF/views/hames/party/simpleCustomerForm.jsp" />
	    		</div>
			</div>
			<div class="modal-footer">
				<div class="row">
					<div class="col-xs-6">
					</div>
					<div class="col-xs-6">
						<div class="form-group text-right">
							<button type="button" class="btn btn-success btn-sm" onclick="saveCustomer()"><i class="fa fa-save"></i> Save</button>
			     			<button type="button" class="btn btn-danger btn-sm" class="close" data-dismiss="modal">x Close</button>
						</div>		
					</div>
				</div>
			</div>
			
	    </div>
	 </div>
</div>

