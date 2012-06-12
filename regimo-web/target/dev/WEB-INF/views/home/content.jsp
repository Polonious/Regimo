<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript">
      $(document).ready(function () {
        $("#waterwheel-carousel-default").waterwheelCarousel({
			startingItem:               0,      // item to place in the center at the start, set to zero to be the middle item
			startingItemSeparation:     150,    // the starting separation distance between each item
			itemSeparationFactor:       0.5,     // determines how drastically the item separation decreases
			startingWaveSeparation:     30,     // the starting separation distance for the wave
			waveSeparationFactor:       0.75,    // determines how drastically the wave separation decreases
			itemDecreaseFactor:         0.8,     // determines how drastically the item's width and height decrease
			opacityDecreaseFactor:      1,     // determines how drastically the item's opacity decreases
			centerOffset:               20,     // the number of pixels to offset the center item in the carousel
			flankingItems:              4,      // the number of items visible on either side of the center
		});
		
      });
</script>

<div id="container">
        <div id="feature">
        	<!-- <div id="slogan">
                <div>At Polonious,
                </div>
                <div id="second-row">we feel your <span id="pain">pain</span>.
                </div>
            </div> -->
        </div> 
    
        <div id="waterwheel-carousel-default">
          <!-- <div class="carousel-controls">
            <div class="carousel-prev"><a href="#">&lt; previous</a></div>
            <div class="carousel-next"><a href="#">&gt; next</a></div>
          </div> -->
          <div class="carousel-images">
            <a href="/wp/post/open-source-is-the-answer">
            	<img border="0" width="460" height="290" src="<c:url value="/resources/images/carousel_image1_5-14.png" />" alt="Open Source is the Answer" />
            </a>
            <a href="/wp/post/single-point-risk">
            	<img border="0" width="460" height="290" src="<c:url value="/resources/images/carousel_image2_5-14.png" />" alt="Single Point Risk" />
            </a>
            <a href="/wp/post/now">
            	<img border="0" width="460" height="290" src="<c:url value="/resources/images/carousel_image3_5-14.png" />" alt="Now" />
            </a>
            <a href="/wp/post/regimo">
            	<img border="0" width="460" height="290" src="<c:url value="/resources/images/carousel_image5_5-14.png" />" alt="Regimo" />
            </a>
          </div>
        </div>    		
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