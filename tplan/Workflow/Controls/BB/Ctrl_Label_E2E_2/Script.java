package tplan.Workflow.Controls.BB.Ctrl_Label_E2E_2;
import resources.tplan.Workflow.Controls.BB.Ctrl_Label_E2E_2.ScriptHelper;
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
import component.entity.tplan.TplanTest;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Script extends AndroidTestScript{

	@Override
	@TplanTest(
			comparisonMethod="searchbinary",
			waitFor="3s"
	)
	public void doTest() throws Exception {
		connect("10.56.252.156", "test");
		
//		System.out.println(exitCode());
//		startWorkflowApplication("sybase");
		
		clickOn("wf_dt_dept100*");
		waitForMatch("show_label",10);
		
	}
}

