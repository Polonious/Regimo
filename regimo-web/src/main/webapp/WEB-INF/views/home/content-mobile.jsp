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
        	<c:forEach var="a" items="${feature}">
			<a href="/content/article/${a.slug}"><img class="mobile" src="${a.imageUrl}" alt="${a.title}"/></a>
			</c:forEach>
          </div>
        </div>
	</div>

	<div id="content2">
    	<div id="content-box">

			<c:forEach var="userDashlet" items="${content.userDashlets}">
				<div>
				<a href="/content/${userDashlet.dashlet.model=='articles'?'category':'article'}/${userDashlet.dashlet.parameter}">
				<h3 id="title${rowCounter.count}">${userDashlet.dashlet.title}</h3></a></div>
				<div class="content-column" id="dashlet_content_${userDashlet.id}"></div>
					<script type="text/javascript">
				        $(document).ready(function() { $('#dashlet_content_${userDashlet.id}').load('content/dashlet/${userDashlet.id}'); });
				    </script>
			</c:forEach>

		</div>
	</div>
	<div class="push"></div>