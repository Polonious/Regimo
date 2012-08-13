<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript">
      $(document).ready(function () {

          $('#featured').orbit({
     	     animation: 'horizontal-push',                  // fade, horizontal-slide, vertical-slide, horizontal-push
     	     animationSpeed: 800,                // how fast animtions are
     	     timer: true, 			 // true or false to have the timer
     	     advanceSpeed: 4000, 		 // if timer is enabled, time between transitions 
     	     pauseOnHover: false, 		 // if you hover pauses the slider
     	     startClockOnMouseOut: false, 	 // if clock should start on MouseOut
     	     startClockOnMouseOutAfter: 1000, 	 // how long after MouseOut should the timer start again
     	     directionalNav: true, 		 // manual advancing directional navs
     	     captions: true, 			 // do you want captions?
     	     captionAnimation: 'fade', 		 // fade, slideOpen, none
     	     captionAnimationSpeed: 800, 	 // if so how quickly should they animate in
     	     bullets: false,			 // true or false to activate the bullet navigation
     	     bulletThumbs: false,		 // thumbnails for the bullets
     	     bulletThumbLocation: '',		 // location from this file where thumbs will be
     	     afterSlideChange: function(){} 	 // empty function 
     	});

      });
</script>

<div id="container">
		<div id="featured"> 
			<div class="content" style="">
				<div class="orbit-feature-image"><a href="/wp/post/open-source-is-the-answer"><img src="<c:url value="/resources/images/carousel_image1_5-14.png" />" /></a></div>				
				<div class="orbit-feature-text">As a business owner you know how hard you and your team work at growing your business in a competitive market place. 
					Each time you experience success, increased sales, market share, open an interstate branch how much do you budget for in software licence fees? If you would like to know how... 
					<br/><br/><a href="/wp/post/open-source-is-the-answer" class="orbit-button" alt="more"></a>
				</div>
			</div>
			<div class="content" style="">
				<div class="orbit-feature-image"><a href="/wp/post/single-point-risk"><img src="<c:url value="/resources/images/carousel_image2_5-14.png" />" /></a></div>				
				<div class="orbit-feature-text">If you use technology in your organisation, you've most likely experienced technical debt creep.
					<br/><br/>
					For IT, it's the old software vulnerable to Internet attack; the cheap developer who's moved on; out-dated code no-one remembers; or unmaintained websites too expensive to change.
					<br/><br/>
					Polonious has saved thousands of dollars for... 
					<br/><br/><a href="/wp/post/single-point-risk" class="orbit-button" alt="more"></a>
				</div>
			</div>
			<div class="content" style="">
				<div class="orbit-feature-image"><a href="/wp/post/now"><img src="<c:url value="/resources/images/carousel_image3_5-14.png" />" /></a></div>				
				<div class="orbit-feature-text">You have the next brilliant idea for a iPhone, iPad or Android game that is going 
					to set the world on fire? Or maybe your looking to develop a business application that is going to give you the 
					competitive edge in you market? 
					<br/><br/>
					You engage Polonious because we have a reputation in the software development market for...
					<br/><br/><a href="/wp/post/now" class="orbit-button" alt="more"></a>
				</div>
			</div>
			<img src="#" width="950px" height="310px" />
		</div>
		<!-- Captions for Orbit -->
		<span class="orbit-caption" id="htmlCaption"><strong>I'm A Badass Caption:</strong> I can haz <a href="#">links</a>, <em>style</em> or anything that is valid markup :)</span>
    
        <!-- div id="waterwheel-carousel-default">
          <<div class="carousel-controls">
            <div class="carousel-prev"><a href="#">&lt; previous</a></div>
            <div class="carousel-next"><a href="#">&gt; next</a></div>
          </div>
          <div class="carousel-images">
            <a href="/wp/post/single-point-risk">
            	<img border="0" width="460" height="290" src="<c:url value="/resources/images/carousel_image2_5-14.png" />" alt="Single Point Risk" />
            </a>
            <a href="/wp/post/open-source-is-the-answer">
            	<img border="0" width="460" height="290" src="<c:url value="/resources/images/carousel_image1_5-14.png" />" alt="Open Source is the Answer" />
            </a>
            <a href="/wp/post/now">
            	<img border="0" width="460" height="290" src="<c:url value="/resources/images/carousel_image3_5-14.png" />" alt="Now" />
            </a>
            <a href="/wp/post/regimo">
            	<img border="0" width="460" height="290" src="<c:url value="/resources/images/carousel_image5_5-14.png" />" alt="Regimo" />
            </a>
          </div>
        </div>   -->  		
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
				        $(document).ready(function() { $('#dashlet_content_${userDashlet.id}').load('dashboard/${userDashlet.id}'); });
				    </script>
			</c:forEach>
            
		</div>  
	</div> 
	
	<div class="push"></div>