
	
	
	//put the following code into the function "customBeforeNavigateForward()":

		
	if(screenKey == "Start" && destScreenKey=="AllDTcreate" && step == 2){
//		alert("1: show AllDTcreate"); 
		step++;
	}
	
	if(screenKey == "Start" && destScreenKey=="AllDTcreate" && step == 6){
		//alert("6: not prepare to show AllDTcreate"); 
		uploadData("getCurrentScreen="+getCurrentScreen());
		end();
		return false;
	}
	
	return true;
	



