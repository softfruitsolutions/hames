<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!-- URL's -->
<c:url value="/inventory/product/save" var="productSaveUrl" />

<script type="text/javascript">

	/* GLOBAL VARIABLES */
	var SAVE_PRODUCT_URL = '${productSaveUrl}';
	
	/**
	 * Save a product
	 */
	function save(){
		var product = $('#product').serialize();
		$.ajax({
			type:'POST',
			url:SAVE_PRODUCT_URL, 
			data:product,
	        async: false,
			success:function(data){
				SuccessAlert.handleSuccess(data);
				setTimeout(function(){
					window.location.href=VIEW_SALE_ORDER_LIST;
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
		    <span class="panel-title panel-title hidden-xs"><i class="imoon imoon-drawer2"></i>PRODUCT</span>
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
				<a class="btn btn-info btn-sm" href="${sa}" title="Refresh"><i class="fa fa-refresh"></i></a>
				<shiro:hasPermission name="inventory:product:create">
				<a class="btn btn-primary btn-sm" onclick="save()">
					<i class="fa fa-save"></i>
					<c:if test="${product.productId == null }" >
						Save
					</c:if>
					<c:if test="${product.productId != null}" >
						Update
					</c:if>
				</a>
				</shiro:hasPermission>
		  	</div>
		  </div>
		  <form:form modelAttribute="product" method="POST" action="#">
		  <div class="panel-body ">
		  	<div class="tab-content">
				<div id="tab1" class="tab-pane active">
					<div class="row">
						<div class="col-xs-6">
							<div class="form-group">
								<label for="jobId" class="col-lg-3 control-label">Product Code</label>
								<div class="col-lg-9">
									<form:input path="productCode" cssClass="form-control input-sm" placeholder="Product Code"/>								 	
								</div><!-- /.col -->
							</div><!-- /form-group -->
							<div class="form-group">
								<label for="jobId" class="col-lg-3 control-label">Product Name</label>
								<div class="col-lg-9">
									<form:input path="productName" cssClass="form-control input-sm" placeholder="Product Name"/>								 	
								</div><!-- /.col -->
							</div><!-- /form-group -->
							<div class="form-group">
								<label class="col-lg-3 control-label">Product Type</label>
								<div class="col-lg-9">
									<form:select path="productType" cssClass="form-control input-sm">
										<form:options items="${productTypes}" />
									</form:select>
								</div>
							</div>
							<div class="form-group">
								<label for="productCategory" class="col-lg-3 control-label">Product Category</label>
		                        <div class="col-lg-9">
									<div class="input-group">
										<form:select path="productCategory" cssClass="form-control input-sm">
			                        		<%-- <form:options items="${customers }" itemLabel="fullName" itemValue="partyId"/> --%>
			                        	</form:select>
									   <span class="input-group-btn">
									        <button class="btn btn-primary light btn-sm" type="button" onclick="Customer.showModal()">
									        	<i class="glyphicons glyphicons-folder_new"></i>
									        </button>
									   </span>
									</div>
		                        </div>
							</div><!-- /form-group -->
							<div class="form-group">
								<label class="col-lg-3 control-label">Unit Of Measure</label>
								<div class="col-lg-9">
									<form:select path="uom" cssClass="form-control input-sm">
										<form:options items="${uom}" itemLabel="text"/>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-xs-6">
							<div class="form-group">
								<label for="productStatus" class="col-lg-3 control-label">Status</label>
								<div class="col-lg-9">
									<form:select path="productStatus" cssClass="form-control input-sm">
										<form:options items="${productStatus}" itemLabel="text"/>
									</form:select>
								</div><!-- /.col -->
							</div><!-- /form-group -->
							<div class="form-group">
								<label for="remarks" class="col-lg-3 control-label">Description</label>
								<div class="col-lg-9">
									<form:textarea path="productDescription" cssClass="form-control input-sm" placeholder="Remarks" />
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