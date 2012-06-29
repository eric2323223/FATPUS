package tplan.Workflow.Controls.BB.Ctrl_672971_Boolean_Checkbox;
import resources.tplan.Workflow.Controls.BB.Ctrl_672971_Boolean_Checkbox.ScriptHelper;
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
		
		clickOn("wf_item_emp1");
//		waitForDispear("inprogress_toast_android");
		waitForMatch("show_All_ff_wf_employee",10);
		
		//vp1:
		clickOn("show_record_1");
		waitForMatch("show_checkbox_true",10);
		
		clickOn("BACK_BB");
		
		//vp2:
		waitForMatch("show_All_ff_wf_employee",10);
		clickOn("show_record_2");
		waitForMatch("show_checkbox_false",10);
		
		clickOn("BACK_BB");
		clickOn("BACK_BB");
	}
}

