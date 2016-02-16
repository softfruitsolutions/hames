<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <!-- Meta, title, CSS, favicons, etc. -->
  <meta charset="utf-8">
  <title><tiles:insertAttribute name="title" /></title>

  <!-- Font CSS (Via CDN) -->
  
  <link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700'>

  <!-- Theme CSS -->
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/absolute/assets/skin/default_skin/css/theme.css" /> ">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/absolute/assets/admin-tools/admin-forms/css/admin-forms.css" />">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/absolute/vendor/plugins/magnific/magnific-popup.css" />">

  <!-- Favicon -->
  <link rel="shortcut icon" href="<c:url value="/resources/absolute/assets/img/favicon.ico" /> ">

   <!-- jQuery -->
  <script src="<c:url value="/resources/absolute/vendor/jquery/jquery-1.11.1.min.js" />"></script>
  <script src="<c:url value="/resources/absolute/vendor/jquery/jquery_ui/jquery-ui.min.js" />"></script>
  <script src="<c:url value="/resources/absolute/vendor/plugins/pnotify/pnotify.js" />" ></script>
  <script src='<c:url value="/resources/hames/js/jquery.maskedinput.min.js" />'  type="text/javascript" ></script>
  <script src='<c:url value="/resources/hames/util/js/alert-util.js" />'  type="text/javascript" ></script>
  <script src='<c:url value="/resources/hames/util/js/datetime-util.js" />' type="text/javascript"></script>
  
  <!-- Theme Javascript -->
  <script src="<c:url value="/resources/absolute/assets/js/utility/utility.js" />"></script>
  <%-- <script src="<c:url value="/resources/absolute/assets/js/demo/demo.js" /> "></script> --%>  
  <script src="<c:url value="/resources/absolute/assets/js/main.js" /> "></script>
  <script src="<c:url value="/resources/absolute/vendor/plugins/magnific/jquery.magnific-popup.js" />"></script>
  
  <!-- Datatables CSS -->
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/absolute/vendor/plugins/datatables/media/css/dataTables.bootstrap.css" />">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/absolute/vendor/plugins/datatables/media/css/dataTables.plugins.css" />">
  
  <!-- Datatables -->
  <script src="<c:url value="/resources/absolute/vendor/plugins/datatables/media/js/jquery.dataTables.js" />"></script>
  <script src="<c:url value="/resources/absolute/vendor/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.min.js" />"></script>
  <script src="<c:url value="/resources/absolute/vendor/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.min.js" />"></script>
  <script src="<c:url value="/resources/absolute/vendor/plugins/datatables/media/js/dataTables.bootstrap.js" />"></script>  
  <script src="<c:url value="/resources/absolute/vendor/plugins/datatables/media/js/fnReloadAjax.js" />" ></script>
  
  <!-- Fonts -->
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/absolute/assets/fonts/glyphicons-pro/glyphicons-pro.css" />">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/absolute/assets/fonts/icomoon/icomoon.css" />">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/absolute/assets/fonts/iconsweets/iconsweets.css" />">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/absolute/assets/fonts/octicons/octicons.css" />">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/absolute/assets/fonts/stateface/stateface.css" />">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/absolute/assets/skin/default_skin/css/theme.css" />">
  
    

  <script type="text/javascript">

	  jQuery(document).ready(function() {
	
	    "use strict";
	
	    // Init Theme Core    
	    Core.init();
	    //Demo.init();
	
	  });
  </script>
</head>

<body class="sb-top sb-top-sm">

	<!-- Start: Main -->
  <div id="main">

    <!-- Start: Header -->
    <header class="navbar navbar-fixed-top navbar-shadow bg-primary">

      <div class="navbar-branding">
        <a class="navbar-brand" href="<c:url value="/dashboard"/>">
          <b>Hames</b> Softfruit
        </a>
      </div>
      <ul class="nav navbar-nav navbar-left">
        <li class="hidden-xs">
          <a class="request-fullscreen toggle-active" href="#">
            <span class="ad ad-screen-full fs18"></span>
          </a>
        </li>
      </ul>

      <ul class="nav navbar-nav navbar-right">
        <li class="menu-divider hidden-xs">
          <i class="fa fa-circle"></i>
        </li>
        <li class="dropdown menu-merge">
          <a href="#" class="dropdown-toggle fw600 p15" data-toggle="dropdown"> <img src="<c:url value="/resources/absolute/assets/img/avatars/1.jpg" />" alt="avatar" class="mw30 br64 mr15"> 
          	<c:out value="${staffUtil.fullName }" />
            <span class="caret caret-tp hidden-xs"></span>
          </a>
          <ul class="dropdown-menu list-group dropdown-persist w250" role="menu">
            <li class="list-group-item">
              <a href="<c:url value="/logout" />" class="animated animated-short">
                <span class="fa fa-power-off"></span><b>Logout</b></a>
            </li>
          </ul>
        </li>
      </ul>

    </header>
    <!-- End: Header -->

    <!-- Start: Sidebar -->
    <aside id="sidebar_left" class="sidebar-light">
	  <tiles:insertAttribute name="menu" />
    </aside>
    
    <!-- Start: Content-Wrapper -->
    <section id="content_wrapper">

      <!-- Start: Topbar -->
      <header id="topbar" class="hidden">
        <div class="topbar-left">
          <ol class="breadcrumb">
            <li class="crumb-active">
              <a href="dashboard.html">Dashboard</a>
            </li>
            <li class="crumb-icon">
              <a href="dashboard.html">
                <span class="glyphicon glyphicon-home"></span>
              </a>
            </li>
            <li class="crumb-link">
              <a href="dashboard.html">Home</a>
            </li>
            <li class="crumb-trail">Dashboard</li>
          </ol>
        </div>
      </header>
      <!-- End: Topbar -->

      <!-- Begin: Content -->
      <section id="content" class="animated fadeIn">
			<tiles:insertAttribute name="body" />
      </section>

    </section>

  </div>
  <!-- End: Main -->

</body>


</html>
