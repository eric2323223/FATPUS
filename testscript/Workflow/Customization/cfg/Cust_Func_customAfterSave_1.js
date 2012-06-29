

if (screen === "AllDTupdateinstance") {
			 var form = document.forms[screen + "Form"];
	           	 var int = form.AllDT_date1_attribKey.value;
			 showConfirmDialog("input value :"+int);
			 uploadData("inputvalue="+int);
			 end();
		    }
		
	 