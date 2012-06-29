if(screenToShow == "Start" && screenToHide=="")
{
	setTimeout(function(){
		uploadData("value="+getCurrentScreen());
		clickMenu("Start","findAll");
	}, 5000);
}
if(screenToShow == "Department" && screenToHide=="Start")
{
	setTimeout(function(){
		uploadData("value="+getCurrentScreen());
		end();
	}, 5000);
}