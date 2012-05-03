<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

	
	<s:url var="actionUrl" value="/userUpdateCommit"/>
<form:form action="${actionUrl}" modelAttribute="UserEditForm" method="post">
	<div class="formInfo">
  		<h2>Edit user</h2>
  		<table>
			<tr>
				<td>First Name edit</td>
				<td>
					<input type="text" name="firstName" id="firstName" 
							autocomplete="off" value="${user.firstName}"  />
				</td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td>
					<input type="text" name="lastName" id="lastName" 
							autocomplete="off" value="${user.lastName}"  />
				</td>
			</tr>
			<tr>
				<td>User Name</td>
				<td>
					<input type="text" name="username" id="username" 
							autocomplete="off" value="${user.username}"  />
				</td>
			</tr>
			<tr>
				<td>Email Name</td>
				<td>
					<input type="text" name="email" id="email" 
							autocomplete="off" value="${user.email}"  />
				</td>
			</tr>
			<td colspan="2">
				<input type="hidden" name="id" id="id" autocomplete="off" value="${user.id}"  />			
			</td>
			</tr>	
		</table>
	</div>

	<p><button type="submit">Save</button></p>

</form:form>