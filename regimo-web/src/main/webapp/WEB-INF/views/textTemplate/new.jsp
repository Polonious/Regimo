<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<s:url var="actionUrl" value="/textTemplate/new"/>
<form:form action="${actionUrl}" modelAttribute="textTemplate" method="post">
	<div class="formInfo">
  		<h2><spring:message code="title.create"/> <spring:message code="textTemplate"/></h2>
  		<s:bind path="*"><c:if test="${status.error}">
  		<div class="error"><spring:message code="error.new"/>.</div>
  		</c:if></s:bind>
	</div>
	<jsp:include page="entry.jsp"/>
	<p><button type="submit"><spring:message code="button.create"/></button></p>
</form:form>