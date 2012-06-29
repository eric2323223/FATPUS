package testscript.Workflow.Migration;
import resources.testscript.Workflow.Migration.Mgrt_Mark_20To213Helper;
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
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author jiaozhou
 */
public class Mgrt_Mark_20To213 extends Mgrt_Mark_20To213Helper
{
	/**
	 * Script Name   : <b>Mgrt_Mark_20To213</b>
	 * Generated     : <b>May 8, 2012 2:40:56 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/05/08
	 * @author jiaozhou
	 */
	public void testMain(Object[] args) 
	{
		TestResult resultBB = Robot.run("tplan.Workflow.iconcommon.BB.EmailIconMarked.Script");
		vpManual("DeviceTest", true, resultBB.isPass()).performTest();
	}
}

