
	if(screenToShow=="Start" && screenToHide=="")
	{
		setTimeout(function(){
			document.getElementById("key1").value="XXX";
			uploadData("value="+document.getElementById("key1").value);
			saveScreens();
			getCurrentMessageValueCollection().getData("key1").setValue("Test");
			updateUIFromMessageValueCollection("Start", getCurrentMessageValueCollection());
			var value = document.getElementById("key1").value;
			uploadData("value="+value);
			end();
		}, 5000);
	}