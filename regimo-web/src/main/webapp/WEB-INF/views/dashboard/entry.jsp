<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fieldset>

	<form:label path="viewName"><s:message code="dashboard.viewName"/> <form:errors path="viewName" cssClass="error" /></form:label>
	<form:input path="viewName" />

	<form:label path="columnCount"><s:message code="dashboard.columnCount"/> <form:errors path="columnCount" cssClass="error" /></form:label>
	<form:input path="columnCount" />

	<form:label path="columnWidth"><s:message code="dashboard.columnWidth"/> <form:errors path="columnWidth" cssClass="error" /></form:label>
	<form:input path="columnWidth" />

	<form:label path="user">
		<s:message code="dashboard.user"/> <form:errors path="user" cssClass="error" />
	</form:label><br>
	<form:select path="user">
		<form:option value="">&nbsp;</form:option>
		<form:options items="${referenceData.users}" itemLabel="username" itemValue="id" />
	</form:select>

	<form:hidden path="id" />

</fieldset>