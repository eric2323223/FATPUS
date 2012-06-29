package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_CredentialRequest_DB_RemovedHelper;
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
import com.sybase.automation.framework.widget.DOF;

import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_CredentialRequest_DB_Removed extends SP_CredentialRequest_DB_RemovedHelper
{
	/**
	 * Script Name   : <b>SP_CredentialRequest_DB_Removed</b>
	 * Generated     : <b>Sep 9, 2011 10:59:59 AM</b>
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
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_CREDENTIAL_REQUEST));
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFCredentialRequestFlowStartingPointFigure().click();
		PropertiesView.setNewKeyServer("key1,string");
		vpManual("Properies", true, PropertiesView.getStartPoint(WorkFlow.SP_CREDENTIAL_REQUEST).getKey().contains("key1,string")).performTest();
		MainMenu.saveAll();
		
		PropertiesView.removeKey("key1");
		MainMenu.saveAll();
		vpManual("Properies", false, PropertiesView.getStartPoint(WorkFlow.SP_CREDENTIAL_REQUEST).getKey().contains("key1,string")).performTest();
		
		WN.closeAll();
		
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFCredentialRequestFlowStartingPointFigure().click();
		vpManual("Properies", false, PropertiesView.getStartPoint(WorkFlow.SP_CREDENTIAL_REQUEST).getKey().contains("key1,string")).performTest();
	}
}
//passed
