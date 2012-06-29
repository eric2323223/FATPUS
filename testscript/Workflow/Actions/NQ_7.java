package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.NQ_7Helper;
import testscript.Workflow.cfg.Cfg;

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
public class NQ_7 extends NQ_7Helper
{
	/**
	 * Script Name   : <b>NQ_7</b>
	 * Generated     : <b>Oct 10, 2011 5:23:02 PM</b>
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
			.name("ObjQuery")
			.startParameter(WN.mboPath(Cfg.projectName, "Department"))
			.parameter("name,string,true,dept_name")
			.queryDefinition("SELECT x.* FROM Department x WHERE x.dept_name = :name")
			.returnType(ObjectQueryWizard.RT_MULTIPLE)
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
							   .logicalType("TEXT")
							   .newKey("nameVal,string")
		);
		WorkFlowEditor.addMenuItem("invokeScr", new WFScreenMenuItem()
				.name("ObjectQuery")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("ObjQuery")
				.parametermapping("name,nameVal")
				.defaultSuccessScreen("resultScr")
		);
		//
		PropertiesView.clickTab("General");
		vpManual("objectquery", true, DOF.getCCombo(DOF.getRoot(),"Object query:").getProperty("text").equals("ObjQuery")).performTest();
		vpManual("parameters", "name,nameVal", PropertiesView.getMenuItem("ObjectQuery").getParameterMapping()[0]).performTest();
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
	}
}

