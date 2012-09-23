<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<div id="page-content">

<f:form modelAttribute="${modelName}" method="post">
	<div class="formInfo">
  		<h2><s:message code="label.create"/> <s:message code="${modelName}"/></h2>
  		<s:bind path="*"><c:if test="${status.error}">
  		<div class="error"><s:message code="error.new"/>.</div>
  		</c:if></s:bind>
	</div>
	<tiles:insertAttribute name="entry" />
	<p><button type="submit"><s:message code="button.create"/></button>
		<a href="../${modelName}"><s:message code="label.return"/></a>
	</p>
</f:form>

</div>
<div class="push"></div>