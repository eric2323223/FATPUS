package testscript.Workflow.Customization;
import resources.testscript.Workflow.Customization.Cust_Overwrite_1Helper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author test
 */
public class Cust_Overwrite_1 extends Cust_Overwrite_1Helper
{
	/**
	 * Script Name   : <b>Cust_Overwrite_1</b>
	 * Generated     : <b>Mar 16, 2012 4:27:54 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/16
	 * @author test
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		
		WN.createWorkFlow(new WorkFlow().name(Cfg.wfName)
				.startParameter(Cfg.projectName)
				.option(WorkFlow.SP_SERVER_INIT)
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll"));
		
		vpManual("generate", false, WN.hasFile(Cfg.projectName, "Generated Workflow")).performTest();
		WN.createWorkFlowPackage(new WorkFlowPackage().startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.unwiredServer("My Unwired Server")
				.deployToServer("false"));
		vpManual("generate", true, WN.hasFile(Cfg.projectName, "Generated Workflow")).performTest();
		vpManual("generate", true, WN.hasFile(WN.generateWorkFlowFilePath(Cfg.projectName, Cfg.wfName, "html->js->Custom.js"))).performTest();
		vpManual("generate", true, WN.hasFile(WN.generateWorkFlowFilePath(Cfg.projectName, Cfg.wfName, "html->css->Stylesheet.css"))).performTest();
		
	}
}

