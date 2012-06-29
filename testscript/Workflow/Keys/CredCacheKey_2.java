package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.CredCacheKey_2Helper;
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

import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Module;
import component.entity.model.StartPoint;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class CredCacheKey_2 extends CredCacheKey_2Helper
{
	/**
	 * Script Name   : <b>CredCacheKey_2</b>
	 * Generated     : <b>Sep 30, 2011 8:09:05 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/30
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF_credential_1")
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_CREDENTIAL_REQUEST));
		
		PropertiesView.editModule(new Module().credentialCacheKey("Mykey"));
		MainMenu.saveAll();
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF_credential_2")
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_CREDENTIAL_REQUEST));
		
		PropertiesView.editModule(new Module().credentialCacheKey("Mykey"));
		MainMenu.saveAll();
		
    	vpManual("getMse", "" , PropertiesView.getStatusMessage()).performTest();
    	vpManual("haserror", 0 , Problems.getErrors().size()).performTest();
		
    	WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF_credential_1"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser(Cfg.deviceUser)
			.verifyResult("Successfully deployed the workflow", true));
    	
    	WN.createWorkFlowPackage(new WorkFlowPackage()
    		.startParameter(WN.filePath(Cfg.projectName, "myWF_credential_2"))
    		.unwiredServer("My Unwired Server")
    		.deployToServer("true")
    		.assignToSelectedUser(Cfg.deviceUser)
    		.verifyResult("Successfully deployed the workflow", true));
	}
}
//passed
