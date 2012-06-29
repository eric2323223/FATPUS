package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.NQ_12Helper;
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
import component.entity.model.WFLview;
import component.entity.model.WFScreenMenuItem;
import component.entity.wizard.ObjectQueryWizard;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvVm
 */
public class NQ_12 extends NQ_12Helper
{
	/**
	 * Script Name   : <b>NQ_12</b>
	 * Generated     : <b>Oct 11, 2011 2:52:05 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/11
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
		WorkFlowEditor.addScreen("detailScr");
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
		
		WorkFlowEditor.addWidget(Cfg.projectName, "myWF.xbw", "resultScr", new WFLview()
			.key("Department").lvDetailScreen("detailScr")
		);
		//
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "detailScr", 
				new WFEditBox().label("id")
							   .key("Department_dept_id_attribKey")
		);
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "detailScr", 
				new WFEditBox().label("name")
							   .key("Department_dept_name_attribKey")
		);
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "detailScr", 
				new WFEditBox().label("head_id")
							   .key("Department_dept_head_id_attribKey")
		);
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
	}
}

