package tplan.Workflow.Controls.android.Ctrl_672971_Boolean_Checkbox;
import resources.tplan.Workflow.Controls.android.Ctrl_672971_Boolean_Checkbox.ScriptHelper;
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
		connect("10.56.252.168", "test");
		
//		System.out.println(exitCode());
//		startWorkflowApplication("sybase");
		
		clickOn("wf_item_emp=1");
//		waitForDispear("inprogress_toast_android");
		waitForMatch("show_All_ff_wf_employee",10);
		
		clickOn("back");
		
//		//vp1:
//		clickOn("show_record_1");
//		waitForMatch("show_checkbox_true.png",10);
//		
//		//back icon
//		clickOn("back_android1");
//		
//		//vp2:
//		waitForMatch("show_All_ff_wf_employee",10);
//		clickOn("show_record_2");
//		waitForMatch("show_checkbox_false.png",10);
	}
}

