package tplan.Workflow.Supplement.Android.S665758_Cancel_ErrorDetail_Android;

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
		
		clickOn("DT_id100");
		clickOnWithOffSet("direction_Android",-29,72); //Menu_Android
		
		clickOn("OpenDepartmentcreate");
		waitForMatch("dept_id",10);
		clickOn("dept_id");
		clickOn("keyboard");
		type("100");
		clickOnWithOffSet("direction_Android",-29,72); //Menu_Android
		clickOn("Create");
		
		waitForMatch("Errorlist",10);
		clickOn("Errorlist");
		
		//position1:
		clickOnWithOffSet("direction_Android",-500,0);
		//position2:
		clickOnWithOffSet("direction_Android",-500,-100);
		
		clickOnWithOffSet("direction_Android",-29,72); //Menu_Android
		clickOn("Cancel");
		
		waitForMatch("Errorlist",10);
	}
	public static void main(String [] args){
		Robot.run("tplan.Workflow.Supplement.Android.S665758_Cancel_ErrorDetail_Android.Script");
	}
}
