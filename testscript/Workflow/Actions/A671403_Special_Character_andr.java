package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.A671403_Special_Character_andrHelper;
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
public class A671403_Special_Character_andr extends A671403_Special_Character_andrHelper
{
	/**
	 * Script Name   : <b>A671403_Special_Character_andr</b>
	 * Generated     : <b>Oct 31, 2011 6:47:08 PM</b>
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
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/AllDT_ddl.sql");
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Actions/conf/AllDT_data.sql");
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllDT (dba)");
		WN.createObjectQuery(new ObjectQuery()
				.name("ObjQuery")
				.startParameter(WN.mboPath(Cfg.projectName, "AllDT"))
				.parameter("specialchar,string,true,string2")
				.queryDefinition("SELECT x.* FROM AllDT x WHERE x.string2 = :specialchar")
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
		WorkFlowEditor.dragMbo(Cfg.projectName, "AllDT");
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("str")
							   .logicalType("TEXT")
							   .newKey("str,string")
							   .setDefaultValue("Q\\A"));
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("objQuery")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("AllDT")
				.objectQuery("ObjQuery")
				.parametermapping("specialchar,str")
				.defaultSuccessScreen("AllDT"));
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
				"id=AllDT_string2_attribKey,value=Q\\A"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("AllDT").throughMenuItem("objQuery");
		s.screen("AllDT").checkListItem("Q\\\\A", "2");
		s.screen("AllDT").moveTo("AllDTDetail").throughListItem("Q\\\\A->2");
		s.screen("AllDTDetail").getData("AllDT_string2_attribKey");
		
		return s;
	}
}