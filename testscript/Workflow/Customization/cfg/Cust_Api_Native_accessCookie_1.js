

  document.cookie = "username=wfuser1";
    document.cookie = "sessionid=12345";
   // uploadData("writing to cookie:username=wfuser1,sessionid=12345");
//    alert(document.cookie);
    if( document.cookie == "sessionid=12345; username=wfuser1"){
  
//    	alert("Pass/reading from cookie:"+document.cookie);
    	uploadData("PassGetCurrentScreen="+getCurrentScreen());
         end();
    	return true;
    }else {
//    	alert("Fail/reading from cookie:"+document.cookie);
    	uploadData("FailGetCurrentScreen="+getCurrentScreen());
	end();
    	return false;
    }
	  