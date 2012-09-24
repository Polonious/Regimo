<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<form:form modelAttribute="user" method="post">
	<div class="formInfo">
  		<h2><s:message code="profile.edit.title"/></h2>
  	</div>
  	<div class="formBody">
  		<table>
  			<tr>
				<td> <s:message code="profile.edit.username"/></td>
				<td>
					<input class="profile-value" value="${user.username}" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td><s:message code="profile.edit.email"/></td>
				<td>
					<input type="text" name="email" id="email"
							autocomplete="off" value="${user.email}"  />
				</td>
			</tr>
			<tr>
				<td><s:message code="profile.edit.firstname"/></td>
				<td>
					<input type="text" name="firstName" id="firstName"
							autocomplete="off" value="${user.firstName}"  />
				</td>
			</tr>

			<tr>
				<td><s:message code="profile.edit.lastname"/></td>
				<td>
					<input type="text" name="lastName" id="lastName"
							autocomplete="off" value="${user.lastName}"  />
				</td>
			</tr>


		</table>

	<p><button type="submit"><s:message code="button.save"/></button>
	<button class="button-cancel" type="button" onclick="location.href='/profile'"><s:message code="button.cancel"/></button></p>
	</div>
</form:form>