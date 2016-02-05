<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="/WEB-INF/views/hames/tld/functions.tld" %>

<!-- URL's  -->
<c:url value="/expense/view" var="expenseManagerUrl" />
<c:url value="/expense/save" var="expenseSaveUrl" />

<script type="text/javascript">
	function save(){
		$('#expenseManager').serialize();
		$('#expenseManager').submit();
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
							<a class="btn btn-info btn-sm" href="${expenseManagerUrl}" title="Back to Expense Manager"><i class="fa fa-reply"></i></a>
							<a class="btn btn-info btn-sm" href="${expenseManagerUrl}" title="Refresh"><i class="fa fa-refresh"></i></a>
							<a class="btn btn-success btn-sm" onclick="save()">
								<i class="fa fa-save"></i>
								<c:if test="${expenseManager.expenseId == null }" >
									Save
								</c:if>
								<c:if test="${expenseManager.expenseId != null}" >
									Update
								</c:if>
							</a>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="expenseManager" method="POST" action="${expenseSaveUrl}">
						<div class="row">
							<form:hidden path="expenseId" cssClass="form-control input-sm" placeholder="Expense ID" readonly="true"/>
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
									<form:label path="expenseId" cssClass="control-label">Expense Category</form:label>
									<form:select path="expenseId" cssClass="form-control input-sm">
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
							
						</div>
						<h5 class="headline">
							Payments
							<div class="pull-right">
								<a href="<c:url value='/expense/addPay' />"><i class="fa fa-plus"></i> Add </a>
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
								<c:forEach items="${expenseManager.payment.paymentItems}" var="pi" varStatus="piStatus">
									<tr>
										<td>
											<form:input path="payment.paymentItems[${piStatus.index}].description" cssClass="form-control input-sm " placeholder="Item Description" data-required="true" />
										</td>
										<td>
											<form:input path="payment.paymentItems[${piStatus.index}].paymentAmount" cssClass="form-control input-sm " placeholder="Amount" data-required="true" />
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