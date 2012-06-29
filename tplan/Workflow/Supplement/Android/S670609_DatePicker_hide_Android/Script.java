package tplan.Workflow.Supplement.Android.S670609_DatePicker_hide_Android;

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
			matchArea="desktop"
	)
	public void doTest() throws Exception {
		connect("10.56.252.156", "test");
		clickOn("myWF_Android");
		
		waitForMatch("label",10);
		clickOn("label");
		clickOnWithOffSet("direction_Android",-29,72); //Menu_Android
		waitForMatch("OpenAllDTcreate",10);
		clickOn("OpenAllDTcreate");
		clickOn("Time1");
		waitForMatch("picker",10);
		
		clickOnWithOffSet("direction_Android",-29,72);
		waitForMatch("OpenStart",10);
		clickOn("OpenStart");
		waitForMatch("label",10);
		clickOn("label");
		
		clickOnWithOffSet("direction_Android",28,72); //Back_Android
	}
//	public static void main(String [] args){
//		Robot.run("tplan.Workflow.Supplement.Android.S670609_DatePicker_hide_Android.Script");
//	}
}