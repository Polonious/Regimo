<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>
<html>
<head>
	<title><tiles:insertAttribute name="title" defaultValue="Polonious" /></title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;" />
	<link rel="stylesheet" href="<c:url value="/resources/mobile/page.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/wordpress.css" />" type="text/css" media="screen" />

	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.css" />
	<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
	<script src="http://code.jquery.com/mobile/1.2.0/jquery.mobile-1.2.0.min.js"></script>

	<script type="text/javascript">
		window.scrollTo(0, 1);
	</script>
	<tiles:useAttribute id="styles" name="styles" classname="java.util.List" ignore="true" /><c:forEach var="style" items="${styles}">
	<link rel="stylesheet" type="text/css" href="${style}" /></c:forEach>
</head>
<body>
	<div data-role="page" id="page">
	<div id="header" class="header">
		<tiles:insertAttribute name="header" />
	</div>
	<div id="content" data-role="content">
		<tiles:insertAttribute name="content" />
	</div>

	<sec:authorize access="isAuthenticated()">
	<div id="nav">
		<ul>
			<li><a href="<c:url value="/signout" />" data-role="button" data-transition="pop">Sign Out</a></li>
		</ul>
	</div>
	</sec:authorize>
	<div id="footer">
		<tiles:insertAttribute name="footer" />
	</div>
	</div>
	<tiles:useAttribute id="scripts" name="scripts" classname="java.util.List" ignore="true" /><c:forEach var="script" items="${scripts}">
	<script type="text/javascript" src="${script}"></script></c:forEach>
</body>
</html>