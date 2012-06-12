<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="header">
<div class="box">
	<a href="/"><img src="<c:url value="/resources/images/logo.png" />" alt="logo"
		width="205" height="57" /></a>
</div>
</div>

<div id="header-bar">
	<c:choose>
	<c:when test="${empty menu}">
		<div class="box"><ul id="nav"><li class="current"><a href="/home">Home</a></li></ul></div>
	</c:when>
	<c:otherwise>
	
		<c:forEach var="userDashlet" items="${menu.userDashlets}">
			<div class="box" id="dashlet_content_${userDashlet.id}"></div>
			<script type="text/javascript">
		        $(document).ready(function()
		        	{ $('#dashlet_content_${userDashlet.id}').load('/dashboard/${userDashlet.id}');});
		    </script>
		</c:forEach>
	
	</c:otherwise>
	</c:choose>	
</div>

<div id="header-bar2"></div>