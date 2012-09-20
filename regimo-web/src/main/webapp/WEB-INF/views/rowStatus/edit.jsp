<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="page-content">

<s:url var="actionUrl" value="/rowStatus/edit"/>
<form:form action="${actionUrl}" modelAttribute="rowStatus" method="post">
	<div class="formInfo">
	<legend><spring:message code="title.edit"/> <spring:message code="rowStatus"/></legend>
	<s:bind path="*">
		<c:if test="${status.error}">
			<div class="error"><spring:message code="error.edit"/></div>
		</c:if>
	</s:bind>
	</div>
	<jsp:include page="entry.jsp"/>
	<p><button type="submit"><spring:message code="button.save"/></button></p>
</form:form>

</div>
<div class="push"></div>