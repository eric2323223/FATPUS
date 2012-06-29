
	
	if (screenKey == "AllDTcreate" && (actionName == "Submit_Workflow")) {
		var form = document.forms[screenKey + "Form"];
           	 var email = form.AllDT_create_string2_paramKey.value;
		var date = form.AllDT_create_date1_paramKey.value;


		//alert(validateEmail(email));
		//alert(validateDate(date));

		uploadData("EmailValidate="+validateEmail(email));
		uploadData("DateValidate="+validateDate(date));
		end();
		
	}
	return true;
	

