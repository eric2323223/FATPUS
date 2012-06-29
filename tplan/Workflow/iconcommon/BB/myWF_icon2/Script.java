package tplan.Workflow.iconcommon.BB.myWF_icon2;
import resources.tplan.Workflow.iconcommon.BB.myWF_icon2.ScriptHelper;
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
import component.entity.tplan.BlackBerryTestScript;
import component.entity.tplan.TplanTest;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Script extends BlackBerryTestScript{

	@Override
	@TplanTest(
			comparisonMethod = "searchbinary", 
			waitFor = "1s", 
			matchRate = 95f,
			matchArea="desktop"
	)
	public void doTest() throws Exception {
		connect("10.56.252.156", "test");
		openWorkflowEntry("myWF_BB6");
		
//		clickOn("myWF_BB6*");
	}
}

