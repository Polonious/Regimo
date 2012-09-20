<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<div id="page-content">

<s:url var="actionUrl" value="/dashlet/edit"/>
<f:form action="${actionUrl}" modelAttribute="entity" method="post">
	<div class="formInfo">
	<legend><s:message code="dashlet.edit.title"/></legend>
	<s:bind path="*">
		<c:if test="${status.error}">
			<div class="error"><s:message code="error.new"/></div>
		</c:if>
	</s:bind>
	</div>
	<jsp:include page="entry.jsp"/>
	<p><button type="submit"><s:message code="button.save"/></button>
		<a href="browse"><s:message code="title.return"/></a>
	</p>
</f:form>

</div>
<div class="push"></div>