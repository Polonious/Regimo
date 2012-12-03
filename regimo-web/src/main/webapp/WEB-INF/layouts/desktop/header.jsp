<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="header">
<div class="cbox">
	<a href="/content/article/we-listen-design-innovate"><img id="logo" src="<c:url value="/resources/images/logo.png" />" alt="logo" /></a>
		<img id="trusted" src="<c:url value="/resources/images/bg_image_new_5-14-trusted.png" />" />
</div>
</div>

<div id="header-bar">
	<c:choose>
	<c:when test="${empty menu}">
		<div class="cbox"><ul id="nav"><li class="current"><a href="/home">Home</a></li></ul></div>
	</c:when>
	<c:otherwise>

		<c:forEach var="userDashlet" items="${menu.userDashlets}">
			<div class="cbox" id="dashlet_content_${userDashlet.id}"></div>
			<script type="text/javascript">
		        $(document).ready(function()
		        	{ $('#dashlet_content_${userDashlet.id}').load('/content/dashlet/${userDashlet.id}');});
		    </script>
		</c:forEach>

	</c:otherwise>
	</c:choose>
</div>

<div id="header-bar2"></div>