package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.FS_Email_01_E2EHelper;
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
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.DeployedMbo;
import component.entity.model.Email;
import component.entity.model.StartPoint;
import component.entity.model.WFEditBox;
import component.entity.model.WFLabel;
import component.entity.model.WFLview;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class FS_Email_01_E2E extends FS_Email_01_E2EHelper
{
	/**
	 * Script Name   : <b>FS_Email_01_E2E_Android</b>
	 * Generated     : <b>Sep 19, 2011 2:14:22 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/19
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		
//		//new add table "Wf_ff_a",which has 2 records:
//	EE.runSQL(new ScrapbookCP().database("sampledb")
//	.type("Sybase_ASA_12.x").name("My Sample Database"), 
//	GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Screens/setup/add_a_b.sql");
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_a (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
     			.mode(DeployOption.MODE_REPLACE)
     			.server("My Unwired Server")
     			.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		
		
		WorkFlowEditor.addScreen("myscreen1");
		MainMenu.saveAll();
		WorkFlowEditor.addScreen("myscreen");
		MainMenu.saveAll();
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_SERVER_INIT)
				.mbo("Wf_ff_a")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall"));
		
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "myscreen");
		WorkFlowEditor.addMenuItem("myscreen",new WFScreenMenuItem().name("Open myscreen1").type("Open").screen("myscreen1"));
		WorkFlowEditor.addWidget("myscreen", new WFLview().NewKeyBindMBOQueryRequest("list,list,Wf_ff_a,aid,aname"));
		
		PropertiesView.addCell("0", "cell line 0", "key1", "100");
		PropertiesView.addCell("1", "cell line 1", "key2", "100");
		
		WorkFlowEditor.addWidget("myscreen1",new WFLabel().name("label").newKey("label,string"));
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.assignToUser(Cfg.deviceUser)
			.unwiredServer("My Unwired Server")
			.deployToServer("true"), 
			customTestScript(), 
//			"tplan.Workflow.iconcommon.BB.server_dt_icon.Script",
			new CallBackMethod().receiver(WorkFlowEditor.class)
			.methodName("sendNotification")
			.parameter(new Email()
			.unwiredServer("My Unwired Server")
			.to(Cfg.deviceUser)
			.subject("findall")));
		
		WFCustomizer.verifyResult(new WFClientResult().data(
				"list_items_count=2"
				));
	}

		private CustomJsTestScript customTestScript() {
			CustomJsTestScript s = new CustomJsTestScript();
			s.screen("myscreen").getListItemsCount();
			s.screen("myscreen").moveTo("myscreen1").throughMenuItem("Open myscreen1");
			s.screen("myscreen1").getCurrentScreen();
			return s;
		}
}
//passed BB6T 20120208
