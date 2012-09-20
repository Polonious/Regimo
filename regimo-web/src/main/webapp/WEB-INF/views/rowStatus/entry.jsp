<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="formBody">

	<form:label path="statusObject">
		<spring:message code="rowStatus.statusObject"/> <form:errors path="statusObject" cssClass="error" />
	</form:label>
	<form:input path="statusObject" />

	<form:label path="name">
		<spring:message code="rowStatus.name"/> <form:errors path="name" cssClass="error" />
	</form:label>
	<form:input path="name" />

	<form:label path="description">
		<spring:message code="rowStatus.description"/> <form:errors path="description" cssClass="error" />
	</form:label>
	<form:input path="description" />

	<form:label path="reference">
		<spring:message code="rowStatus.reference"/> <form:errors path="reference" cssClass="error" />
	</form:label>
	<form:input path="reference" />

	<form:label path="current">
		<spring:message code="rowStatus.current"/> <form:errors path="current" cssClass="error" />
	</form:label>
	<form:checkbox path="current" />

	<form:hidden path="id" />

</div>