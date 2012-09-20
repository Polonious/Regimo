<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="formBody">

	<form:label path="name">
		<s:message code="textTemplate.name"/> <form:errors path="name" cssClass="error" />
	</form:label>
	<form:input path="name" />

	<form:label path="category">
		<s:message code="textTemplate.category"/> <form:errors path="category" cssClass="error" />
	</form:label>
	<form:input path="category" />

	<form:label path="model">
		<s:message code="textTemplate.model"/> <form:errors path="model" cssClass="error" />
	</form:label>
	<form:input path="model" />

	<form:label path="content">
		<s:message code="textTemplate.content"/> <form:errors path="content" cssClass="error" />
	</form:label>
	<form:textarea path="content" id="dashletContent" cssStyle="width:700px; height:500px;"/>

	<form:hidden path="id" />

</div>