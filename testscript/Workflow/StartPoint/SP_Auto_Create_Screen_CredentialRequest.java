package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Auto_Create_Screen_CredentialRequestHelper;
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
public class SP_Auto_Create_Screen_CredentialRequest extends SP_Auto_Create_Screen_CredentialRequestHelper
{
	/**
	 * Script Name   : <b>SP_Auto_Create_Screen_CredentialRequest</b>
	 * Generated     : <b>Sep 9, 2011 2:48:57 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/09
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CREDENTIAL_REQUEST));
		
		vpManual("hasscreen", true, WorkFlowEditor.hasScreen("Credential Request")).performTest();
		vpManual("hasscreen", "GoTo", WorkFlowEditor.getLinkTypeBetween(WorkFlow.SP_CREDENTIAL_REQUEST,"Credential Request")).performTest();
		
		System.out.print("keys="+PropertiesView.getStartPoint(WorkFlow.SP_CREDENTIAL_REQUEST).getKey());
		vpManual("haskey", "cc_username,string:cc_password,string", PropertiesView.getStartPoint(WorkFlow.SP_CREDENTIAL_REQUEST).getKey()).performTest();
	
		vpManual("haserror",1,Problems.getErrors().size()).performTest();
	}
}
//passed
