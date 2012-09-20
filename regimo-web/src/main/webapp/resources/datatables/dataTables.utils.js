
function standardViewUpdateAction(oObj){
	return "<a href='view?id="+oObj.aData.id+"'>View</a> <a href='edit?id="+oObj.aData.id+"'>Edit</a>";
}

function standardUpdateAction(oObj){
	return "<a href='edit?id="+oObj.aData.id+"'>Edit</a>";
}

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