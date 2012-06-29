if(screenToShow == "Start" && screenToHide=="" && step==0)
{
setTimeout(function(){
	var el = getFormElementById(document.getElementById("StartForm"),"key1");
	uploadData("value="+getAttribute(el, "type"));
	end();
}, 5000);

}