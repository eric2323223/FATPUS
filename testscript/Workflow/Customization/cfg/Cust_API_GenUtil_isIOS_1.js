if(screenToHide=="" && screenToShow=="Start"){
	var message;
	if(isIOS()){message="IOS";}
	if(isIPad()){message="IPad";}
	if(isBlackBerry()){message="BlackBerry";}
	if(isBlackBerry5()){message="BlackBerry5";}
	if(isAndroid()){message="Android";}
	if(isWindowsMobile()){message="WindowsMobile";}
	uploadData("platform="+message);
	end();
}