package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.Other_Menu_1Helper;
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
import component.entity.MBOProperties;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Operation;
import component.entity.model.PK;
import component.entity.model.StartPoint;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author flvVm
 */
public class Other_Menu_1 extends Other_Menu_1Helper
{
	/**
	 * Script Name   : <b>Other_Menu_1</b>
	 * Generated     : <b>Sep 30, 2011 3:25:20 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/30
	 * @author flvVm
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Department");
		mbo.addOperation(new Operation().startParameter(WN.mboPath(Cfg.projectName, "Department"))
				.name("O_HParas")
				.sqlQuery("DELETE FROM sampledb.dba.department WHERE dept_id = :dept_id")
		);
		mbo.addOperation(new Operation().startParameter(WN.mboPath(Cfg.projectName, "Department"))
				.name("O_NParas")
				.sqlQuery("DELETE FROM sampledb.dba.department WHERE dept_id = 100")
		);
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
		);
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addScreen("Screen1");
		WorkFlowEditor.link("Client-initiated", "Screen1");
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Screen1", 
				new WFEditBox().label("dept_id")
							   .logicalType("NUMERIC")
							   .newKey("key1,int")
		);
		//
		WorkFlowEditor.addMenuItem("Screen1", new WFScreenMenuItem()
				.name("OTher")
				.type("Submit Workflow")
				.project(Cfg.projectName)
				.mbo("Department")
				.operation("O_NParas")
		);
		PropertiesView.clickTab("General");
		vpManual("exist", true, DOF.getCCombo(DOF.getRoot(),"Operation:").getProperty("text").equals("O_NParas")).performTest();
		//
		WorkFlowEditor.setMenuItem("Screen1", new WFScreenMenuItem()
				.name("OTher")
				.operation("O_HParas")
				.parametermapping("dept_id,key1")
		);
		PropertiesView.clickTab("General");
		vpManual("exist", true, DOF.getCCombo(DOF.getRoot(),"Operation:").getProperty("text").equals("O_HParas")).performTest();
		//
		vpManual("properties", "dept_id,key1", PropertiesView.getMenuItem("OTher").getParameterMapping()[0]).performTest();
		
	}
}

