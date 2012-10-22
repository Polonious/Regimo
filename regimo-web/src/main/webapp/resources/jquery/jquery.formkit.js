/*
 * Formkit jQuery plugin
 * serialize/deserialize form
 *
 * @website https://github.com/Polonious/jquery-formkit/
 * @author: Ted Liang <tedliang[dot]email[at]gmail[dot]com>
 * @version 1.2
 *
 * Licensed under the MIT License
 */
(function($){

	var formkit = {
		format: 'object',
		traditional: false,
		ignore: ':submit, :reset, :image, :file',
		extractForm: 'extractForm',
		extractDataTable: 'extractDataTable',
		fillForm: 'fillForm',
		copyForm: 'copyForm',
		resetForm: 'resetForm',
		extractField: 'extractField',
		fillField: 'fillField'
	};

	var config = $('script[data-formkit-config]').first().attr('data-formkit-config');
	if( config ) $.extend(formkit, eval("({" + config + "})"));

	$.fn[formkit.extractForm] = function ( options ) {
		if(typeof options == 'string'){
			options = { format: options };
		}
		else if($.isFunction(options)){
			if(formkit.format=='array') options = { buildArray: options };
			else options = { buildObject: options };
		}

		var settings = $.extend({}, defaultFn, formkit, (options || {}));

		if(settings.format=='array'){
			return form.toArray(form.enabledElements(this, null, settings.ignore), settings.buildArray);
		}

		var obj = form.toObject(form.enabledElements(this, null, settings.ignore), settings.buildObject);
		return (settings.format=='query')? $.param(obj, settings.traditional) : obj;
	};

	$.fn[formkit.fillForm] = function( data ) {
		if ( typeof data == 'string' ) {
			var param, data = arrayToObject($.map(data.split( "&" ), function(val){
				param = val.split( "=" );
				return { name: decodeURIComponent( param[0] ),
						value: decodeURIComponent( param[1].replace( rPlus, "%20" )) };
			}));
		}
		else if ( $.isArray( data ) ) {
			data = arrayToObject(data);
		}
		return form.fromObject(form.elements(this), data);
	};

	$.fn[formkit.extractDataTable] = function( tableId, prefix ) {
		prefix = prefix || tableId; 
		var data = this[formkit.extractForm]({ignore:"[name^="+prefix+"]"});
		var $dataTable = $("#"+tableId).dataTable();
		var keys = $dataTable.$("input[name^="+prefix+"Name]")[formkit.extractForm]("array");
		var values = $dataTable.$("input[name^="+prefix+"Value]")[formkit.extractForm]();
		$.each(keys, function(){
			if(this.value){
				var val = values[this.name.replace("Name","Value")];
				if(val){
					data[prefix+"['"+this.value+"']"]=val;
				}
			}
		});
		return data;
	};

	$.fn[formkit.copyForm] = function( target ) {
		return ((!target || typeof target == 'string') ? $(target) :
			target)[formkit.fillForm](this[formkit.extractForm]());
	};

	$.fn[formkit.resetForm] = function() {
		this.each(function() { this.reset(); });
		return this;
	};

	$.fn[formkit.extractField] = function( name ) {
		if(name){
			return form.toObject(form.enabledElements(this, name), defaultFn.buildObject)[name];
		}
		else{
			var ret = this[formkit.extractForm](), count = 0, attr;
			for (k in ret) if (ret.hasOwnProperty(k)) {attr=k, count++;}
			switch (count) {
				case 0: return undefined;
				case 1: return ret[attr];
				default: return ret;
			}
		}
	};

	$.fn[formkit.fillField] = function( name, value ) {
		if (value==undefined) {
			if (this.length==0) return $();
			value = name;
			name = this[0].name;
		}
		var obj = {};
		obj[name]=value;
		return form.fromObject(form.elements(this, false, name), obj);
	};

	var	defaultFn = {
		buildObject: function(/*String*/value, /*Object*/obj){
			var name = this.name, val = obj[name];
			if(typeof value == "number") value = value.toString();
			if(typeof val == "string"){
				obj[name] = [val, value];
			}else if($.isArray(val)){
				val.push(value);
			}else{
				obj[name] = value;
			}
		},
		buildArray: function(/*String*/value){
			return {name: this.name, value: value};
		}
	};

	var arrayToObject = function(array){
		var ret = {};
		$.each(array, function(){
			defaultFn.buildObject.call(this, this.value, ret);
		});
		return ret;
	};

	var rcheck = /^(radio|checkbox)$/i, rCRLF = /\r?\n/g, rPlus = /\+/g;

	var form = {
		toObject: function formToObject(elems, buildObject){
			var ret = {};
			elems.each(function(){
				var value = field.toObject(this);
				if(value !== null){
					buildObject.call(this, value, ret);
				}
			});
			return ret; // Object
		},

		fromObject: function formFromObject(elems, ret){
			return elems.filter(function(){
				return field.fromObject(this, ret[this.name]);
			});
		},

		toArray: function formToArray(elems, buildArray){
			return elems.map(function( i, elem ){
				var val = field.toObject(elem);
				return val == null ?
						null :
						$.isArray( val ) ?
							$.map( val, function( val, i ){
								return buildArray.call( elem, val.replace( rCRLF, "\r\n" ) );
							}) :
							buildArray.call( elem, val.replace( rCRLF, "\r\n" ) );
			}).get();
		},

		elements: function(nodes, ignoreDisabled, name, ignore){
			if(nodes.length==0) return $([]);
			var filter = ":input[name"+(name?"='"+name+"'":"")+"]";
			if(nodes[0].elements){
				nodes = $($.makeArray(nodes[0].elements)).filter(filter);
			}
			else{
				nodes = nodes.map(function(){
					var $this = $(this);
					if($this.is(filter)) return this;
					else return $this.find(filter).get();
				});
			}
			if(ignoreDisabled) nodes = nodes.not("[disabled]");
			return nodes.not(ignore?ignore:formkit.ignore);
		},

		enabledElements: function(nodes, name, ignore){
			return form.elements(nodes, true, name, ignore);
		}

	};

	var field = {
		toObject: function fieldToObject(/*DOMNode*/ inputNode){
			var ret = null;
			if(rcheck.test( inputNode.type )){
				if(inputNode.checked){
					ret = inputNode.value;
				}
			}else{
				ret = $(inputNode).val();
			}
			return ret; // Object
		},

		fromObject: function fieldToObject(/*DOMNode*/ inputNode, value){
			if(value == undefined || value === null) return;

			var $node = $(inputNode);
			if(rcheck.test(inputNode.type)){
				var prop = (inputNode.value == value ||
							($.isArray(value) &&
							($.inArray(inputNode.value, value)!=-1 ||
							$.inArray(parseFloat(inputNode.value), value)!=-1)));
				if($node.prop('checked')!=prop){
					$node.prop('checked', prop);
					return true;
				}
			}else{
				if($node.val()!=value){
					$node.val(value);
					return true;
				}
			}
		}

	};

})(jQuery);