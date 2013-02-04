<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="page-content">

<div id="viewPage">
<p>
	<h3 class="alt">${dashlet.title}</h3>
	<fieldset>
		<b><s:message code="dashlet.type"/>:</b> ${dashlet.type}<br />
		<b><s:message code="dashlet.model"/>:</b> ${dashlet.model}<br />
		<b><s:message code="dashlet.parameter"/>:</b> ${dashlet.parameter}<br /><br />
		<b><s:message code="dashlet.content"/>:</b><br />
		<pre><c:out value="${dashlet.content}"/></pre>
	</fieldset>
	<br /><br />
	<a href="edit?id=${dashlet.id}"><s:message code="button.edit"/></a>
</p>
</div>

</div>