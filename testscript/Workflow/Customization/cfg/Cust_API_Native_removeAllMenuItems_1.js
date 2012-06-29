

if(screenToShow == "Start" && screenToHide=="" && step==0){

	//alert("begin to remove all menuItems");
	removeAllMenuItems();
	//alert("PassScreenToShow="+getCurrentScreen());
	uploadData("PassScreenToShow="+getCurrentScreen());
	end();
}