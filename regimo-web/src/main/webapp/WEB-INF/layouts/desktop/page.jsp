<%@ page contentType="text/html; charset=UTF-8" pageEncoding="iso-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>
<html>
<head>
	<title><tiles:insertAttribute name="title" defaultValue="Polonious" /></title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	<link rel="stylesheet" href="<c:url value="/resources/wordpress.css" />" type="text/css" media="screen" />
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" type="text/css" />
	<link rel="stylesheet" href="<c:url value="/resources/css/dropdown-menu.css" />" type="text/css" />
	<link rel="stylesheet" href="<c:url value="/resources/css/waterwheel-carousel.css" />" type="text/css" />
	<link rel="stylesheet" href="<c:url value="/resources/orbit/orbit-1.2.3.css" />" type="text/css" />
	<link href="/resources/jquery/select2/select2.css" rel="stylesheet" type="text/css"/>

	<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<c:url value="/resources/waterwheelCarousel/jquery.waterwheelCarousel.min.js" />"></script>
	<script type="text/javascript" src="/resources/orbit/jquery.orbit-1.2.3.min.js"></script>
</head>

<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-10992736-4']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>

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
	<script type="text/javascript" src="/resources/jquery/select2/select2.min.js"></script>
	<script type="text/javascript" src="/resources/jquery/html5breadcrumbs.js"></script>
</html>