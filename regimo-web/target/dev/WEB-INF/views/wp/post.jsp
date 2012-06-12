<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="page-content">
<h2><c:out value="${post.postTitle}" escapeXml="false"/></h2>
<p class="TrueNotes">
	<c:out value="${post.postContent}" escapeXml="false"/>
</p>
</div>
<div class="push"></div>