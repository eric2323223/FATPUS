if(screenToShow == "Start" && screenToHide=="" && step==0)
{
setTimeout(function(){
	var request = getXMLHTTPRequest();
	uploadData("value="+request);
	end();
}, 5000);
}