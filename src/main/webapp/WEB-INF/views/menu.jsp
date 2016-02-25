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
             <li>
           	   <a href="<c:url value="/saleorder/report" />">
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

<%-- <div class="main-menu">
	<ul>
		<li class="">
			<a href="<c:url value="/dashboard" />"> 
				<span class="menu-icon"> <i class="fa fa-desktop fa-lg"></i></span>
				<span class="text">Dashboard</span> <span class="menu-hover"></span>
			</a>
		</li>
		<shiro:hasPermission name="expense:manager:view">
			<li class='openable'>
				<a href='#'> 
					<span class="menu-icon"> <i class="fa fa-inbox fa-lg"></i></span>
					<span class="text">Expense</span><span class="menu-hover"></span>
				</a>
				<ul class="submenu">
					<shiro:hasPermission name="expense:manager:view">
						<li class='<c:if test="${menu=='expensemanager'}">active</c:if>'>
							<a href='<c:url value="/expense/list" />'><span class="submenu-label"><i class="fa fa-folder-o fa-lg"></i>  Manager</span></a>
						</li>
					</shiro:hasPermission>
					<shiro:hasPermission name="expense:category:view">
						<li class='<c:if test="${menu=='expensecategory'}">active</c:if>'>
							<a href='<c:url value="/expense/category" />'><span class="submenu-label"><i class="fa fa-cog fa-lg"></i>  Category</span></a>
						</li>
					</shiro:hasPermission>
				</ul>
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="order:view">
			<li class='openable'>
				<a href='#'> 
					<span class="menu-icon"> <i class="fa fa-inbox fa-lg"></i></span>
					<span class="text">Order</span><span class="menu-hover"></span>
				</a>
				<shiro:hasPermission name="order:saleorder:view">
					<ul class="submenu">
						<li class='<c:if test="${menu=='viewsaleorder'}">active</c:if>'>
							<a href='<c:url value="/saleorder/list" />'><span class="submenu-label"><i class="fa fa-inbox fa-lg"></i>  Sale Order</span></a>
						</li>
					</ul>
				</shiro:hasPermission>
			</li>
		</shiro:hasPermission>
		
		<shiro:hasPermission name="party:customer:view">
		<li class='<c:if test="${menu=='customer'}">active</c:if>'>
			<a href="<c:url value="/customer/list" />"> 
				<span class="menu-icon"> <i class="fa fa-user fa-lg"></i></span>
				<span class="text">Customer</span><span class="menu-hover"></span>
			</a>
		</li>
		</shiro:hasPermission>
		
		<shiro:hasPermission name="staff:view">
		<li class='openable'> <!-- <c:if test="${menu=='staff' || menu=='staffrole'}"> active</c:if>  -->
			<a href='#'> 
				<span class="menu-icon"> <i class="fa fa-group fa-lg"></i></span>
				<span class="text">Staffing</span><span class="menu-hover"></span>
			</a>
			<shiro:hasPermission name="staff:view">
			<ul class="submenu">
				<li class='<c:if test="${menu=='staff'}">active</c:if>'>
					<a href='<c:url value="/staff/list" />'><span class="submenu-label"><i class="fa fa-user"></i> Staff</span></a>
				</li>
			</ul>
			</shiro:hasPermission>
		</li>
		</shiro:hasPermission>
		
		<shiro:hasPermission name="admin:view">
		<li class='openable'> <!-- <c:if test="${menu=='staff' || menu=='staffrole'}"> active</c:if>  -->
			<a href='#'> 
				<span class="menu-icon"> <i class="fa fa-user fa-lg"></i></span>
				<span class="text">Administrator</span><span class="menu-hover"></span>
			</a>
			<ul class="submenu">
				<shiro:hasPermission name="admin:rolepermission:view">
					<li class='<c:if test="${menu=='rolepermission'}">active</c:if>'>
						<a href='<c:url value="/role/list" />'><span class="submenu-label"><i class="fa fa-sitemap"></i> Role</span></a>
					</li>
				</shiro:hasPermission>
				<shiro:hasPermission name="admin:useraccount:view">
					<li class='<c:if test="${menu=='useraccount'}">active</c:if>'>
						<a href='<c:url value="/useraccount/view" />'><span class="submenu-label"><i class="fa fa-group"></i> User Account</span></a>
					</li>
				</shiro:hasPermission>
			</ul>
		</li>
		</shiro:hasPermission>
		
	</ul>
</div> --%>
<!-- /main-menu -->

