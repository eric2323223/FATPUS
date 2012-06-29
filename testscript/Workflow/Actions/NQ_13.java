package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.NQ_13Helper;
import testscript.Workflow.cfg.Cfg;

import com.rational.test.ft.object.interfaces.GefEditPartTestObject;
import com.rational.test.ft.object.interfaces.ScrollTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
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
public class NQ_13 extends NQ_13Helper
{
	/**
	 * Script Name   : <b>NQ_13</b>
	 * Generated     : <b>Oct 11, 2011 4:05:17 PM</b>
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
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "resultScr", 
				new WFEditBox().label("id")
							   .key("Department_dept_id_attribKey")
		);
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "resultScr", 
				new WFEditBox().label("name")
							   .key("Department_dept_name_attribKey")
		);
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "resultScr", 
				new WFEditBox().label("head_id")
							   .key("Department_dept_head_id_attribKey")
		);
		
		controlEdit("id,name,head_id".split(","));
		
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
	}
	
	private void controlEdit(String[] string) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		DOF.getWFScreenFigure(DOF.getRoot(), "resultScr").doubleClick();
		TestObject[] boxes = DOF.getWFEditBoxFigures(DOF.getRoot());
		for(TestObject box:boxes){
			((GefEditPartTestObject)box).click();
			sleep(0.5);
			PropertiesView.clickTab("General");
			String label = DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString();
			if(label.equals(string[0])){
				verifyPoint("Department_dept_id_attribKey");
			} else if (label.equals(string[1])) {
				verifyPoint("Department_dept_name_attribKey");
			} else if (label.equals(string[2])) {
				verifyPoint("Department_dept_head_id_attribKey");
			}
		}
	}
	
	private void verifyPoint(String string) {
		PropertiesView.clickTab("General");
		//WO.setCCombo(DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding"), "Key:"), obj.getKey());
		TestObject[] comps = DOF.getGroup(DOF.getRoot(), "Input Data Binding").find(atDescendant("class", "org.eclipse.swt.widgets.Composite"));
		if (comps != null && comps.length > 0) {
			TestObject[] combos = comps[0].find(atDescendant("class", "org.eclipse.swt.custom.CCombo"));
			ScrollTestObject sto = (ScrollTestObject)combos[0];
			vpManual("control", true, sto.getProperty("text").equals(string)).performTest();
		}
	}
	
}

