<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<s:url var="actionUrl" value="/user/new"/>
<f:form action="${actionUrl}" modelAttribute="entity" method="post">
	<div class="formInfo">
  		<h2><s:message code="user.create.title"/></h2>
  		<s:bind path="*">
  			<c:if test="${status.error}">
  				<div class="error"><s:message code="dashlet.new.error"/>.</div>
  			</c:if>
  		</s:bind>
	</div>
	<jsp:include page="entry.jsp"/>
	<p><button type="submit"><s:message code="button.create"/></button>
		<a href="browse"><s:message code="title.return"/></a>
	</p>
</f:form>

<div class="push"></div>