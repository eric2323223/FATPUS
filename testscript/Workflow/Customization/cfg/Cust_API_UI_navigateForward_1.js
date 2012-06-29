
	if(screenToShow=="Start" && screenToHide=="")
	{
		setTimeout(function(){
			navigateForward("Department");
		}, 5000);
	}
	if(screenToShow=="Department" && screenToHide=="Start")
	{
		setTimeout(function(){
			uploadData("value="+getCurrentScreen());
			end();
		}, 5000);
	}