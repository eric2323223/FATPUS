package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Email_Delete_MBOHelper;
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
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Email_Delete_MBO extends SP_Email_Delete_MBOHelper
{
	/**
	 * Script Name   : <b>SP_Email_Delete_MBO</b>
	 * Generated     : <b>Sep 13, 2011 10:37:32 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/13
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall"));
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();  
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		PropertiesView.clickTab("Keys");
		PropertiesView.setNewKeyBindMBO("key1,string,Department,dept_name");
		PropertiesView.setNewKeyBindMBO("key2,int,Department,dept_id");
		
		System.out.print(PropertiesView.getKeyAttributesofStartPoint(new StartPoint()).getKey());
		vpManual("Properies", "key1,string,Department/dept_name",PropertiesView.getKeyAttributesofStartPoint(new StartPoint()).getKey().split(":")[0].split(":")[0]).performTest();
		vpManual("Properies", "key2,int,Department/dept_id", PropertiesView.getKeyAttributesofStartPoint(new StartPoint()).getKey().split(":")[1]).performTest();
		
		WorkFlowEditor.removeScreen("Department");
		WorkFlowEditor.removeScreen("DepartmentDetail");
		WorkFlowEditor.removeScreen("Departmentupdateinstance");
		WorkFlowEditor.removeScreen("Departmentcreate");
		WorkFlowEditor.removeScreen("Departmentdeleteinstance");
		MainMenu.saveAll();
		
		WorkFlowEditor.addScreen("my");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "my");
		MainMenu.saveAll();
		
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		
		vpManual("Properies", "key1,string,Department/dept_name",PropertiesView.getKeyAttributesofStartPoint(new StartPoint()).getKey().split(":")[0].split(":")[0]).performTest();
		vpManual("Properies", "key2,int,Department/dept_id", PropertiesView.getKeyAttributesofStartPoint(new StartPoint()).getKey().split(":")[1]).performTest();
		vpManual("noerror", 0, Problems.getErrors().size()).performTest();
		
	}
}
//passed
