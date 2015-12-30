<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="javassist.compiler.Javac"%>
<%@page import="org.apache.taglibs.standard.tag.common.xml.ForEachTag"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tittle</title>
<style type="text/css">
	table {
    width:50%;
	}
	th, td {
	    border-collapse: collapse;
	}
	th, td {
	    padding: 5px;
	    text-align: center;
	    width: 50%
	}
	table tr:nth-child(even) {
	    background-color: #eee;
	}
	table tr:nth-child(odd) {
	   background-color:#fff;
	}
	table th	{
	    background-color: green;
	    color: white;
	}
	#maindiv{
		width: 100%;
		background-color: #ffffff;
		padding: 25px;
		
		}
	#tablestaffs{
		border-color: #ff00ff;
	}
	#searchdiv{
		border: thin ;
		margin-bottom: 20px;
		
	}
	
	
</style>
<script type="text/javascript">
	function delet(id) {
		document.location.href="/hames/staffview?id="+id;
	}
	function search(){
		//String tag=document.getElementById("searchet").value;
		document.location.href="/hames/stafflistview?tag="+document.getElementById("searchet").value;
		//alert(alert(document.getElementById("searchet").value));
	}
</script>
</head>
	<div id="maindiv">
		<div id="searchdiv">
			<input id="searchet"></input>
			<button onclick="search();">Search</button>
		</div>
		<table id="tablestaffs">
		<tr>
		<th >Name</th>
		<th>Role</th>
		</tr>
			<c:forEach items="${list}" var="staffnames">
				
				<tr onclick="delet('${staffnames.getStaffId()}')"><td>${staffnames.getFirstName()}</td><td>${staffnames.getMiddleName()}</td></tr>
			</c:forEach>		
		</table>
		<p>${nme}</p>
	</div>
</html>