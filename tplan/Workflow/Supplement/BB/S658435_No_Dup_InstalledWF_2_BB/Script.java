package tplan.Workflow.Supplement.BB.S658435_No_Dup_InstalledWF_2_BB;

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
