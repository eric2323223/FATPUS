package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.Keys_AllHelper;
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
public class Keys_All extends Keys_AllHelper
{
	/**
	 * Script Name   : <b>Keys_All</b>
	 * Generated     : <b>Sep 30, 2011 8:37:40 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/30
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		//	E2E>
//		callScripts("testscript.Workflow.Keys.*E2E*");
		
//		callScript("testscript.Workflow.Keys.ActivationKey_E2E_1_1");
//		callScript("testscript.Workflow.Keys.ActivationKey_E2E_1_2");
//		callScript("testscript.Workflow.Keys.ActivationKey_E2E_3_1");
//		callScript("testscript.Workflow.Keys.ActivationKey_E2E_3_2");
//		
//		callScript("testscript.Workflow.Keys.Cred_Act_Complex_E2E_1");
//		callScript("testscript.Workflow.Keys.Cred_Act_Complex_E2E_2");
//		callScript("testscript.Workflow.Keys.Cred_Act_Complex_E2E_3");
//		
//		callScript("testscript.Workflow.Keys.CredCacheKey_E2E_1");
//		callScript("testscript.Workflow.Keys.CredCacheKey_E2E_2_1");
//		callScript("testscript.Workflow.Keys.CredCacheKey_E2E_2_2");
		
		//Tooling:
		
//		callScripts("testscript.Workflow.Keys.*","E2E");
		
		
callScripts("testscript.Workflow.ConditionalStartScreen.*E2E");
		
//		////run E2E :0 out of 7 scripts failed, fail rate: 0.0%
		callScripts("testscript.Workflow.AllKindOfMbo.*E2E*");
//		//E2E: 0 out of 10 scripts failed, fail rate: 0.0%
		callScripts("testscript.Workflow.ConditionalScreen.*E2E");
//		//E2E:  0 out of 6 scripts failed, fail rate: 0.0%
		callScripts("testscript.Workflow.EmailInjection.*E2E");
//		
		callScripts("testscript.Workflow.Keys.*E2E*");
		
		
		
		
	}
}

