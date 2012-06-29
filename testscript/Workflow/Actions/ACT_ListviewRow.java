package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_ListviewRowHelper;
import testscript.Workflow.cfg.Cfg;

import com.sybase.automation.framework.widget.DOF;
import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Relationship;
import component.entity.model.WFKey;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class ACT_ListviewRow extends ACT_ListviewRowHelper
{
	/**
	 * Script Name   : <b>ACT_ListviewRow</b>
	 * Generated     : <b>Oct 12, 2011 8:51:46 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/12
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->employee (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Department"))
			.target("Employee")
			.mapping("dept_id,dept_id")
			.composite("true")
			.type(Relationship.TYPE_OTM));
		//wf
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		//
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		DOF.getWFScreenFigure(DOF.getRoot(), "Employee_add").doubleClick();
		PropertiesView.clickTab("General");
		WorkFlowEditor.setMenuItem("Employee_add", new WFScreenMenuItem()
			.name("Create")
			.parametermapping("manager_id," + new WFKey().name("key"))
		);
		vpManual("parameters", "manager_id,key", PropertiesView.getMenuItem("Create").getParameterMapping()[1]).performTest();
		PropertiesView.clickTab("General");
		//DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
		DOF.getCCombo(DOF.getRoot(), "Type:").click();
		vpManual("exist", true, DOF.getCCombo(DOF.getRoot(),"Type:").getProperty("text").equals("Add Key Collection")).performTest();
		WorkFlowEditor.setMenuItem("Employee_add", new WFScreenMenuItem()
			.name("Create")
			.type("Update Key Collection")
		);
		//
		PropertiesView.clickTab("General");
		//DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
		DOF.getCCombo(DOF.getRoot(), "Type:").click();
		vpManual("exist", true, DOF.getCCombo(DOF.getRoot(),"Type:").getProperty("text").equals("Update Key Collection")).performTest();
		//
		WorkFlowEditor.setMenuItem("Employee_add", new WFScreenMenuItem()
			.name("Create")
			.type("Delete Key Collection")
		);
		PropertiesView.clickTab("General");
		//DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
		DOF.getCCombo(DOF.getRoot(), "Type:").click();
		vpManual("exist", true, DOF.getCCombo(DOF.getRoot(),"Type:").getProperty("text").equals("Delete Key Collection")).performTest();
	}
}

