package tplan.Workflow.Controls.android.Ctrl_641211_Choice_Complex;
import resources.tplan.Workflow.Controls.android.Ctrl_641211_Choice_Complex.ScriptHelper;
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
		
		clickOn("wf_item_dept1");
//		waitForDispear("inprogress_toast_android");
		waitForMatch("show_A_headid_1",10);
		clickOn("A_click_choice");
		waitForMatch("show_A_all_headid",10);
		clickOn("show_A_Sales");
		waitForMatch("show_A_selected_Sales",10);
		
		clickOn("MENU");
		clickOn("findByheadid");
//		waitForDispear("inprogress_toast_android");
		
		waitForMatch("show_B_dept_id",10);
		clickOn("B_dept_id_200");
		waitForMatch("B_show_all",10);
		
	}
}

