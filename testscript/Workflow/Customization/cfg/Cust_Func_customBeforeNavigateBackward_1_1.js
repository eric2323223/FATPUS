
	
	
	//put the following code into the function "customAfterShowScreen()":

	if(screenToShow == "Start" && screenToHide=="" && step == 1){
		//alert("1:prepare to Open AllDTcreate");
		setTimeout(function(){clickMenu("Start","Open AllDTcreate");}, 5000);
		//alert("1:prepare to show AllDTcreate"); 
			step++;
		}
	
	if(screenToShow == "AllDTcreate" && screenToHide=="Start" && step == 3){
		//alert("3:prepare to click cancel");
		setTimeout(function(){clickMenu("AllDTcreate","Cancel");}, 5000);
		//alert("3:prepare to show AllDTcreate"); 
		step++;
	}
	
	if(screenToShow == "Start" && screenToHide=="AllDTcreate" && step == 5){
			//alert("5.cilck AllDTcreate again, Start/currentScreen="+getCurrentScreen());
			setTimeout(function(){clickMenu("Start","Open AllDTcreate");}, 5000);
			//alert("prepare to show AllDTcreate");
			step++;
	}	
	
	if(screenToShow == "AllDTcreate" && screenToHide=="Start" && step == 7){
		//alert("7:prepare to click cancel,need to click the cancel button... ");
		setTimeout(function(){clickMenu("AllDTcreate","Cancel");}, 5000);
		//alert("enter >>>>>>>7.customBeforeNavigateBackward()");
	}



