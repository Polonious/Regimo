<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div id="page-content">
<div id="browsePage">
	<h2><s:message code="label.browse"/> <s:message code="${modelName}"/></h2>

	<a href='../${modelName}/new'><s:message code="button.new"/></a>

	<div class="ex_highlight_row">
		<table cellpadding="0" cellspacing="0" border="0" class="display" 
				data-table-config="columns:'${datatable.sColumns}', action:'${datatable.action}'">
			<thead>
				<tr>
					<c:forEach items="${datatable.columnDefs}" var="column">
						<th width="${column.sWidth}"><s:message code="${column.mDataProp}"/></th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="5" class="dataTables_empty">Loading data ...</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
</div>
<div class="push"></div>