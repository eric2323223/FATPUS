package tplan.Workflow.Controls.android.Ctrl_Complex_RT_1;
import resources.tplan.Workflow.Controls.android.Ctrl_Complex_RT_1.ScriptHelper;
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
		waitForMatch("show_emp_id_1",10);
		
		//vp1:
		clickOn("show_emp_id_input");
		type("1");
		clickOn("MENU");
		clickOn("findByPrimaryKey");
//		waitForDispear("inprogress_toast_android");
		
		waitForMatch("show_all_controls",10);
		
	}
}
