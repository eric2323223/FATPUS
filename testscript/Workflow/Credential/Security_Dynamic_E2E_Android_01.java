package testscript.Workflow.Credential;
import java.sql.SQLException;

import resources.testscript.Workflow.Credential.Security_Dynamic_E2E_Android_01Helper;
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
import com.sybase.automation.framework.common.DBUtil;
import com.sybase.automation.framework.widget.DOF;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.StartPoint;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.ASA;
import component.entity.resource.IDBResource;
import component.entity.resource.RC;
import component.view.Problems;
import component.view.properties.workflow.WFControlObject;

/**
 * Description   : Functional Test Script
 * @author flv
 */
public class Security_Dynamic_E2E_Android_01 extends Security_Dynamic_E2E_Android_01Helper
{
	/**
	 * Script Name   : <b>Security_Dynamic_E2E_Android_01</b>
	 * Generated     : <b>Oct 20, 2011 3:16:17 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/20
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
		);
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_CREDENTIAL_REQUEST));
		MainMenu.saveAll();
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		ToggleGUITestObject obj = WFControlObject.getCheckBoxOnRoot("Authentication", "Use static credentials");
		obj.clickToState(NOT_SELECTED);
		MainMenu.saveAll();
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(0.5);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
//		DOF.getWFScreenFigure(DOF.getRoot(), "Departmentcr...").click(RIGHT);
//		DOF.getContextMenu().click(atPath("Delete"));
		MainMenu.saveAll();
		WorkFlowEditor.addScreen("scr1");
		WorkFlowEditor.link("Credential Request", "scr1");
		//
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "scr1", 
				new WFEditBox().label("username")
							   .logicalType("TEXT")
							   .newKey("username,string")
							   .setCredentialUserName(true)
							   .setDefaultValue("supAdmin")
		);
		//
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "scr1", 
				new WFEditBox().label("password")
							   .logicalType("TEXT")
							   .newKey("password,string")
							   .password(true)
							   .setCredentialPassword(true)
							   .setDefaultValue("s3pAdmin")
		);
		//
		GefEditPartTestObject usr = WFControlObject.getEditBoxOnScreen("scr1", "username");
		usr.click();
		PropertiesView.maximize();
		ToggleGUITestObject usrCheckBox = WFControlObject.getCheckBoxOnRoot("Advanced", "Credential cache &username");
		Object obj1 = usrCheckBox.invoke("getSelection");
		vpManual("selected", true, obj1.equals(true)).performTest();
		ToggleGUITestObject pwdCheckBox = WFControlObject.getCheckBoxOnRoot("Advanced", "Credential cache pass&word");
		obj1 = pwdCheckBox.invoke("getSelection");
		vpManual("selected", true, obj1.equals(false)).performTest();
		PropertiesView.restore();
		//
		GefEditPartTestObject pwd = WFControlObject.getEditBoxOnScreen("scr1", "password");
		pwd.click();
		PropertiesView.maximize();
		usrCheckBox = WFControlObject.getCheckBoxOnRoot("Advanced", "Credential cache &username");
		obj1 = usrCheckBox.invoke("getSelection");
		vpManual("selected", true, obj1.equals(false)).performTest();
		pwdCheckBox = WFControlObject.getCheckBoxOnRoot("Advanced", "Credential cache pass&word");
		obj1 = pwdCheckBox.invoke("getSelection");
		vpManual("selected", true, obj1.equals(true)).performTest();
		PropertiesView.restore();
		//
		WorkFlowEditor.addMenuItem("scr1", new WFScreenMenuItem()
				.name("Save")
				.type("Save")
		);
		//
		WorkFlowEditor.addScreen("scr2");
		WorkFlowEditor.link("Client-initiated", "scr2");
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "scr2", 
				new WFEditBox().label("dept_id")
							   .logicalType("NUMERIC")
							   .newKey("key1,int")
		);
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "scr2", 
				new WFEditBox().label("dept_name")
							   .logicalType("TEXT")
							   .newKey("key2,string")
		);
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "scr2", 
				new WFEditBox().label("dept_head_id")
							   .logicalType("NUMERIC")
							   .newKey("key3,int")
		);
		WorkFlowEditor.addMenuItem("scr2", new WFScreenMenuItem()
				.name("Create")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.operation("create")
				.parametermapping("dept_id,key1")
				.parametermapping("dept_name,key2")
				.parametermapping("dept_head_id,key3")
		);
		WorkFlowEditor.addMenuItem("scr2", new WFScreenMenuItem()
				.name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.defaultSuccessScreen("Department")
		);
		//
		MainMenu.saveAll();
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		vpManual("warning", 0, Problems.getWarnings().size()).performTest();
		try {
			DBUtil.runSQL((IDBResource)RC.getResource(ASA.class), "delete from department where dept_id=11");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
		.assignToUser("supAdmin")
		.unwiredServer("My Unwired Server")
		.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
		customTestScript());
		//
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=username,value=supAdmin|" +
				"id=password,value=s3pAdmin|" +
				"id=Department_dept_id_attribKey,value=11|"+
				"id=Department_dept_name_attribKey,value=AA|"+ 
				"id=Department_dept_head_id_attribKey,value=11"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("scr1").getData("username");
		s.screen("scr1").getData("password");
		s.screen("scr1").moveTo("scr2").throughMenuItem("Save");
		s.screen("scr2").setData("key1", "11");
		s.screen("scr2").setData("key2", "AA");
		s.screen("scr2").setData("key3", "11");
		s.screen("scr2").menuItem("Create");
		s.screen("scr2").moveTo("Department").throughMenuItem("findAll");
		s.screen("Department").moveTo("DepartmentDetail").throughListItem("AA->1");
		s.screen("DepartmentDetail").getData("Department_dept_id_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_name_attribKey");
		s.screen("DepartmentDetail").getData("Department_dept_head_id_attribKey");
		return s;
	}
		
}

