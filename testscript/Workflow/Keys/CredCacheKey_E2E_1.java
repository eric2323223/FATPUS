package testscript.Workflow.Keys;
import java.awt.Point;

import resources.testscript.Workflow.Keys.CredCacheKey_E2E_1Helper;
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
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.GeneratedFile;
import component.entity.model.Module;
import component.entity.model.StartPoint;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class CredCacheKey_E2E_1 extends CredCacheKey_E2E_1Helper
{
	/**
	 * Script Name   : <b>CredCacheKey_E2E_1</b>
	 * Generated     : <b>Oct 28, 2011 3:50:47 AM</b>
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
		
////		// Note:there are 2 records in testtable:
////		EE.runSQL(new ScrapbookCP().database("sampledb")
////				.type("Sybase_ASA_12.x").name("My Sample Database"), 
////				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Keys/setup/createTable.sql");

		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->testtable (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF_cre_1")
				WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF1")
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_CREDENTIAL_REQUEST)
				);
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Testtable");
		PropertiesView.editModule(new Module().credentialCacheKey("MyCrediential"));
		PropertiesView.setAuthentication("supAdmin","s3pAdmin");
		
		WorkFlowEditor.addMenuItem("Start",new WFScreenMenuItem().name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Testtable")
				.objectQuery("findAll")
				.defaultSuccessScreen("Testtable"));
		
		WFCustomizer.runTest(new WorkFlowPackage()
//			.startParameter(WN.filePath(Cfg.projectName, "myWF_cre_1"))
			.startParameter(WN.filePath(Cfg.projectName, "myWF1"))
			.assignToUser(Cfg.deviceUser)
			.unwiredServer("My Unwired Server")
			.deployToServer("true"), 
			customTestScript()
//			, 
//			"tplan.Workflow.iconcommon.BB.myWF_icon.Script"
			);
	
	WFCustomizer.verifyResult(new WFClientResult().data("list_items_count=2"));
	
		deleteGeneratedWorkFlowFolder();
	
	//vp2: manual change the crediential key:
	sleep(1);
	PropertiesView.editModule(new Module().credentialCacheKey("MynewCrediential"));
	MainMenu.saveAll();
	WFCustomizer.runTest(new WorkFlowPackage()
//		.startParameter(WN.filePath(Cfg.projectName, "myWF_cre_1"))
		.startParameter(WN.filePath(Cfg.projectName, "myWF1"))
		.assignToUser(Cfg.deviceUser)
		.unwiredServer("My Unwired Server")
		.deployToServer("true"), 
		customTestScript()
//		, 
//		"tplan.Workflow.iconcommon.BB.myWF_icon.Script"
		);

	WFCustomizer.verifyResult(new WFClientResult().data("list_items_count=2"));

	deleteGeneratedWorkFlowFolder();
	
	//vp3:delete the crediential key:
	sleep(1);
	PropertiesView.editModule(new Module().credentialCacheKey(""));
	MainMenu.saveAll();
	WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName, "myWF1"))
		.assignToUser(Cfg.deviceUser)
		.unwiredServer("My Unwired Server")
		.deployToServer("true"), 
		customTestScript()
//		, 
//		"tplan.Workflow.iconcommon.BB.myWF_icon.Script"
		);

	WFCustomizer.verifyResult(new WFClientResult().data("list_items_count=2"));
	
}
	public static void deleteGeneratedWorkFlowFolder() {
		//delete the Generated Workflow->"+"myWF1
		DOF.getWNTree().click(RIGHT,atPath(WN.filePath(Cfg.projectName, "Generated Workflow->"+"myWF1")));
		DOF.getContextMenu().click(atPath("Delete"));
		DOF.getButton(DOF.getDialog("Confirm Resource Delete"), "&Yes").click();
		sleep(3);
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Credential_Request").setData("cc_username", "supAdmin");
		s.screen("Credential_Request").setData("cc_password", "s3pAdmin");
		s.screen("Credential_Request").moveTo("Start").throughMenuItem("Save");
		s.screen("Start").moveTo("Testtable").throughMenuItem("findAll");
		s.screen("Testtable").getListItemsCount();
		return s;
	}
}
//passed 20120131