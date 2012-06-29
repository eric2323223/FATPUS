package tplan.Workflow.Controls.WM.DemoScript;

import java.io.IOException;

import component.entity.tplan.WMTestScript;
import component.entity.tplan.TplanTest;


public class Script extends WMTestScript{

	@Override
	@TplanTest(
			comparisonMethod="searchbinary",
			waitFor="3s"
	)
	public void doTest() throws Exception {
		connect("10.56.252.203", "sybase");
		//Start app from container app list
//		clickOn("WN_start");

//		clickOn("WN_workflow");
		clickOn("workflow_icon");
		//
		clickOn("workflow_App");
	}

}
