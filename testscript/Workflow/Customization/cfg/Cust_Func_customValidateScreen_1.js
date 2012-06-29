	
	if (screenKey == "AllDTcreate") {
			
			var form = document.forms[screenKey + "Form"];
			
	       		 var int = form.AllDT_create_int1_paramKey.value;
           		 var email = form.AllDT_create_string2_paramKey.value;
	 		var date = form.AllDT_create_date1_paramKey.value;
	 		var datetime = form.AllDT_create_datetime1_paramKey.value;
	 		var time = form.AllDT_create_time1_paramKey.value;
	 		
	 		if(validateNumber(int) && validateEmail(email) && validateDate(date) && validateDateTime(datetime) && validateTime(time)){
	 			uploadData("message=all true");
	 			alert("all true");
	 		}else{

//	 		alert("validateNumber(int)"+validateNumber(int));
	 		uploadData("validateNumber(int)"+validateNumber(int));
	 		
//	 		alert("validateEmail(email)"+validateEmail(email));
	 		uploadData("validateEmail(email)"+validateEmail(email));
	 		
//	 		alert("validateDate(date)"+validateDate(date));
	 		uploadData("validateDate(date)"+validateDate(date));
	 		
//	 		alert("validateDateTime(datetime)"+validateDateTime(datetime));
	 		uploadData("validateDateTime(datetime)"+validateDateTime(datetime));
	 		
//	 		alert("validateTime(time)"+validateTime(time));
	 		uploadData("validateTime(time)"+validateTime(time));

			
			}
		end();
		return true;
		}
	   







