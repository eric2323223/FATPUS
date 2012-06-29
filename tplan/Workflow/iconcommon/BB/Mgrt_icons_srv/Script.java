package tplan.Workflow.iconcommon.BB.Mgrt_icons_srv;
import resources.tplan.Workflow.iconcommon.BB.Mgrt_icons_srv.ScriptHelper;
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
import component.entity.tplan.Robot;
import component.entity.tplan.TplanTest;

/**
 * Description   : Functional Test Script
 * @author jiaozhou
 */
public class Script extends BlackBerryTestScript
{
	/**
	 * Script Name   : <b>Script</b>
	 * Generated     : <b>Apr 25, 2012 3:25:51 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/04/25
	 * @author jiaozhou
	 */
		@Override
		@TplanTest(
				comparisonMethod = "searchbinary", 
				waitFor = "1s", 
				matchRate = 95f,
				matchArea="desktop"
					)
	public void doTest() throws Exception {
		connect("10.56.252.196", "sybase");
		openMessageEntry("Mgrt_BB6");
	}
	
	public static void main(String[] args){
		Robot.run("tplan.Workflow.iconcommon.BB.Mgrt_icons_srv.Script");
	}
}

