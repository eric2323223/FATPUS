package tplan.Workflow.Supplement.BB.S662835_UnreadMailCount_BB6;

import component.entity.tplan.BlackBerryTestScript;
import component.entity.tplan.TplanTest;

public class Script  extends BlackBerryTestScript{

	@Override
	@TplanTest(
			comparisonMethod = "searchbinary", 
			waitFor = "1s", 
			matchRate = 95f,
			matchArea="desktop"
	)
	public void doTest() throws Exception {
		connect("10.56.252.156", "test");
		openMessageEntry("MailNumber");
	}
}

