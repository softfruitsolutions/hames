<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- URL's -->
<c:url value="/inventory/pricelist/view" var="pricelistViewUrl" />
<c:url value="/inventory/pricelist/create" var="pricelistCreateUrl" />
<c:url value="/inventory/pricelist/save" var="pricelistSaveUrl" />

<script type="text/javascript">
	/* GLOBAL VARIABLES */
	var VIEW_PRICELIST = "";
	var SAVE_PRICELIST = '${pricelistSaveUrl}';

	/**
	 * Save a product
	 */
	function save(){
		var pricelist = $('#pricelist').serialize();
		$.ajax({
			type:'POST',
			url:SAVE_PRICELIST, 
			data:pricelist,
	        async: false,
			success:function(data){
				SuccessAlert.handleSuccess(data);
				setTimeout(function(){
					window.location.href=VIEW_PRICELIST;
			    },1000);
				
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
		    <span class="panel-title panel-title hidden-xs"><i class="fa fa-money"></i> PRICE LIST</span>
		     <ul class="nav panel-tabs">
		      <li class="active">
		        <a href="#tab1" data-toggle="tab">Create</a>
		      </li>
		      <li class="">
		        <a href="#tab2" data-toggle="tab">Audit</a>
		      </li>
		    </ul>
		  </div>
		  <div class="panel-menu">
		  	<div class="btn-group">
		  		<a class="btn btn-info btn-sm" href="${pricelistViewUrl}" title="Back to Pricelist"><i class="fa fa-arrow-left"></i></a>
				<a class="btn btn-info btn-sm" href="${pricelistCreateUrl}" title="Refresh"><i class="fa fa-refresh"></i></a>
				<shiro:hasPermission name="inventory:pricelist:create">
				<a class="btn btn-primary btn-sm" onclick="save()">
					<i class="fa fa-save"></i>
					<c:if test="${pricelist.priceListId == null }" >
						Save
					</c:if>
					<c:if test="${pricelist.priceListId != null}" >
						Update
					</c:if>
				</a>
				</shiro:hasPermission>
		  	</div>
		  </div>
		  <form:form modelAttribute="pricelist" method="POST" action="#">
		  <div class="panel-body ">
		  	<div class="tab-content">
				<div id="tab1" class="tab-pane active">
					<div class="row">
						<form:hidden path="priceListId"/>
						<div class="col-xs-6">
							<div class="form-group">
								<label for="priceListName" class="col-lg-3 control-label">Price List Name</label>
								<div class="col-lg-9">
									<form:input path="priceListName" cssClass="form-control input-sm" placeholder="Price List Name"/>								 	
								</div><!-- /.col -->
							</div><!-- /form-group -->
							<div class="form-group">
								<label for="priceListDescription" class="col-lg-3 control-label">Description</label>
								<div class="col-lg-9">
									<form:textarea path="priceListDescription" cssClass="form-control input-sm" placeholder="Price List Description"/>								 	
								</div><!-- /.col -->
							</div><!-- /form-group -->
						</div>
						<div class="col-xs-6">
							<div class="form-group">
								<div class="checkbox-custom fill checkbox-primary mb5">
								  <input checked="" id="isDefault" name="isDefault" type="checkbox">
								  <label for="isDefault">Default</label>
								</div>
							</div>
						</div>
					</div>
					<br />
					<div class="row">
						<div class="col-xs-12">
							<h5>Price List Items</h5>
							<hr class="short alt">
						</div>
					</div>
				</div>
				<div id="tab2" class="tab-pane">
					<jsp:include page="/WEB-INF/views/hames/system/audit2.jsp" />
				</div>
			</div>
		 </div>
		 </form:form>
		</div>
	</div>
</div>