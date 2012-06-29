package tplan.Workflow.iconcommon.BB.EmailIconMarked;
import resources.tplan.Workflow.iconcommon.BB.EmailIconMarked.ScriptHelper;
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
import component.entity.tplan.BlackBerryTestScript;
import component.entity.tplan.Robot;
import component.entity.tplan.TplanTest;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Script extends BlackBerryTestScript{

	@Override
	@TplanTest(
			comparisonMethod="searchbinary",
			waitFor="3s",
			matchRate = 95f,
			matchArea="rectangle:105,233,387,590"
	)
	public void doTest() throws Exception {
		connect("10.56.252.196", "sybase");
		
//		System.out.println(exitCode());
//		startWorkflowApplication("sybase");
		
		waitForMatch("findall_marked_1",10);
	}
	public static void main(String[] args){
		Robot.run("tplan.Workflow.iconcommon.BB.EmailIconMarked.Script");
	}
}

