package tplan.Workflow.Supplement.BB.S670127_NoDatePicker_for_readonly_BB;

import component.entity.tplan.BlackBerryTestScript;
import component.entity.tplan.Robot;
import component.entity.tplan.TplanTest;

public class Script extends BlackBerryTestScript{

	@Override
	@TplanTest(
			comparisonMethod = "searchbinary", 
			waitFor = "1s", 
			matchRate = 98f,
			matchArea="Rectangle:45,96,400,610"
	)
	public void doTest() throws Exception {
		connect("10.56.252.156", "test");
//		openMessageEntry("int1_BB");
		
		clickOn("Date1");
		waitForMatch("Time1", 10);
		
		clickOn("Datetime1");
		waitForMatch("Time1", 10);
		
		clickOn("Time1");
		waitForMatch("TimeHand", 10);
		
		
	}
	public static void main(String [] args){
		Robot.run("tplan.Workflow.Supplement.BB.S670127_NoDatePicker_for_readonly_BB.Script");
	}
}
