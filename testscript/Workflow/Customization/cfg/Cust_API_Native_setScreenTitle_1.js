
		
	if(screenToShow === "Start" && screenToHide === ""){
		setScreenTitle(screenToShow, "myNewScreen");
//		alert("change screenTitle end");
		uploadData("PassGetCurrentScreen="+getCurrentScreen());
		end();
	}