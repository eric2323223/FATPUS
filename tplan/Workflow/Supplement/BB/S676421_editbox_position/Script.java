package tplan.Workflow.Supplement.BB.S676421_editbox_position;

import component.entity.tplan.BlackBerryTestScript;
import component.entity.tplan.TplanTest;

public class Script extends BlackBerryTestScript{

	@Override
	@TplanTest(
			comparisonMethod = "searchbinary", 
			waitFor = "1s", 
			matchRate = 95f,
			matchArea="desktop"
	)
	public void doTest() throws Exception {
		connect("10.56.252.156", "test");
		
		openWorkflowEntry("myWF_BB");
		waitForMatch("Edit_Position", 10);
	}
}
