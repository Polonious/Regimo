<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="formBody">

	<form:label path="title">
		<s:message code="post.title"/> <form:errors path="title" cssClass="error" />
	</form:label>
	<form:input path="title" />

	<form:label path="slug">
		<s:message code="post.slug"/> <form:errors path="slug" cssClass="error" />
	</form:label>
	<form:input path="slug" />

	<form:label path="summary">
		<s:message code="post.summary"/> <form:errors path="summary" cssClass="error" />
	</form:label>
	<form:input path="summary" />

	<form:label path="detail">
		<s:message code="post.detail"/> <form:errors path="detail" cssClass="error" />
	</form:label>
	<form:textarea path="detail" data-dojo-type="dijit/Editor"/>

	<form:label path="categories">
		<s:message code="post.categories"/> <form:errors path="categories" cssClass="error" />
	</form:label><br>
	<form:select data-type="select2" path="categories" multiple="true" items="${referenceData.categories}" itemLabel="name" itemValue="id"
		cssStyle="width:300px" cssClass="populate"/>

	<form:hidden path="id" />

</div>