<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="main-menu">
	<ul>
		<li class="">
			<a href="/hames/"> 
				<span class="menu-icon"> <i class="fa fa-desktop fa-lg"></i></span>
				<span class="text">Dashboard</span> <span class="menu-hover"></span>
			</a>
		</li>
		<li class='<c:if test="${menu=='order'}">active</c:if>'>
			<a href="orderview"> 
				<span class="menu-icon"> <i class="fa fa-inbox fa-lg"></i></span>
				<span class="text">Order</span> <span class="menu-hover"></span>
			</a>
		</li>
		<li class='<c:if test="${menu=='customer'}">active</c:if>'>
			<a href="customerview"> 
				<span class="menu-icon"> <i class="fa fa-user fa-lg"></i></span>
				<span class="text">Customer</span><span class="menu-hover"></span>
			</a>
		</li>
		<li class='openable'> <!-- <c:if test="${menu=='staff' || menu=='staffrole'}"> active</c:if>  -->
			<a href='#'> 
				<span class="menu-icon"> <i class="fa fa-group fa-lg"></i></span>
				<span class="text">Staffing</span><span class="menu-hover"></span>
			</a>
			<ul class="submenu">
				<li class='<c:if test="${menu=='staff'}">active</c:if>'>
					<a href='<c:url value="/staff/list" />'><span class="submenu-label"><i class="fa fa-user"></i> Staff</span></a>
				</li>
				<li class='<c:if test="${menu=='staffrole'}">active</c:if>'>
					<a href='<c:url value="/staffrole/list" />'><span class="submenu-label"><i class="fa fa-sitemap"></i> Role</span></a>
				</li>
			</ul>
		</li>
	</ul>
</div>
<!-- /main-menu -->

