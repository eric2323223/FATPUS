package tplan.Workflow.Controls.WM.DemoScript2;

import component.entity.tplan.AndroidTestScript;
import component.entity.tplan.Robot;
import component.entity.tplan.SuadeTestScript;
import component.entity.tplan.TplanTest;

public class Script extends SuadeTestScript{
	@TplanTest(
			comparisonMethod="searchbinary",
			waitFor="5s",
			matchRate=97F,
			matchArea="desktop"
//			matchArea="Rectangle:101,166,540,800"
	)
	public void doTest() throws Exception {
		connect("10.56.252.203", "sybase");
//Start app from email
		//back to desktop
		clickOn("WN_start");
//		//clck on the email icon
		clickOn("WM_message");
//		//select Text message item
		clickOn("TextMessage");

		clickOnWithOffSet("WN_start",20,50);
		clickOnWithOffSet("WN_start",80,95);
		clickOn("WM_Message_Item");

	}
	
	public static void main(String[] args){
		Robot.run("tplan.Workflow.Controls.WM.DemoScript2.Script");
	}

}
