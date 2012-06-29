
	if (screen === "AllDTcreate") {
			var form = document.forms[screen + "Form"];
			var int = form.AllDT_create_date1_paramKey.value;
           	 if(validateDate(int)){
           		 uploadData("id=AllDT_create_date1_paramKey,value="+int);
			 end();
           		
           	 } else {
           		document.forms[screen+'Form'].AllDT_create_date1_paramKey.value="2011-01-01";
           		uploadData("id=AllDT_create_date1_paramKey,value=2011-01-01");
			end();
           	 }
	}
	


			
		