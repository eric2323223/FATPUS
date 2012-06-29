package testscript.Workflow.Keys;
import java.awt.MenuItem;

import resources.testscript.Workflow.Keys.Action_ParameterMappingHelper;
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
import component.entity.WFFlowDesigner;
import component.entity.WFScreenDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Action_ParameterMapping extends Action_ParameterMappingHelper
{
	/**
	 * Script Name   : <b>Action_ParameterMapping</b>
	 * Generated     : <b>Oct 10, 2011 5:49:13 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/10
	 * @author ffan
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
				.objectQuery("findByPrimaryKey")
				.subject("dept_id=1")
				.subjectMatchingRule("dept_id=")
				.setParameterValue("dept_id,Subject,dept_id="));
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), "Departmentupdateinstance").doubleClick();
		DOF.getWFMenuItemFigure(DOF.getRoot(), "Update").click();
		
		System.out.println("1="+PropertiesView.getParameterMappingofMenuItem().getKey());
		vpManual("PM","dept_name,string,Key,Department_dept_name_attribKey:dept_head_id,int,Key,Department_dept_head_id_attribKey:dept_id,int,Key,Department_dept_id_attribKey",PropertiesView.getParameterMappingofMenuItem().getKey()).performTest();
	}
}
//passed
