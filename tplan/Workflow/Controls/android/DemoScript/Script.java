package tplan.Workflow.Controls.android.DemoScript;

import java.awt.Point;

import component.entity.tplan.AndroidTestScript;
import component.entity.tplan.Robot;
import component.entity.tplan.SuadeTestScript;
import component.entity.tplan.TplanTest;

public class Script extends SuadeTestScript{
	@TplanTest(
			comparisonMethod="searchbinary",
			waitFor="5s",
			matchRate=96F,
			matchArea="desktop"
//			matchArea="Rectangle:0,0,750,650"
	)
	public void doTest() throws Exception {
//		connect("10.56.252.203", "sybase");
		
		connect("10.56.252.226", "test");
		clickOnForMillisecond(new Point(406, 528), 250);
		clickOn(332,287,250);
		type("10.56.252.230");
		clickOn(332,425,250);
		type("username");

	}
	
	public static void main(String[] args){
		Robot.run("tplan.Workflow.Controls.android.DemoScript.Script");
	}
	

}
