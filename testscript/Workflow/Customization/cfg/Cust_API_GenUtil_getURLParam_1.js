if(screenToShow == "Start" && screenToHide=="" && step==0)
{
setTimeout(function(){
	//alert(window.location.href);
	var param = getURLParam("screenToShow");
	uploadData("value="+param);
	end();
}, 5000);
}