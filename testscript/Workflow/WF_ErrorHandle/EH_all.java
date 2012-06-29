package testscript.Workflow.WF_ErrorHandle;
import resources.testscript.Workflow.WF_ErrorHandle.EH_allHelper;
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
import component.entity.MainMenu;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class EH_all extends EH_allHelper
{
	/**
	 * Script Name   : <b>EH_all</b>
	 * Generated     : <b>Feb 4, 2012 4:54:13 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/02/04
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		callScripts("testscript.Workflow.WF_ErrorHandle.*");
		
//		callScript("testscript.Workflow.WF_ErrorHandle.EH_633845_Improved_ErrorMsg_3");
//		callScript("testscript.Workflow.WF_ErrorHandle.EH_633845_Improved_ErrorMsg_4");
//		callScript("testscript.Workflow.WF_ErrorHandle.ErrorMessage_1_1");
//		callScript("testscript.Workflow.WF_ErrorHandle.ErrorMessage_1_2");
//		callScript("testscript.Workflow.WF_ErrorHandle.ErrorMessage_1_3");
//		callScript("testscript.Workflow.WF_ErrorHandle.ErrorMessage_2");
//		callScript("testscript.Workflow.WF_ErrorHandle.ErrorMessage_3");
		callScript("testscript.Workflow.WF_ErrorHandle.ErrorMessage_4_1");
		callScript("testscript.Workflow.WF_ErrorHandle.ErrorMessage_4_2");
//		callScript("testscript.Workflow.WF_ErrorHandle.ErrorMessage_4_3");

		
		
	}
}

