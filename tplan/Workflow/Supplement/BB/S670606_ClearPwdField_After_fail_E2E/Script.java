package tplan.Workflow.Supplement.BB.S670606_ClearPwdField_After_fail_E2E;

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
		waitForMatch("Credential_BB", 10);
		
		clickOn("credential_u");
		type("sup");
		clickOn("credential_pw");
		type("sup");
		
		clickOn("MENU_BB");
		clickOn("save");
		
		clickOn("MENU_BB");
		clickOn("findall");
		
		waitForMatch("credential_null_pw", 10);
	}
}
