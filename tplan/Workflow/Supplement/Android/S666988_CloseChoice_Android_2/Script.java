package tplan.Workflow.Supplement.Android.S666988_CloseChoice_Android_2;

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
			matchArea="Rectangle:27,89,480,350"
	)
	public void doTest() throws Exception {
		connect("10.56.252.156", "test");
		clickOn("DT_findall_BB6");
		clickOn("id");
		waitForMatch("list",10);
		clickOn("click_id");
		waitForMatch("id",10);
	}
	public static void main(String [] args){
		Robot.run("tplan.Workflow.Supplement.Android.S666988_CloseChoice_Android_2.Script");
	}
}
