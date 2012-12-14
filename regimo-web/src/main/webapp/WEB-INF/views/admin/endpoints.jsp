<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="page-content">

<div id="viewPage">

<table id="endpointsTable" width="100%" data-table-config="source:'local'">
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