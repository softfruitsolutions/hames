<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	#div_maintable{
		background-color: #eeeeee;
		width: 80%;
		height: auto;
		margin-left: 30px;
		border-radius: 8px;
		margin-right: 30px;
		padding:10px;
	}
	tr,td,th{
		border:1px solid black;
		padding: 5px;
		text-align: center;
		font-size: 15px;
	}
	#div_discounttable{
		text-align: right;
		width: 80%;
		padding-right: 20px;
		font-size: 20px;
	}
	#searchdiv{
		margin-left: 100px;
		background-color: #dddddd;
		width: 200px;
		list-style: hiragana;
		list-style-type: none;
		list-style-image: none;
		font-size: 15px;
		
	}
	#et_discount{
		width: 40px;
	}
	#btn_confirm{
		height: 50px;
		width: 120px;
		color: white;
		background-color: green;
		border-radius: 8px;
	}
	#confirmdiv{
		text-align: right;
		margin-right: 150px;
	}
	#dialog_over{
		width: 100%;
		height: 100%;
		position: relative;
		background: rgba(0,0,0,0.14);
		visibility: hidden;
	}
	#dialog{
		width: 40%;
		height: 40%;
		background-color: #eeeeee;
		position: static;
	}
	ul.nobullet{
		list-style-type: none;
	}
	#searchli{
		height: 30px;
		padding-left: 15px;
	}
	#searchli:HOVER {
		background-color: #ffffff;
	}
	#div_billdetails{
		background-color: #eeeeee;
		width: 80%;
		height: auto;
		margin-left: 30px;
		border-radius: 8px;
		margin-right: 30px;
		padding:10px;
	}
	#label_date{
		text-align: right;
	}
</style>
<script type="text/javascript">
	function search() {
		document.location.href="/hames/searchbill?name="+document.getElementById("customername").value;
		}
	function takebill(id){
		document.location.href="/hames/servicebill?id="+id;
	}
	function calculate(sum) {
		var discount=$('#et_discount').val();
		var dif=sum-discount;
		//$('#text_grandtotal').val(dif);
		document.getElementById("text_grandtotal").innerHTML="Grand total: "+dif;
	}
	function print(orderid,payment){
		   
		    if (confirm("Do you want to confirm this payment") == true) {
		    	var dis=document.getElementById("et_discount").value;
		    	document.location.href="/hames/savebill?order="+orderid+"&payment="+payment+"&disc="+dis;
		    } else {
		        
		    }
		
	}
	function viewbill(id){
		document.location.href="/hames/viewbillbyorder?id="+id;
	}
</script>
</head>
	<h1>Service bill</h1>
		<label>Search Customer Name</label>
		<input id="customername"></input>
		<button onclick="search()">Search</button>
		<div id="searchdiv">
			<ul class="nobullet">
				<c:forEach items="${customernames}" var="customers">
					<li id="searchli" onclick="takebill('${customers.getCustomerId()}')">${customers.getFirstName()}</li>
				</c:forEach>
			</ul>
		</div>
		<div id="div_billdetails">
			<label>Customer Name: &nbsp;</label>${customername}
			<div id="label_date"><label id="date_label">Date:&nbsp;</label>${date}</div>
		</div></br></br>
	<div id="div_maintable">
		<table>
			<tr>
				<th width="100px">Order no:</th><th width="500px">Order</th><th width=150px">Total Rate</th><th width=150px">Advance</th>
			</tr>
			<c:forEach items="${billitems}" var="billvalues">
				<tr onclick="viewbill('${billvalues.getOrderId()}')"> 
					<td width="100px">${billvalues.getOrderId()}</td><td width="500px">${billvalues.getJobName()}</td><td width=150px">${billvalues.getTotalAmount()}</td><td width="150px">${billvalues.getAdvance()}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="div_discounttable">
		Total amount: ${sum}</br></br>
		Discount:<input id="et_discount" value='0' width="10" oninput="calculate('${sum}')"></input></br></br>
		<div id="text_grandtotal">Grand total: ${sum}</div>
	</div></br></br>
	<c:set var="conf" scope="session" value="${confrm}">
	
	</c:set>
	<c:if test="${conf=='ok'}">
		<button id="btn_confirm" onclick="print('${ordr}','${sum}')">Confirm</button>
	</c:if>
	
	<div>
		<p>Excep:${customername}</p>
	</div>
	<div id="dialog_over">
		<div id="dialog">
			<h3>test</h3>
		</div>
	</div>

</html>