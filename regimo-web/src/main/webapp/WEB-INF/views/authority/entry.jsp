<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="formBody">

	<form:label path="name">
		<spring:message code="authority.name"/> <form:errors path="name" cssClass="error" />
	</form:label>
	<form:input path="name" />

	<form:label path="url">
		<spring:message code="authority.url"/> <form:errors path="url" cssClass="error" />
	</form:label>
	<form:input path="url" />

	<form:label path="roles">
		<spring:message code="authority.roles"/> <form:errors path="roles" cssClass="error" />
	</form:label><br>
	<form:select path="roles" multiple="true" items="${referenceData.roles}" itemLabel="name" itemValue="id"
		cssStyle="width:300px" cssClass="populate"/>

	<form:hidden path="id" />

</div>

<script>
	$(document).ready(function() { $("#roles").select2(); });
</script>