package tplan.Workflow.Controls.android.Ctrl_674011_Boolean_PK;
import resources.tplan.Workflow.Controls.android.Ctrl_674011_Boolean_PK.ScriptHelper;
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

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Script extends AndroidTestScript{

	@Override
	public void doTest() throws Exception {
//		connect("10.35.180.238", "test");
		connect("10.56.252.156", "test");
//		System.out.println(exitCode());
//		startWorkflowApplication("sybase");
		
		clickOn("wf_item_myWF");
		
//		waitForDispear("inprogress_toast_android");
		
		waitForMatch("show_boolCheckbBox",10);
		
	// ************vp1:begin to verify false checkBox...
		clickOn("findAll");
//		waitForDispear("inprogress_toast_android");
		waitForMatch("show_boolFlaseRecord",10);
		
		//open the false detail ...
//		clickOn("show_boolFlaseRecord");
//		waitForMatch("show_boolFalseCheckbox_detail",10);
//		
//		//back icon ...
//		clickOn("back_android1");
		clickOn("back_android1");
		waitForMatch("show_boolCheckbBox",10);
		
		
		//************* vp2:begin to verify true checkBox...
		clickOn("show_boolCheckbBox");
		waitForMatch("show_boolTrueCheckBox",10);
		clickOn("findAll");
//		waitForDispear("inprogress_toast_android");
		waitForMatch("show_boolTrueRecord",10);
		
		//open the True detail ...
//		clickOn("show_boolTrueRecord");
//		waitForMatch("show_boolTrueCheckbox_detail",10);
		
		
	}
}
