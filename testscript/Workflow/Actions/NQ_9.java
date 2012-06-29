package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.NQ_9Helper;
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
import component.entity.model.ObjectQuery;
import component.entity.model.StartPoint;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.wizard.ObjectQueryWizard;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvVm
 */
public class NQ_9 extends NQ_9Helper
{
	/**
	 * Script Name   : <b>NQ_9</b>
	 * Generated     : <b>Oct 10, 2011 5:47:51 PM</b>
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
		//MBOProperties mbo = new MBOProperties(Cfg.projectName, "Department");
		WN.createObjectQuery(new ObjectQuery()
			.name("findByPrimaryKeys")
			.startParameter(WN.mboPath(Cfg.projectName, "Department"))
			.parameter("pk,int,false,dept_id")
			.queryDefinition("SELECT x.* FROM Department x WHERE x.dept_id = :pk")
			.returnType(ObjectQueryWizard.RT_SINGLE)
		);
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
		);
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addScreen("invokeScr");
		WorkFlowEditor.addScreen("resultScr");
		WorkFlowEditor.link("Client-initiated", "invokeScr");
		//
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "invokeScr", 
				new WFEditBox().label("dept_Name")
							   .logicalType("NUMERIC")
							   .newKey("did,int")
		);
		WorkFlowEditor.addMenuItem("invokeScr", new WFScreenMenuItem()
				.name("ObjectQuery")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findByPrimaryKeys")
				.parametermapping("pk,did")
				.defaultSuccessScreen("resultScr")
		);
		//
		PropertiesView.clickTab("General");
		vpManual("objectquery", true, DOF.getCCombo(DOF.getRoot(),"Object query:").getProperty("text").equals("findByPrimaryKeys")).performTest();
		vpManual("parameters", "pk,did", PropertiesView.getMenuItem("ObjectQuery").getParameterMapping()[0]).performTest();
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
	}
}

