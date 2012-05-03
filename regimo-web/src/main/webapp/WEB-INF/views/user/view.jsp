<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div id="viewPage">
<p>
	<h3 class="alt">${entity.username}</h3>
	<address>
		${entity.firstName} ${entity.lastName}  
		<br />
		${entity.email}
	</address>
	<s:url var="updateUrl" value="/user/edit/{id}"><s:param name="id" value="${entity.id}"/></s:url>
	<a href="${updateUrl}">Edit</a> 
</p>
</div>