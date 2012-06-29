package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Choice_prop_02_211Helper;
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
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.GlobalConfig;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.Relationship;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFChoice;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Choice_prop_02_211 extends Ctrl_Choice_prop_02_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 14, 2011 2:41:07 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/14
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
//		WN.useProject(Cfg.projectName);
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//				.type("Sybase_ASA_12.x").name("My Sample Database"), 
//				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Controls/setup/create_table_e1.sql");
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->e1 (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Department"))
			.target("E1")
			.mapping("dept_id,dept_id")
			.composite("true")
			.type(Relationship.TYPE_OTM));
		
			WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("wf1")
		.option(WorkFlow.SP_SERVER_INIT));
		
		PropertiesView.jumpStart(new WorkFlow()
		.mbo("Department")
		.objectQuery("findByPrimaryKey")
		.subject("dept0")
		.subjectMatchingRule("dept")
		);
		
		WorkFlowEditor.addScreen("linktoemail");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "linktoemail");
			
		WorkFlowEditor.addWidget(Cfg.projectName, "wf1.xbw", "linktoemail", new WFChoice().label("choice:"));
	
		//***************Create relationship key
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click();
		PropertiesView.setNewKeyBindMBORelationshipInScrDesign("key1,list,Department,E1s,emp_id,dept_id");
		//*************end
		
		//**************verify dynamic list
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
		 TestObject[] boxes = DOF.getWFChoiceFigures(DOF.getRoot());
		 TestObject box = boxes[0];
		 ((GefEditPartTestObject)box).click();
		 PropertiesView.clickTab("General");
		 
		 DOF.getButton(DOF.getRoot(), "&Dynamic").click();
		 DOF.getCCombo(DOF.getRoot(), "Value key:").click();
		 String s = PropertiesView.verifykeylistcom();
		 boolean b = s.contains("key1.key1,key1.key2");
		 vpManual("valuekeylist",true,b).performTest();
		 WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Value key:"),s.split(",")[1]);
		 
		 DOF.getCCombo(DOF.getRoot(), "Display name key:").click();
		 String s1 = PropertiesView.verifykeylistcom();
		 boolean b1 = s1.contains("key1.key1,key1.key2");
		 vpManual("valuekeylist",true,b1).performTest();
		 WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Display name key:"),s1.split(",")[2]);
		 MainMenu.saveAll();
		//*********reopen workflow ***************
		    DOF.getRoot().click();
		    WN.closeAll();
		    WN.openWorkFlow("wf", "wf1.xbw");
		//*********reopen workflow end***************	
		    
		     DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
			 TestObject[] boxes1 = DOF.getWFChoiceFigures(DOF.getRoot());
			 TestObject box1 = boxes1[0];
			 ((GefEditPartTestObject)box1).click();
			 PropertiesView.clickTab("General");
			 DOF.getButton(DOF.getRoot(), "&Dynamic").click();
			 vpManual("ifnamevalueright","key1.key2",DOF.getCCombo(DOF.getRoot(), "Display name key:").getProperty("text")).performTest();
			 vpManual("ifvalueright","key1.key1",DOF.getCCombo(DOF.getRoot(), "Value key:").getProperty("text")).performTest();
	}
}

