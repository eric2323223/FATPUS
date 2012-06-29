package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.Other_CreateScreensFromDnDHelper;
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
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Operation;
import component.entity.model.WFEditBox;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvVm
 */
public class Other_CreateScreensFromDnD extends Other_CreateScreensFromDnDHelper
{
	/**
	 * Script Name   : <b>Other_CreateScreensFromDnD</b>
	 * Generated     : <b>Oct 10, 2011 10:39:12 AM</b>
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
				.option(WorkFlow.SP_CLIENT_INIT)
		);
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		//WorkFlowEditor.link("Client-initiated", "Department");
		//
		vpManual("screen", true, WorkFlowEditor.hasScreen("Department")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("DepartmentDetail")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Department_create")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Department_update_instance")).performTest();
		vpManual("screen", false, WorkFlowEditor.hasScreen("Department_delete")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Department_O_HParas")).performTest();
		vpManual("screen", true, WorkFlowEditor.hasScreen("Department_O_NParas")).performTest();
		//
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("Department", "DepartmentDetail")).performTest();
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("DepartmentDetail", "Department_update_instance")).performTest();
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("DepartmentDetail", "Department_delete")).performTest();
		//Department Screen
		vpManual("Menu", true, WorkFlowEditor.hasMenuItemInScreen("Department", "Submit")).performTest();
		vpManual("Menu", true, WorkFlowEditor.hasMenuItemInScreen("Department", "Cancel Screen")).performTest();
		controlEdit("Department"," /Department".split(","));
		//DepartmentDetail Screen
		vpManual("Menu", true, WorkFlowEditor.hasMenuItemInScreen("DepartmentDetail", "Open Department_update_instance")).performTest();
		vpManual("Menu", true, WorkFlowEditor.hasMenuItemInScreen("DepartmentDetail", "Open Department_delete")).performTest();
		vpManual("Menu", true, WorkFlowEditor.hasMenuItemInScreen("DepartmentDetail", "Back")).performTest();
		controlEdit("DepartmentDetail","Dept id:/Department_dept_id_attribKey,Dept name:/Department_dept_name_attribKey,Dept head id:/Department_dept_head_id_attribKey".split(","));
		//Department_update_instance Screen
		vpManual("Menu", true, WorkFlowEditor.hasMenuItemInScreen("Department_update_instance", "Update")).performTest();
		vpManual("Menu", true, WorkFlowEditor.hasMenuItemInScreen("Department_update_instance", "Cancel")).performTest();
		controlEdit("Department_update_instance","Dept id:/Department_dept_id_attribKey,Dept name:/Department_dept_name_attribKey,Dept head id:/Department_dept_head_id_attribKey".split(","));
		//Department_delete Screen
//		vpManual("Menu", true, WorkFlowEditor.hasMenuItemInScreen("Department_delete", "Delete")).performTest();
//		vpManual("Menu", true, WorkFlowEditor.hasMenuItemInScreen("Department_delete", "Cancel")).performTest();
//		controlEdit("Department_delete","Dept id:/Department_dept_id_attribKey,Dept name:/Department_dept_name_attribKey,Dept head id:/Department_dept_head_id_attribKey".split(","));
		//Department_create Screen
		vpManual("Menu", true, WorkFlowEditor.hasMenuItemInScreen("Department_create", "Create")).performTest();
		vpManual("Menu", true, WorkFlowEditor.hasMenuItemInScreen("Department_create", "Cancel")).performTest();
		controlEdit("Department_create","Dept id:/Department_dept_id_attribKey,Dept name:/Department_dept_name_attribKey,Dept head id:/Department_dept_head_id_attribKey".split(","));
		//Department_O_HParas Screen
		vpManual("Menu", true, WorkFlowEditor.hasMenuItemInScreen("Department_O_HParas", "O_HParas")).performTest();
		vpManual("Menu", true, WorkFlowEditor.hasMenuItemInScreen("Department_O_HParas", "Cancel")).performTest();
		controlEdit("Department_O_HParas","Dept id:/Department_O_HParas_dept_id_paramKey".split(","));
		//Department_O_NParas Screen
		vpManual("Menu", true, WorkFlowEditor.hasMenuItemInScreen("Department_O_NParas", "O_NParas")).performTest();
		vpManual("Menu", true, WorkFlowEditor.hasMenuItemInScreen("Department_O_NParas", "Cancel")).performTest();
		//   
		vpManual("error", 0, Problems.getErrors().size()).performTest();
	}
	
	private void controlEdit(String string, String[] ctrl) {
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		DOF.getWFScreenFigure(DOF.getRoot(), string).doubleClick();
		TestObject[] boxes = DOF.getWFEditBoxFigures(DOF.getRoot());
		int i = 0;
		for(TestObject box:boxes){
			((GefEditPartTestObject)box).click();
			sleep(0.5);
			Object l = DOF.getTextField(DOF.getRoot(), "Label:");
			Object k = DOF.getTextField(DOF.getRoot(), "Key:");
			String label = null ;
			String key = null ;
			String cv = ctrl[i++];
			if (null != l) {
				label = DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString();
			}
			if (null != k) {
				key = DOF.getTextField(DOF.getRoot(), "Key:").getProperty("text").toString();
			}
			//
			if (null != label) {
				vpManual("control", true, label.equals(cv.split("/")[0].trim())).performTest();
			}
			//
			if (null != key) {
				vpManual("control", true, label.equals(cv.split("/")[1].trim())).performTest();
			}
		}
	}
}

