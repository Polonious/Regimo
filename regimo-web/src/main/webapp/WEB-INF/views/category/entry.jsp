<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="formBody">

	<form:label path="name">
		<s:message code="category.name"/> <form:errors path="name" cssClass="error" />
	</form:label>
	<form:input path="name" />

	<form:label path="slug">
		<s:message code="category.slug"/> <form:errors path="slug" cssClass="error" />
	</form:label>
	<form:input path="slug" />

	<form:hidden path="id" />

</div>