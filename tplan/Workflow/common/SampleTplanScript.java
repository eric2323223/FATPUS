package tplan.Workflow.common;

import component.entity.tplan.AndroidTestScript;
import component.entity.tplan.Robot;
import component.entity.tplan.TplanTest;

public class SampleTplanScript extends AndroidTestScript {

	@Override
	@TplanTest(
//			comparisonMethod = "searchbinary", 
			waitFor = "5s", 
			matchRate = 95f,
//			matchArea="desktop")
//			matchArea="5554:avd22")
			matchArea="Rectangle:0,0,1,1")
			
	public void doTest() throws Exception {
//		connect("java://local"); 
//		startWorkflowApplication("123qwe");
		connect("10.35.180.238", "test");
		clickOn("Messages_BB");
		disconnect();
	}

//	@Override
//	@TplanTest(comparisonMethod = "searchbinary", waitFor = "1s", matchRate = 95f)
//	public void doTest() throws Exception {
//		connect("10.35.180.238", "test");
//		startWorkflowApplication("123qwe");
//		disconnect();
//	}

	public static void main(String[] args) {
		Robot.run("tplan.Workflow.common.SampleTplanScript");
	}
}
