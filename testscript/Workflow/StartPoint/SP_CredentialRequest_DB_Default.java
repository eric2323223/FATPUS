package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_CredentialRequest_DB_DefaultHelper;
import testscript.Workflow.cfg.Cfg;



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
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_CredentialRequest_DB_Default extends SP_CredentialRequest_DB_DefaultHelper
{
	/**
	 * Script Name   : <b>SP_CredentialRequest_DB_Default</b>
	 * Generated     : <b>Sep 13, 2011 10:58:04 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/13
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWFs")
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_CREDENTIAL_REQUEST));
		
		vpManual("hasscreen", true, WorkFlowEditor.hasScreen("Credential Request")).performTest();
		System.out.print("keys="+PropertiesView.getStartPoint(WorkFlow.SP_CREDENTIAL_REQUEST).getKey());
		vpManual("haskey", "cc_username,string:cc_password,string", PropertiesView.getStartPoint(WorkFlow.SP_CREDENTIAL_REQUEST).getKey()).performTest();
		
		vpManual("ifbuttondisable",false,PropertiesView.ifbuttondisable("Ed&it...")).performTest();
		vpManual("ifbuttondisable",false,PropertiesView.ifbuttondisable("&Remove")).performTest();
		
	}
}
//passed
