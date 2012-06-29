package tplan.Workflow.WMSingleEXE.UI1;

import java.awt.Point;
import java.io.File;
import java.util.Date;


import component.entity.tplan.Robot;
import component.entity.tplan.SuadeTestScript;
import component.entity.tplan.TplanTest;
import component.entity.tplan.WMTestScript;

public class Script extends WMTestScript {

	@Override
	@TplanTest(
			comparisonMethod = "searchbinary", 
			waitFor = "3s", 
			matchRate = 99f,
			datumPoint = "winLogo",
			coordinateConfigFile = "NamedPoints.xml",
			matchArea="rectangle:0,0,666,982"
				
	)
	public void doTest() throws Exception {
		connect("10.56.252.226","test");
		clickOnNamedPoint("testPoint1");
	}

	public static void main(String[] args){
		Robot.run("tplan.Workflow.WMSingleEXE.UI1.Script");
	}

}
