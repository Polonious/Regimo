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
			<div class="content" style="">
				<div class="orbit-feature-image"><a href="/wp/post/open-source-is-the-answer"><img src="<c:url value="/resources/images/carousel_image1_5-14.png" />" /></a></div>
				<div class="orbit-feature-text">
					As a business owner you know how hard you and your team work at growing your business in a competitive market place.
					<br/><br/>Each time you experience success, increased sales, market share, open an interstate branch how much do you budget for in software licence fees?
					<br/><br/>If you would like to know how...
				</div>
				<div><a href="/wp/post/open-source-is-the-answer" class="orbit-button" alt="more"></a></div>
			</div>
			<div class="content" style="">
				<div class="orbit-feature-image"><a href="/wp/post/single-point-risk"><img src="<c:url value="/resources/images/carousel_image2_5-14.png" />" /></a></div>
				<div class="orbit-feature-text">
					If you use technology in your organisation, you've most likely experienced technical debt creep.
					<br/><br/>
					For IT, it's the old software vulnerable to Internet attack; the cheap developer who's moved on;
					out-dated code no-one remembers; or unmaintained websites too expensive to change.
					<br/><br/>
					Polonious has saved thousands of dollars for...
				</div>
				<div><a href="/wp/post/single-point-risk" class="orbit-button" alt="more"></a></div>
			</div>
			<div class="content" style="">
				<div class="orbit-feature-image"><a href="/wp/post/now"><img src="<c:url value="/resources/images/carousel_image3_5-14.png" />" /></a></div>
				<div class="orbit-feature-text">
					You have the next brilliant idea for a iPhone, iPad or Android game that is going
					to set the world on fire? Or maybe your looking to develop a business application that is going to give you the
					competitive edge in you market?
					<br/><br/>
					You engage Polonious because we have a reputation in the software development market for...
				</div>
				<div><a href="/wp/post/now" class="orbit-button" alt="more"></a></div>
			</div>
			<div class="content" style="">
				<div class="orbit-feature-image"><a href="/wp/post/regimo"><img src="<c:url value="/resources/images/carousel_image5_5-14.png" />" /></a></div>
				<div class="orbit-feature-text">
					You already have a great looking website managed in WordPress, Drupal or Joomla but it is slow and
					unusable on a mobile phone? You would like it to load in a flash and look great on smartphones and
					tablets too without much effort? Regimo is what you are after.
					<br/><br/>You have a great business idea and need a web application to manage your clients, staff,
					products, and events as well as features...
				</div>
				<div><a href="/wp/post/regimo" class="orbit-button" alt="more"></a></div>
			</div>
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

	<div class="push"></div>