<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="formBody">

	<form:label path="language">
		<s:message code="appLocale.language"/> <form:errors path="language" cssClass="error" />
	</form:label>
	<form:input path="language" />

	<form:label path="country">
		<s:message code="appLocale.country"/> <form:errors path="country" cssClass="error" />
	</form:label>
	<form:input path="country" />

	<form:label path="variant">
		<s:message code="appLocale.variant"/> <form:errors path="variant" cssClass="error" />
	</form:label>
	<form:input path="variant" />

    <table id="messages">
      <thead>
	    <tr>
	        <th>Key</th>
	        <th>Value</th>
	    </tr>
	  </thead>
	    <c:forEach items="${appLocale.messages}" var="message" varStatus="status">
	        <tr>
	            <td><input value="${message.key}" name="messagesName${status.index}"/></td>
	            <td><input value="${message.value}" name="messagesValue${status.index}"/></td>
	        </tr>
	    </c:forEach>
	</table>

	<form:hidden path="id" />

	<br><br><p><a href="javascript:void(0);" onclick="fnClickAddRow();">Add a new row</a>. <i>Notes: Message will be removed if key is empty!</i></p>
	<p><br><label>Import messages from Property file:</label><div id="docUploader"></div></p>
	<p><br><a href="export?id=${appLocale.id}" target="_blank">Export messages</a></p>

</div>
