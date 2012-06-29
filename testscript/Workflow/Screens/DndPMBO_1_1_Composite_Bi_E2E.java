package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.DndPMBO_1_1_Composite_Bi_E2EHelper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Relationship;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DndPMBO_1_1_Composite_Bi_E2E extends DndPMBO_1_1_Composite_Bi_E2EHelper
{
	/**
	 * Script Name   : <b>DndPMBO_1_1_Composite_Bi_E2E_Android</b>
	 * Generated     : <b>Sep 16, 2011 2:19:22 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/16
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Screens/setup/add_a_b.sql");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_a (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_b (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Wf_ff_a"))
			.target("Wf_ff_b")
			.mapping("aid,aid")
			.composite("true")
			.bidirectional("true")
			.type(Relationship.TYPE_OTO));
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Wf_ff_a");
		vpManual("Screen", true, WorkFlowEditor.hasScreen("Wfffa")).performTest();
		vpManual("Screen", true, WorkFlowEditor.hasScreen("WfffaDetail")).performTest();
		vpManual("Screen", true, WorkFlowEditor.hasScreen("Wfffacreate")).performTest();
		
		vpManual("Screen", true, WorkFlowEditor.hasScreen("WfffbDetail")).performTest();
		vpManual("Screen", true, WorkFlowEditor.hasScreen("Wfffbupdateinstance")).performTest();
		
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
			.name("findAll")
			.type("Online Request")
			.project(Cfg.projectName)
			.mbo("Wf_ff_a")
			.objectQuery("findAll")
			.defaultSuccessScreen("Wfffa"));

		vpManual("error", 0, Problems.getErrors().size()).performTest();
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.assignToUser(Cfg.deviceUser)
			.unwiredServer("My Unwired Server")
			.deployToServer("true"), 
			customTestScript()
//			, 
//			Cfg.tplanScript_client_1
			);

		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=Wf_ff_a_aid_attribKey,value=1|" +
				"id=Wf_ff_a_aname_attribKey,value=Aone|" +
				
				"id=Wf_ff_b_bid_attribKey,value=1|" +
				"id=Wf_ff_b_aid_attribKey,value=1|" +
				"id=Wf_ff_b_bname_attribKey,value=Bone" ));
	}

		private CustomJsTestScript customTestScript() {
			CustomJsTestScript s = new CustomJsTestScript();
			s.screen("Start").moveTo("Wfffa").throughMenuItem("findAll");
			s.screen("Wfffa").moveTo("WfffaDetail").throughListItem("1->0");
			s.screen("WfffaDetail").getData("Wf_ff_a_aid_attribKey");
			s.screen("WfffaDetail").getData("Wf_ff_a_aname_attribKey");
			
			s.screen("WfffaDetail").moveTo("WfffbDetail").throughMenuItem("Open WfffbDetail");
			s.screen("WfffbDetail").getData("Wf_ff_b_bid_attribKey");
			s.screen("WfffbDetail").getData("Wf_ff_b_aid_attribKey");
			s.screen("WfffbDetail").getData("Wf_ff_b_bname_attribKey");
			return s;
	}
		
}
//passed BB6T 20120308
