<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fieldset>
	<form:hidden path="id" />
	<form:label path="username"><s:message code="user.username"/> <form:errors path="username" cssClass="error" /></form:label>
	<form:input path="username" />
	<c:if test="${empty user.id}">
	<form:label path="password">Password (at least 6 characters) <form:errors path="password" cssClass="error" /></form:label>
	<form:password path="password" />
	</c:if>
	<form:label path="firstName"><s:message code="user.firstname"/> <form:errors path="firstName" cssClass="error" /></form:label>
	<form:input path="firstName" />
	<form:label path="lastName"><s:message code="user.lastname"/> <form:errors path="lastName" cssClass="error" /></form:label>
	<form:input path="lastName" />
	<form:label path="email"><s:message code="user.email"/> <form:errors path="email" cssClass="error" /></form:label>
	<form:input path="email" />

	<form:label path="roles">
		<s:message code="user.roles"/> <form:errors path="roles" cssClass="error" />
	</form:label><br>
	<form:select path="roles" items="${referenceData.roles}" multiple="true"
		itemLabel="name" itemValue="id" cssStyle="width:300px" />
	<br>

	<form:label path="rowStatus">
		<s:message code="user.rowStatus"/> <form:errors path="rowStatus" cssClass="error" />
	</form:label><br>
	<form:select path="rowStatus" items="${referenceData.rowStatus('User')}"
		itemLabel="name" itemValue="id" cssStyle="width:300px" />

</fieldset>

<script>
	$(document).ready(function() {
		$("#roles,#rowStatus").select2();
	});
</script>