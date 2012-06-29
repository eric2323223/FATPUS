package tplan.Workflow.Supplement.Android.S668687_validation_for_dynachoice_Android;

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
			matchArea="desktop"
	)
	public void doTest() throws Exception {
		connect("10.56.252.156", "test");
		clickOn("DT_findall_BB6");
		
		clickOnWithOffSet("direction_Android",-29,72); //Menu_Android
		clickOn("Openmyscreen");
		
		clickOn("id");
		type("1");
		clickOn("name");
		type("f");
		
		clickOnWithOffSet("direction_Android",-29,72); //Menu_Android
		clickOn("create");
		
//		waitForMatch("alert",10);
		clickOn("alert");
//		clickOn("OK");
	}
	public static void main(String [] args){
		Robot.run("tplan.Workflow.Supplement.Android.S668687_validation_for_dynachoice_Android.Script");
	}
}
