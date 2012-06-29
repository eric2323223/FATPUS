package tplan.Workflow.Controls.BB.Ctrl_677226_listview_coincident;
import resources.tplan.Workflow.Controls.BB.Ctrl_677226_listview_coincident.ScriptHelper;
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
		
		clickOn("wf_item_dept1");
//		waitForDispear("inprogress_toast_android");
		
		//vp1:Verify the datas display well on listview screen 
		waitForMatch("show_allDeptRecord",10);
		
		// open the detail screen of the first record 
		clickOn("show_firstRecord");
		waitForMatch("show_DeptFirstRecord_Detail",10);
		
		// click back icon
		clickOn("BACK_BB");
		
		//vp2:Verify the listview is coincidentwith the previous one
		waitForMatch("show_allDeptRecord",10);
		
		clickOn("BACK_BB");
	}
}

