package tplan.Workflow.Controls.android.Ctrl_Editbox_RT;

import component.entity.tplan.AndroidTestScript;
import component.entity.tplan.SuadeTestScript;

public class Script extends AndroidTestScript{

	@Override
	public void doTest() throws Exception {
////		connect("10.35.180.238", "test");
//		connect("10.56.252.156", "test");
//		System.out.println(exitCode());
//		homeScreen();
//		startWorkflowApplication();
//		clickOn("wf_item_android.png",5,5);
//		waitForDispear("inprogress_toast_android.png");
//		waitForMatch("wf_editbox_android.png",10);
		
		
//		connect("10.35.180.238", "test");
		connect("10.56.252.156", "test");
		System.out.println(exitCode());
		homeScreen();
		startWorkflowApplication("sybase");
		clickOn("wf_item_android");
		waitForDispear("inprogress_toast_android");
		waitForMatch("wf_editbox_android",10);
	}
}
