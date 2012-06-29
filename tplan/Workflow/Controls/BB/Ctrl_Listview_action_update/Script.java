package tplan.Workflow.Controls.BB.Ctrl_Listview_action_update;
import resources.tplan.Workflow.Controls.BB.Ctrl_Listview_action_update.ScriptHelper;
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
import com.sybase.automation.framework.common.SpecialKeys;

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
		
		clickOn("wf_item_findall");
		waitForMatch("show_blistview_1",10);
		
		//enter detail of record
		clickOn("click_blistview_record");
		waitForMatch("show_bdetailscreen_3",10);
		
		clickOn("MENU_BB");
		clickOn("open_screen_alistview");
		waitForMatch("show_alistview_4",10);
		
		clickOn("click_alistview_record");
		waitForMatch("show_adetailscreen",10);
		
		//excute update "aid=2,bid=11" operation;
		clickOn("MENU_BB");
		clickOn("Open_screen_updateA");
		
		waitForMatch("show_update_A",10);
		
		clickOn("update_A_bid");
		clickOn("DELETE_BB");
		clickOn("DELETE_BB");
		type("11");
		
		clickOn("MENU_BB");
		clickOn("update");
		waitForMatch("after_update",10);
		
		//submit update operation
		clickOn("BACK_BB");
		clickOn("BACK_BB");
		clickOn("MENU_BB");
		clickOn("submit");
		
		clickOn("MENU_BB");
		clickOn("switch_Application_BB");
		clickOn("Message_BB");
		
		
		
	}
}
