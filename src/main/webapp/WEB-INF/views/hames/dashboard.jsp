<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
$(function(){
	var currentUser = $('#userCount').text();
	$({numberValue: 0}).animate({numberValue: currentUser}, {
		duration: 2500,
		easing: 'linear',
		step: function() { 
			$('#userCount').text(Math.ceil(this.numberValue)); 
		}
	});
});
		
</script>
<div class="main-header clearfix">
	<div class="page-title">
		<h3 class="no-margin">Dashboard</h3>
		<span>Welcome back <c:out value="${staffUtil.firstName }"></c:out></span>
	</div><!-- /page-title -->
</div>
<div class="padding-md">
	<div class="row">
		<div class="col-sm-6 col-md-3">
			<div class="panel-stat3 bg-danger">
				<h2 id="userCount" class="m-top-none"><c:out value="${customerCount}" /></h2>
				<h5>Registered customers</h5>
				<div class="stat-icon">
					<i class="fa fa-user fa-3x"></i>
				</div>
				<div class="refresh-button">
					<i class="fa fa-refresh"></i>
				</div>
				<div class="loading-overlay">
					<i class="loading-icon fa fa-refresh fa-spin fa-lg"></i>
				</div>
			</div>
		</div><!-- /.col -->
	</div>
</div>