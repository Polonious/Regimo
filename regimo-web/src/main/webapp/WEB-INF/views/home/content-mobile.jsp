<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
            <a href="/wp/post/open-source-is-the-answer"><img src="<c:url value="/resources/images/carousel_image1_m.png" />" alt="Open Source is the Answer" /></a>
            <a href="/wp/post/single-point-risk"><img src="<c:url value="/resources/images/carousel_image2_m.png" />" alt="Single Point Risk" /></a>
            <a href="/wp/post/now"><img src="<c:url value="/resources/images/carousel_image3_m.png" />" alt="Now" /></a>
            <a href="/wp/post/start-up"><img src="<c:url value="/resources/images/carousel_image4_m.png" />" alt="Start Up" /></a>
            <a href="/wp/post/regimo"><img src="<c:url value="/resources/images/carousel_image5_m.png" />" alt="Regimo" /></a>
          </div>
        </div>    		
	</div>
    
    
      
	<div id="content">
    	<div id="content-box">

			<c:forEach var="userDashlet" items="${content.userDashlets}">
				<div><h3 id="title${rowCounter.count}">${userDashlet.dashlet.title}</h3></div>
				<div class="content-column" id="dashlet_content_${userDashlet.id}"></div>
					<script type="text/javascript">
				        $(document).ready(function() { $('#dashlet_content_${userDashlet.id}').load('dashboard/${userDashlet.id}'); });
				    </script>
			</c:forEach>
            
		</div>  
	</div> 