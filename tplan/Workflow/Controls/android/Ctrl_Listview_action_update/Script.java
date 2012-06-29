package tplan.Workflow.Controls.android.Ctrl_Listview_action_update;
import resources.tplan.Workflow.Controls.android.Ctrl_Listview_action.ScriptHelper;
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
		
		clickOn("wf_item_bid22");
//		waitForDispear("inprogress_toast_android");
		waitForMatch("show_b_listView_1",10);
		
		//enter detail of record
		clickOn("show_b_listView_1");
		waitForMatch("show_b_detailscreen_2",10);
		
		clickOn("MENU");
		clickOn("finda");
		waitForMatch("show_a_listView_3",10);
		
		//enter detail of record "click_alistvIEW_4"
		clickOn("click_alistvIEW_4");
		waitForMatch("show_clicked_alistview_5",10);
		
		//excute update to "aid=2,bid=2" operation:
		clickOn("MENU");
		clickOn("Open_Screen_update_a");
		waitForMatch("show_clicked_alistview_5",10);
		clickOn("click_bid22");
		clickOn("DELETE_BUTTON");
		
		clickOn("MENU");
		clickOn("update");
		waitForMatch("after_update",10);
		
		clickOn("BACK");
		
		clickOn("BACK");
		clickOn("submit");
		
	}
}
