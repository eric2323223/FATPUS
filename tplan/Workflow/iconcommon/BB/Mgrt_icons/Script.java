package tplan.Workflow.iconcommon.BB.Mgrt_icons;
import resources.tplan.Workflow.iconcommon.BB.Mgrt_icons.ScriptHelper;
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
	 * Generated     : <b>Apr 23, 2012 2:57:57 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/04/23
	 * @author jiaozhou
	 */
	@Override
	@TplanTest(
			comparisonMethod = "searchbinary", 
			waitFor = "1s", 
			matchRate = 95f,
			matchArea="rectangle:105,233,387,590"
	)
	public void doTest() throws Exception {
		connect("10.56.252.196", "sybase");
		openWorkflowEntry("Mgrt_BB6_6");
	}
	
	public static void main(String[] args){
		Robot.run("tplan.Workflow.iconcommon.BB.Mgrt_icons.Script");
	}
}

