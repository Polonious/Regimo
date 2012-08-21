<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="container">
        <div id="feature">
        </div> 
    
        <div id="waterwheel-carousel-default">
          <!-- <div class="carousel-controls">
            <div class="carousel-prev"><a href="#">&lt; previous</a></div>
            <div class="carousel-next"><a href="#">&gt; next</a></div>
          </div> -->
          <div class="carousel-images">
            <a href="/wp/post/open-source-is-the-answer"><img class="mobile" src="<c:url value="/resources/images/carousel_image1_5-14.png" />" alt="Open Source is the Answer" /></a>
            <a href="/wp/post/single-point-risk"><img class="mobile" src="<c:url value="/resources/images/carousel_image2_5-14.png" />" alt="Single Point Risk" /></a>
            <a href="/wp/post/now"><img class="mobile" src="<c:url value="/resources/images/carousel_image3_5-14.png" />" alt="Now" /></a>
            <a href="/wp/post/regimo"><img class="mobile" src="<c:url value="/resources/images/carousel_image5_5-14.png" />" alt="Regimo" /></a>
          </div>
        </div>    		
	</div>
    
    
      
	<div id="content">
    	<div id="content-box">

			<c:forEach var="userDashlet" items="${content.userDashlets}">
				<div>
				<c:if test="${userDashlet.dashlet.title!='About us'}"><a href="/wp/category/${userDashlet.dashlet.parameter}"></c:if>
				<c:if test="${userDashlet.dashlet.title=='About us'}"><a href="/wp/post/${userDashlet.dashlet.parameter}"></c:if>
				<h3 id="title${rowCounter.count}">${userDashlet.dashlet.title}</h3></a></div>
				<div class="content-column" id="dashlet_content_${userDashlet.id}"></div>
					<script type="text/javascript">
				        $(document).ready(function() { $('#dashlet_content_${userDashlet.id}').load('dashboard/${userDashlet.id}'); });
				    </script>
			</c:forEach>
            
		</div>  
	</div>
	<div class="push"></div>