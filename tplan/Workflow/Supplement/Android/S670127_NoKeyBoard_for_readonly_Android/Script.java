package tplan.Workflow.Supplement.Android.S670127_NoKeyBoard_for_readonly_Android;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import component.entity.tplan.AndroidTestScript;
import component.entity.tplan.Robot;
import component.entity.tplan.TplanTest;

public class Script extends AndroidTestScript{

	@Override
	@TplanTest(
			comparisonMethod = "searchbinary", 
			waitFor = "1s", 
			matchRate = 95f,
			matchArea="Rectangle:27,89,487,764"
	)
	public void doTest() throws Exception {
		connect("10.56.252.156", "test");
		clickOn("DT_int1");
		waitForMatch("String1",10);
		clickOn("String1");
		waitForMatch("Double1",10);
		clickOn("Double1");
		waitForMatch("keyboard",10);
		clickOn("keyboard");
	}
//	public static void main(String [] args){
//		Robot.run("tplan.Workflow.Supplement.Android.S670127_NoKeyBoard_for_readonly_Android.Script");
//	}
}
