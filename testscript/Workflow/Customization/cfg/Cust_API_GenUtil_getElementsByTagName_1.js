if(screenToShow == "Start" && screenToHide=="" && step==0)
{
setTimeout(function(){
	var els = getElementsByTagName(document.getElementById("StartScreenDiv"),"input");
	uploadData("value="+els.length);
	end();
}, 5000);

}