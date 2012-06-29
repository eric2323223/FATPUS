

  if(screenToShow == "Start" && screenToHide==""){
        var topOfFormElem = document.getElementById("topOf" + screenToShow + "Form");
        //alert("topOfFormElem="+topOfFormElem);
        topOfFormElem.innerHTML = "<i><B>myhtml sybaseQA </B></i><br/>";
        //alert("true/topOfFormElem.innerHTML="+topOfFormElem.innerHTML);
	uploadData("PassGetCurrentScreen="+getCurrentScreen());
	end();
        return true;
    }else{
    	//  alert("false");
	uploadData("FailGetCurrentScreen="+getCurrentScreen());
	end();
    	return false;
    }
	  