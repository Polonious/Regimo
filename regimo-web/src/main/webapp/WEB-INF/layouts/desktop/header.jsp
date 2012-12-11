<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
<c:when test="${empty menu}">
	<div id="header">
		<div class="cbox">
			<a href="/content/article/we-listen-design-innovate"><img id="logo" src="<c:url value="/resources/images/logo.png" />" alt="logo" /></a>
			<img id="trusted" src="<c:url value="/resources/images/trusted-technologists.png" />" />
		</div>
	</div>
	<div id="header-bar">
		<div class="cbox"><ul id="nav"><li class="current"><a href="/home">Home</a></li></ul></div>
	</div>
</c:when>
<c:otherwise>
	<c:forEach var="userDashlet" items="${menu.userDashlets}">
		<script type="text/javascript">
	        $(document).ready(function(){
	        	$('#header-container').load('/content/dashlet/${userDashlet.id}');
	        });
	    </script>
	</c:forEach>
</c:otherwise>
</c:choose>

<div id="header-bar2"></div>