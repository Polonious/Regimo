var giCount = 0;
var $msgDatatable;
pageReady.push(function(){
	$.fn.dataTableExt.afnSortData['dom-text'] = function  ( oSettings, iColumn )
	{
		var aData = [];
		$( 'td:eq('+iColumn+') input', oSettings.oApi._fnGetTrNodes(oSettings) ).each( function () {
			aData.push( this.value );
		} );
		return aData;
	};
	
	$msgDatatable = $("#messages").dataTable({
		"sPaginationType": "full_numbers",
		"iDisplayLength": 25,
		"iTabIndex": 1,
		"aoColumns": [
		  			{ "sSortDataType": "dom-text" },
		  			{ "sSortDataType": "dom-text" }
		  		]
		  	} );
	$("#mainForm").submit(function(event) {
		var data = $(this).extractDataTable("messages");
		$.post(location.href, data, function(result) {
			$("#formInfo div, #mainForm label[for] span").remove();
			var outcomeMsgs = [];
			$.each(result.userMessages, function(){
				if(this.field){
					$("#mainForm label[for="+this.field+"]").append('<span class="error">'+this.text+'</span>');
				}
				else{
					outcomeMsgs.push('<div class="'+this.type+'">'+this.text+'</div>');
				}
			});
			$("#formInfo").append(outcomeMsgs.join(""));
			$('html, body').animate({ scrollTop: $('#formInfo').offset().top }, 'slow');
			if(result.success && !$("#id").val()){
				alert("Redirect to edit page. id:"+result.id);
				window.location = "edit?id="+result.id;
			}
		}, "json");
		return false;
	});
	
	$('#fileupload').fileupload({
        dataType: 'json',
        done: function (e, data) {
    		var isNew, msg = data.result;
    		for(var key in msg){
    			isNew = true;
    			$msgDatatable.$("input[value='"+key+"']").each(function(){
    				isNew = false;
    				$msgDatatable.$("input[name="+this.name.replace("Name","Value")+"]").val(msg[key]);
    			});
    		    if(isNew){
    		    	fnClickAddRow(key, msg[key]);
    		    }
    		}
        }
    });
});

function fnClickAddRow(name, value) {
	name = name || ""; 
	value = value || ""; 
	$msgDatatable.fnAddData( [
        "<input value='"+name+"' name=messagesNameA"+giCount+"/>",
        "<input value='"+value+"' name=messagesValueA"+giCount+"/>"] );
    giCount++;
    $msgDatatable.fnPageChange( 'last' );    
}
