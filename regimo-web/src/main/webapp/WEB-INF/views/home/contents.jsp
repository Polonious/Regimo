<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.17/themes/smoothness/jquery-ui.css" type="text/css" />
<c:choose><c:when test="${currentDevice.mobile}">
<link rel="stylesheet" href="<c:url value="/resources/dashboard/dashboard-mobile.css" />" type="text/css" media="screen" />
</c:when><c:otherwise>
<link rel="stylesheet" href="<c:url value="/resources/dashboard/dashboard.css" />" type="text/css" media="screen" />
</c:otherwise></c:choose>
<script type="text/javascript" src="http://code.jquery.com/ui/1.8.17/jquery-ui.min.js"></script>
<script type="text/javascript" src="<c:url value="/resources/dashboard/dashboard.js" />"></script>

<div class="demo"><c:set var="columnWidth" value="${fn:split(dashboard.columnWidth, ',')}" />
	<c:forEach var="i" begin="1" end="${dashboard.columnCount}" step="1"><c:set var="cWidth" value="320" /><c:if test="${!currentDevice.mobile}"><c:set var="cWidth" value="${columnWidth[(i-1)]}" /></c:if>
	<div class="column" style="width:${cWidth}px;">
		<c:forEach var="userDashlet" items="${dashboard.userDashlets}"><c:if test="${userDashlet.columnIndex==i}">
		<div class="dashlet">
			<div class="dashlet-header">${userDashlet.dashlet.title}</div>
			<div class="dashlet-content" id="dashlet_content_${userDashlet.id}"></div>
			<script type="text/javascript">
		        $(document).ready(function() { $('#dashlet_content_${userDashlet.id}').load('contents/${userDashlet.id}'); });
		    </script>
		</div>
		</c:if></c:forEach>
	</div>
	</c:forEach>
</div>

