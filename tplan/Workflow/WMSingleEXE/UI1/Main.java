package tplan.Workflow.WMSingleEXE.UI1;

/**
 * Generated on Mon Jun 11 01:30:16 PDT 2012
 * T-Plan Robot v2.0.6 (Build No. 2.0.6-20110418.1)
 * Default Java Converter version 2.0.6
 */

import com.tplan.robot.ApplicationSupport;
import com.tplan.robot.AutomatedRunnable;
import com.tplan.robot.scripting.DefaultJavaTestScript;
import com.tplan.robot.scripting.JavaTestScript;

import component.entity.tplan.ActionFailedException;
import component.entity.tplan.Robot;
import component.entity.tplan.SuadeTestScript;
import component.entity.tplan.TplanTest;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Main extends SuadeTestScript {
	@TplanTest(
			comparisonMethod = "searchbinary", 
			waitFor = "3s", 
			matchRate = 97f,
			datumPoint = "windowsLogo",
			matchArea = "desktop"
				
//			matchArea="rectangle:105,233,387,590"
	)
   public void doTest() throws IOException, ActionFailedException {
		connect("10.56.252.226","test");
		
		//Click on server name
		
		clickOn(375,270, 250);
		//sleep(5000);
		
		type("10.56.252.229");
		//sleep(5000); 
		
		//clickOn user name
		
		clickOnRelativePoint(375, 425, 250);
		type("userwme");
		//sleep(6000);
		clickOn(383,532,250);
		
		type("123");
		//click Done button
		clickOn(197,793, 250);
		sleep(15000);
		//Click on menu button
		
		clickOn(500,794,250);
		sleep(3000);
		//click show log button
		clickOn(449,581,250);
		//Click done button
	    sleep(15000);
		screenshot(new File("c:\\captured.jpg"));
		
		//click on done button
		//clickOn(197,793,250);
		sleep(1000);
		
	
	}

   public static void main(String args[]) {
      Robot.run("tplan.Workflow.WMSingleEXE.UI1.Main");
   }

}
