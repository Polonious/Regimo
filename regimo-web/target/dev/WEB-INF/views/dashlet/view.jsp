<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="viewPage">
<p>
	<h3 class="alt">${entity.title}</h3>
	<fieldset>
		<b><spring:message code="dashlet.column.type"/>:</b> ${entity.type}<br />
		<b><spring:message code="dashlet.column.model"/>:</b> ${entity.model}<br /> 
		<b><spring:message code="dashlet.column.parameter"/>:</b> ${entity.parameter}<br /><br />
		<b><spring:message code="dashlet.column.content"/>:</b><br />
		<span class="TrueNotes"><c:out value="${entity.content}"/></span>
	</fieldset>
	<br /><br />
	<s:url var="updateUrl" value="/dashlet/edit/{id}"><s:param name="id" value="${entity.id}"/></s:url>
	<a href="${updateUrl}"><spring:message code="button.edit"/></a> 
</p>
</div>