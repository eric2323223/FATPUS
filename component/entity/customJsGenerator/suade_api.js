function clickMenu(screenKey, menuItem){
	var menu;
	if(isWindowsMobile()){
		menuItem = screenKey + menuItem;
		menu = document.getElementById(menuItem);
		menu.click();
	}else{
		var menuItemId = getMenuItemId(screenKey, menuItem);
		var menu = document.getElementById(menuItemId);
		var theEvent = document.createEvent("MouseEvent");
		theEvent.initMouseEvent("click", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
		menu.dispatchEvent(theEvent);
	}
}

function clickListItem(toScreen, index) {
	if(isWindowsMobile()){
		var code = getHrefJavascriptCode(index);
		eval(code);
	}else{
		var listItems = document.getElementsByClassName("listviewLines ui-link-inherit");
		for(var i=0;i<listItems.length;i++){
			if(i==index){
				var id = listItems[i].getAttribute("id");
				navigateForward(toScreen, id);
			}
		}
	}
	
//	var id = $($('.ui-btn-text > a')[index]).attr("id");
//	navigateForward(toScreen, id);
}

function getHrefJavascriptCode(index){
	return getLink(index).href.toString();
}

function getLink(index){
	var links = document.getElementsByTagName("a");
	var i = 0;
	while(links[i].href.indexOf("javascript:navigateForward")==-1){
		i++;
	}
	return links[i+index*getTableColumsCount()];
}

function clickListItemByData(toScreen, columnData, columnIndex) {
	if(isWindowsMobile()){
		var values = document.getElementsByTagName("plain");
		var columnCount = getTableColumsCount();
//		alert("columnCount="+columnCount);
		var recordCount = values.length/columnCount;
//		alert("recordCount="+recordCount);
		for(var i=0;i<recordCount;i++){
			var value = values[i*columnCount+columnIndex];
			if(value.innerText==columnData){
//				alert(value.parentElement);
				var p = value.parentElement;
				eval(p.href.toString());
			}
		}
	}else{
		var id = getLinkId(columnData, columnIndex);
		navigateForward(toScreen, id);
	}
}

function getLinkId(data, index){
	var listItems = document.getElementsByClassName("listviewLines ui-link-inherit");
	for(var i = 0;i<listItems.length;i++){
		var fields = listItems[i].getElementsByClassName("lv_line_field");
		if(fields[index].textContent == data){
			return listItems[i].getAttribute("id");
		}
	}
}

function getListItem(id, index){
	if(getLinkId(id, index)==null){
		uploadData("found=false");
	}else{
		uploadData("found=true");
	}
}

function getListItemsCount(screenName){
	if(isWindowsMobile()){
		uploadData("list_items_count="+getTableLinks()/getTableColumsCount());
	}else{
		var screen = getScreen(screenName);
		var count = screen.getElementsByClassName("lv_lines").length;
		uploadData("list_items_count="+count);
	}
}

function getListTable(){
	var tables = document.getElementsByTagName("Table");
	for(var i=0;i<tables.length;i++){
		var clazz = tables[i].getAttribute("class");
		if(clazz=="listview"){
			return tables[i];
		}
	}
}

function getTableColumsCount(){
	var links = document.getElementsByTagName("a");
	var count = 1;
    var i= 0;
    while(true){
    	var href = links[i].href;
    	if(href.indexOf("javascript:navigateForward")!=-1){
    		break;
    	}
    	i++;
    }
    while(links[i].href == links[i+1].href){
    	count++;
    	i++
    }
    return count;
}

function getTableLinks(){
	var links = document.getElementsByTagName("a");
	var count = 0;
	for(var i=0;i<links.length;i++){
		if(links[i].href.indexOf("javascript:navigateForward")!=-1){
			count++;
		}
	}
    return count;
}

function checkLabel(screenName, key){
	var screen = getScreen(screenName);
	var labels = screen.getElementsByTagName("label");
	for(var i=0;i<labels.length;i++){
		var k = labels[i].getAttribute("for");
		if(k === key){
			uploadData("value="+labels[i].textContent);
			return;
		}
	}
	
//	var labels = $(getScreen(screenName)).find("label");
//	$.each(labels, function(){
//		var k = $(this).attr("for");
//		if(k === key){
//			uploadData("value="+$(this).text());
//			return;
//		}
//	});
}

function getMenuItemDisplayName(screenName, id)
{
//	var links = $(getScreen(screenName)).find("a");
//	$.each(links, function(){
//		var iid = $(this).attr("id");
//		if(iid === id){
//			uploadData("displayName="+$(this).text());
//		}
//	});
	
	var value = "menuItemCallBack"+screenName+id+"();";
	var link = getLinkByAttribute("onclick",value);
	uploadData("displayName="+$(link).text());
}

function uploadData(d)
{
	if(isWindowsMobile()){
		var objHTTP = getXMLHTTPRequest();
		objHTTP.open('POST',server(),false);
		objHTTP.send(d);
	}else{
	   var options =
	   {
	      method : "POST",
	      async : false,
	      data : (d),
	      // 	data : ("user=eric;age=10"),
	      headers : ("Content-Type", "application/x-www-form-urlencoded"	)
	   };
	   getExternalResource(server(), options);
	}
}

function end()
{
   uploadData("EOM=true");
   closeWorkflow();
}

function uploadIdValue(screen, id)
{
   var element = getElementInScreen(screen, id);
   var data = "screen=" + screen + ";id=" + id + ";value=" + element.value;
   uploadData(data);
}

function getElementInScreen(screen, id){
	var fullScreenName = screen+"ScreenDiv"
	var elm;
    var elms = document.getElementById(fullScreenName).getElementsByTagName("*");
    for (var i = 0; i < elms.length; i++) {
        if (elms[i].id == id) {
            elm = elms[i];
            break;
        }
    }
    return elm;
}

function getScreenDisplayName(screenName){
	uploadData("value="+$(getScreen(screenName)).attr("sup_screen_title"));
} 

function getMenuItemId(screenName, menuItemName){
	var screen = getScreen(screenName);
	var links = screen.getElementsByTagName("a");
	for(var i=0;i<links.length;i++){
		var name = links[i].getAttribute("name");
		if(name==menuItemName){
			return links[i].getAttribute("id");
		}
	}
}

function getScreen(screenName){
	var screenDiv = screenName+"ScreenDiv";
	return document.getElementById(screenDiv);
}

function getLinkByAttribute(attribute, value)
{
	var result;
	var links = document.getElementsByTagName("a");
	for(var link in links){
		var v = link.getAttribute(attribute);
		if(value == v){
			return link;
		}
	}
	
//	var result;
//	var links = $('a');
//	links.each(function(){
//		var v= $(this).attr(attribute);
//		if(value === v){
//			result = $(this);
//			return false;
//		}
//	});
//	return result
}
