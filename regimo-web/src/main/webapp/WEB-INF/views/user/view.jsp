<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div id="page-content">

<div id="viewPage">
<p>
	<h3 class="alt">${user.username}</h3>
	<address>
		${user.firstName} ${user.lastName}
		<br />
		${user.email}
	</address>
	<a href="edit?id=${user.id}"><s:message code="button.edit"/></a>
</p>
</div>

</div>