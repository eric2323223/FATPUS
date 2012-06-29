
	if(screenToShow == "Start" && screenToHide == ""){
	    var bottomOfFormElem = document.getElementById("bottomOf"+screenToShow+"Form");
	    bottomOfFormElem.innerHTML ="<p>Testing input event <b id='inputevent'> used to modify the Footer text</b> </p><input type='button' onclick='changeText()' value='Change Text'/>";
	  // alert("pass");
	   uploadData("PassScreenToShow="+screenToShow);
	   end();
	}
	 return true;