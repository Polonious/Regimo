<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<s:url var="actionUrl" value="/authority/new"/>
<f:form action="${actionUrl}" modelAttribute="authority" method="post">
	<div class="formInfo">
  		<h2><s:message code="title.create"/> <s:message code="authority"/></h2>
  		<s:bind path="*"><c:if test="${status.error}">
  		<div class="error"><s:message code="error.new"/>.</div>
  		</c:if></s:bind>
	</div>
	<jsp:include page="entry.jsp"/>
	<p><button type="submit"><s:message code="button.create"/></button>
		<a href="browse"><s:message code="title.return"/></a>
	</p>
</f:form>