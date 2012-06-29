package tplan.Workflow.Controls.BB.Locale_specific_Language;
import resources.tplan.Workflow.Controls.BB.Locale_specific_Language.ScriptHelper;
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
		
////		System.out.println(exitCode());
////		startWorkflowApplication("sybase");
		
		clickOn("wf_item_t1");
		waitForMatch("show_T_olc_record",10);
		
		// enter into the detail screen to VP:
		clickOn("show_T_olc_record");
		waitForMatch("french_c_d",10);
		
		//back to message page
		clickOn("MENU_BB");
		clickOn("change_application");
		clickOn("MESSAGE_BB");
		
	}
}

