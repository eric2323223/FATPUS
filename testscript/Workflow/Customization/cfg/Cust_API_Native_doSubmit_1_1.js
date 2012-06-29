
if(screen == "AllDTcreate" && menuItem =="Submit_Workflow" ){
		//alert("entry>>>>>>>>>>>>>>");
		doSubmitWorkflow(screen, "Submit Workflow", "", "", true);
		uploadData("PassGetCurrentScreen="+getCurrentScreen());
		end();
	}
return true;