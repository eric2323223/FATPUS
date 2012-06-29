package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.ActivationKey_E2E_1_2Helper;
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
import component.entity.GlobalConfig;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Module;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class ActivationKey_E2E_1_2 extends ActivationKey_E2E_1_2Helper
{
	/**
	 * Script Name   : <b>ActivationKey_E2E_1_2</b>
	 * Generated     : <b>Oct 27, 2011 5:30:32 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/27
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		
		// Note:there are 2 records in testtable:
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Keys/setup/createTable.sql");
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->testtable (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_ACTIVATE));
		
		PropertiesView.editModule(new Module().activeKey("MyActivtionKey"));
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Testtable");
		WorkFlowEditor.addMenuItem("Start",new WFScreenMenuItem().name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Testtable")
				.objectQuery("findAll")
				.defaultSuccessScreen("Testtable"));
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.assignToUser(Cfg.deviceUser)
			.unwiredServer("My Unwired Server")
			.deployToServer("true"), 
			customTestScript()
//			, 
//			"tplan.Workflow.iconcommon.BB.myWF_icon.Script"
			);
	
	WFCustomizer.verifyResult(new WFClientResult().data("list_items_count=2"));
}
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Activate").moveTo("Start").throughMenuItem("Submit Workflow");
		s.screen("Start").moveTo("Testtable").throughMenuItem("findAll");
		s.screen("Testtable").getListItemsCount();
		return s;
	}
}
//passed 20120131
