package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Listview_action_E2E_2Helper;
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
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.Relationship;
import component.entity.model.WFEditBox;
import component.entity.model.WFLview;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

import java.util.ArrayList;
/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Ctrl_Listview_action_E2E_2 extends Ctrl_Listview_action_E2E_2Helper
{
	/**
	 * Script Name   : <b>Ctrl_Listview_action_E2E_2</b>
	 * Generated     : <b>Dec 23, 2011 4:08:50 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/12/23
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
////////////////      VP:finished update operation    /////////////////////
		WN.useProject(Cfg.projectName);
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//			.type("Sybase_ASA_12.x").name("My Sample Database"), 
//			GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Controls/setup/createff_wf_ab.sql");
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->ff_wf_a (dba)"), 10, 10);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->ff_wf_b (dba)"), 100, 10);
		WN.createRelationship(new Relationship().startParameter(WN.mboPath(Cfg.projectName, "Ff_wf_b"))
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
					.mbo("Ff_wf_b")
					.objectQuery("findAll")
					.subject("findall")
					.subjectMatchingRule("findall"));
			
			WorkFlowEditor.addScreen("bListView");
			WorkFlowEditor.addScreen("bDetailScreen");
			
			WorkFlowEditor.addScreen("aListView");
			WorkFlowEditor.addScreen("aDetailScreen");
			
			WorkFlowEditor.addScreen("add_A");
			WorkFlowEditor.addScreen("update_A");
			
			WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT,"bListView");
			
			//1.set bListView:
			WorkFlowEditor.showPropertiesViewInSD("bListView");
			PropertiesView.setNewKeyBindMBOQueryRequest("Ff_wf_b,list,Ff_wf_b,bid,bname");
			
			WorkFlowEditor.addWidget("bListView", new WFLview().key("Ff_wf_b")
							.lvDetailScreen("bDetailScreen"));
			addCell("0","cell line 0","key1","100");
			addCell("1","cell line 1","key2","100");
			sleep(0.5);
			
			//2.set bDetailScreen:
			WorkFlowEditor.addWidget("bDetailScreen", new WFEditBox().label("bid:")
					.key("key1"));
			WorkFlowEditor.addWidget("bDetailScreen", new WFEditBox().label("bname:")
					.key("key2"));
			
			WorkFlowEditor.showPropertiesViewInSD("bDetailScreen");
			PropertiesView.setNewKeyBindMBORelationship("Screen Design,relationship,list,Ff_wf_b,ba,aid,bid");
			
			WorkFlowEditor.addMenuItem("bDetailScreen", new WFScreenMenuItem().name("submit")
					.project(Cfg.projectName)
					.mbo("Ff_wf_b")
					.type("Online Request"));
			
			WorkFlowEditor.link("bDetailScreen","aListView");
			
			//3.set aListView:
			WorkFlowEditor.addWidget("aListView", new WFLview().key("relationship")
					.lvDetailScreen("aDetailScreen"));
			addCell("0","cell line 0","key1","100");
			addCell("1","cell line 1","key2","100");
			sleep(0.5);
			
			WorkFlowEditor.addMenuItem("aListView", new WFScreenMenuItem().name("add")
					.type("Create Key Collection")
					.screen("add_A"));
			
			//4.set aDetailScreen:
			WorkFlowEditor.addWidget("aDetailScreen", new WFEditBox().label("aid:")
					.key("key1")
					.ifReadonly(true)
					.logicalType("NUMERIC"));
			WorkFlowEditor.addWidget("aDetailScreen", new WFEditBox().label("bid:")
					.key("key2")
					.ifReadonly(true));
			
			//5."delete" operation
			WorkFlowEditor.addMenuItem("aDetailScreen", new WFScreenMenuItem().name("delete")
					.project(Cfg.projectName)
					.mbo("Ff_wf_a")
					.type("Delete Key Collection")
					.operation("delete")
					.setListKey("relationship")
					.parametermapping("aid,key1")
					.parametermapping("bid,key2")
					);
			
			//6.set screen update_a:
			WorkFlowEditor.link("aDetailScreen", "update_A");
			
			WorkFlowEditor.addWidget("update_A", new WFEditBox().label("aid:")
					.key("key1"));
			WorkFlowEditor.addWidget("update_A", new WFEditBox().label("bid:")
					.key("key2"));
			
			//7."update" operation
			WorkFlowEditor.addMenuItem("update_A", new WFScreenMenuItem().name("update")
					.project(Cfg.projectName)
					.mbo("Ff_wf_a")
					.type("Update Key Collection")
					.operation("update")
					.setListKey("relationship")
					.parametermapping("aid,key1")
					.parametermapping("bid,key2"));
			
			//8.create screen add_b:
			WorkFlowEditor.addWidget("add_A", new WFEditBox().label("aid:")
					.key("key1")
					.logicalType("NUMERIC"));
			WorkFlowEditor.addWidget("add_A", new WFEditBox().label("bid:")
					.key("key2"));
			
			//9."add" operation
			WorkFlowEditor.addMenuItem("add_A", new WFScreenMenuItem().name("create")
					.project(Cfg.projectName)
					.mbo("Ff_wf_a")
					.type("Add Key Collection")
					.operation("create")
					.setListKey("relationship")
					.parametermapping("aid,key1")
					.parametermapping("bid,key2")
					);
			
			WN.createWorkFlowPackage(new WorkFlowPackage()
				.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
				.unwiredServer("My Unwired Server")
				.assignToUser("dt")
				.verifyResult("Successfully deployed the workflow", true));
			WorkFlowEditor.sendNotification(Cfg.projectName, "myWF.xbw", new Email()
				.subject("findall")
				.to("dt")
				.unwiredServer("My Unwired Server"));
		
			
//1.used to Android
		TestResult result = Robot.run("tplan.Workflow.Controls.android.Ctrl_Listview_action_update.Script");
		vpManual("DeviceTest", true, result.isPass()).performTest();
		
////2.used to BB.passed
		TestResult resultBB = Robot.run("tplan.Workflow.Controls.BB.Ctrl_Listview_action_update.Script");
		vpManual("DeviceTest", true, resultBB.isPass()).performTest();
		
		//vp_update: verify the record "aid=2,bid=11" has updated in backend db in ff_wf_a   Note:OK>>>
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("aid=2");
		clause.add("bid=11");
		vpManual("dbresult", true, 1 == CDBUtil.getRecordCount("ffan_xp32en_2", "wf", "Ff_wf_a", clause)).performTest();
	}
	
	public void addCell(String order,String cellName,String cellKey,String length){
		TestObject[] boxes = DOF.getWFListViewFigures(DOF.getRoot());
		((GefEditPartTestObject)boxes[0]).click();
		PropertiesView.maximize();
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
