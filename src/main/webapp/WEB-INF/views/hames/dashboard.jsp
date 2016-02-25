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

<div class="row">
	<div class="col-sm-3">
		<div class="bs-component">
			<div class="panel panel-tile text-primary br-b bw5 br-primary-light">
			  <div class="panel-body pl20 p5">
			    <i class="icon-users icon-bg"></i>
			    <h2 class="mt20 lh20" id="userCount">
			      <b><c:out value="${customerCount}" /></b>
			    </h2>
			    <h5 class="text-muted">CUSTOMERS</h5>
			  </div>
			</div>
		</div>	
	</div>
	<div class="col-sm-3">
       <div class="panel bg-primary light of-h mb10">
         <div class="pn pl20 p12">
           <div class="icon-bg p5">
             <i class="icon-shopping-cart2"></i>
           </div>
           <h2 class="mt15 lh15">
             <b><c:out value="${saleOrderCount}" /></b>
           </h2>
           <h5 class="text-muted">ORDERS</h5>
         </div>
       </div>
     </div>
</div>
