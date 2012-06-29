
//put the following code into the function "customAfterShowScreen()";

if(screenToShow == "Start" && screenToHide=="" && step==0)
	{
		setTimeout(function(){
		clickMenu("Start","Open AllDTcreate");
		}, 5000);
		step++;
	}

	if(screenToShow == "AllDTcreate" && screenToHide=="Start" && step==1)
	{
		setTimeout(function(){
		document.forms[getCurrentScreen()+'Form'].AllDT_create_int1_paramKey.value="23";
		clickMenu("AllDTcreate","Create");
		//end();
		}, 5000);
	}