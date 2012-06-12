$(function() {
	$( ".column" ).sortable({
		connectWith: ".column",
		handle: '.dashlet-header'
	});

	$( ".dashlet" ).addClass( "ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" )
		.find( ".dashlet-header" )
			.addClass( "ui-widget-header ui-corner-all" )
			.prepend( "<span class='ui-icon ui-icon-minusthick'></span>")
			.end()
		.find( ".dashlet-content" );

	$( ".dashlet-header .ui-icon" ).click(function() {
		$( this ).toggleClass( "ui-icon-minusthick" ).toggleClass( "ui-icon-plusthick" );
		$( this ).parents( ".dashlet:first" ).find( ".dashlet-content" ).toggle();
	});

});
