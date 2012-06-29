package tplan.Workflow.Controls.BB.Ctrl_Listview_action_add;
import resources.tplan.Workflow.Controls.BB.Ctrl_Listview_action_add.ScriptHelper;
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
		
		clickOn("wf_item_findall");
		waitForMatch("show_blistview_1",10);
		
		//enter detail of record
		clickOn("click_blistview_record");
		waitForMatch("show_bdetailscreen_3",10);
		
		clickOn("MENU_BB");
		clickOn("open_screen_alistview");
		waitForMatch("show_alistview_4",10);
		
		
		clickOn("MENU_BB");
		clickOn("add");
		waitForMatch("show_add_A",10);
		
		//excute add "aid=3,bid=22" operation;
		clickOn("add_A_aid");
		clickOn("DELETE_BB");
		type("3");
		
		clickOn("add_A_bid");
		type("22");
		
		clickOn("MENU_BB");
		clickOn("create");
		waitForMatch("after_create",10);
		
// submit the add operation		
		clickOn("BACK_BB");
		clickOn("MENU_BB");
		clickOn("submit");
	}
}

