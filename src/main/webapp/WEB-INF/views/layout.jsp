<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
  
<head>
    <meta charset="utf-8">
    <title><tiles:insertAttribute name="title" /></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

	<!-- HAMES -->
	<script src='<c:url value="/resources/hames/util/js/datetime-util.js" />' type="text/javascript"></script>
	
	<!-- Jquery -->
	<script src='<c:url value="/resources/endless/js/jquery.min.js" />' type="text/javascript"></script>
	
    <!-- Bootstrap core CSS -->
    <link href='<c:url value="/resources/endless/bootstrap/css/bootstrap.min.css" />' rel="stylesheet">
	
	<!-- Font Awesome -->
	<link href='<c:url value="/resources/endless/css/font-awesome.min.css" />' rel="stylesheet">
	
	<!-- Pace -->
	<link href='<c:url value="/resources/endless/css/pace.css" />' rel="stylesheet">
	
	<!-- Color box -->
	<link href='<c:url value="/resources/endless/css/colorbox/colorbox.css" />' rel="stylesheet">
	
	<!-- Morris -->
	<link href='<c:url value="/resources/endless/css/morris.css" />' rel="stylesheet"/>	
	
	<!-- Endless -->
	<link href='<c:url value="/resources/endless/css/endless.min.css" />' rel="stylesheet">
	<link href='<c:url value="/resources/endless/css/endless-skin.css" />' rel="stylesheet">
	
  </head>
	<style type="text/css">
		.form-group{
			margin-bottom:5px !important;
		}
	</style>
  <body class="">
	<div id="wrapper" class="preload">
		<div id="top-nav" class="fixed skin-3">
			<a href="#" class="brand">
				<span>HAMES</span>
				<span class="text-toggle"> Admin</span>
			</a><!-- /brand -->					
			<button type="button" class="navbar-toggle pull-left" id="sidebarToggle">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<button type="button" class="navbar-toggle pull-left hide-menu" id="menuToggle">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<ul class="nav-notification clearfix">
				<li class="profile dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">
						<strong><c:out value="${staffUtil.firstName }"></c:out></strong>
						<span><i class="fa fa-chevron-down"></i></span>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a class="clearfix" href="#">
								<div class="detail">
									<strong><c:out value="${staffUtil.firstName }"></c:out></strong>
									<p class="grey"><c:out value="${staffUtil.emailId }"></c:out></p> 
								</div>
							</a>
						</li>
						<li><a tabindex="-1" href="profile.html" class="main-link"><i class="fa fa-edit fa-lg"></i> Edit profile</a></li>
						<li><a tabindex="-1" href="gallery.html" class="main-link"><i class="fa fa-picture-o fa-lg"></i> Photo Gallery</a></li>
						<li><a tabindex="-1" href="#" class="theme-setting"><i class="fa fa-cog fa-lg"></i> Setting</a></li>
						<li class="divider"></li>
						<li><a tabindex="-1" class="main-link logoutConfirm_open" href='<c:url value='/logout'></c:url>'><i class="fa fa-lock fa-lg"></i> Log out</a></li>
					</ul>
				</li>
			</ul>
		</div><!-- /top-nav-->
		
		<aside class="fixed skin-6">
			<div class="sidebar-inner scrollable-sidebar">
				<div class="size-toggle">
					<a class="btn btn-sm" id="sizeToggle">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</a>
					<a class="btn btn-sm pull-right logoutConfirm_open"  href="#logoutConfirm">
						<i class="fa fa-power-off"></i>
					</a>
				</div><!-- /size-toggle -->	
				<div class="user-block clearfix">
					<img src="resources/endless/img/user.jpg" alt="User Avatar">
					<div class="detail">
						<strong><c:out value="${staffUtil.firstName }"></c:out></strong>
						<span class="badge badge-danger m-left-xs bounceIn animation-delay4">4</span>
						<ul class="list-inline">
							<li><a href="profile.html">Profile</a></li>
							<li><a href="inbox.html" class="no-margin">Inbox</a></li>
						</ul>
					</div>
				</div><!-- /user-block -->
				<div class="search-block">
					<div class="input-group">
						<input type="text" class="form-control input-sm" placeholder="search here...">
						<span class="input-group-btn">
							<button class="btn btn-default btn-sm" type="button"><i class="fa fa-search"></i></button>
						</span>
					</div><!-- /input-group -->
				</div><!-- /search-block -->
				<tiles:insertAttribute name="menu" />
			</div><!-- /sidebar-inner -->
		</aside>

		<div id="main-container">
			<div id="breadcrumb">
				<ul class="breadcrumb">
					 <li><i class="fa fa-home"></i><a href="index-2.html"> Home</a></li>
					 <li class="active">Dashboard</li>	 
				</ul>
			</div><!-- /breadcrumb-->
			<br />
			<div class="col-xs-12">
				<jsp:include page="/WEB-INF/views/alertMessages.jsp" />
			</div>
			<tiles:insertAttribute name="body" />
		</div><!-- /main-container -->
		<!-- Footer
		================================================== -->
		<!-- <footer>
			<div class="row">
				<div class="col-sm-6">
					<span class="footer-brand">
						<strong class="text-danger">Softfruit</strong> Solutions
					</span>
					<p class="no-margin">
						&copy; 2015 <strong>Softfruit Solutions</strong>. ALL Rights Reserved. 
					</p>
				</div>/.col
			</div>/.row
		</footer> -->
		
	<a href="#" id="scroll-to-top" class="hidden-print"><i class="fa fa-chevron-up"></i></a>
	
	
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	
	

	<!-- Bootstrap -->
    <script src='<c:url value="/resources/endless/bootstrap/js/bootstrap.js" />'></script>
   
	<!-- Flot -->
	<%-- <script src='<c:url value="/resources/endless/js/jquery.flot.min.js" />' ></script> --%>
   
	<!-- Morris -->
	<script src='<c:url value="/resources/endless/js/morris.min.js" />'></script>	
	
	<!-- Colorbox -->
	<script src='<c:url value="/resources/endless/js/jquery.colorbox.min.js" />' ></script>	

	<!-- Pace -->
	<script src='<c:url value="/resources/endless/js/uncompressed/pace.js" />' ></script>
	
	<!-- Popup Overlay -->
	<script src='<c:url value="/resources/endless/js/jquery.popupoverlay.min.js" />' ></script>
	
	<!-- Slimscroll -->
	<script src='<c:url value="/resources/endless/js/jquery.slimscroll.min.js" />' ></script>
	
	<!-- Modernizr -->
	<script src='<c:url value="/resources/endless/js/modernizr.min.js" />' ></script>
	
	<!-- Cookie -->
	<script src='<c:url value="/resources/endless/js/jquery.cookie.min.js" />' ></script>
	
	<!-- Endless -->
	<%-- <script src='<c:url value="/resources/endless/js/endless/endless_dashboard.js" />'></script> --%>
	<script src='<c:url value="/resources/endless/js/endless/endless.js" />'></script>
	
	<!-- Masked Input -->
	<script src='<c:url value="/resources/endless/js/jquery.maskedinput.min.js" />'  type="text/javascript" ></script>
	
	<!-- DataTable JQuery  -->
	<script src='<c:url value="/resources/endless/js/jquery.dataTables.min.js" />' type="text/javascript" ></script>
	
  </body>

</html>
