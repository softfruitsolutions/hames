<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style>
		.textAlignRight{
			text-align: right;
		}
		.textBold{
			font-weight: bold;
		}
		.15px{
			font-size:16px !important;
		}
	</style>	
</head>

<body>
<div class="row">
	<div class="col-md-8">
		<div class="panel panel-primary panel-border top">
		  <div class="panel-heading">
		    <span class="panel-title">Sale Report</span>
		  </div>
		  <div class="panel-body">
		  	  <table class="table table-hover table-striped table-condensed">
		  	  	<thead>
			  	  	<tr>
			  	  		<th>Order Date</th>
			  	  		<th>No Of Orders</th>
			  	  		<th class="textAlignRight">Total Amount</th>
			  	  		<th class="textAlignRight">In Cash</th>
			  	  		<th class="textAlignRight">Discount</th>
			  	  		<th class="textAlignRight">Balance Due</th>
			  	  	</tr>
		  	  	</thead>
		  	  	<tbody>
		  	  		
		  	  		<c:forEach items="${saleReport.datas}" var="data" varStatus="srStatus" >
		  	  		<tr>
		  	  			<%-- <c:set var="orderDate">
		  	  				<c:out value="${data.get('_id')}" />
		  	  			</c:set> --%>
		  	  			<td>
		  	  				<c:out value="${data.get('_id')}" />
		  	  			</td>
		  	  			<td><c:out value="${data.get('ordersCount') }" /></td>
		  	  			<td class="textAlignRight"><c:out value="${data.get('totalAmount') }" /></td>
		  	  			<td class="textAlignRight"><c:out value="${data.get('amountPaid') }" /></td>
		  	  			<td class="textAlignRight"><c:out value="${data.get('discountAmount') }" /></td>
		  	  			<td class="textAlignRight"><c:out value="${data.get('balanceDue') }" /></td>
		  	  		</tr>
		  	  		</c:forEach>
		  	  	</tbody>
		  	  	<tfoot>
		  	  		<tr class="primary">
		  	  			<td class="textBold 15px">Total</td>
		  	  			<td></td>
		  	  			<td class="textAlignRight textBold " style="font-size : 15px;"><c:out value="${saleReport.values.get('totalAmount') }" /></td>
		  	  			<td class="textAlignRight textBold " style="font-size : 15px;"><c:out value="${saleReport.values.get('amountPaid') }" /></td>
		  	  			<td class="textAlignRight textBold " style="font-size : 15px;"><c:out value="${saleReport.values.get('discountAmount') }" /></td>
		  	  			<td class="textAlignRight textBold " style="font-size : 15px;"><c:out value="${saleReport.values.get('balanceDue') }" /></td>
		  	  		</tr>
		  	  	</tfoot>
		  	  </table>
		  </div>
		</div>
	</div>
</div>
		    
</body>
</html>