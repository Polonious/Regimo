<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="page-content">
<div id="browsePage">
	<h2><spring:message code="profile.browse.title"/></h2>

	<a href='<s:url value="/user/new"/>'><spring:message code="button.new"/></a>
	
	<link rel="stylesheet" href="<c:url value="/resources/datatables/css/demo_page.css" />" type="text/css" />
	<link rel="stylesheet" href="<c:url value="/resources/datatables/css/demo_table.css" />" type="text/css" />
	<script type="text/javascript" language="javascript" src="<c:url value="/resources/datatables/jquery.dataTables.min.js" />"></script>
	<script type="text/javascript" language="javascript" src="<c:url value="/resources/datatables/dataTables.utils.js" />"></script>
	<script type="text/javascript" charset="utf-8">
		$(document).ready(initDataTable);
		function initDataTable(){
			var oTable = $('#example').dataTable( {
				"bProcessing": true,
				"bServerSide": true,
				"bStateSave": true,
				"sPaginationType": "full_numbers",
				"sAjaxSource": "browse",
				"fnServerData": _getServerData,
				"aoColumns": [
				              { "mDataProp": "username" },
				              { "mDataProp": "firstName" },
				              { "mDataProp": "lastName" },
				              { "mDataProp": "email" },
				              { "mDataProp": "id",
				            	"bUseRendered": false, 
				            	"bSortable": false,
				            	"fnRender": standardViewUpdateAction }
				          ]
			} );
		}
	</script>
		
	<div class="ex_highlight_row">
		<table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
			<thead>
				<tr>
					<th width="20%"><spring:message code="profile.edit.username"/></th>
					<th width="25%"><spring:message code="profile.edit.firstname"/></th>
					<th width="25%"><spring:message code="profile.edit.lastname"/></th>
					<th width="15%"><spring:message code="profile.edit.email"/></th>
					<th width="10%"><spring:message code="button.action"/></th>
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