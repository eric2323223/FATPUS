package testscript.Workflow.Deploy;
import resources.testscript.Workflow.Deploy.Deploy_allHelper;
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
public class Deploy_all extends Deploy_allHelper
{
	/**
	 * Script Name   : <b>Deploy_all</b>
	 * Generated     : <b>Jan 31, 2012 1:58:45 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/01/31
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
//		callScripts("testscript.Workflow.Deploy.*", "E2E");
		
		callScript("testscript.Workflow.Deploy.DPLY_PKG_CreateZip_5");
		callScript("testscript.Workflow.Deploy.DPLY_PKG_Create_Err_1");
		callScript("testscript.Workflow.Deploy.DPLY_PKG_Create_Err_2");
		callScript("testscript.Workflow.Deploy.D_659790_AutoSave_Before_Gen");
		callScript("testscript.Workflow.Deploy.D_689898_Screen_Delete");
		callScript("testscript.Workflow.Deploy.D_690360_Version_Workflowjs");
		callScript("testscript.Workflow.Deploy.Initial_New_NoAssign");
		callScript("testscript.Workflow.Deploy.Standard_Replace_Assigned_1");
		callScript("testscript.Workflow.Deploy.Standard_Replace_Unassigned_1");
		callScript("testscript.Workflow.Deploy.Standard_Update_Assigned_1");
		callScript("testscript.Workflow.Deploy.Standard_Update_Assigned_2");
		callScript("testscript.Workflow.Deploy.Standard_Update_Assigned_3");
		callScript("testscript.Workflow.Deploy.workflow_Wizard_ServerProfile");
	}
}

