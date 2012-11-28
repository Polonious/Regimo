<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="formBody">

	<form:label path="title">
		<s:message code="article.title"/> <form:errors path="title" cssClass="error" />
	</form:label>
	<form:input path="title" />

	<form:label path="slug">
		<s:message code="article.slug"/> <form:errors path="slug" cssClass="error" />
	</form:label>
	<form:input path="slug" />

	<form:label path="summary">
		<s:message code="article.summary"/> <form:errors path="summary" cssClass="error" />
	</form:label>
	<form:input path="summary" />

	<form:label path="detail">
		<s:message code="article.detail"/> <form:errors path="detail" cssClass="error" />
	</form:label>
	<form:textarea path="detail" data-dojo-type="dijit/Editor" data-dojo-props="extraPlugins:['insertHorizontalRule','|',
			'formatBlock',{name:'dijit/_editor/plugins/FontChoice', command:'fontName', generic:true},
			'fontSize','foreColor','hiliteColor','|','createLink','insertImage','viewsource']"/>

	<form:label path="categories">
		<s:message code="article.categories"/> <form:errors path="categories" cssClass="error" />
	</form:label><br>
	<form:select data-type="select2" path="categories" multiple="true" items="${referenceData.categories}" itemLabel="name" itemValue="id"
		cssStyle="width:300px" cssClass="populate"/>

	<br><br>
	<form:label path="image">
		<s:message code="article.image"/> <form:errors path="image" cssClass="error" />
	</form:label>
	<form:hidden path="image"/>
	<div id="imageDiv"></div>
	<br/>
	<div id="docUploader"></div>

	<script type="text/javascript">
		pageReady.push(function(){
			require(["dijit/Editor",
			         "dijit/_editor/plugins/FontChoice", // 'fontName','fontSize','formatBlock'
			         "dijit/_editor/plugins/TextColor",
			         "dijit/_editor/plugins/LinkDialog",
			         "dijit/_editor/plugins/ViewSource"]);
			require(["dojo/ready", "dojo/dom", "dojo/dom-construct", "dojox/form/Uploader", "dojox/form/uploader/FileList",
			         "dojox/form/uploader/plugins/IFrame"], function(ready, dom, domConstruct, Uploader, FileList){
				ready(function(){

					var showImage = function(src){
						domConstruct.create("img", {src: src}, "imageDiv");
					};

					var image = dom.byId("image").value;
					if(image.indexOf("doc://")==0){
						showImage("/document/"+image.substr(6));
					}

					var uploadOnComplete = function(dataArray){
						domConstruct.empty("imageDiv");
                        for(var i=0, l=dataArray.length, doc; i<l; i++){
                        	doc = dataArray[i];
                        	dom.byId("image").value = "doc://"+doc;
                        	showImage("/document/"+doc);
                        }
                	};

					var uploader = new dojox.form.Uploader({
						url: "/document",
						label: "Select file",
						multiple: false,
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

	<form:hidden path="id" />

</div>