<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-md-12">
	<h3 class="headline m-top-md">
		Staff Role
		<span class="line"></span>
	</h3>
	
	<div class="panel panel-default">
		<div class="panel-heading">
			Available Roles
			<span class="pull-right">
				<a class="btn btn-xs btn-info" href="staffrole" title="Create Staff Role"><i class="fa fa-edit"></i> Create Staff Role</a>
			</span>
			<br/>
		</div>
		<div class="panel-body">
			<table class="table table-striped table-hover" id="responsiveTable">
				<thead>
					<tr>
						<th>Role ID</th>
						<th>Role Name</th>
						<th>Role Description</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${staffRoles}" var="role">
						<tr onclick="view(${role.roleId})">
							<td>${role.roleId }</td>
							<td>${role.roleName }</td>
							<td>${role.roleDescription }</td>
							<td>
								<a href="<c:url value='staffrole?id=${role.roleId }'>${role.roleName }</c:url>" title="Edit">
									<i class="fa fa-pencil"></i>
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>