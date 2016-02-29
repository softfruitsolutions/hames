<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div class="sidebar-left-content nano-content ">
	<ul class="nav sidebar-menu">
	   <li class="active">
	     <a href="<c:url value="/dashboard" />">
	       <span class="glyphicon glyphicon-home"></span>
	       <span class="sidebar-title">Dashboard</span>
	     </a>
	   </li>
	   <shiro:hasPermission name="order:view">
	   <li>
	   	   <a class="accordion-toggle" href="#">
	         <span class="imoon imoon-stack"></span>
	         <span class="sidebar-title">Order</span>
	         <span class="caret"></span>
	       </a>
	       <ul class="nav sub-nav">
	       	 <shiro:hasPermission name="order:saleorder:view">
             <li>
           	   <a href="<c:url value="/saleorder/" />">
                  <span class="imoon imoon-drawer2"></span>
				  Sale
               </a>
             </li>
             </shiro:hasPermission>
             <shiro:hasPermission name="order:saleorder:report:view">
             <li>
           	   <a href="<c:url value="/report/saleorder" />">
                  <span class="glyphicons glyphicons-notes"></span>
				  Sale Report
               </a>
             </li>
             </shiro:hasPermission>
           </ul>
       </li>
       </shiro:hasPermission>
       <shiro:hasPermission name="expense:manager:view">
	   <li>
	   	   <a class="accordion-toggle" href="#">
	         <span class="glyphicon glyphicon-tags"></span>
	         <span class="sidebar-title">Expense</span>
	         <span class="caret"></span>
	       </a>
	       <ul class="nav sub-nav">
	       	 <shiro:hasPermission name="expense:manager:view">
             <li>
           	   <a href="<c:url value="/expense/list" />">
                  <span class="fa fa-folder"></span>
				  Manager
               </a>
             </li>
             </shiro:hasPermission>
             <shiro:hasPermission name="expense:category:view">
             <li>
           	   <a href="<c:url value="/expense/category" />">
                  <span class="fa fa-folder-o"></span>
				  Category
               </a>
             </li>
             </shiro:hasPermission>
           </ul>
       </li>
       </shiro:hasPermission>
	   <shiro:hasPermission name="party:customer:view">
	   <li>
	   	   <a class="accordion-toggle" href="#">
	         <span class="imoon imoon-users"></span>
	         <span class="sidebar-title">Party</span>
	         <span class="caret"></span>
	       </a>
	       <ul class="nav sub-nav">
	       	 <shiro:hasPermission name="party:customer:view">
             <li>
           	   <a href="<c:url value="/customer/list" />">
                  <span class="imoon imoon-users2"></span>
				  Customer
               </a>
             </li>
             </shiro:hasPermission>
           </ul>
       </li>
       </shiro:hasPermission>
	   <shiro:hasPermission name="hr:staff:view">
	   <li>
	   	   <a class="accordion-toggle" href="#">
	         <span class="imoon imoon-users"></span>
	         <span class="sidebar-title">Human Resource</span>
	         <span class="caret"></span>
	       </a>
	       <ul class="nav sub-nav">
	       	 <shiro:hasPermission name="hr:staff:view">
             <li>
           	   <a href="<c:url value="/staff/list" />">
                  <span class="imoon imoon-users2"></span>
				  Staff
               </a>
             </li>
             </shiro:hasPermission>
           </ul>
       </li>
       </shiro:hasPermission>
	   <li>
	       <a class="accordion-toggle" href="#">
	         <span class="imoon imoon-settings"></span>
	         <span class="sidebar-title">Settings</span>
	         <span class="caret"></span>
	       </a>
	       <ul class="nav sub-nav">
	           <li>
	           	   <a class="accordion-toggle" href="#">
	                  <span class="imoon imoon-user3"></span>
	                  Administrator
	                  <span class="caret"></span>
	               </a>
	               	
	               <ul class="nav sub-nav">
	               	  <shiro:hasPermission name="admin:rolepermission:view">
		               	  <li>
		                    <a href="<c:url value="/role/view" />"> Role </a>
		                  </li>
	                  </shiro:hasPermission>
	                  <shiro:hasPermission name="admin:useraccount:view">
		                  <li>
		                    <a href="<c:url value="/useraccount/view" />"> User Account </a>
		                  </li>
		              </shiro:hasPermission>
	               </ul>
	           </li>
	       </ul>
       </li>
	</ul>
</div>
