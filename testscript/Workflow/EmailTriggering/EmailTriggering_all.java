package testscript.Workflow.EmailTriggering;
import resources.testscript.Workflow.EmailTriggering.EmailTriggering_allHelper;
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

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class EmailTriggering_all extends EmailTriggering_allHelper
{
	/**
	 * Script Name   : <b>EmailTriggering_all</b>
	 * Generated     : <b>Feb 4, 2012 4:46:43 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/02/04
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//tooling: 0 out of 11 scripts failed, fail rate: 0.0%
		callScripts("testscript.Workflow.EmailTriggering.*","E2E");
		
		//E2E:
		callScripts("testscript.Workflow.EmailTriggering.*E2E*");
		
	}
}

