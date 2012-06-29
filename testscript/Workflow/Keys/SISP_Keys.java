package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.SISP_KeysHelper;
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
import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class SISP_Keys extends SISP_KeysHelper
{
	/**
	 * Script Name   : <b>SISP_Keys</b>
	 * Generated     : <b>Sep 30, 2011 12:37:21 PM</b>
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
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall"));
		
//		System.out.println(PropertiesView.getKeyAttributeofStartPoint().getKey());
		vpManual("Properies", "Department,list,Department:Department_dept_id_attribKey,int,Department/dept_id:Department_dept_name_attribKey,string,Department/dept_name:Department_dept_head_id_attribKey,int,Department/dept_head_id", PropertiesView.getKeyAttributeofStartPoint().getKey()).performTest();

		PropertiesView.setNewKeyBindMBO("newKey,int,Department,dept_id");
//		System.out.println(PropertiesView.getKeyAttributeofStartPoint().getKey());
		vpManual("Properies", true, PropertiesView.getKeyAttributeofStartPoint().getKey().contains("newKey,int,Department/dept_id")).performTest();

		PropertiesView.editKeyAttributesofStartPoint("newKey","string");
//		System.out.println(PropertiesView.getKeyAttributeofStartPoint().getKey());
		vpManual("Properies", "newKey,string,Department/dept_id:Department,list,Department:Department_dept_id_attribKey,int,Department/dept_id:Department_dept_name_attribKey,string,Department/dept_name:Department_dept_head_id_attribKey,int,Department/dept_head_id", PropertiesView.getKeyAttributeofStartPoint().getKey()).performTest();
		
		PropertiesView.removeKeyServer("newKey");
//		System.out.println(PropertiesView.getKeyAttributeofStartPoint().getKey());
		vpManual("Properies", "Department,list,Department:Department_dept_id_attribKey,int,Department/dept_id:Department_dept_name_attribKey,string,Department/dept_name:Department_dept_head_id_attribKey,int,Department/dept_head_id", PropertiesView.getKeyAttributeofStartPoint().getKey()).performTest();
	}
}
//passed
