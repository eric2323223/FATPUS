package tplan.Workflow.WMSingleEXE.UI3;
import component.entity.tplan.Robot;
import component.entity.tplan.SuadeTestScript;
import component.entity.tplan.TplanTest;


public class Script extends SuadeTestScript {

	
	

   @Override
   @TplanTest(comparisonMethod = "searchbinary", 
		      waitFor = "3s", 
		      matchRate = 99f,
		      matchArea = "desktop"
//			  matchArea="rectangle:105,233,387,590"
   )
   
  public void doTest() throws Exception {
		connect("10.56.252.226","test");
		//click on advanced
		clickOnWithOffSet("win", -285, -206);
		sleep(3000);
		//Click on Disable button
		clickOnWithOffSet("win", -149, -301);
		sleep(2000);
		clickOn("Disable");
		//click ok
		clickOnWithOffSet("win", -391, -159);
		////click on setting
		clickOnWithOffSet("win", -93, -159);
		sleep(1);
		//click enable
		clickOn("Enable");
		//click ok
		clickOnWithOffSet("win", -391, -159);
		////click on setting
		clickOnWithOffSet("win", -93, -159);
		clickOn("Disable");
		//Click connection tab
		clickOnWithOffSet("win", -413, -203);
		
	}
	
	public static void main(String[] args){
		Robot.run("tplan.Workflow.WMSingleEXE.UI3.Script");
		
	}

}
