/*
 * Sybase Mobile Workflow version 2.1.3
 * 
 * Custom.js
 * This file will not be regenerated, and it is expected that the user will
 * include customized code herein.
 *
 * The template used to create this file was compiled on Wed Oct 12 11:13:54 PDT 2011
 * 
 * Copyright (c) 2010,2011 Sybase Inc. All rights reserved.
 */

var step = 0;		
 
function customBeforeWorkflowLoad() {
    return true;
}

function customAfterWorkflowLoad() {
}

function customBeforeSubmit(screenKey, actionName, workflowMessageToSend) {
    return true;
}

function customAfterSubmit(screenKey, actionName) {
}

function customBeforeNavigateForward(screenKey, destScreenKey) {
    return true;
}

function customAfterNavigateForward(screenKey, destScreenKey) {
}

function customBeforeNavigateBackward(screenKey, isCancelled) {
    return true;
}

function customAfterNavigateBackward(screenKey, isCancelled) {
}

function customBeforeShowScreen(screenToShow, screenToHide) {
    return true;
}

function customAfterShowScreen(screenToShow, screenToHide) {

}

function customValidateScreen(screenKey, values) {
    return true;
}

function customBeforeMenuItemClick(screen, menuItem) {
    return true;
}


function customAfterMenuItemClick(screen, menuItem) {
}

function customBeforeSave(screen) {
    return true;
}

function customAfterSave(screen) {
}

function customConditionalNavigation( currentScreenKey, actionName, defaultNextScreen, conditionName, workflowMessage ) {
    return false;
}
function customBeforeReportErrorFromNative(errorString) {
	uploadData("ERROR="+errorString);
    return true;
}

function customAfterReportErrorFromNative(errorString) {
	uploadData("ERROR="+errorString);
}

function customGetPictureURIFromCamera() {
	getPicture(customGetPictureError, 
	           customGetPictureURISuccess, 
	           { sourceType: PictureOption.SourceType.CAMERA,
                 destinationType: PictureOption.DestinationType.IMAGE_URI}
               );
}

function customGetPictureURIFromLibrary() {
	getPicture(customGetPictureError, 
	           customGetPictureURISuccess, 
	           { sourceType: PictureOption.SourceType.PHOTOLIBRARY,
                 destinationType: PictureOption.DestinationType.IMAGE_URI}
               );
}

function customGetPictureURISuccess(fileName, imageURI ){
	var pictureURIValue = new MessageValue();
	pictureURIValue.setKey("");					// Must be set by the user.
	pictureURIValue.setValue(imageURI);
	pictureURIValue.setType(MessageValueType.FILE);
	var mvc = getWorkflowMessage().getValues();
	if( mvc ) {
		mvc.add( pictureURIValue.getKey(), pictureURIValue );
		getWorkflowMessage().setHasFileMessageValue(true);			// Must be set when using the URI option.
		// Add a message value for the MIME type of the image if desired.
		//var mimeType = getMimeType(fileName);
		//var mimeMessageValue = new MessageValue();
		//mimeMessageValue.setKey("");				// Must be set by the user.
		//mimeMessageValue.setValue(mimeType);
		//mimeMessageValue.setType(MessageValueType.TEXT);
		//mvc.add(mimeMessageValue.getKey(), mimeMessageValue);
	}	
}

function customGetPictureDataFromCamera(){
	getPicture(customGetPictureError, 
	           customGetPictureDataSuccess, 
	           { sourceType: PictureOption.PictureSourceType.CAMERA,
                 destinationType: PictureOption.DestinationType.IMAGE_DATA}
               );
}

function customGetPictureDataFromLibrary(){
	getPicture(customGetPictureError, 
	           customGetPictureDataSuccess, 
	           { sourceType: PictureOption.PictureSourceType.PHOTOLIBRARY,
                 destinationType: PictureOption.DestinationType.IMAGE_DATA}
               );
}

function customGetPictureDataSuccess(fileName, imageData ){
	var pictureDataValue = new MessageValue();
	pictureDataValue.setKey("");					// Must be set by the user.
	pictureDataValue.setValue(imageData);
	pictureDataValue.setType(MessageValueType.TEXT);
	var mvc = getWorkflowMessage().getValues();
	if( mvc ) {
		mvc.add( pictureDataValue.getKey(), pictureDataValue );
		// Add a message value for the MIME type of the image if desired.
		//var mimeType = getMimeType(fileName);
		//var mimeMessageValue = new MessageValue();
		//mimeMessageValue.setKey("");				// Must be set by the user.
		//mimeMessageValue.setValue(mimeType);
		//mimeMessageValue.setType(MessageValueType.TEXT);
		//mvc.add(mimeMessageValue.getKey(), mimeMessageValue);
	}	
}

// This callback handles a picture error.
function customGetPictureError(result){
	alert("customGetPictureError result: " + result);
}

function getMimeType(fileName) {
	var lastPeriod = fileName.lastIndexOf(".");
	var extension = fileName.substr(lastPeriod + 1);
	if (extension === "jpg") {
		extension = "jpeg";
	}
	var mimeType = "image/" + extension;
	return mimeType;
}

function customAfterDataReceived(incomingWorkflowMessage) {}


function server(){}


//this function includes all necessary js files for the application
function include(file)
{

  var script  = document.createElement('script');
  script.src  = file;
  script.type = 'text/javascript';
  script.charset = "UFT-8";
  //script.defer = true;

  document.getElementsByTagName('head').item(0).appendChild(script);

}

include('js/suade_api.js');
//include('js/myFile2.js');
