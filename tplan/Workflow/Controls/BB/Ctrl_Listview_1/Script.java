package tplan.Workflow.Controls.BB.Ctrl_Listview_1;
import resources.tplan.Workflow.Controls.BB.Ctrl_Listview_1.ScriptHelper;
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
		
		clickOn("wf_item_d1");
//		waitForDispear("inprogress_toast_android");
		waitForMatch("show_2record",10);
		
		clickOn("first_record_toclick");
		waitForMatch("show_first_detail",10);
		
		clickOn("MENU_BB");
		clickOn("switch_Application_BB");
		clickOn("Message_BB");
		
	}
}

