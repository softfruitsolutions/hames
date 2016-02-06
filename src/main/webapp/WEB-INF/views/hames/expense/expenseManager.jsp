<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="/WEB-INF/views/hames/tld/functions.tld" %>

<!-- URL's  -->
<c:url value="/expense/list" var="expenseManagerListUrl" />
<c:url value="/expense/view" var="expenseManagerUrl" />
<c:url value="/expense/save" var="expenseSaveUrl" />
<c:url value="/expense/addPay" var="expenseAddPayUrl" />

<script type="text/javascript">

	$(function(){
		$('#expenseDate').mask("99/99/9999");
	});
	
	function save(){
		$('#expenseManager').prop('action', '${expenseSaveUrl}');
		$('#expenseManager').serialize();
		$('#expenseManager').submit();
	}
	
	function addPayment(){
		$('#expenseManager').prop('action', '${expenseAddPayUrl}');
		$('#expenseManager').serialize();
		$('#expenseManager').submit();
	}
	
	function removeTableRow(trid){
		$('#expenseManager #paymentItem'+trid).remove();
		
	}
	
	
</script>
<div class="col-md-12">
	<h3 class="headline m-top-md">
		Expense Manager
		<span class="line"></span>
	</h3>
	
	<div class="col-md-12">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="btn-toolbar no-margin">
						<div class="btn-group">
							<a class="btn btn-info btn-sm" href="${expenseManagerListUrl}" title="Back to Expense Manager"><i class="fa fa-reply"></i></a>
							<a class="btn btn-info btn-sm" href="${expenseManagerUrl}" title="Refresh"><i class="fa fa-refresh"></i></a>
							<a class="btn btn-success btn-sm" onclick="save()">
								<i class="fa fa-save"></i> Save
							</a>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="expenseManager" method="POST" action="${expenseSaveUrl}">
						<div class="row">
							<form:hidden path="expenseId" />
							<form:hidden path="status" />
							
							<div class="col-xs-3">
								<div class="form-group">
									<form:label path="expenseDate" cssClass="control-label">Expense Date</form:label>
									<form:input path="expenseDate" cssClass="form-control input-sm" placeholder="Expense Date"/>								 	
								</div>
							</div>
							<div class="col-xs-3">
								<div class="form-group">
									<form:label path="payeeName" cssClass="control-label">Payee Name</form:label>
									<form:input path="payeeName" cssClass="form-control input-sm" placeholder="Payee Name"/>								 	
								</div>
							</div>
							<div class="col-xs-3">
								<div class="form-group">
									<form:label path="expenseCategory.categoryId" cssClass="control-label">Expense Category</form:label>
									<form:select path="expenseCategory.categoryId" cssClass="form-control input-sm">
										<c:forEach items="${expenseCategories}" var="ec">
											<form:option value="${ec.categoryId }" label="${ec.categoryName }" />
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div class="col-xs-3">
								<div class="form-group">
									<form:label path="notes" cssClass="control-label">Notes</form:label>
									<form:textarea path="notes" cssClass="form-control input-sm" placeholder="Notes"/>								 	
								</div>
							</div>
							<form:hidden path="payment.paymentId"/>
							<form:hidden path="payment.paymentDate"/>
							<form:hidden path="payment.paymentStatus"/>
						</div>
						<h5 class="headline">
							Payments
							<div class="pull-right">
								<a href="#" onclick="addPayment()"><i class="fa fa-plus"></i> Add </a>
							</div>
							<span class="line"></span>
							
						</h5>
						<table class="table table-striped table-hover table-condensed ">
							<thead>
								<tr>
									<td>Description</td>
									<td>Amount</td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td></td>
									<td>
										<div class="">
											<form:input path="payment.totalAmount" cssClass="form-control input-sm" placeholder="Total Amount" style="font-size:14px;font-weight:bold;"/>								 	
										</div><!-- /.col -->
									</td>
								</tr>
								<c:forEach items="${expenseManager.payment.paymentItems}" var="pi" varStatus="piStatus">
									<tr id="paymentItem${piStatus.index}">
										<td>
											<form:input path="payment.paymentItems[${piStatus.index}].description" cssClass="form-control input-sm " placeholder="Item Description" data-required="true" />
										</td>
										<td>
											<form:input path="payment.paymentItems[${piStatus.index}].paymentAmount" cssClass="form-control input-sm " placeholder="Amount" data-required="true" />
										</td>
										<td>
											<a href="#" onclick="removeTableRow(${piStatus.index})"><i class="fa fa-pencil"></i></a>
										</td>
									</tr>
								</c:forEach>
								
							</tbody>
						</table>
						<jsp:include page="/WEB-INF/views/hames/audit.jsp" />
					</form:form>
					
				</div>
				
				
			</div>
		</div>
	</div>
</div>