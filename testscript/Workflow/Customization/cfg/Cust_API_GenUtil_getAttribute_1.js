if(screenToShow == "Start" && screenToHide=="" && step==0)
{
setTimeout(function(){
	uploadData("value="+getAttribute(document.getElementById("key1"),"type"));
	end();
}, 5000);

}