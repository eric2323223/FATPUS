package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Validate_Time_AndroidHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.ScrapbookCP;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Validate_Time_Android extends Validate_Time_AndroidHelper
{
	/**
	 * Script Name   : <b>Validate_Time_Android</b>
	 * Generated     : <b>Aug 17, 2011 9:44:44 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/08/17
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().type("Sybase_ASA_12.x")
				.name("My Sample Database")
				.database("sampledb"), Cfg.create_table_sql);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->T_olc (dba)", 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name(
				"wfValidate_Time").option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "T_olc");
		WorkFlowEditor.link("Start Screen", "T_olc_create");
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
				.startParameter(WN.wfPath(Cfg.projectName, "wfValidate_Time.xbw"))
				.unwiredServer("My Unwired Server")
				.assignToUser("DeviceTest")
				.verifyResult("Successfully deployed the workflow", true));
		
//		vpManual("DeviceTest", true, Robot.run(this).isPass()).performTest();
	}
}

