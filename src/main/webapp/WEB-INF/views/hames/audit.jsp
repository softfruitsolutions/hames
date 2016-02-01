<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript">

	$(function(){
		$('#dateCreated').mask("99/99/9999");
		$('#dateModified').mask("99/99/9999");
	});
	
	
</script>

<div class="panel panel-default">
	<div class='panel-body collapse in' id="auditable">
		<div class="row">
			<div class="col-md-3">
				<div class="form-group">
					<label class="control-label">Staff Created</label>
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:input path="staffCreated" class="form-control input-sm" placeholder="Staff Created"  readonly="true"/>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="form-group">
					<label class="control-label">Staff Modified</label>
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-user"></i></span>
						<form:input path="staffModified" class="form-control input-sm" placeholder="Staff Modified"  readonly="true"/>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="form-group">
					<label class="control-label">Date Created</label>
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						<form:input path="dateCreated" cssClass="form-control input-sm" placeholder="Date Created" readonly="true"/>								 	
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="form-group">
					<label class="control-label">Date Modified</label>
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						<form:input path="dateModified" cssClass="form-control input-sm" placeholder="Date Modified" readonly="true"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>