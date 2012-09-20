<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fieldset>
	<form:hidden path="id" />
	<form:label path="username"><s:message code="main.signin.username"/> <form:errors path="username" cssClass="error" /></form:label>
	<form:input path="username" />
	<c:if test="${empty entity.id}">
	<form:label path="password">Password (at least 6 characters) <form:errors path="password" cssClass="error" /></form:label>
	<form:password path="password" />
	</c:if>
	<form:label path="firstName"><s:message code="profile.edit.firstname"/> <form:errors path="firstName" cssClass="error" /></form:label>
	<form:input path="firstName" />
	<form:label path="lastName"><s:message code="profile.edit.lastname"/> <form:errors path="lastName" cssClass="error" /></form:label>
	<form:input path="lastName" />
	<form:label path="email"><s:message code="profile.edit.email"/> <form:errors path="email" cssClass="error" /></form:label>
	<form:input path="email" />
</fieldset>