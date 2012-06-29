package tplan.Workflow.Supplement.BB.S666988_CloseChoice_BB6_touch_2;

import component.entity.tplan.BlackBerryTestScript;
import component.entity.tplan.Robot;
import component.entity.tplan.TplanTest;

public class Script  extends BlackBerryTestScript{

	@Override
	@TplanTest(
			comparisonMethod = "searchbinary", 
			waitFor = "1s", 
			matchRate = 97f,
			matchArea="Rectangle:45,96,400,610"
	)
	public void doTest() throws Exception {
		connect("10.56.252.156", "test");
		openMessageEntry("DT_findall");
		
		waitForMatch("id1",10);
		clickOn("A1");
		waitForMatch("Option",10);
		clickOn("array");
		waitForMatch("Option",10);
		clickOnWithOffSet("hangoff_BB",-70,0);
		waitForMatch("id1",10);
	}
	
//	public static void main(String [] args){
//		Robot.run("tplan.Workflow.Supplement.BB.S666988_CloseChoice_BB6_touch_2.Script");
//	}
}
