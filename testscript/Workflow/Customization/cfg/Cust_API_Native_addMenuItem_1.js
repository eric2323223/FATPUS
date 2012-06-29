
//put the following code into the function "customBeforeShowScreen()":
	  
if(screenToShow === "AllDTcreate" && screenToHide === "Start"){
	    alert("Before add menuItem");
	    	addMenuItem("MySave", "saveScreen",null,"AllDTcreate","Save");
	       alert("pass/After add menuItem");
	    	uploadData("screenToShow="+screenToShow);
		
	}
	return true;
