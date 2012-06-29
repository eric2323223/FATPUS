

if(screenToShow == "Start" && screenToHide==""){
	  var bottomOfFormElem = document.getElementById("bottomOf" + screenToShow + "Form");
	    bottomOfFormElem.innerHTML = "<p>Copyright 2011, Sybase Inc.</p>"; 
        //alert("true/bottomOfFormElem.innerHTML"+bottomOfFormElem.innerHTML);
        uploadData("PassGetCurrentScreen="+getCurrentScreen());
        end();
        return true;
    }else{
    	//  alert("false");
    	 uploadData("FailGetCurrentScreen="+getCurrentScreen());
          end();
    	return false;
    }  