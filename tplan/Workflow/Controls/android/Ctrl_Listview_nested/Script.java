package tplan.Workflow.Controls.android.Ctrl_Listview_nested;
import resources.tplan.Workflow.Controls.android.Ctrl_Listview_nested.ScriptHelper;
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
		
		clickOn("wf_item_cid111");
//		waitForDispear("inprogress_toast_android");
		
		waitForMatch("show_clistview_1",10);
		
		clickOn("show_clistview_1");
		waitForMatch("show_cDetailScreen_2",10);
		
		clickOn("MENU");
		clickOn("findb");
		waitForMatch("show_blistview_3",10);
		clickOn("click_blistview_record");
		waitForMatch("show_bDetailScreen_4",10);
		
		clickOn("MENU");
		clickOn("finda");
		
		waitForMatch("show_alistview_record",10);
	}
}

