package tplan.Workflow.common;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import com.tplan.robot.ApplicationSupport;
import com.tplan.robot.AutomatedRunnable;
import com.tplan.robot.scripting.JavaTestScript;
import component.entity.tplan.AndroidTestScript;
import component.entity.tplan.Robot;
import component.entity.tplan.TplanTest;

public class StartEmail_android extends AndroidTestScript{

	@Override
	@TplanTest(
			comparisonMethod = "searchbinary", 
			waitFor = "5s", 
			matchRate = 95f,
			matchArea="desktop")
//			matchArea="5554:avd22")
//			matchArea="Rectangle:0,0,1,1")
			
	public void doTest() throws Exception {
		connect("10.56.252.156", "test");
//		connect("10.35.180.238", "test");
		openMessageEntry("message_entry_android");
		disconnect();
		
	}
	
	public static void main(String[] args){
		Robot.run("tplan.Workflow.common.StartEmail_android");
	}
	
	
}
