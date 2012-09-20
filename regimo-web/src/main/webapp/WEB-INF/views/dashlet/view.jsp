<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="page-content">

<div id="viewPage">
<p>
	<h3 class="alt">${entity.title}</h3>
	<fieldset>
		<b><s:message code="dashlet.column.type"/>:</b> ${entity.type}<br />
		<b><s:message code="dashlet.column.model"/>:</b> ${entity.model}<br />
		<b><s:message code="dashlet.column.parameter"/>:</b> ${entity.parameter}<br /><br />
		<b><s:message code="dashlet.column.content"/>:</b><br />
		<pre><c:out value="${entity.content}"/></pre>
	</fieldset>
	<br /><br />
	<s:url var="updateUrl" value="/dashlet/edit?id={id}"><s:param name="id" value="${entity.id}"/></s:url>
	<a href="${updateUrl}"><s:message code="button.edit"/></a>
</p>
</div>

</div>
<div class="push"></div>