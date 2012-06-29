package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.IsManyNQ_Listview_control_01_211Helper;
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
import component.entity.model.WFLview;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class IsManyNQ_Listview_control_01_211 extends IsManyNQ_Listview_control_01_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 25, 2011 9:54:41 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
	
		 WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("wf2")
				.option(WorkFlow.SP_SERVER_INIT));


		PropertiesView.jumpStart(new WorkFlow()
		        .mbo("Department")
				.objectQuery("findAll")
				.subject("getall")
				.subjectMatchingRule("getall")
				);

		WorkFlowEditor.addScreen("linktoemail");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "linktoemail");
		
		WorkFlowEditor.addWidget(Cfg.projectName, "wf2.xbw", "linktoemail", new WFLview());
		DOF.getWFScreenDesignCanvas().click();
		PropertiesView.setNewKeyBindMBOQueryRequest("listkey,list,Department,dept_id,dept_name,dept_head_id");
		
		TestObject[] boxes = DOF.getWFListViewFigures(DOF.getRoot());
		TestObject box = boxes[0];
		((GefEditPartTestObject)box).click();
		PropertiesView.clickTab("General");
		boolean b = PropertiesView.verifykeylist().contains("listkey");
		vpManual("verifykeylist",true,b).performTest();
		WO.setCCombo(DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")),PropertiesView.verifykeylist().split(",")[0]);
		MainMenu.saveAll();
		
		//*******************set key to blank,verify no key bind
		WN.closeAll();
	    WN.openWorkFlow("wf", "wf2.xbw");
	    
	  //************************verify whether the new added control exist**************
	    DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
		TestObject[] boxes1 = DOF.getWFListViewFigures(DOF.getRoot());
		TestObject box1 = boxes1[0];
		((GefEditPartTestObject)box1).click();
		PropertiesView.clickTab("General");
		String key = DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text").toString();
		System.out.print(">>>>>>>>"+key);
		vpManual("keybind","listkey",key).performTest();
		//*****************end
	}	
}

