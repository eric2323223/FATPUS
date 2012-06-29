package testscript.Workflow.ConditionalStartScreen.setup;
import resources.testscript.Workflow.ConditionalStartScreen.setup.CdtnSP_AllHelper;
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
 * @author min zhao
 */
public class CdtnSP_All extends CdtnSP_AllHelper
{
	/**
	 * Script Name   : <b>CdtnSP_All</b>
	 * Generated     : <b>May 9, 2012 3:23:08 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/05/09
	 * @author min zhao
	 */
	public void testMain(Object[] args) 
	{
	// TODO Insert code here
		callScripts("testscript.Workflow.ConditionalStartScreen.*", "E2E");
	}
}

