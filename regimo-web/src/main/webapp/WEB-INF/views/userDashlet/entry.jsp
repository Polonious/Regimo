<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fieldset>

	<form:label path="dashboard">
		<s:message code="userDashlet.dashboard"/> <form:errors path="dashboard" cssClass="error" />
	</form:label><br>
	<form:select data-type="select2" path="dashboard" items="${referenceData.dashboards}" multiple="false"
		itemLabel="viewName" itemValue="id" cssStyle="width:300px" /><br><br>

	<form:label path="dashlet">
		<s:message code="userDashlet.dashlet"/> <form:errors path="dashlet" cssClass="error" />
	</form:label><br>
	<form:select data-type="select2" path="dashlet" items="${referenceData.dashlets}" multiple="false"
		itemLabel="title" itemValue="id" cssStyle="width:300px" /><br><br>

	<form:label path="columnIndex"><s:message code="userDashlet.columnIndex"/> <form:errors path="columnIndex" cssClass="error" /></form:label>
	<form:input path="columnIndex" />

	<form:label path="displaySequence"><s:message code="userDashlet.displaySequence"/> <form:errors path="displaySequence" cssClass="error" /></form:label>
	<form:input path="displaySequence" />

	<form:hidden path="id" />

</fieldset>