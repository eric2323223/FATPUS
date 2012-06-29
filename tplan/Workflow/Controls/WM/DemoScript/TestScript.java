package tplan.Workflow.Controls.WM.DemoScript;

import component.entity.tplan.AndroidTestScript;
import component.entity.tplan.Robot;
import component.entity.tplan.SuadeTestScript;
import component.entity.tplan.TplanTest;

public class TestScript extends SuadeTestScript{
	@TplanTest(
			comparisonMethod="searchbinary",
			waitFor="5s",
			matchRate=97F,
//			matchArea="desktop"
			matchArea="Rectangle:101,166,540,700"
	)
	public void doTest() throws Exception {
		connect("10.56.252.203", "sybase");
		//Start app from container app list
		clickOn("WN_start");

//		clickOn("WN_workflow");
		clickOn("workflow_icon");
		//
		clickOn("workflow_App");


	}
	
	public static void main(String[] args){
		Robot.run("tplan.Workflow.Controls.WM.DemoScript.TestScript");
	}

}
