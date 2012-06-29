package testscript.Workflow.AllKindOfMbo;
import resources.testscript.Workflow.AllKindOfMbo.M_685115_One2One_3level_E2EHelper;
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

import component.entity.EE;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Relationship;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class M_685115_One2One_3level_E2E extends M_685115_One2One_3level_E2EHelper
{
	/**
	 * Script Name   : <b>One2One_3level_E2E_1_1</b>
	 * Generated     : <b>Oct 25, 2011 10:41:03 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->ff_wf_threeLevel_a (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->ff_wf_threeLevel_b (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->ff_wf_threeLevel_c (dba)");
	
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Ff_wf_threeLevel_a"))
			.target("Ff_wf_threeLevel_b")
			.composite("true")
			.bidirectional("true")
			.mapping("aid,aid")
			.type(Relationship.TYPE_OTO));
		WN.createRelationship(new Relationship()
				.startParameter(WN.mboPath(Cfg.projectName, "Ff_wf_threeLevel_b"))
				.target("Ff_wf_threeLevel_c")
				.composite("true")
				.bidirectional("true")
				.mapping("bid,bid")
				.type(Relationship.TYPE_OTO));
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Ff_wf_threeLevel_a");
		
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("aid")
				.newKey("aid,int"));
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		
		WorkFlowEditor.addMenuItem("Start",new WFScreenMenuItem().name("findByaid")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Ff_wf_threeLevel_a")
				.objectQuery("findByPrimaryKey")
				.defaultSuccessScreen("FfwfthreeLevelaDetail")
				.parametermapping("aid,aid"));
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.assignToUser(Cfg.deviceUser)
			.unwiredServer("My Unwired Server")
			.deployToServer("true"), 
			customTestScript()
			
//			, 
//			"tplan.Workflow.iconcommon.BB.myWF_icon.Script"   //USED BB6T
			
			);
		
		WFCustomizer.verifyResult(new WFClientResult()
			.data("id=Ff_wf_threeLevel_a_aid_attribKey,value=1|" +
				"id=Ff_wf_threeLevel_a_aname_attribKey,value=aone|" +
				
				"id=Ff_wf_threeLevel_b_bid_attribKey,value=22|" +
				"id=Ff_wf_threeLevel_b_bname_attribKey,value=twotwo|" +
				"id=Ff_wf_threeLevel_b_aid_attribKey,value=1|"+
				
				"id=Ff_wf_threeLevel_c_cid_attribKey,value=111|" +
				"id=Ff_wf_threeLevel_c_bid_attribKey,value=22|" +
				"id=Ff_wf_threeLevel_c_cname_attribKey,value=cone"));
	}

	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").setData("aid","1");
		s.screen("Start").moveTo("FfwfthreeLevelaDetail").throughMenuItem("findByaid");
		
		s.screen("FfwfthreeLevelaDetail").getData("Ff_wf_threeLevel_a_aid_attribKey");
		s.screen("FfwfthreeLevelaDetail").getData("Ff_wf_threeLevel_a_aname_attribKey");
		
		s.screen("FfwfthreeLevelaDetail").moveTo("FfwfthreeLevelbDetail").throughMenuItem("Open FfwfthreeLevelbDetail");
		
		s.screen("FfwfthreeLevelbDetail").getData("Ff_wf_threeLevel_b_bid_attribKey");
		s.screen("FfwfthreeLevelbDetail").getData("Ff_wf_threeLevel_b_bname_attribKey");
		s.screen("FfwfthreeLevelbDetail").getData("Ff_wf_threeLevel_b_aid_attribKey");
		
		s.screen("FfwfthreeLevelbDetail").moveTo("FfwfthreeLevelcDetail").throughMenuItem("Open FfwfthreeLevelcDetail");
		
		s.screen("FfwfthreeLevelcDetail").getData("Ff_wf_threeLevel_c_cid_attribKey");
		s.screen("FfwfthreeLevelcDetail").getData("Ff_wf_threeLevel_c_bid_attribKey");
		s.screen("FfwfthreeLevelcDetail").getData("Ff_wf_threeLevel_c_cname_attribKey");
		
		return s;
	}
}
//passed 20120118
//passed ANDROID NO tplan