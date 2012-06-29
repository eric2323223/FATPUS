package component.entity.tplan;

import java.io.IOException;

public abstract class AndroidTestScript extends SuadeTestScript {
	public final static String SCREEN_HOME = "android_home";
	public final static String SCREEN_WF_HOME = "android_wf_home";
	public final static String SCREEN_WF = "android_wf";
	public final static String SCREEN_WF_APP = "android_wf_app";
	public final static String SCREEN_WF_LOGIN = "android_wf_login";
	
	private String currentScreenName = null;

	public abstract void doTest() throws Exception ;
	
	public void clickContextMenu(String fileName) throws IOException, ActionFailedException{
//		clickOn("menu_android");
//		clickOn(fileName);
		press("F2","1500");
	}
	
	public void loginWorkflowApp(String text) throws IOException, ActionFailedException{
		if(isExist("login_button_android")){
			type(text);
			clickOn("login_button_android");
		}
	}
	
	protected int getClickHoldTime(){
		return 100;
	}

	@Override
	protected void moveToMessageListScreen() throws IOException, ActionFailedException {
//		clickOn("back_android");
		press("Escape", "1500");
	}

	@Override
	protected void moveToWorkflowListScreen() throws IOException, ActionFailedException {
//		clickOn("menu_android");
//		clickOn("contextMenu_workflows_android");
		press("F2", "1500");
		press("RIGHT", "1500");
		press("Enter", "1500");
	}

	public void homeScreen() throws IOException, ActionFailedException {
//		long endTime = System.currentTimeMillis() + 8*1000;
//		while(System.currentTimeMillis() < endTime){
//			if(!isExist("main_menu_android")){
//				if(isExist("back_android")){
//					clickOn("back_android");
//				}
//				if(isExist("back_highlight_android")){
//					clickOn("back_highlight_android");
//				}
//			}else{
//				break;
//			}
//		}
//		currentScreenName = SCREEN_HOME;
		press("HOME", "1500");
	}
	
	public void startWorkflowApplication(String text) throws IOException, ActionFailedException{
		homeScreen();
		clickOn("desktopIcon_android");
		loginWorkflowApp(text);
		clickContextMenu("contextMenu_workflows_android");
		currentScreenName = SCREEN_WF;
	}
	
	public void startWorkflowEntry(String image, String password) throws IOException, ActionFailedException{
		waitForMatch(image, 30);
		clickOn(image);
		if(isExist("inprogress_toast_android")){
			type(password);
			clickOn("login_button_android");
		}
		clickOn(image);
		currentScreenName = SCREEN_WF_APP;
	}
	
	public void startMessageEntry(String image, String password) throws IOException, ActionFailedException{
		homeScreen();
		startWorkflowApplication(password);
		waitForMatch(image, 30);
		clickOn(image);
//		if(isExistEdge("inprogress_toast_android")){
//			type(password);
//			clickOn("login_button_android");
//		}
//		clickOn(image);
		currentScreenName = SCREEN_WF_APP;
	}
	
	private String getCurrentScreenName(){
		return this.currentScreenName;
	}

}
