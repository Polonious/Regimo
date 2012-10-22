<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<div id="page-content">

<f:form id="mainForm" modelAttribute="${modelName}" method="post">
	<div id="formInfo" class="formInfo">
	<legend><s:message code="label.edit"/> <s:message code="${modelName}"/></legend>
	<s:bind path="*">
		<c:if test="${status.error}">
			<div class="error"><s:message code="error.edit"/></div>
		</c:if>
	</s:bind>
	</div>
	<tiles:insertAttribute name="entry" />
	<p><button type="submit"><s:message code="button.save"/></button>
		<a href="../${modelName}"><s:message code="label.return"/></a>
	</p>
</f:form>

</div>
<div class="push"></div>