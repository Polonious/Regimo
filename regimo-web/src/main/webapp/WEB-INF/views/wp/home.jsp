<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="page-content">
<c:forEach var="menuitem" items="${cagegory}">
	<c:url value="/wp/category/${menuitem.slug}" var="link" />
	<a href="${link}" data-role="button" data-transition="pop"><c:out value="${menuitem.name}" escapeXml="false"/></a><br>
</c:forEach>
</div>
<div class="push"></div>
