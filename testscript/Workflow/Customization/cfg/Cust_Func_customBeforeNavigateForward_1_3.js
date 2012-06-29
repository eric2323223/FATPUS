
	
	
	//put the following code into the function "customAfterShowScreen()":

	if(screenToShow == "Start" && screenToHide=="" && step == 1){
//		alert("1:prepare to Open AllDTcreate");
		setTimeout(function(){clickMenu("Start","Open AllDTcreate");}, 5000);
			step++;
		}
	
	if(screenToShow == "AllDTcreate" && screenToHide=="Start" && step == 3){
//		alert("3:prepare to click Cancel");
		setTimeout(function(){clickMenu("AllDTcreate","Cancel");}, 5000);
		step++;
	}
	
	if(screenToShow == "Start" && screenToHide=="AllDTcreate" && step == 5){
//		alert("5:prepare to Open AllDTcreate again");
		setTimeout(function(){clickMenu("Start","Open AllDTcreate");}, 5000);
			step++;
		}



