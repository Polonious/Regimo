<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<div class="box">
	<a href="/home"><img src="<c:url value="/resources/images/logo.png" />" alt=""
		width="129" height="36" /></a>
	
	<a href="/signin.html"><div class="header-signin"><span>Sign In</span></div></a>
	<a href="/signup.html"><div class="header-signup"><span>Sign Up</span></div></a>
					
</div>