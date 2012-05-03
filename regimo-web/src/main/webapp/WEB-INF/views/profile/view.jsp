<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

	

	<div class="formInfo">
  		<h2>View profile</h2>
  		<table>
			<tr>
				<td>First Name</td>
				<td>
					${user.firstName}
				</td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td>
					${user.lastName}
				</td>
			</tr>
			<tr>
				<td>User Name</td>
				<td>
					${user.username}
				</td>
			</tr>
			<tr>
				<td>Email Name</td>
				<td>
					${user.email}
				</td>
			</tr>
	
		</table>
	</div>


<s:url var="updateUrl" value="/profile/edit"></s:url>
	<a href="${updateUrl}">Edit</a> 

