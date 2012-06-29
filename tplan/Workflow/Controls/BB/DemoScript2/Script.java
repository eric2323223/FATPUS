package tplan.Workflow.Controls.BB.DemoScript2;

import component.entity.tplan.BlackBerryTestScript;
import component.entity.tplan.Robot;
import component.entity.tplan.SuadeTestScript;
import component.entity.tplan.TplanTest;

public class Script extends BlackBerryTestScript {

	@TplanTest(
			comparisonMethod="searchbinary",
			waitFor="5s",
			matchRate=95F,
//			matchArea="desktop"
			matchArea="Rectangle:0,0,550,850"
	)
	public void doTest() throws Exception {
		connect("10.56.252.203", "sybase");
//		clickOn("MessageBox_BB");
//		clickOn("Message_Item");
		openMessageEntry("Message_Item");
	}
	public static void main(String[] args){
		Robot.run("tplan.Workflow.Controls.BB.DemoScript2.Script");
	}

}
