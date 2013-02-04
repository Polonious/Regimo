<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="page-content">
<h2><c:out value="${category.name}" escapeXml="false"/></h2>
<c:forEach var="post" items="${category.articles}">
	<c:url value="/content/article/${post.slug}" var="link" />
	<b><c:out value="${post.title}" escapeXml="false"/></b></br></br>
	<p><c:out value="${post.summary}" escapeXml="false"/></br></br>
	<a href="${link}" data-role="button" data-transition="slide" class="button"></a><br/><br></br><hr><br>
	</p>
</c:forEach>
</div>