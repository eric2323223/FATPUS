package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.DndCMBO_1_1_N_Composite_N_bi_E2EHelper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Relationship;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DndCMBO_1_1_N_Composite_N_bi_E2E extends DndCMBO_1_1_N_Composite_N_bi_E2EHelper
{
	/**
	 * Script Name   : <b>DndCMBO_1_1_N_Composite_N_bi_E2E</b>
	 * Generated     : <b>Sep 16, 2011 1:53:43 PM</b>
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
	EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_a (dba)");
	EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_b (dba)");
	WN.createRelationship(new Relationship()
		.startParameter(WN.mboPath(Cfg.projectName, "Wf_ff_a"))
		.target("Wf_ff_b")
		.mapping("aid,aid")
		.composite("false")
		.bidirectional("false")
		.type(Relationship.TYPE_OTO));
	
	WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
			.server("My Unwired Server")
			.mode(DeployOption.MODE_REPLACE)
			.serverConnectionMapping("My Sample Database,sampledb"));
	
	WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
			.name("myWF")
			.option(WorkFlow.SP_CLIENT_INIT));
	
	WorkFlowEditor.dragMbo(Cfg.projectName, "Wf_ff_b");
	
	vpManual("Screen", true, WorkFlowEditor.hasScreen("Wfffb")).performTest();
	vpManual("Screen", true, WorkFlowEditor.hasScreen("WfffbDetail")).performTest();
	vpManual("Screen", true, WorkFlowEditor.hasScreen("Wfffbupdateinstance")).performTest();
	
	WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
		.name("findAll")
		.type("Online Request")
		.project(Cfg.projectName)
		.mbo("Wf_ff_b")
		.objectQuery("findAll")
		.defaultSuccessScreen("Wfffb"));

	vpManual("error", 0, Problems.getErrors().size()).performTest();
	
	WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName, "myWF"))
		.assignToUser(Cfg.deviceUser)
		.unwiredServer("My Unwired Server")
		.deployToServer("true"), 
		customTestScript()
//		, 
//		"tplan.Workflow.iconcommon.BB.myWF_icon.Script"
		);

	WFCustomizer.verifyResult(new WFClientResult().data(
			"id=Wf_ff_b_bid_attribKey,value=1|" +
			"id=Wf_ff_b_aid_attribKey,value=1|" +
			"id=Wf_ff_b_bname_attribKey,value=Bone" ));
}

	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").moveTo("Wfffb").throughMenuItem("findAll");
		s.screen("Wfffb").moveTo("WfffbDetail").throughListItem("1->0");
		
		s.screen("WfffbDetail").getData("Wf_ff_b_bid_attribKey");
		s.screen("WfffbDetail").getData("Wf_ff_b_aid_attribKey");
		s.screen("WfffbDetail").getData("Wf_ff_b_bname_attribKey");
		
		return s;
}
}
//passed BB6T 20120202
