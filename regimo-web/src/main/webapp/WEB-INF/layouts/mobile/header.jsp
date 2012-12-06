<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
<c:when test="${empty menu}">
<div class="box">
	<a href="/home"><img src="<c:url value="/resources/images/logo.png" />" alt=""
		width="129" height="36" /></a>

	<a href="/signin.html"><div class="header-signin"><span>Sign In</span></div></a>
	<a href="/signup.html"><div class="header-signup"><span>Sign Up</span></div></a>

</div>
</c:when>
<c:otherwise>
	<c:forEach var="userDashlet" items="${menu.userDashlets}">
		<script type="text/javascript">
	        $(document).ready(function()
	        	{ $('#header').load('/content/dashlet/${userDashlet.id}');});
	    </script>
	</c:forEach>
</c:otherwise>
</c:choose>
