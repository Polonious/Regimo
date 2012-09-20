<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="formBody">

	<form:label path="name">
		<spring:message code="role.name"/> <form:errors path="name" cssClass="error" />
	</form:label>
	<form:input path="name" />

	<form:label path="description">
		<spring:message code="role.description"/> <form:errors path="description" cssClass="error" />
	</form:label>
	<form:input path="description" />

	<form:label path="authorities">
		<spring:message code="role.authorities"/> <form:errors path="authorities" cssClass="error" />
	</form:label><br>
	<form:select path="authorities" multiple="true" items="${referenceData.authorities}" itemLabel="name" itemValue="id"
		cssStyle="width:300px" cssClass="populate"/>

	<form:hidden path="id" />

</div>

<script>
	$(document).ready(function() { $("#authorities").select2(); });
</script>