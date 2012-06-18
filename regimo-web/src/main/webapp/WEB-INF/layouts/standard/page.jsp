<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>
<html>
<head>
	<title><tiles:insertAttribute name="title" defaultValue="Regimo" /></title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />	
    <meta name="description" content="Regimo" />
    <meta name="keywords" content="Regimo" />
	<link rel="stylesheet" href="<c:url value="/resources/page.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/wordpress.css" />" type="text/css" media="screen" />
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
</head>
<body>
  	<div id="header-container">
		<tiles:insertAttribute name="header" />
	</div>
	<div id="content-container">
		<tiles:insertAttribute name="content" />
	</div>
	<div id="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>