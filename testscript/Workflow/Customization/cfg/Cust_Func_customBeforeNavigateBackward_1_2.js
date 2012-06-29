
	//put the follow code into the function "customBeforeShowScreen()":

	
	if(screenToShow == "Start" && screenToHide=="" && step == 0){
		//alert("0: prepare to show Start");
		step++;
		return true;
	}
	
	
	if(screenToShow == "AllDTcreate" && screenToHide=="Start" && step == 2){
		//alert("2:prepare to show AllDTcreate"); 
		step++;
		return true;
	}
	
	if(screenToShow == "Start" && screenToHide=="AllDTcreate" && step == 4){
		//alert("4:prepare to show Start"); 
		step++;
		return true;
	}
	
	if(screenToShow == "AllDTcreate" && screenToHide=="Start" && step ==6){
		//alert("6:prepare to show AllDTcreate"); 
		step++;
		return true;
	}




