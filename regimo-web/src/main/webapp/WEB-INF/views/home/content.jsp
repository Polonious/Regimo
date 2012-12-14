<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript">
      $(document).ready(function () {

          $('#featured').orbit({
     	     animation: 'horizontal-push',       // fade, horizontal-slide, vertical-slide, horizontal-push
     	     animationSpeed: 800,
     	     timer: true,
     	     advanceSpeed: 8000,
     	     pauseOnHover: true,
     	     startClockOnMouseOut: true,
     	     startClockOnMouseOutAfter: 1000,
     	     directionalNav: true, 		 		 // manual advancing directional navs
     	     captions: true, 			 		 // do you want captions?
     	     captionAnimation: 'fade', 		 	 // fade, slideOpen, none
     	     captionAnimationSpeed: 800,
     	     bullets: false,			 		 // true or false to activate the bullet navigation
     	     bulletThumbs: false,		 		 // thumbnails for the bullets
     	     bulletThumbLocation: '',		     // location from this file where thumbs will be
     	     afterSlideChange: function(){} 	 // custom action
     	});

      });
</script>

<!--[if IE]>
     <style type="text/css">
         .timer { display: none !important; }
         div.caption { background:transparent; filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000,endColorstr=#99000000);zoom: 1; }
    </style>
<![endif]-->

<div id="container">
		<div id="featured">
		<c:forEach var="a" items="${feature}">
			<div class="content" style="">
				<div class="orbit-feature-image"><a href="content/article/${a.slug}"><img src="${a.imageUrl}"/></a></div>
				<div class="orbit-feature-text">${a.summary}</div>
				<div><a href="/content/article/${a.slug}" class="orbit-button" alt="more"></a></div>
			</div>
		</c:forEach>
		</div>
		<!-- Captions for Orbit -->
		<span class="orbit-caption" id="htmlCaption"><strong>I'm A Badass Caption:</strong> I can haz <a href="#">links</a>, <em>style</em> or anything that is valid markup :)</span>
	</div>


	<div id="content">
    	<div id="content-box">

			<div class="content-header">
			<c:forEach var="userDashlet" items="${content.userDashlets}" varStatus="rowCounter">
				<div><h3 id="title${rowCounter.count}">${userDashlet.dashlet.title}</h3></div>
			</c:forEach>
			</div>

			<c:forEach var="userDashlet" items="${content.userDashlets}">
				<div class="content-column" id="dashlet_content_${userDashlet.id}"></div>
					<script type="text/javascript">
				        $(document).ready(function() { $('#dashlet_content_${userDashlet.id}').load('content/dashlet/${userDashlet.id}'); });
				    </script>
			</c:forEach>

		</div>
	</div>