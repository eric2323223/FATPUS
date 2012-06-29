package testscript.Workflow.ConditionalScreen;
import resources.testscript.Workflow.ConditionalScreen.ConditionalScreen_allHelper;
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
 * @author eric
 */
public class ConditionalScreen_all extends ConditionalScreen_allHelper
{
	/**
	 * Script Name   : <b>ConditionalScreen_all</b>
	 * Generated     : <b>Feb 1, 2012 12:07:15 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/02/01
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		//tooling:
		//callScripts("testscript.Workflow.ConditionalScreen.*", "E2E");
		
		
		//E2E: 0 out of 10 scripts failed, fail rate: 0.0%
		//callScripts("testscript.Workflow.ConditionalScreen.*E2E");
		
		//all pass in batch mode  0 out of 6 scripts failed, fail rate: 0.0%
		callScript("testscript.Workflow.ConditionalScreen.CdtnScr_Request_CRUD_01_Create_E2E");
		callScript("testscript.Workflow.ConditionalScreen.CdtnScr_Request_CRUD_01_Update_E2E");
		callScript("testscript.Workflow.ConditionalScreen.CdtnScr_Request_CRUD_01_Delete_E2E");
		
		callScript("testscript.Workflow.ConditionalScreen.CdtnScr_Request_CRUD_02_Create_E2E");
		callScript("testscript.Workflow.ConditionalScreen.CdtnScr_Request_CRUD_02_Update_E2E");
		callScript("testscript.Workflow.ConditionalScreen.CdtnScr_Request_CRUD_02_Delete_E2E");
		
		//could run>>>>>>> all pass in batch mode
		callScript("testscript.Workflow.ConditionalScreen.CdtnScr_Request_Alltypes_1_1_E2E");
		callScript("testscript.Workflow.ConditionalScreen.CdtnScr_Request_Alltypes_2_1_E2E");
		callScript("testscript.Workflow.ConditionalScreen.CdtnScr_Result_Alltypes_1_1_E2E");
		callScript("testscript.Workflow.ConditionalScreen.CdtnScr_Result_Alltypes_2_1_E2E");
	
	}
}

