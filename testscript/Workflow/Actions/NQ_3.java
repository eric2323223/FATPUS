package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.NQ_3Helper;
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
import component.entity.model.ObjectQuery;
import component.entity.model.StartPoint;
import component.entity.model.WFScreenMenuItem;
import component.entity.wizard.ObjectQueryWizard;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvVm
 */
public class NQ_3 extends NQ_3Helper
{
	/**
	 * Script Name   : <b>NQ_3</b>
	 * Generated     : <b>Oct 10, 2011 2:03:46 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/10
	 * @author flvVm
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Department");
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
		);
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addScreen("invokeScr");
		WorkFlowEditor.addScreen("resultScr");
		WorkFlowEditor.link("Client-initiated", "invokeScr");
		//
		WorkFlowEditor.addMenuItem("invokeScr", new WFScreenMenuItem()
				.name("ObjectQuery")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.defaultSuccessScreen("resultScr")
		);
//		PropertiesView.clickTab("General");
//		DOF.getButton(DOF.getRoot(), "Invoke object &query").click();
//		DOF.getCCombo(DOF.getRoot(), "Object query:").click();
		// 
		//DOF.getPoppedUpList().getChildren();
		//
		WN.createObjectQuery(new ObjectQuery()
								.name("ObjQuery")
								.startParameter(WN.mboPath(Cfg.projectName, "Department"))
								.parameter("name,string,true,dept_name")
								.queryDefinition("SELECT x.* FROM Department x WHERE x.dept_name = :name")
								.returnType(ObjectQueryWizard.RT_MULTIPLE)
								);
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		WorkFlowEditor.setMenuItem("invokeScr", new WFScreenMenuItem()
				.name("ObjectQuery")
				.objectQuery("ObjQuery")
		);
		//
		PropertiesView.clickTab("General");
		vpManual("objectquery", true, DOF.getCCombo(DOF.getRoot(),"Object query:").getProperty("text").equals("ObjQuery")).performTest();
		//
		vpManual("error", 1, Problems.getErrors().size()).performTest();
	}
}

