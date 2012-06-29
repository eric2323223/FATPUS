package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.EmailSub_1_m_Composite_P_SO_E2EHelper;
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
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.Relationship;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class EmailSub_1_m_Composite_P_SO_E2E extends EmailSub_1_m_Composite_P_SO_E2EHelper
{
	/**
	 * Script Name   : <b>EmailSub_1_m_Composite_P_SO_E2E</b>
	 * Generated     : <b>Sep 16, 2011 4:57:52 PM</b>
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
		//add record to be O2M:
		//	EE.runSQL(new ScrapbookCP().database("sampledb")
		//	.type("Sybase_ASA_12.x").name("My Sample Database"), 
		//	GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Screens/setup/add_record.sql");
	
	EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_a (dba)");
	EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_b (dba)");
	WN.createRelationship(new Relationship()
		.startParameter(WN.mboPath(Cfg.projectName, "Wf_ff_a"))
		.target("Wf_ff_b")
		.mapping("aid,aid")
		.composite("true")
		.type(Relationship.TYPE_OTM));
	
	WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
			.server("My Unwired Server")
				.mode(DeployOption.MODE_REPLACE)
			.serverConnectionMapping("My Sample Database,sampledb"));
	
	WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
			.name("myWF")
			.option(WorkFlow.SP_SERVER_INIT)
			.mbo("Wf_ff_a")
			.objectQuery("findByPrimaryKey")
			.subject("dept_id=1")
			.subjectMatchingRule("dept_id=")
			.setParameterValue("aid,Subject,dept_id="));
	
	WFCustomizer.runTest(new WorkFlowPackage()
	.startParameter(WN.filePath(Cfg.projectName, "myWF"))
	.assignToUser(Cfg.deviceUser)
	.unwiredServer("My Unwired Server")
	.deployToServer("true"), 
	customTestScript(), 
//	"tplan.Workflow.iconcommon.BB.server_dt_icon.Script",
	new CallBackMethod().receiver(WorkFlowEditor.class)
		.methodName("sendNotification")
		.parameter(new Email().to(Cfg.deviceUser)
				.subject("dept_id=1")));
	
	WFCustomizer.verifyResult(new WFClientResult().data(
			"id=Wf_ff_a_aid_attribKey,value=1|" +
			"id=Wf_ff_a_aname_attribKey,value=Aone|" +
			"list_items_count=2|"+
			"id=Wf_ff_b_bid_attribKey,value=1|" +
			"id=Wf_ff_b_aid_attribKey,value=1|" +
			"id=Wf_ff_b_bname_attribKey,value=Bone" ));
}

private CustomJsTestScript customTestScript() {
	CustomJsTestScript s = new CustomJsTestScript();
	s.screen("WfffaDetail").getData("Wf_ff_a_aid_attribKey");
	s.screen("WfffaDetail").getData("Wf_ff_a_aname_attribKey");
	s.screen("WfffaDetail").moveTo("Wff_b").throughMenuItem("Open Wfffb");
	
	s.screen("Wfffb").getListItemsCount();
	s.screen("Wfffb").moveTo("WfffbDetail").throughListItem("1->0");
	
	s.screen("WfffbDetail").getData("Wf_ff_b_bid_attribKey");
	s.screen("WfffbDetail").getData("Wf_ff_b_aid_attribKey");
	s.screen("WfffbDetail").getData("Wf_ff_b_bname_attribKey");	
	
	return s;
	
	}
}
//passed BB6T 20120206
