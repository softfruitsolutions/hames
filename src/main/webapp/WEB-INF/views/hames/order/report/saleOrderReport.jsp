<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- URL's  -->
<c:url value="/report/saleorder" var="viewSaleOrderReport" />
<c:url value="/report/saleorder/data" var="orderReportData" />

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<style>
	
		.tfootertext{
			font-weight:bold;
			font-size:14px !important;
		}
	</style>
		
	<script type="text/javascript">
	
		/* Variables */
		var saleReportDatatable = null;
		
	 	$(function () {
	 		getReportData();
	        $("#fromOrderDate").on("dp.change", function (e) {
	            $('#toOrderDate').data("DateTimePicker").setMinDate(e.date);
	        });
	        $("#toOrderDate").on("dp.change", function (e) {
	            $('#fromOrderDate').data("DateTimePicker").setMaxDate(e.date);
	        });
	    });
	 	
	 	function getReportData(){
	 		var saleOrderCriteria = $('#saleOrderSearchCriteria').serialize();
	 		$.ajax({
				type:'GET',
				url:'${orderReportData}'+"?"+saleOrderCriteria,
		        async: false,
				success:function(data){
					if(saleReportDatatable != null){
						saleReportDatatable.fnDestroy();	
					}
					loadReportDatatable(data.datas);
				},
				error:function(data){
				}
			});
	 	}
	 	
	 	/**
	 	*	Load Sale Report Datatable
	 	*/
	 	function loadReportDatatable(aaData){
	 		saleReportDatatable = $('#saleReportDatatable').dataTable ({
				"bPaginate": false,
				"aaData": aaData,
				"bSort": false,
				"aoColumns" : [
				               {mDataProp : '_id'},
				               {mDataProp : 'ordersCount'},
				               {
			                        "mData": 'totalAmount',
			                        "sClass":"text-right",
			                        "mRender": function(data, type, full) {
			                            return data.toFixed(2);
			                        },
			                    },
			                    {
			                        "mData": 'amountPaid',
			                        "sClass":"text-right",
			                        "mRender": function(data, type, full) {
			                            return data.toFixed(2);
			                        },
			                    },	
			                    {
			                        "mData": 'discountAmount',
			                        "sClass":"text-right",
			                        "mRender": function(data, type, full) {
			                            return data.toFixed(2);
			                        },
			                    },	
			                    {
			                        "mData": 'balanceDue',
			                        "sClass":"text-right",
			                        "mRender": function(data, type, full) {
			                            return data.toFixed(2);
			                        },
			                    }
				              ],
				"bFilter": false,
			    "bInfo" : false,
				"footerCallback": function ( row, data, start, end, display ) {
					var api = this.api(), data;	 
					
					// Remove the formatting to get integer data for summation
					var intVal = function ( i ) {
						return typeof i === 'string' ? i.replace(/[\$,]/g, '')*1 : typeof i === 'number' ?	i : 0;
					};
					
					ordersCount = api.column(1).data().reduce( function (a, b) {
						return intVal(a) + intVal(b);
					},0 );
					totalAmount = api.column(2).data().reduce( function (a, b) {
						return intVal(a) + intVal(b);
					},0 );
					amountPaid = api.column(3).data().reduce( function (a, b) {
						return intVal(a) + intVal(b);
					},0 );
					discountAmount = api.column(4).data().reduce( function (a, b) {
						return intVal(a) + intVal(b);
					},0 );
					balanceDue = api.column(5).data().reduce( function (a, b) {
						return intVal(a) + intVal(b);
					},0 );
					
					$('#ordersCount').html(ordersCount);	
					$('#totalAmount').html(MathUtil.toFixed(totalAmount));
					$('#amountPaid').html(MathUtil.toFixed(amountPaid));
					$('#discountAmount').html(MathUtil.toFixed(discountAmount));
					$('#balanceDue').html(MathUtil.toFixed(balanceDue));
				},
			});
	 	}
	</Script>	
</head>

<body>

<div class="row">
	<div class="col-md-8">
		<div class="panel panel-primary panel-border top">
		  <div class="panel-heading">
		    <span class="panel-title">SALE REPORT</span>
		  </div>
		  <div class="panel-menu">
		  	<div class="btn-group">
		  		<a href="${viewSaleOrderReport }" class="btn btn-primary light btn-sm" title="Refresh">
				    <i class="glyphicon glyphicon-refresh"></i>
				</a>
				<a href="#" class="btn btn-primary btn-sm" title="Search" onclick="getReportData()">
				    <i class="glyphicon glyphicon-search"></i> Search
				</a>
		  	</div>
		  </div>
		  <div class="panel-body">
		  	  <form:form modelAttribute="saleOrderSearchCriteria" action="#" cssClass="form-horizontal">
		  	  <div class="row">
		  	  	<div class="col-xs-7">
		  	  		<div class="form-group">
						<label class="col-lg-4 control-label">Reporting Period</label>
						<div class="col-xs-8">
							<form:select path="reportPeriod" cssClass="form-control input-sm col-xs-7">
								<form:options items="${reportPeriod }" itemLabel="text"/>
							</form:select>
						</div><!-- /.col -->
					</div>
					<div class="form-group">
						<label class="col-lg-4 control-label">Date Range</label>
						<div class="col-lg-4">
							<div class="input-group date">
								<span class="input-group-addon input-sm cursor">
		                          <i class="fa fa-calendar"></i>
		                        </span>
		                        <form:input path="fromOrderDate" cssClass="form-control input-sm datepicker"/>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="input-group date">
								<span class="input-group-addon input-sm cursor">
		                          <i class="fa fa-calendar"></i>
		                        </span>
		                      	<form:input path="toOrderDate" cssClass="form-control input-sm datepicker"/>
							</div>
						</div><!-- /.col -->
					</div>
		  	  	</div>
		  	  	<div class="col-xs-5">
		  	  		<div class="form-group">
						<label class="col-lg-4 control-label">Group By</label>
						<div class="col-xs-8">
							<form:select path="groupBy" cssClass="form-control input-sm col-xs-7">
								<option value="orderDate">Order Date</option>
								<option value="staffConcerned">Staff</option>
							</form:select>
						</div><!-- /.col -->
					</div>
		  	  	</div>
		  	  </div>
		  	  </form:form>
		  	  <hr class="short" />
		  	  <table class="table table-hover table-condensed" id="saleReportDatatable">
		  	  	<thead>
			  	  	<tr>
			  	  		<th class="nosort">Order Date</th>
			  	  		<th class="nosort">No Of Orders</th>
			  	  		<th class="nosort text-right">Total Amount</th>
			  	  		<th class="nosort text-right">In Cash</th>
			  	  		<th class="nosort text-right">Discount</th>
			  	  		<th class="nosort text-right">Balance Due</th>
			  	  	</tr>
		  	  	</thead>
		  	  	<tbody>
		  	  		<!-- Content will be loaded by ajax call  -->
		  	  	</tbody>
		  	  	<tfoot>
		  	  		<tr class="primary">
		  	  			<td class="tfootertext">Total in OMR</td>
		  	  			<td class="tfootertext" id="ordersCount"></td>
		  	  			<td class="tfootertext" id="totalAmount"></td>
		  	  			<td class="tfootertext" id="amountPaid"></td>
		  	  			<td class="tfootertext" id="discountAmount"></td>
		  	  			<td class="tfootertext" id="balanceDue"></td>
		  	  		</tr>
		  	  	</tfoot>
		  	  </table>
		  </div>
		</div>
	</div>
</div>
		    
</body>
</html>