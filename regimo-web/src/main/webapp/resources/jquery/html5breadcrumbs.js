/*
 HTML5Breadcrumbs
 Copyright 2010 - 2011 D. Rimron <darran@xalior.com>
*/
var html5breadcrumbs = function(){
    var dbName = 'html5breadcrumbs';
    var version = '1.0';
    var dbTable = 'html5crumbs';
    var displayName = 'HTML5CRUMBS';
    var maxSize = 1024*1024*5; //Upto 5MB for logfiles....
    var db = null;
    var ui = null;
    var graph = null;
    var data = null;
    var results = null;
    var daycounts = null;

    var graphTimer;
    var graphAnim = 0;

    var defaultErrorHandle = function(tx,error){ console.log(error.message); }
    var defaultDataHandle = function(result){ console.log(result); }

    var now = function() { return new Date().getTime(); }

    return {
        go: function() {
            if (window.openDatabase) {
                db = openDatabase(dbName, version, displayName, maxSize);
                db.transaction(function(tx) {
                    var url = document.URL;
                    var title = document.getElementsByTagName('title')[0];
                    if(title)
                        title = title.innerHTML
                    else
                        title = "Untitled Page";
                    var setupTable = function(tx,error) {
                        tx.executeSql("CREATE TABLE "+ dbTable + " (url TEXT, timestamp REAL, title TEXT)", [], function(result) {}, defaultErrorHandle);
                        tx.executeSql("INSERT INTO " + dbTable + " (url,timestamp, title) VALUES (?,?,?)",[url,now(),title],function(result) {},defaultErrorHandle);
                    }
                    tx.executeSql("INSERT INTO " + dbTable + " (url,timestamp, title) VALUES (?,?,?)",[url,now(),title],function(result) {},setupTable);
                }); // okay! log that URL!
                var head = document.getElementsByTagName('head')[0],
                    style = document.createElement('style'),
                    rules = document.createTextNode('#html5breadcrumbs_show{cursor: pointer;background:rgba(0,0,0,0.75);border-bottom:2px solid rgba(255,255,255,0.75);border-left:2px solid rgba(255,255,255,0.75);border-radius:0 0 10px 10px;border-right:2px solid rgba(255,255,255,0.75);border-top:none;color:rgba(255,255,255,0.75);font-size:30px;height:30px;position:absolute;right:60px;text-align:center;top:0;vertical-align:middle;width:30px;padding-top: 5px;}#html5breadcrumbs_ui{display:none;width:100%;height:100%;background-color:rgba(0,0,0,0.8);position:absolute;top:0;left:0;z-index:10240}#html5breadcrumbs_ui>div{color:white;width:600px;height:600px;margin:auto;padding-top:5px;display:block;border:#aaa 2px solid;background:rgba(0,0,0,0.9);margin-top:30px;border-radius:10px;}#html5breadcrumbs_data{height:515px;width:600px;overflow:auto;display:block;}#html5breadcrumbs_display{width:600px;height:140px;display:block;}#html5breadcrumbs_ui>div>span{height:40px;display:block;align:center;}.html5breadcrumbs_url{color:#ccc;font-size:12px;float:right;}.html5breadcrumbs_date{width:120px;border-top:dotted 1px #999;}.html5breadcrumbs_datum{min-width:400px;border-top:dotted 1px #999;border-left:solid 1px #999;}');
                style.id = "html5breadcrumbs_style";
                style.type = 'text/css';
                if(style.styleSheet)
                    style.styleSheet.cssText = rules.nodeValue;
                else
                    style.appendChild(rules);
                head.appendChild(style);
                var body = document.getElementsByTagName('body')[0],
                    button = document.createElement('div');
                if( button.attachEvent ){
                    button.attachEvent('onclick', 'html5breadcrumbs.show()');
                } else {
                    button.setAttribute('onclick', 'html5breadcrumbs.show()');
                }
                button.id = "html5breadcrumbs_show";
                button.innerHTML = "<span>+</span>";
                body.appendChild(button);
                ui = document.createElement('div');
                ui.id = "html5breadcrumbs_ui";
                if( ui.attachEvent ){
                    ui.attachEvent('onclick', 'html5breadcrumbs.hide()');
                } else {
                    ui.setAttribute('onclick', 'html5breadcrumbs.hide()');
                }
                ui.innerHTML = '<div onclick="html5breadcrumbs.stopEvent(evt)"><p style="font-size:20px;text-align:center;">Breadcrumbs</p><div id="html5breadcrumbs_data">DATA</div>'+
                	'<p style="text-align:center;"><button type="button" onclick="html5breadcrumbs.wipe();return false;">Reset</button> <button type="button" onclick="html5breadcrumbs.hide();return false;">Close</button></p></div>';

                body.appendChild(ui);
            } else {
                console.log("Error Could not create DB either the DB has exceeded its size limit or you are using the wrong browser.");
            }

            return this;
        },

        refresh: function() {
            db.transaction(function(tx) {
                tx.executeSql("SELECT * FROM "+ dbTable +" ORDER BY timestamp DESC", [], html5breadcrumbs.drawTable, defaultErrorHandle);
            });
        },

        drawTable: function(tx,newresults) {
            results = newresults;
            if(!data)
                data = document.getElementById('html5breadcrumbs_data');
            daycounts = [0,0,0,0,0,0,0,0];
            var res_count = results.rows.length;
            var table_rows = ["<table><tbody>"];
            for(var i=0;i<res_count;i++) {
                var recorddate = new Date(results.rows.item(i).timestamp);
                var thisdate = now();
                var second = recorddate.getSeconds();
                var minute = recorddate.getMinutes();
                var hour = recorddate.getHours();
                var day = recorddate.getDate();
                var month = recorddate.getMonth() + 1;
                var year = recorddate.getFullYear();
                if(results.rows.item(i).timestamp > (thisdate-86400000)) {
                    daycounts[0]++;                } else if (results.rows.item(i).timestamp > (thisdate-172800000)){
                    daycounts[1]++;                } else if (results.rows.item(i).timestamp > (thisdate-259200000)){
                    daycounts[2]++;                } else if (results.rows.item(i).timestamp > (thisdate-345600000)){
                    daycounts[3]++;                } else if (results.rows.item(i).timestamp > (thisdate-432000000)){
                    daycounts[4]++;                } else if (results.rows.item(i).timestamp > (thisdate-518400000)){
                    daycounts[5]++;                } else if (results.rows.item(i).timestamp > (thisdate-604800000)){
                    daycounts[6]++;                } else {
                    daycounts[7]++;                }
                table_rows.push("<tr><td class=\"html5breadcrumbs_date\">"+year+"/"+month+"/"+day+" "+hour+":"+minute+":"+second+
                		"</td><td class=\"html5breadcrumbs_datum\">"+results.rows.item(i).url+
                		"<span class=\"html5breadcrumbs_url\"><a href=\""+results.rows.item(i).url+"\">"+results.rows.item(i).title+
                		"</a></span></a></td></tr>");
            }
            table_rows.push("</tbody></table>");
            data.innerHTML = table_rows.join("");

        },

        show: function() {
            ui.style.cssText="display: block !important;";
            html5breadcrumbs.refresh();
        },

        hide: function() {
           ui.style.cssText="";
        },

        wipe: function() {
            db.transaction(function(tx) {
                tx.executeSql("DELETE FROM " + dbTable ,[],defaultDataHandle,defaultErrorHandle);
            });
        },

        stopEvent: function(evt) {
            evt.stopPropagation();
            return false;
        }
    }
}().go();
/*
 No, seriously, that's it. You don't need to do anything.
 You have included this file (right?!) and that's it.

 Everything else is automagic.

 Of course, you can style this for shits and giggles if
 you are overriden with desire. Or a designer. *grin*

 The global object, 'html5breadcrumbs', can also be toyed
 with and generally manipulated in however your want to,
 personally, via the same routines that the UI uses. But
 to see those hooks you best either re-read this file, and
 pay close attention to the returned object (the public API)
 or, for an overall easier time, check the documentation....
*/
