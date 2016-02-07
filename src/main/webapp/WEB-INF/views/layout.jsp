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

  <!-- Favicon -->
  <link rel="shortcut icon" href="<c:url value="/resources/absolute/assets/img/favicon.ico" /> ">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	  <!--[if lt IE 9]>
	  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
  <![endif]-->
  
   <!-- jQuery -->
  <script src="<c:url value="/resources/absolute/vendor/jquery/jquery-1.11.1.min.js" />"></script>
  <script src="<c:url value="/resources/absolute/vendor/jquery/jquery_ui/jquery-ui.min.js" />"></script>
  
  
  <!-- Theme Javascript -->
  <script src="<c:url value="/resources/absolute/assets/js/utility/utility.js" />"></script>
 <%--  <script src="<c:url value="/resources/absolute/assets/js/demo/demo.js" /> "></script> --%>
  <script src="<c:url value="/resources/absolute/assets/js/main.js" /> "></script>
  
  <!-- Datatables CSS -->
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/absolute/vendor/plugins/datatables/media/css/dataTables.bootstrap.css" />">
  <link rel="stylesheet" type="text/css" href="<c:url value="/resources/absolute/vendor/plugins/datatables/media/css/dataTables.plugins.css" />">
  
  <!-- Datatables -->
  <script src="<c:url value="/resources/absolute/vendor/plugins/datatables/media/js/jquery.dataTables.js" />"></script>
  <script src="<c:url value="/resources/absolute/vendor/plugins/datatables/extensions/TableTools/js/dataTables.tableTools.min.js" />"></script>
  <script src="<c:url value="/resources/absolute/vendor/plugins/datatables/extensions/ColReorder/js/dataTables.colReorder.min.js" />"></script>
  <script src="<c:url value="/resources/absolute/vendor/plugins/datatables/media/js/dataTables.bootstrap.js" />"></script>  

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
        <a class="navbar-brand" href="dashboard.html">
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
          <a href="#" class="dropdown-toggle fw600 p15" data-toggle="dropdown"> <img src="<c:url value="/resources/absolute/assets/img/avatars/1.jpg" />" alt="avatar" class="mw30 br64 mr15"> John.Smith
            <span class="caret caret-tp hidden-xs"></span>
          </a>
          <ul class="dropdown-menu list-group dropdown-persist w250" role="menu">
            <li class="list-group-item">
              <a href="#" class="animated animated-short">
                <span class="fa fa-power-off"></span><b>Logout</b></a>
            </li>
          </ul>
        </li>
      </ul>

    </header>
    <!-- End: Header -->

    <!-- Start: Sidebar -->
    <aside id="sidebar_left" class="sidebar-light">

      <!-- Start: Sidebar Left Content -->
      <div class="sidebar-left-content nano-content ">

        <!-- Start: Sidebar Menu -->
        <ul class="nav sidebar-menu">

          <li>
            <a href="dashboard.html">
              <span class="glyphicon glyphicon-home"></span>
              <span class="sidebar-title">Dashboard</span>
            </a>
          </li>
          <li>
            <a href="http://admindesigns.com/demos/admin/README/index.html">
              <span class="glyphicon glyphicon-book"></span>
              <span class="sidebar-title">Documentation</span>
            </a>
          </li>
          <li class="active">
            <a class="accordion-toggle menu-open" href="#">
              <span class="fa fa-columns"></span>
              <span class="sidebar-title">Layout Templates</span>
              <span class="caret"></span>
            </a>
            <ul class="nav sub-nav">
              <li>
                <a class="accordion-toggle" href="#">
                  <span class="fa fa fa-arrows-h"></span>
                  Sidebars
                  <span class="caret"></span>
                </a>
                <ul class="nav sub-nav">
                  <li>
                    <a href="layout_sidebar-left-static.html">
                      Left Static </a>
                  </li>
                  <li>
                    <a href="layout_sidebar-left-fixed.html">
                      Left Fixed </a>
                  </li>
                  <li>
                    <a href="layout_sidebar-left-widgets.html">
                      Left Widgets </a>
                  </li>
                  <li>
                    <a href="layout_sidebar-left-minified.html">
                      Left Minified </a>
                  </li>
                  <li>
                    <a href="layout_sidebar-left-light.html">
                      Left White </a>
                  </li>
                  <li>
                    <a href="layout_sidebar-right-static.html">
                      Right Static </a>
                  </li>
                  <li>
                    <a href="layout_sidebar-right-fixed.html">
                      Right Fixed </a>
                  </li>
                  <li>
                    <a href="layout_sidebar-right-menu.html">
                      Right w/Menu </a>
                  </li>
                </ul>
              </li>
              <li>
                <a class="accordion-toggle" href="#">
                  <span class="fa fa-arrows-v"></span>
                  Navbar
                  <span class="caret"></span>
                </a>
                <ul class="nav sub-nav">
                  <li>
                    <a href="layout_navbar-static.html">
                      Navbar Static </a>
                  </li>
                  <li>
                    <a href="layout_navbar-fixed.html">
                      Navbar Fixed </a>
                  </li>
                  <li>
                    <a href="layout_navbar-menus.html">
                      Navbar Menus </a>
                  </li>
                  <li>
                    <a href="layout_navbar-contextual.html">
                      Contextual Example </a>
                  </li>
                  <li>
                    <a href="layout_navbar-search-alt.html">
                      Search Alt Style </a>
                  </li>
                </ul>
              </li>
              <li>
                <a class="accordion-toggle" href="#">
                  <span class="fa fa-hand-o-up"></span>
                  Topbar
                  <span class="caret"></span>
                </a>
                <ul class="nav sub-nav">
                  <li>
                    <a href="layout_topbar.html">
                      Default Style </a>
                  </li> 
                  <li>
                    <a href="layout_topbar-menu.html">
                      Default w/Menu </a>
                  </li>  
                  <li>
                    <a href="layout_topbar-alt.html">
                      Alternate Style </a>
                  </li>  
                </ul>
              </li>
              <li>
                <a class="accordion-toggle" href="#">
                  <span class="fa fa-arrows-v"></span>
                  Content Body
                  <span class="caret"></span>
                </a>
                <ul class="nav sub-nav">
                  <li>
                    <a href="layout_content-blank.html">
                      Blank Starter </a>
                  </li>
                  <li>
                    <a href="layout_content-fixed.html">
                      Fixed Window </a>
                  </li>
                  <li>
                    <a href="layout_content-heading.html">
                      Content Heading </a>
                  </li>
                  <li>
                    <a href="layout_content-tabs.html">
                      Content Tabs </a>
                  </li>
                </ul>
              </li>
              <li>
                <a class="accordion-toggle" href="#">
                  <span class="fa fa-pause"></span>
                  Content Trays
                  <span class="caret"></span>
                </a>
                <ul class="nav sub-nav">
                  <li>
                    <a href="layout_tray-left.html">
                      Tray Left Static </a>
                  </li> 
                  <li>
                    <a href="layout_tray-left-fixed.html">
                      Tray Left Fixed </a>
                  </li> 
                  <li>
                    <a href="layout_tray-right.html">
                      Tray Right Static </a>
                  </li> 
                  <li>
                    <a href="layout_tray-right-fixed.html">
                      Tray Right Fixed </a>
                  </li> 
                  <li>
                    <a href="layout_tray-both.html">
                      Left + Right Static </a>
                  </li> 
                  <li>
                    <a href="layout_tray-both-fixed.html">
                      Left + Right Fixed </a>
                  </li> 
                </ul>
              </li>
              <li>
                <a class="accordion-toggle" href="#">
                  <span class="fa fa-plus-square-o"></span>
                  Boxed Layout
                  <span class="caret"></span>
                </a>
                <ul class="nav sub-nav">
                  <li>
                    <a href="layout_boxed.html">
                      Default </a>
                  </li>
                  <li>
                    <a href="layout_boxed-horizontal.html">
                      Horizontal Menu </a>
                  </li>
                </ul>
              </li>
              <li class="active">
                <a class="accordion-toggle menu-open" href="#">
                  <span class="fa fa-arrow-circle-o-up"></span>
                  Horizontal Menu
                  <span class="caret"></span>
                </a>
                <ul class="nav sub-nav">
                  <li class="active">
                    <a href="layout_horizontal-sm.html">
                      Small Size</a>
                  </li>
                  <li>
                    <a href="layout_horizontal-md.html">
                      Medium Size</a>
                  </li>
                  <li>
                    <a href="layout_horizontal-lg.html">
                      Large Size</a>
                  </li>
                  <li>
                    <a href="layout_horizontal-light.html">
                      Light Skin</a>
                  </li>
                  <li>
                    <a href="layout_horizontal-topbar.html">
                      With Topbar</a>
                  </li>
                  <li>
                    <a href="layout_horizontal-topbar-alt.html">
                      With Alt Topbar</a>
                  </li>
                  <li>
                    <a href="layout_horizontal-collapsed.html">
                      Collapsed onLoad</a>
                  </li>
                  <li>
                    <a href="layout_horizontal-boxed.html">
                      In Boxed Layout</a>
                  </li>
                </ul>
              </li>
            </ul>
          </li>
          <li>
            <a class="accordion-toggle" href="#">
              <span class="glyphicon glyphicon-fire"></span>
              <span class="sidebar-title">Admin Plugins</span>
              <span class="caret"></span>
            </a>
            <ul class="nav sub-nav">
              <li>
                <a href="admin_plugins-panels.html">
                  <span class="glyphicon glyphicon-book"></span> Admin Panels </a>
              </li>
              <li>
                <a href="admin_plugins-modals.html">
                  <span class="glyphicon glyphicon-modal-window"></span> Admin Modals </a>
              </li>
              <li>
                <a href="admin_plugins-dock.html">
                  <span class="glyphicon glyphicon-equalizer"></span> Admin Dock </a>
              </li>
            </ul>
          </li>
          <li>
            <a class="accordion-toggle" href="#">
              <span class="glyphicon glyphicon-check"></span>
              <span class="sidebar-title">Admin Forms</span>
              <span class="caret"></span>
            </a>
            <ul class="nav sub-nav">
              <li>
                <a href="admin_forms-elements.html">
                  <span class="glyphicon glyphicon-edit"></span> Admin Elements </a>
              </li>
              <li>
                <a href="admin_forms-widgets.html">
                  <span class="glyphicon glyphicon-calendar"></span> Admin Widgets </a>
              </li>
              <li>
                <a href="admin_forms-validation.html">
                  <span class="glyphicon glyphicon-check"></span> Admin Validation </a>
              </li>
              <li>
                <a href="admin_forms-layouts.html">
                  <span class="fa fa-desktop"></span> Admin Layouts </a>
              </li>
              <li>
                <a href="admin_forms-wizard.html">
                  <span class="fa fa-clipboard"></span> Admin Wizard </a>
              </li>
            </ul>
          </li>
          <li>
            <a class="accordion-toggle" href="#">
              <span class="glyphicon glyphicon-shopping-cart"></span>
              <span class="sidebar-title">Ecommerce</span>
              <span class="caret"></span>
            </a>
            <ul class="nav sub-nav">
              <li>
                <a href="ecommerce_dashboard.html">
                  <span class="glyphicon glyphicon-shopping-cart"></span> Dashboard
                  <span class="label label-xs bg-primary">New</span>
                </a>
              </li>
              <li>
                <a href="ecommerce_products.html">
                  <span class="glyphicon glyphicon-tags"></span> Products </a>
              </li>
              <li>
                <a href="ecommerce_orders.html">
                  <span class="fa fa-money"></span> Orders </a>
              </li>
              <li>
                <a href="ecommerce_customers.html">
                  <span class="fa fa-users"></span> Customers </a>
              </li>
              <li>
                <a href="ecommerce_settings.html">
                  <span class="fa fa-gears"></span> Store Settings </a>
              </li>
            </ul>
          </li>
          <li>
            <a class="accordion-toggle" href="#">
              <span class="glyphicon glyphicon-duplicate"></span>
              <span class="sidebar-title">Pages</span>
              <span class="caret"></span>
            </a>
            <ul class="nav sub-nav">
              <li>
                <a class="accordion-toggle" href="#">
                  <span class="fa fa-gears"></span> Utility
                  <span class="caret"></span>
                </a>
                <ul class="nav sub-nav">
                  <li>
                    <a href="landing-page/landing1/index.html" target="_blank"> Landing Page </a>
                  </li>
                  <li>
                    <a href="pages_confirmation.html" target="_blank"> Confirmation
                      <span class="label label-xs bg-primary">New</span>
                    </a>
                  </li>
                  <li>
                    <a href="pages_login.html" target="_blank"> Login </a>
                  </li>
                  <li>
                    <a href="pages_login(alt).html" target="_blank"> Login Alt
                      <span class="label label-xs bg-primary">New</span>
                    </a>
                  </li>
                </ul>
              </li>
              <li>
                <a class="accordion-toggle" href="#">
                  <span class="fa fa-desktop"></span> Basic
                  <span class="caret"></span>
                </a>
                <ul class="nav sub-nav">
                  <li>
                    <a href="pages_search-results.html">Search Results
                      <span class="label label-xs bg-primary">New</span>
                    </a>
                  </li>
                  <li>
                    <a href="pages_timeline.html"> Timeline Split </a>
                  </li>
                  <li>
                    <a href="pages_timeline-single.html"> Timeline Single </a>
                  </li>
                  <li>
                    <a href="pages_faq.html"> FAQ Page </a>
                  </li>
                  <li>
                    <a href="pages_calendar.html"> Calendar </a>
                  </li>
                </ul>
              </li>
              <li>
                <a class="accordion-toggle" href="#">
                  <span class="fa fa-usd"></span> Misc
                  <span class="caret"></span>
                </a>
                <ul class="nav sub-nav">
                  <li>
                    <a href="pages_invoice.html"> Printable Invoice </a>
                  </li>
                  <li>
                    <a href="pages_blank.html"> Blank </a>
                  </li>
                  <li>
                    <a href="pages_profile.html"> Profile </a>
                  </li>
                  <li>
                    <a href="pages_timeline.html"> Timeline Split </a>
                  </li>
                  <li>
                    <a href="pages_timeline-single.html"> Timeline Single </a>
                  </li>
                  <li>
                    <a href="pages_faq.html"> FAQ Page </a>
                  </li>
                </ul>
              </li>
            </ul>
          </li>

        </ul>
        <!-- End: Sidebar Menu -->

      </div>
      <!-- End: Sidebar Left Content -->

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
