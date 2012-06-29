	
	//alert("step="+step+"|screenToShow="+screenToShow+"|screenToHide="+screenToHide);
	if(screenToShow=="Start" && screenToHide=="" && step==0)
	{
		setTimeout(function(){
			step++;
			uploadData("value="+getCurrentScreen());
			clickMenu("Start","findAll");
		}, 5000);
	}
	if(screenToShow=="Start" && screenToHide=="Department" && step==2)
	{
		setTimeout(function(){
			step++;
			uploadData("value="+getCurrentScreen());
			end();
		}, 5000);
	}
	if(screenToShow=="Department" && screenToHide=="Start" && step==1)
	{
		setTimeout(function(){
			step++;
			uploadData("value="+getCurrentScreen());
			navigateBack("true");
		}, 5000);
	}