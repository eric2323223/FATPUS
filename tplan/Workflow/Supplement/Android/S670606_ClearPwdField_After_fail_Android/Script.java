package tplan.Workflow.Supplement.Android.S670606_ClearPwdField_After_fail_Android;

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
		clickOn("myWF_Android");
		
		waitForDispear("inprogress_toast_android");
		
		clickOn("Username");
		waitForMatch("Password",10);
		type("sup");
		clickOn("Password");
		type("sup");
		clickOnWithOffSet("direction_BB",-29,72); //Menu_Android
		clickOn("Save");
		waitForMatch("Start",10);
		clickOnWithOffSet("direction_BB",-29,72); //Menu_Android
		clickOn("findall");
		
		waitForDispear("inprogress_toast_android");
	
		waitForMatch("Password",10);//can not vp if the password is empty
	}
	public static void main(String [] args){
		Robot.run("tplan.Workflow.Supplement.Android.S670606_ClearPwdField_After_fail_Android.Script");
	}
}
