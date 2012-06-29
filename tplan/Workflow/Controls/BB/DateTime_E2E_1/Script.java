package tplan.Workflow.Controls.BB.DateTime_E2E_1;
import resources.tplan.Workflow.Controls.BB.DateTime_E2E_1.ScriptHelper;
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
		
		clickOn("wf_item_t1");
//		waitForDispear("inprogress_toast_android");
		waitForMatch("show_t_olc_record",10);
		
		//open the detail of record
		clickOn("show_t_olc_record");
		waitForMatch("show_d_detail",10);
		
		clickOn("BACK_BB");
		
		waitForMatch("show_t_olc_record",10);
		
		//open the detail of record
		clickOn("show_t_olc_record");
		waitForMatch("show_d_detail",10);
	}
}

