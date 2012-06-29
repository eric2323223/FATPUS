if(screenToShow == "Start" && screenToHide=="" && step==0)
{
	setTimeout(function(){
	clickMenu("Start","Open AllDTcreate");
	}, 5000);
	step++;
}

if(screenToShow === "AllDTcreate" && screenToHide === "Start" && step ==1){

	//alert("Pass/closeWorkflow");
	uploadData("PassGetCurrentScreen="+getCurrentScreen());

	closeWorkflow();
	end();
}	
	

