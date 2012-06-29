package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.ActivationKey_2Helper;
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

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.Module;
import component.entity.model.StartPoint;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class ActivationKey_2 extends ActivationKey_2Helper
{
	/**
	 * Script Name   : <b>ActivationKey_2</b>
	 * Generated     : <b>Sep 30, 2011 8:27:55 AM</b>
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
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF_Activation_1")
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_ACTIVATE));
		
		PropertiesView.editModule(new Module().activeKey("Mykey"));
		MainMenu.saveAll();
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF_Activation_2")
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_ACTIVATE)	);
		
		PropertiesView.editModule(new Module().activeKey("Mykey"));
		MainMenu.saveAll();
		
    	vpManual("getMse", "" , PropertiesView.getStatusMessage()).performTest();
    	vpManual("haserror", 0 , Problems.getErrors().size()).performTest();
		
    	WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF_Activation_1"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser(Cfg.deviceUser)
			.verifyResult("Successfully deployed the workflow", true));
    	
    	WN.createWorkFlowPackage(new WorkFlowPackage()
    		.startParameter(WN.filePath(Cfg.projectName, "myWF_Activation_2"))
    		.unwiredServer("My Unwired Server")
    		.deployToServer("true")
    		.assignToSelectedUser(Cfg.deviceUser)
    		.verifyResult("Successfully deployed the workflow", true));
	}
}
//passed
