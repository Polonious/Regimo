<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="page-content">

<div id="viewPage">

<table id="endpointsTable" width="100%" data-table-config="source:'local'">
  <thead>
  <tr>
    <th>Filename</th>
    <th>Content Type</th>
    <th>Size</th>
    <th>Preview</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="doc" items="${documents}">
    <tr>
      <td><a target="_blank" href="/document/${doc.id}/${doc.filename}?download">${doc.filename}</a></td>
      <td>${doc.contentType}</td>
      <td>${doc.length}</td>
      <!-- <td>${doc.metaData.get('userId')}</td> -->
      <td>
      	<c:if test="${fn:startsWith(doc.contentType, 'image')}">
      	<a target="_blank" href="/document/${doc.id}/${doc.filename}">
      		<img style="max-width:200px;" src="/document/${doc.id}/${doc.filename}"/>
      	</a>
      	</c:if>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<br/>
<br/>
<div id="imageDiv"></div>
<br/>
<div id="docUploader"></div>

	<script type="text/javascript">
		pageReady.push(function(){
			require(["dojo/ready", "dojo/dom-construct", "dojox/form/Uploader",
			         "dojox/form/uploader/plugins/IFrame"], function(ready, domConstruct, Uploader){
				ready(function(){

					var showImage = function(src){
						domConstruct.create("img", {src: src, style: "max-width:800px;"}, "imageDiv");
						domConstruct.create("div", {innerHTML: "URL: "+src}, "imageDiv");
					};

					var uploadOnComplete = function(dataArray){
                        for(var i=0, l=dataArray.length, doc; i<l; i++){
                        	doc = dataArray[i];
                        	showImage("/document/"+doc);
                        }
                	};

					var uploader = new dojox.form.Uploader({
						url: "/document",
						label: "Upload file",
						multiple: true,
						uploadOnSelect: true,
				        onComplete: uploadOnComplete,
						getForm: function(){return null;}
				    	}, "docUploader");
					uploader.startup();
					if(uploader.uploadType=="iframe"){
						uploader.set("url", "/document/upload");
					}
				});
			});

		});
	</script>


</div>

</div>