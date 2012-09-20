<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="formBody">

	<form:hidden path="id" />
	<form:label path="title"><s:message code="dashlet.column.title"/> <form:errors path="title" cssClass="error" /></form:label>
	<form:input path="title" />
	<form:label path="type"><s:message code="dashlet.column.type"/><form:errors path="type" cssClass="error" /></form:label>
	<form:select path="type">
		<form:options items="${referenceData.dashletTypeOptions}" itemLabel="label" itemValue="value"/>
	</form:select>
	<form:label path="model"><s:message code="dashlet.column.model"/> <form:errors path="model" cssClass="error" /></form:label>
	<form:input path="model" />
	<form:label path="parameter"><s:message code="dashlet.column.parameter"/> <form:errors path="parameter" cssClass="error" /></form:label>
	<form:input path="parameter" />	
	<form:label path="content"><s:message code="dashlet.column.content"/> <form:errors path="content" cssClass="error" /></form:label>
	<form:textarea path="content" id="dashletContent" cssStyle="width:700px; height:500px;"/>

</div>