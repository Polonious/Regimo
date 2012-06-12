<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1><a title="Regimo" href="<c:url value="/" />"><img src="<c:url value="/resources/logo-header.png" />" alt="Regmio" /></a></h1>
<div id="nav">
	<ul>
		<sec:authorize access="isAnonymous()">
		<li><a href="<c:url value="/signin" />">Sign In</a></li>
		<li><a href="<c:url value="/signup" />">Join Now</a></li>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
		<li><a href="<c:url value="/invite" />">Invite</a></li>
		<li><a href="<c:url value="/events" />">Events</a></li>
		<li><a href="<c:url value="/develop/apps" />">Develop</a></li>
		<li><a href="<c:url value="/settings" />">Settings</a></li>
		<li><a href="<c:url value="/signout" />">Sign Out</a></li>
		</sec:authorize>
	</ul>
</div>
