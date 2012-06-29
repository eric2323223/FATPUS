package testscript.Workflow.Controls;
import java.util.ArrayList;

import resources.testscript.Workflow.Controls.Ctrl_Listview_nested_E2EHelper;
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
import com.sybase.automation.framework.common.CDBUtil;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.DeployedMbo;
import component.entity.model.Email;
import component.entity.model.Relationship;
import component.entity.model.WFCheckbox;
import component.entity.model.WFEditBox;
import component.entity.model.WFLview;
import component.entity.model.WFNewscreen;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Ctrl_Listview_nested_E2E extends Ctrl_Listview_nested_E2EHelper
{
	/**
	 * Script Name   : <b>Ctrl_Listview_nested_E2E_Android</b>
	 * Generated     : <b>Aug 30, 2011 6:28:42 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/08/30
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//			.type("Sybase_ASA_12.x").name("My Sample Database"), 
//			GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Controls/setup/createff_wf_abc_nested.sql");
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->ff_wf_a (dba)"), 10, 10);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->ff_wf_ac_b (dba)"), 100, 10);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->ff_wf_c (dba)"), 300, 10);
		WN.createRelationship(new Relationship().startParameter(WN.mboPath(Cfg.projectName, "Ff_wf_c"))
				.name("cb")
				.mapping("cid,cid")
				.composite("true")
				.target("Ff_wf_ac_b")
				.type(Relationship.TYPE_OTM));
		WN.createRelationship(new Relationship().startParameter(WN.mboPath(Cfg.projectName, "Ff_wf_ac_b"))
				.name("ba")
				.mapping("bid,bid")
				.composite("true")
				.target("Ff_wf_a")
				.type(Relationship.TYPE_OTM));
		 WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,sampledb"));
			
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT));
		PropertiesView.jumpStart(new WorkFlow()
				.mbo("Ff_wf_c")
				.objectQuery("findByPrimaryKey")
				.subject("cid=111")
				.subjectMatchingRule("cid=")
				.setParameterValue("cid,Subject,cid="));
		
		WorkFlowEditor.addScreen("cListView");
		WorkFlowEditor.addScreen("cDetailScreen");
		
		WorkFlowEditor.addScreen("bListView");
		WorkFlowEditor.addScreen("bDetailScreen");
		
		WorkFlowEditor.addScreen("aListView");
		WorkFlowEditor.addScreen("aDetailScreen");
		
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT,"cListView");
		
		//1.set cListView:
		WorkFlowEditor.showPropertiesViewInSD("cListView");
		PropertiesView.setNewKeyBindMBOQueryRequest("key2,list,Ff_wf_c,cid,cname");
		
		WorkFlowEditor.addWidget("cListView", new WFLview().key("key2")
						.lvDetailScreen("cDetailScreen"));
		addCell("0","cell line 0","key1","100");
		addCell("1","cell line 1","key2","100");
		
		//2.set cDetailScreen:
		WorkFlowEditor.addWidget("cDetailScreen", new WFEditBox().label("cid:")
				.key("key1"));
		WorkFlowEditor.addWidget("cDetailScreen", new WFEditBox().label("cname:")
				.key("key2"));
		
		WorkFlowEditor.addMenuItem("cDetailScreen", new WFScreenMenuItem().name("findb")
				.project(Cfg.projectName)
				.mbo("Ff_wf_c")
				.type("Online Request")
				.objectQuery("findByPrimaryKey")
				.defaultSuccessScreen("bListView")
				.parametermapping("cid,key1"));
		
		//3.set bListView:
		WorkFlowEditor.addWidget("bListView", new WFLview().key("Ff_wf_c_cb_relationshipKey")
				.lvDetailScreen("bDetailScreen"));
		addCell("0","cell line 0","Ff_wf_ac_b_bid_attribKey","100");
		addCell("1","cell line 1","Ff_wf_ac_b_bname_attribKey","100");
		addCell("2","cell line 2","Ff_wf_ac_b_cid_attribKey","100");
		
		//4.set bDetailScreen:
		WorkFlowEditor.addWidget("bDetailScreen", new WFEditBox().label("bid:")
				.key("Ff_wf_ac_b_bid_attribKey"));
		WorkFlowEditor.addWidget("bDetailScreen", new WFEditBox().label("bname:")
				.key("Ff_wf_ac_b_bname_attribKey"));
		WorkFlowEditor.addWidget("bDetailScreen", new WFEditBox().label("cid:")
				.key("Ff_wf_ac_b_cid_attribKey"));
		
		
		WorkFlowEditor.addMenuItem("bDetailScreen", new WFScreenMenuItem().name("finda")
				.project(Cfg.projectName)
				.mbo("Ff_wf_ac_b")
				.type("Online Request")
				.objectQuery("findByPrimaryKey")
				.defaultSuccessScreen("aListView")
				.parametermapping("bid,Ff_wf_ac_b_bid_attribKey"));
		
		//5.set aListView:
		WorkFlowEditor.addWidget("aListView", new WFLview().key("Ff_wf_ac_b_ba_relationshipKey")
				.lvDetailScreen("aDetailScreen"));
		addCell("0","cell line 0","Ff_wf_a_aid_attribKey","100");
		addCell("1","cell line 1","Ff_wf_a_bid_attribKey","100");
		
		//6.set aDetailScreen:
		WorkFlowEditor.addWidget("aDetailScreen", new WFEditBox().label("aid:")
				.key("Ff_wf_a_aid_attribKey"));
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		WorkFlowEditor.addWidget("aDetailScreen", new WFEditBox().label("bid:")
				.key("Ff_wf_a_bid_attribKey"));
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.assignToUser("dt")
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, "myWF.xbw", new Email()
			.subject("cid=111")
			.to("dt")
			.unwiredServer("My Unwired Server"));

		
		////1.used to Android:
//		TestResult result = Robot.run("tplan.Workflow.Controls.android.Ctrl_Listview_nested.Script");
//		vpManual("DeviceTest", true, result.isPass()).performTest();
		
		////2.used to BB:passed
		TestResult resultBB = Robot.run("tplan.Workflow.Controls.BB.Ctrl_Listview_nested.Script");
		vpManual("DeviceTest", true, resultBB.isPass()).performTest();
		
	}
	
	public void addCell(String order,String cellName,String cellKey,String length){
		TestObject[] boxes = DOF.getWFListViewFigures(DOF.getRoot());
		((GefEditPartTestObject)boxes[0]).click();
		PropertiesView.maximize();
		PropertiesView.clickTab("Cell");
		PropertiesView.clickTab("General");
		PropertiesView.clickTab("Cell");
		
		DOF.getButton(DOF.getGroup(DOF.getRoot(), "Cell Lines"), "&Add").click();
		String[] cellname = TableHelper.getColumnData(DOF.getTable(DOF.getGroup(DOF.getRoot(),"Cell Lines")), 0);
		for(int i=0;i<cellname.length;i++){
			if(cellname[i].equals(cellName))
				TableHelper.clickAtCell(DOF.getTable(DOF.getRoot()),i , 0);
		DOF.getButton(DOF.getGroup(DOF.getRoot(), "Fields for cell line "+order), "&Add").click();
		TopLevelTestObject dialog = DOF.getDialog("Listview Field");
		
		DOF.getCCombo(dialog, "Key:").click();
		DOF.getCCombo(dialog, "Key:").setProperty("text", cellKey);
		
		WO.setTextField(dialog, DOF.getTextField(dialog, "Field width:"), length);
		DOF.getButton(dialog,"OK").click();
		
		sleep(1);
		MainMenu.saveAll();
		PropertiesView.restore();
		}
	}
}

