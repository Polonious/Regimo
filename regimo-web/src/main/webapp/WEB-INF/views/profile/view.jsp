<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	
<form>
	<div class="formInfo">
  		<h2><s:message code="profile.view.title"/></h2>
  	</div>
  	<div class="formBody">
  		<table>
  			<tr>
				<td><s:message code="profile.edit.username"/></td>
				<td>					
					<input class="profile-value" value="${user.username}" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td><s:message code="profile.edit.email"/></td>
				<td>					
					<input class="profile-value" value="${user.email}" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td><s:message code="profile.edit.firstname"/></td>
				<td>					
					<input class="profile-value" value="${user.firstName}" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td><s:message code="profile.edit.lastname"/></td>
				<td>					
					<input class="profile-value" value="${user.lastName}" readonly="readonly" />
				</td>
			</tr>

	
		</table>

	<s:url var="updateUrl" value="/profile/edit"></s:url>
	<p><button type="button" onclick="location.href='../profile/edit'"><s:message code="button.edit"/></button>
	<button class="button-cancel" type="button" onclick="location.href='../home'"><s:message code="button.OK"/></button></p>
	</div>
</form>
