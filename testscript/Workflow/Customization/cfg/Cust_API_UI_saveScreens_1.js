
	if(screenToShow=="Start" && screenToHide=="")
	{
		setTimeout(function(){
			uploadData("valueCount="+getCurrentMessageValueCollection().getValues().length);
			document.getElementById("key1").value = "TEST1";
			clickMenu("Start","Open Screen2");
		}, 5000);
	}
	if(screenToShow=="Screen2" && screenToHide=="Start")
	{
		setTimeout(function(){
			uploadData("valueCount="+getCurrentMessageValueCollection().getValues().length);
			document.getElementById("key2").value = "TEST2";
			saveScreens();
			uploadData("valueCount="+getCurrentMessageValueCollection().getValues().length);
			end();
		}, 5000);
	}