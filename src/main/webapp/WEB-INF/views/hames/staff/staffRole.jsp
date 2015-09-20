<div class="col-md-12">
	<h3 class="headline m-top-md">
		Staff Role
		<span class="line"></span>
	</h3>
	
	<div class="col-md-12">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">
					Role
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label">Role ID</label>
								<input class="form-control input-sm" type="text" placeholder="Role ID" disabled>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label">Role Name</label>
								<input class="form-control input-sm" type="text" placeholder="Role Name">
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label">Role Description</label>
								<textarea class="form-control input-sm" placeholder="Role Description"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="panel panel-default">
				<div class="panel-heading">
					Available Roles
				</div>
			</div>
		</div>
	</div>
	
	<div class="col-md-12">
		<div class="row">
			<jsp:include page="/WEB-INF/views/hames/audit.jsp" />
		</div>
	</div>
</div>