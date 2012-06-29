
	if(screenToShow=="Start" && screenToHide=="")
	{
		setTimeout(function(){
			clickMenu("Start","findAll");
		}, 5000);
	}
	if(screenToShow=="Department" && screenToHide=="Start")
	{
		setTimeout(function(){
			uploadData("value="+getListViewKey("Department));
			end();
		}, 5000);
	}