package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Menu_Maximize_211Helper;
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
import component.entity.PropertiesView;
import component.entity.WFScreenDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Menu_Maximize_211 extends Ctrl_Menu_Maximize_211Helper
{
	/**
	 * Script Name   : <b>Script2</b>
	 * Generated     : <b>Oct 14, 2011 12:15:29 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/14
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");

		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem()
				.name("addmenuitem")
				.type("Online Request")
				.mbo(Cfg.projectName+"/Department")
				.project(Cfg.projectName)
			    .mbo("Department")
				);
		WFScreenDesigner.invokeaddItem("addmenuitem");
		PropertiesView.maximize();
		
		PropertiesView.setMenuItemNotMaxNotRestore(new WFScreenMenuItem()
		.type("Online Request")
		.project(Cfg.projectName)
	    .mbo("Department"));
		PropertiesView.clickTab("General");
		vpManual("detailnotnull","wf/Department",DOF.getTextField(DOF.getRoot(), "Mobile business object:").getProperty("text").toString()).performTest();
		PropertiesView.restore();
		
		WFScreenDesigner.invokeaddItem("addanothermenuitem");
		PropertiesView.setMenuItemNotMaxNotRestore(new WFScreenMenuItem()
		.type("Online Request"));
		PropertiesView.maximize();
		PropertiesView.setMenuItemNotMaxNotRestore(new WFScreenMenuItem()
		.project(Cfg.projectName)
	    .mbo("Department"));
		PropertiesView.clickTab("General");
		vpManual("detailnotnull","wf/Department",DOF.getTextField(DOF.getRoot(), "Mobile business object:").getProperty("text").toString()).performTest();
		PropertiesView.restore();
		
	}
}

