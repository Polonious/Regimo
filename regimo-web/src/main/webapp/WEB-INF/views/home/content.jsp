<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!--[if IE]>
     <style type="text/css">
         .timer { display: none !important; }
         div.caption { background:transparent; filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000,endColorstr=#99000000);zoom: 1; }
    </style>
<![endif]-->
<style>
.contentWrapper {

	background: white;
	padding-bottom: 40px;
}

.signupWrapper .container {
	padding-bottom: 20px;
}

.signupWrapper .container span {
	display: block;
	letter-spacing: 1px;
	color: #000;
	padding: 20px 0;
	border-bottom: 1px solid #ddd;
}

#mainBit {
	color: white;
}

#mainBit h1 {
	margin-bottom: 15px;
	font-size: 36px;
	color: white;
}

#mainBit h2 {
	margin-bottom: 28px;
	line-height: 30px;
	font-size: 19px;
	font-weight: normal !important;
	color: white;
}

#mainBit h3 {
	font-weight: normal !important;
	font-size: 18px;
	color: white;
}

#mainBit p {
	font-size: 14px;
	font-weight: 300;
	color: #ccebf6;
}

#mainBit p a {
	color: #ccebf6;
}

#mainBit p a:hover {
	text-decoration: underline;
}

#mainBit .feature h2 {
	margin-bottom: 0;
}

#boxes h2 {
	font-size: 16px;
}

#boxes p {
	color: #797f85
}

#mainBit {
	width: 890px;
	margin-bottom: 30px;
	padding: 35px 0 13px 30px;
	text-align: center;
	background: #00A3D3;
	border-radius: 3px;
}

#mainBit h2 {
	margin-bottom: 24px;
}

.features {
	text-align: left;
	padding-bottom: 10px;
}

.feature {
	display: inline-block;
	zoom: 1;
	*display: inline;
	_height: 0px;
	vertical-align: top;
	width: 199px;
	margin-right: 20px;
}

.feature:hover a a {
	text-decoration: underline;
}

.feature p {
	margin: 0;
}

.feature img {
	margin-bottom: 3px;
}

#template_1412 #mainBit .features h2 {
	margin-bottom: 2px;
}

#signup {
	width: 920px;
	margin: 0 auto 30px auto;
	text-align: center;
}

#signup a.greenButton {
	height: 40px !important;
	line-height: 42px !important;
	-moz-box-shadow: 0 1px 0px #ccc !important;
	box-shadow: 0 1px 0px #ccc !important;
	margin-bottom: 10px;
}


#signup2 {
	
	width: 920px;
	margin: 3em auto 30px auto;
	text-align: center;
}


.boxes {
	
	margin-bottom: 20px;
	border-top: 1px solid #e8ebee;
}

.boxes h2 {
	font-family: Arial, Helvetica, Sans-serif;
	font-size: 16px;
}

#col1 .box {
	padding-left: 0;
}

#col2 {
	width: 306px;
}

#col2 .box {
	border-right: 1px solid #e8ebee;
	border-left: 1px solid #e8ebee;
}

#col3 {
	width: 299px;
}
#col3 .box {
	padding-right: 0;
}

.column {
	display: inline-block;
	zoom: 1;
	*display: inline;
	_height: 0px;
	vertical-align: top;
	width: 307px;
}

.column .box {
	height: 65px;
	padding: 20px;
	border-bottom: 1px solid #e8ebee;
}


</style>

	<div class="contentWrapper">


		<section class="signupWrapper">
			<div class="container">
				<span>FEATURE TOUR</span>
			</div>
		</section>


		<!-- MAIN BIT -->

		<section id="mainBit">
			<h1>ALL YOU NEED TO RUN YOUR CASES</h1>
			<h2>
				Try the world's best case management software and you'll see why<br />
				so many people around the world love Polonious
			</h2>
			<div class="features">
			<c:forEach var="a" items="${feature}">
				<div class="feature">
					<a title="${a.title}"
						href="content/article/${a.slug}">
						<p>
							<img src="${a.imageUrl}" width="200" 
								alt="${a.title}" />
						</p>
						<h2>${a.title}</h2>
						<p>${a.summary}</p>
						<p>
							<a title="${a.title}" href="content/article/${a.slug}">Learn more</a>
						</p>
					</a>
				</div>
			</c:forEach>
			</div>
		</section>

		<!-- MIDDLE Booking bit -->

		<section id="signup">
			<a href="/signup/" data-gacategory="Features Page Links"
				data-galabel="Try Polonious for free - TOP"><a class="button"
				href="/bookAConsultation">Book a consultation</a>
				<p>
					<a href="/content/article/getting-started/"
						data-gacategory="Features Page Links"
						data-gaaction="Internal Pagelink" data-galabel="Getting started">Getting
						started</a> is easy &amp; you can do it now
				</p>
		</section>


		<!-- BOXES -->

		<c:forEach var="userDashlet" items="${content.userDashlets}">
			<div id="dashlet_content_${userDashlet.id}"></div>
				<script type="text/javascript">
			        $(document).ready(function() { $('#dashlet_content_${userDashlet.id}').load('content/dashlet/${userDashlet.id}'); });
			    </script>
		</c:forEach>


		<section id="signup2">
			<a href="/signup/" data-gacategory="Features Page Links"
				data-galabel="Try Polonious for free - TOP"><a class="button"
				href="/bookAConsultation">Book a consultation</a>
				<p>
					<a href="/content/article/getting-started/"
						data-gacategory="Features Page Links"
						data-gaaction="Internal Pagelink" data-galabel="Getting started">Getting
						started</a> is easy &amp; you can do it now
				</p>
		</section>



	</div>
