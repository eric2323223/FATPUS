
//put the following code into function "customBeforeNavigateBackward"()":

	
	if(screenKey == "AllDTcreate" && isCancelled == true && step == 4){
		//alert("4.customBeforeNavigateBackward() getPreviousScreen="+getPreviousScreen());
		uploadData("1.getPreviousScreen="+getPreviousScreen());
		return true;
	}
	
	if(screenKey == "AllDTcreate" && isCancelled == true && step == 7){
		//alert("7.customBeforeNavigateBackward() getPreviousScreen="+getPreviousScreen());
		uploadData("2.getPreviousScreen="+getPreviousScreen());
		end();
		return false;
	}




