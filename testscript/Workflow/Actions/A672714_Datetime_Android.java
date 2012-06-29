package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.A672714_Datetime_AndroidHelper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.GlobalConfig;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.ObjectQuery;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.wizard.ObjectQueryWizard;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class A672714_Datetime_Android extends A672714_Datetime_AndroidHelper
{
	/**
	 * Script Name   : <b>A672714_Datetime_Android</b>
	 * Generated     : <b>Oct 31, 2011 6:26:25 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/31
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/DiffTZ.sql");
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/DiffTZ_data.sql");
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->DiffTZ (dba)");
		WN.createObjectQuery(new ObjectQuery()
				.name("findbyparam")
				.startParameter(WN.mboPath(Cfg.projectName, "DiffTZ"))
				.parameter("d,datetime,true,d")
				.queryDefinition("SELECT x.* FROM DiffTZ x WHERE x.d = :d")
				.returnType(ObjectQueryWizard.RT_MULTIPLE));
		//
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
			.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "DiffTZ");
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("d")
							   .logicalType("DATETIME")
							   .newKey("d,DateTime")
							   .setDefaultValue("2011-10-27T08:00:00"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findbyparam")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("DiffTZ")
				.objectQuery("ObjQuery")
				.parametermapping("d,d")
				.defaultSuccessScreen("DiffTZ"));
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		//
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser("supAdmin")
			.unwiredServer("My Unwired Server")
			.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
			customTestScript(), 
			"tplan.Workflow.common.StartWF_android");
		WFCustomizer.verifyResult(new WFClientResult().data(
				"found=true|"+
				"id=d,value=2011-10-27T08:00:00"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("DiffTZ").throughMenuItem("findbyparam");
		s.screen("DiffTZ").checkListItem("2", "0");
		s.screen("DiffTZ").moveTo("Start_Screen").throughMenuItem("Cancel Screen");
		s.screen("Start_Screen").getData("d");
		
		return s;
	}
}
