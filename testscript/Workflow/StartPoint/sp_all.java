package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.sp_allHelper;
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
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.ListHelper;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class sp_all extends sp_allHelper
{
	/**
	 * Script Name   : <b>sp_all</b>
	 * Generated     : <b>Sep 9, 2011 2:03:25 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/09
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
//		callScripts("testscript.Workflow.StartPoint.*");
		
		
		callScript("testscript.Workflow.StartPoint.SP_Auto_Create_Screen_CredentialRequest");//pass
//		callScript("testscript.Workflow.StartPoint.SP_Cannot_Dup_SP_DT");
		callScript("testscript.Workflow.StartPoint.SP_CredentialRequest_DB_Default");//pass
		callScript("testscript.Workflow.StartPoint.SP_CredentialRequest_DB_Editable");//pass
//		callScript("testscript.Workflow.StartPoint.SP_Cut_Paste_04");
		callScript("testscript.Workflow.StartPoint.SP_EmailTrg_DefPrj");//pass
		callScript("testscript.Workflow.StartPoint.SP_Email_DB_DT");//pass
		callScript("testscript.Workflow.StartPoint.SP_Email_DB_Editable");//pass
		callScript("testscript.Workflow.StartPoint.SP_Email_DB_mbo_attr_binary");//pass
		callScript("testscript.Workflow.StartPoint.SP_Email_DB_mbo_attr_boolean");//pass
		callScript("testscript.Workflow.StartPoint.SP_Email_DB_mbo_QueryResults");//pass
		callScript("testscript.Workflow.StartPoint.SP_Email_DB_mbo_relationship");//pass
		callScript("testscript.Workflow.StartPoint.SP_Email_DB_Removed");//pass
		callScript("testscript.Workflow.StartPoint.SP_Email_DB_str");//pass
		callScript("testscript.Workflow.StartPoint.SP_Email_Delete_MBO");//pass
		callScript("testscript.Workflow.StartPoint.SP_Email_General_MBO");//pass
		callScript("testscript.Workflow.StartPoint.SP_Must_Have_Name");//pass
		callScript("testscript.Workflow.StartPoint.SP_New_Key_in_Screen");
		callScript("testscript.Workflow.StartPoint.SP_New_Key_in_Screen_2");
	
	}
}

