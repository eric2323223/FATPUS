package tplan.Workflow.common;

import java.awt.Point;

import component.entity.tplan.AndroidTestScript;
import component.entity.tplan.Robot;
import component.entity.tplan.TplanTest;

public class StartWF_android extends AndroidTestScript {

	@Override
	@TplanTest(
			comparisonMethod = "searchbinary", 
			waitFor = "5s", 
			matchRate = 95f,
			matchArea="desktop")
//			matchArea="5554:avd22")
//			matchArea="Rectangle:0,0,1,1")
			
	public void doTest() throws Exception {
//		connect("10.35.180.238", "test");
		connect("10.56.252.252", "test");
		openMessageEntry("myWF_android");
		disconnect();
	}


	public static void main(String[] args){
		Robot.run("tplan.Workflow.common.StartWF_android");
	}

}
