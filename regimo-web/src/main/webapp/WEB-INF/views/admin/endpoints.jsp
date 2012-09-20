<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="page-content">

<div id="viewPage">

	<link rel="stylesheet" href="<c:url value="/resources/datatables/css/demo_page.css" />" type="text/css" />
	<link rel="stylesheet" href="<c:url value="/resources/datatables/css/demo_table.css" />" type="text/css" />
	<script type="text/javascript" language="javascript" src="<c:url value="/resources/datatables/jquery.dataTables.min.js" />"></script>
	<script type="text/javascript" charset="utf-8">
		$(document).ready(initDataTable);
		function initDataTable(){
			var oTable = $('#endpointsTable').dataTable({
				"sPaginationType": "full_numbers",
				"iDisplayLength": 25,
				"iTabIndex": 1
			});
		}
	</script>

<table id="endpointsTable" width="100%">
  <thead>
  <tr>
    <th>Path</th>
    <th>Methods</th>
    <th>Authority</th>
    <th>Parameters</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${endpoints}" var="endPoint" varStatus="status">
    <tr>
      <td>${endPoint.patternsCondition}</td>
      <td>${endPoint.methodsCondition}</td>
      <td><a href="/authority/edit?id=${authorities[status.index].id}">${authorities[status.index].name}</a></td>
      <td>${endPoint.paramsCondition}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</div>

</div>
<div class="push"></div>