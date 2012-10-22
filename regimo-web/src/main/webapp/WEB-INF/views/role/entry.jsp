<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="formBody">

	<form:label path="name">
		<s:message code="role.name"/> <form:errors path="name" cssClass="error" />
	</form:label>
	<form:input path="name" />

	<form:label path="description">
		<s:message code="role.description"/> <form:errors path="description" cssClass="error" />
	</form:label>
	<form:input path="description" />

	<form:label path="authorities">
		<s:message code="role.authorities"/> <form:errors path="authorities" cssClass="error" />
	</form:label><br>
	<form:select data-type="select2" path="authorities" multiple="true" items="${referenceData.authorities}" itemLabel="name" itemValue="id"
		cssStyle="width:300px" cssClass="populate"/>

	<form:hidden path="id" />

</div>