package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.NQ_14Helper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Relationship;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvVm
 */
public class NQ_14 extends NQ_14Helper
{
	/**
	 * Script Name   : <b>NQ_14</b>
	 * Generated     : <b>Oct 11, 2011 5:03:42 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/11
	 * @author flvVm
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->employee (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Employee"))
			.target("Department")
			.mapping("dept_id,dept_id")
			.composite("true")
			.type(Relationship.TYPE_OTM));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
			.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Employee");

		vpManual("screen1", true, WorkFlowEditor.hasScreen("Employee")).performTest();
		vpManual("screen2", true, WorkFlowEditor.hasScreen("EmployeeDetail")).performTest();
		vpManual("screen3", true, WorkFlowEditor.hasScreen("Employee_create")).performTest();
		vpManual("screen4", false, WorkFlowEditor.hasScreen("Employee_delete")).performTest();
		vpManual("screen5", true, WorkFlowEditor.hasScreen("Employee_update_instance")).performTest();
		
		vpManual("screen6", true, WorkFlowEditor.hasScreen("Department")).performTest();
		vpManual("screen7", true, WorkFlowEditor.hasScreen("DepartmentDetail")).performTest();
		vpManual("screen8", true, WorkFlowEditor.hasScreen("Department_add")).performTest();
		vpManual("screen9", true, WorkFlowEditor.hasScreen("Department_update_instance")).performTest();
		
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
			.name("findAll")
			.type("Online Request")
			.project(Cfg.projectName)
			.mbo("Employee")
			.objectQuery("findAll")
			.defaultSuccessScreen("Employee"));
		
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("Start Screen", "Employee")).performTest();
		
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		DOF.getWFScreenFigure(DOF.getRoot(), "Start Screen").doubleClick();
		PropertiesView.clickTab("General");
		vpManual("objectquery", true, DOF.getCCombo(DOF.getRoot(),"Object query:").getProperty("text").equals("findAll")).performTest();
		
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		}

}

