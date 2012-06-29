package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.DynaChoice_Control_01_211Helper;
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
public class DynaChoice_Control_01_211 extends DynaChoice_Control_01_211Helper
{
	/**
	 * Script Name   : <b>Script2</b>
	 * Generated     : <b>Oct 25, 2011 2:41:38 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//test DynaChoice_Control_01
		
		WN.useProject(Cfg.projectName);
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Controls/setup/create_table_myemployee.sql");
		
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->myemployee (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Department"))
			.target("Myemployee")
			.mapping("dept_id,dept_id")
			.composite("true")
			.type(Relationship.TYPE_OTM));
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
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
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		PropertiesView.setNewKeyBindMBORelationshipInScrDesign("key1,string,Department,myemployees,emp_id");
		//*************end
		
		//**************verify dynamic list
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
		 TestObject[] boxes = DOF.getWFChoiceFigures(DOF.getRoot());
		 TestObject box = boxes[0];
		 ((GefEditPartTestObject)box).click();
		 PropertiesView.clickTab("General");
		 
		 DOF.getButton(DOF.getRoot(), "&Dynamic").click();
		 DOF.getCCombo(DOF.getRoot(), "Value key:").click();
		 boolean b = PropertiesView.verifykeylistcom().contains("key1.key1,key1.key2");
		 vpManual("valuekeylist",true,b).performTest();
		 WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Value key:"),PropertiesView.verifykeylistcom().split(",")[1]);
		 
		 DOF.getCCombo(DOF.getRoot(), "Display name key:").click();
		 boolean b1 = PropertiesView.verifykeylistcom().contains("key1.key1,key1.key2");
		 vpManual("displaynamekeylist",true,b1).performTest();
		 WO.setCCombo(DOF.getCCombo(DOF.getRoot(), "Display name key:"),PropertiesView.verifykeylistcom().split(",")[2]);
	}
}

