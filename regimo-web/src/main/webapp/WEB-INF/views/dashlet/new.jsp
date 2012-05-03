<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<s:url var="actionUrl" value="/dashlet/new"/>
<form:form action="${actionUrl}" modelAttribute="entity" method="post">
	<div class="formInfo">
  		<h2>Create dashlet</h2>
  		<s:bind path="*"><c:if test="${status.error}">
  		<div class="error">Unable to create. Please fix the errors below and resubmit.</div>
  		</c:if></s:bind>
	</div>
	<jsp:include page="entry.jsp"/>
	<p><button type="submit">Create</button></p>
</form:form>