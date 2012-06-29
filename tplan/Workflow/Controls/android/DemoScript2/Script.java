package tplan.Workflow.Controls.android.DemoScript2;

import component.entity.tplan.AndroidTestScript;
import component.entity.tplan.Robot;
import component.entity.tplan.SuadeTestScript;
import component.entity.tplan.TplanTest;

public class Script extends SuadeTestScript{
	@TplanTest(
			comparisonMethod="searchbinary",
			waitFor="5s",
			matchRate=96F,
//			matchArea="desktop"
			matchArea="Rectangle:0,0,750,650"
	)
	public void doTest() throws Exception {
		connect("10.56.252.203", "sybase");
//Start from container app list
		clickOn("Android_Home");
		clickOn("Workflow_start");
		clickOn("Android_Menu");
		clickOn("Android_Workflow");
		
		clickOn("Android_App");

	}
	
	public static void main(String[] args){
		Robot.run("tplan.Workflow.Controls.android.DemoScript2.Script");
	}

}
