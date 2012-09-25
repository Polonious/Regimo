$(document).ready(function(){
	var defaultOption = {
		common: {
			"sPaginationType": "full_numbers",
			"iDisplayLength": 25
		},
		local: {
			"iTabIndex": 1
		},
		ajax: {
			"bProcessing": true,
			"bServerSide": true,
			"bStateSave": true,
			"sAjaxSource": "",
			"fnServerData": _getServerData,
			"aoColumns": []
		}};
	
	var configTag = "data-table-config";
	$("table["+configTag+"]").each(function(){
		var $this=$(this), customOption=eval("({" + $this.attr(configTag) + "})");
		var source = customOption.source || "", isAjax = (source!="local");
		var option = $.extend({}, defaultOption.common, 
				isAjax?defaultOption.ajax:defaultOption.local);
		if(isAjax){
			$.each(customOption.columns.split(","), function(){
				option.aoColumns.push({mDataProp:this});
			});
			option.aoColumns.push({
				"mDataProp":"id", 
				"bUseRendered": false,
            	"bSortable": false,
            	"fnRender":datatableActions[customOption.action]});
		}
		$this.dataTable(option);
	});
});

var datatableActions = {
	standardViewUpdate: function(oObj){
		var path = window.location.pathname;
		return "<a href='"+path+"/view?id="+oObj.aData.id+"'>View</a> <a href='"+path+"/edit?id="+oObj.aData.id+"'>Edit</a>";
	},
	standardUpdate: function(oObj){
		return "<a href='"+window.location.pathname+"/edit?id="+oObj.aData.id+"'>Edit</a>";
	}
};

function _getServerData( sSource, aoData, fnCallback ) {
	$.each( aoData, function(i, para) {
		var paraNameParts = para.name.split("_");
	    if(paraNameParts.length == 2 && !isNaN(paraNameParts[1])){
	    	if(paraNameParts[0]=="iSortCol" || paraNameParts[0]=="sSortDir"){
	    		para.name=paraNameParts[0]+"_["+paraNameParts[1]+"]";
	    	}
	    	else{
	    		para.name="columnDefs["+paraNameParts[1]+"]."+paraNameParts[0];
	    	}
	    }
	});

    $.ajax( {
        "dataType": 'json',
        "type": "POST",
        "url": sSource,
        "data": aoData,
        "success": fnCallback
    } );
}