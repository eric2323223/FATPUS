package tplan.Workflow.Supplement.Android.S670133_HighlightCrntChc_Android_1;

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
			matchRate = 98f,
			matchArea="Rectangle:27,89,487,764"
	)
	public void doTest() throws Exception {
		connect("10.56.252.156", "test");
		clickOn("DT_findall_Android");
//		waitForDispear("inprogress_toast_android");
		clickOn("edge");
		waitForMatch("HightLight_A1",10);
		clickOn("HightLight_A1");
	}
	public static void main(String [] args){
		Robot.run("tplan.Workflow.Supplement.Android.S670133_HighlightCrntChc_Android_1.Script");
	}
}