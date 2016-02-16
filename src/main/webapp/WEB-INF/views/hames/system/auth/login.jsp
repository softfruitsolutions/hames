<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Hames | Softfruit</title>
	
	<!-- Font CSS (Via CDN) -->
	<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700'>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/absolute/assets/skin/default_skin/css/theme.css" /> ">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/absolute/assets/admin-tools/admin-forms/css/admin-forms.css" />">
	<link rel="shortcut icon" href="<c:url value="/resources/absolute/assets/img/favicon.ico" /> ">
	<script src="<c:url value="/resources/absolute/vendor/plugins/canvasbg/canvasbg.js" />"></script>

</head>

<body class="external-page sb-l-c sb-r-c">

	<!-- Start: Main -->
	<div id="main" class="animated fadeIn">

		<!-- Start: Content-Wrapper -->
		<section id="content_wrapper">

			<!-- Begin: Content -->
			<section id="content">
				<div class="admin-form theme-info mw500" id="login">
 
					<c:if test="${shiroLoginFailure != null }">
						<div class="alert alert-sm alert-border-left alert-danger alert-dismissable">
						  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
						  <i class="fa fa-info pr10"></i>
						  <strong>Wrong!</strong> Incorrect username or password
						</div>
					</c:if>
					
					<!-- Login Panel/Form -->
					<div class="panel panel-info mt10 br-n top mt30 mb25">
						<form class="login" action="" method="POST">
							<div class="panel-body bg-light p25 pb15">

								<!-- Divider -->
								<div class="section-divider mv30">
									<span>LOGIN</span>
								</div>

								<!-- Username Input -->
								<div class="section">
									<label for="username" class="field-label text-muted fs18 mb10">Username</label>
										<label for="username" class="field prepend-icon">
	 									<input type="text" placeholder="Enter username" id="username" name="username" class="gui-input" required />
										<label for="username" class="field-icon"> <i class="fa fa-user"></i>
										</label>
									</label>
								</div>

								<!-- Password Input -->
								<div class="section">
									<label for="password" class="field-label text-muted fs18 mb10">Password</label>
										<label for="password" class="field prepend-icon">
										<input type="password" name="password" id="password" class="gui-input" placeholder="Enter password"> 
										<label for="password" class="field-icon"> <i class="fa fa-lock"></i>
										</label>
									</label>
								</div>

							</div>

							<div class="panel-footer clearfix">
								<button type="submit" class="button btn-primary mr10 pull-right"><i class="fa fa-sign-in"></i> Sign In</button>
								<label class="switch ib switch-primary mt10"> <input
									type="checkbox" name="remember" id="remember" checked>
									<label for="remember" data-on="YES" data-off="NO"></label> <span>Remember
										me</span>
								</label>
							</div>

						</form>
					</div>
				</div>
			</section>
			<!-- End: Content -->
		</section>
		<!-- End: Content-Wrapper -->
	</div>
	<!-- End: Main -->


	<!-- BEGIN: PAGE SCRIPTS -->

	<!-- jQuery -->
	<script src="<c:url value="/resources/absolute/vendor/jquery/jquery-1.11.1.min.js" />"></script>
	<script src="<c:url value="/resources/absolute/vendor/jquery/jquery_ui/jquery-ui.min.js" />"></script>
	<script	src="<c:url value="/resources/absolute/assets/js/utility/utility.js" />"></script>
	<script src="<c:url value="/resources/absolute/assets/js/main.js" /> "></script>

	<!-- Page Javascript -->
	<script type="text/javascript">
	  jQuery(document).ready(function() {
	
	    "use strict";
	
	    Core.init();
	  });
  	</script>

	</body>
</html>
