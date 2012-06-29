package tplan.Workflow.Controls.BB.Ctrl_682462_DynaChoice_HTML;
import resources.tplan.Workflow.Controls.BB.Ctrl_682462_DynaChoice_HTML.ScriptHelper;
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
		waitForMatch("show_dept_about_chioce",10);
		
		clickOn("choice_dept_about_record");
		waitForMatch("show_multi_records",10);
		
		
	}
}

