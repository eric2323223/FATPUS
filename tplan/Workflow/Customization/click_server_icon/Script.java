package tplan.Workflow.Customization.click_server_icon;

import component.entity.tplan.BlackBerryTestScript;
import component.entity.tplan.Robot;
import component.entity.tplan.TplanTest;

public class Script  extends BlackBerryTestScript{

	@Override
	@TplanTest(
			comparisonMethod = "searchbinary", 
			waitFor = "1s", 
			matchRate = 95f,
			matchArea="rectangle:14,143,450,580"
	)
	public void doTest() throws Exception {
		connect("10.56.252.156", "test");
		openMessageEntry("DT");
	}
	
	public static void main(String[] args){
		Robot.run("tplan.Workflow.Customization.click_server_icon.Script");
	}
}
