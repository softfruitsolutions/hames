<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!-- URL's  -->
<c:url value="/inventory/product/group/save" var="groupSaveUrl" />

<script type="text/javascript">
	/**
	 * Product Group related functions for order
	 * 1. Showing group modal
	 * 2. Saving a group
	 * 3. Set group data to option field after saving a product
	 */
	var ProductGroup = (function(){
		this.showModal = function(){
			$('#productGroupModal').modal({
				show:true,
				backdrop:'static',
			});
		};
		this.hideModal = function(){
			$('form#productGroup')[0].reset();
			$('#productGroupModal').modal('hide');
		};
		this.postCreate = function(){
			//setCustomerValues(null);
			this.hideModal();
		};
		return this;
	})();
	
	function saveProductGroup(){
		var productGroup= $('form#productGroup').serialize();
		$.ajax({
			type:'POST',
			url:'${groupSaveUrl}',
			data:productGroup,
	        async: false,
			success:function(data){
				SuccessAlert.handleSuccess(data);
				ProductGroup.postCreate();
			},
			error:function(data){
				ErrorAlert.handleError(data.responseJSON.message);
			}
		});
	}
	
</script>

<div class="row">
	<div class="col-md-12">
		<div class="panel panel-primary panel-border top">
		  <div class="panel-heading">
		    <span class="panel-title panel-title hidden-xs"><i class="imoon imoon-user"></i> PRODUCT GROUP</span>
		    <ul class="nav panel-tabs">
		      <li class="active">
		        <a href="#productGroupCreateTab" data-toggle="tab">Create</a>
		      </li>
		    </ul>
		  </div>
		  <form:form modelAttribute="productGroup" method="POST" action="#">
		  <div class="panel-body">
		  	<div class="tab-content">
				<div id="productGroupCreateTab" class="tab-pane active">
					<div class="row">
						<div class="col-xs-12">
							<div class="form-group">
								<form:label path="productGroupName" cssClass="col-lg-5 control-label">Group Name</form:label>
								<div class="col-lg-7">
									<form:input path="productGroupName" cssClass="form-control input-sm" placeholder="Group Name" />								 	
								</div><!-- /.col -->
							</div><!-- /form-group -->
							<div class="form-group">
								<form:label path="productGroupDescription" cssClass="col-lg-5 control-label">Group Description</form:label>
								<div class="col-lg-7">
									<form:textarea path="productGroupDescription" cssClass="form-control input-sm" placeholder="Group description" />								 	
								</div><!-- /.col -->
							</div><!-- /form-group -->
						</div>
					</div>
				</div>
				<div id="tab2" class="tab-pane">
					<%-- <jsp:include page="/WEB-INF/views/hames/audit.jsp" /> --%>
				</div>
			</div>
		  </div>
		  </form:form>
		</div>
	</div>
</div>