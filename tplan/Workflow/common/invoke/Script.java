package tplan.Workflow.WMSingleEXE.invoke;

import component.entity.tplan.Robot;
import component.entity.tplan.SuadeTestScript;
import component.entity.tplan.TplanTest;

public class Script extends SuadeTestScript {

	@Override
	@TplanTest(
			comparisonMethod = "searchbinary", 
			waitFor = "3s", 
			matchRate = 97f,
			matchArea = "desktop"
//			matchArea="rectangle:105,233,387,590"
	)
	public void doTest() throws Exception {
		connect("10.56.252.226", "test");
		//clickOn("invoke");
		//click Hybrid Application(Sybase Workflow icon) to invoke.
		clickOnWithOffSet("win", -240, -301);
		sleep(3000);
	}
	
	public static void main(String[] args){
		Robot.run("tplan.Workflow.WMSingleEXE.invoke.Script");
	}

}
