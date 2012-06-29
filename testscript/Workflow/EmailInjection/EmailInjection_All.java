package testscript.Workflow.EmailInjection;
import resources.testscript.Workflow.EmailInjection.EmailInjection_AllHelper;
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
public class EmailInjection_All extends EmailInjection_AllHelper
{
	/**
	 * Script Name   : <b>EmailInjection_All</b>
	 * Generated     : <b>Oct 24, 2010 9:57:36 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/10/24
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//E2E:  0 out of 6 scripts failed, fail rate: 0.0%
		callScripts("testscript.Workflow.EmailInjection.*E2E");
	
		//Tooling:
		callScripts("testscript.Workflow.EmailInjection.*","E2E");
		
	}
}

