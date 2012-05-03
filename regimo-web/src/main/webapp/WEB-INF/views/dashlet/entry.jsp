<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fieldset>
	<form:hidden path="id" />
	<form:label path="title">Title <form:errors path="title" cssClass="error" /></form:label>
	<form:input path="title" />
	<form:label path="type">Type <form:errors path="type" cssClass="error" /></form:label>
	<form:select path="type">
		<form:options items="${referenceData.dashletTypeOptions}" itemLabel="label" itemValue="value"/>
	</form:select>
	<form:label path="model">Model <form:errors path="model" cssClass="error" /></form:label>
	<form:input path="model" />
	<form:label path="parameter">Parameter <form:errors path="parameter" cssClass="error" /></form:label>
	<form:input path="parameter" />	
	<form:label path="content">Content <form:errors path="content" cssClass="error" /></form:label>
	<form:textarea path="content" id="dashletContent" cssStyle="width:400px; height:200px;"/>
</fieldset>