<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="formBody">

	<form:label path="statusObject">
		<s:message code="rowStatus.statusObject"/> <form:errors path="statusObject" cssClass="error" />
	</form:label>
	<form:input path="statusObject" />

	<form:label path="name">
		<s:message code="rowStatus.name"/> <form:errors path="name" cssClass="error" />
	</form:label>
	<form:input path="name" />

	<form:label path="description">
		<s:message code="rowStatus.description"/> <form:errors path="description" cssClass="error" />
	</form:label>
	<form:input path="description" />

	<form:label path="reference">
		<s:message code="rowStatus.reference"/> <form:errors path="reference" cssClass="error" />
	</form:label>
	<form:input path="reference" />

	<form:label path="current">
		<s:message code="rowStatus.current"/> <form:errors path="current" cssClass="error" />
	</form:label>
	<form:checkbox path="current" />

	<form:hidden path="id" />

</div>