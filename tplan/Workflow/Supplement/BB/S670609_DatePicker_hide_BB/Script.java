package tplan.Workflow.Supplement.BB.S670609_DatePicker_hide_BB;

import component.entity.tplan.BlackBerryTestScript;
import component.entity.tplan.Robot;
import component.entity.tplan.TplanTest;

public class Script extends BlackBerryTestScript{

	@Override
	@TplanTest(
			comparisonMethod = "searchbinary", 
			waitFor = "1s", 
			matchRate = 95f,
			matchArea="Rectangle:45,96,400,610"
	)
	public void doTest() throws Exception {
		connect("10.56.252.156", "test");
		openWorkflowEntry("myWF_BB");
		
		waitForMatch("label", 10);
		clickOnWithOffSet("hangoff_BB", -230, 0);
		clickOn("Open_AllDTcreate");
		clickOn("Datetime1");
		waitForMatch("picker", 10);
		clickOnWithOffSet("hangoff_BB", -230, 0);
		clickOn("Open_Start");
		waitForMatch("label", 10);
		
	}
	public static void main(String [] args){
		Robot.run("tplan.Workflow.Supplement.BB.S670609_DatePicker_hide_BB.Script");
	}
}

