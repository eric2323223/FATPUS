
	if(screenToShow=="Start" && screenToHide=="")
	{
		setTimeout(function(){
			uploadData("valueCount="+getCurrentMessageValueCollection().getValues().length);
			document.getElementById("key1").value = "TEST";
			saveScreen(getCurrentMessageValueCollection(), "Start");
			uploadData("valueCount="+getCurrentMessageValueCollection().getValues().length);
			uploadData("value="+getCurrentMessageValueCollection().getData("key1").getValue());
			end();
		}, 5000);
	}