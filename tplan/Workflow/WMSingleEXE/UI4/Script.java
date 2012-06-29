package tplan.Workflow.WMSingleEXE.UI4;

import component.entity.tplan.Robot;
import component.entity.tplan.SuadeTestScript;
import component.entity.tplan.TplanTest;

public class Script extends SuadeTestScript {

	@Override
	@TplanTest(
			comparisonMethod = "searchbinary", 
			waitFor = "3s", 
			matchRate = 99f,
			matchArea = "desktop"
//			matchArea="rectangle:105,233,387,590"
	)
	public void doTest() throws Exception {
		connect("10.56.252.226","test");
		//click ok
		clickOnWithOffSet("win", -391, -159);		
		//click on setting
		clickOnWithOffSet("win", -93, -159);
		//type("10.56.252.226");
		clickOn("user");
		//type("userwm");
		//clickOn("activationcode");
		//type("123");
		//click Done button
		clickOnWithOffSet("win", -391, -159);
		sleep(9);
		//click menu button
		clickOnWithOffSet("win", -87, -159);
		//click show log button
		clickOnWithOffSet("win", -143, -168);
		sleep(5);
		//click Done button
		clickOnWithOffSet("win", -391, -159);
	}
	
	public static void main(String[] args){
		Robot.run("tplan.Workflow.WMSingleEXE.UI4.Script");
	}

}
