<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

	
<form>
	<div class="formInfo">
  		<h2>View profile</h2>
  	</div>
  	<div class="formBody">
  		<table>
  			<tr>
				<td>User Name</td>
				<td>					
					<input class="profile-value" value="${user.username}" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td>Email Name</td>
				<td>					
					<input class="profile-value" value="${user.email}" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td>First Name</td>
				<td>					
					<input class="profile-value" value="${user.firstName}" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td>					
					<input class="profile-value" value="${user.lastName}" readonly="readonly" />
				</td>
			</tr>

	
		</table>

	<s:url var="updateUrl" value="/profile/edit"></s:url>
	<p><button type="button" onclick="location.href='edit'">Edit</button><button class="button-cancel" type="button" onclick="location.href='../home'">Ok</button></p>
	</div>
</form>
