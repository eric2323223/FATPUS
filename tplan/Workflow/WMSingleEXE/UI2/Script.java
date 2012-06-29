package tplan.Workflow.WMSingleEXE.UI2;
import java.io.File;

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
		//clickOnWithOffSet("win", -285, -206);
		clickOn(308,741,250);
		
		//Check on restart client
		clickOn(139,425,250);
		
		//click Done button
		clickOn(197,793,250);
		sleep(10000);
		
		//click show log button
		clickOn(454,584,250);
		sleep(9000);
		screenshot(new File("c:\\captured_RestartLog.jpg"));
		
		//click Done button
		clickOnWithOffSet("win", -391, -159);
	}
	
	public static void main(String[] args){
		Robot.run("tplan.Workflow.WMSingleEXE.UI2.Script");
		
	}

}
