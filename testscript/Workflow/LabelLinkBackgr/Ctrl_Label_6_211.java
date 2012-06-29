package testscript.Workflow.LabelLinkBackgr;
import resources.testscript.Workflow.LabelLinkBackgr.Ctrl_Label_6_211Helper;
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
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFLabel;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Label_6_211 extends Ctrl_Label_6_211Helper
{
	/**
	 * Script Name   : <b>Ctrl_Label_6_211</b>
	 * Generated     : <b>Nov 4, 2011 1:55:11 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/04
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//test Ctrl_Label_6 
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
	
//		 WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//					.server("My Unwired Server")
//					.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("wflabel")
				.option(WorkFlow.SP_SERVER_INIT));


		PropertiesView.jumpStart(new WorkFlow()
		        .mbo("Department")
				.objectQuery("findByPrimaryKey")
				.subject("deptid=2")
				.subjectMatchingRule("deptid=")
				.setParameterValue("dept_id,Subject,deptid="));

		WorkFlowEditor.addScreen("linktoemail");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "linktoemail");
		
		
		//******************add key from key tab	
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		 DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
		PropertiesView.clickTab("Keys");
		PropertiesView.setNewKeyBindMBO("labelkey,string,Department,dept_name");
	//**************end
		WorkFlowEditor.addWidget(Cfg.projectName, "wflabel.xbw", "linktoemail", new WFLabel().name("labelctrl"));
		WO.setCCombo(DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")),"labelkey");
		MainMenu.saveAll();
		
		
		//copy and paste control
	   TestObject[] boxes = DOF.getWFLabelFigures(DOF.getRoot());
		   TestObject box = boxes[0];
		((GefEditPartTestObject)box).click(RIGHT);
	   DOF.getContextMenu().click(atPath("Edit->Copy"));
	   DOF.getWFScreenDisplayFigure().click(RIGHT);
	   DOF.getContextMenu().click(atPath("Edit->Paste"));
	   TestObject[] boxes1 = DOF.getWFLabelFigures(DOF.getRoot());
	   TestObject box1 = boxes1[1];
	   ((GefEditPartTestObject)box1).click();
	   PropertiesView.clickTab("General");
	   vpManual("pastelabel","labelkey",DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text")).performTest();
	
		
	}
}

