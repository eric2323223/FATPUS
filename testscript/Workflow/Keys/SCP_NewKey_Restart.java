package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.SCP_NewKey_RestartHelper;
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

import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.model.DeployOption;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class SCP_NewKey_Restart extends SCP_NewKey_RestartHelper
{
	/**
	 * Script Name   : <b>SCP_NewKey_Restart</b>
	 * Generated     : <b>Nov 26, 2011 5:15:52 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/26
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF_restart1")
				.option(WorkFlow.SP_SERVER_INIT)
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall"));
		
		System.out.println("before restart:"+PropertiesView.getKeyOfScreen("DepartmentDetail").getKey());
		String keybefore = "Department_dept_id_attribKey,int,Department/dept_id,:Department_dept_name_attribKey,string,Department/dept_name,:Department_dept_head_id_attribKey,int,Department/dept_head_id,";
		vpManual("keyOfRestart",keybefore,PropertiesView.getKeyOfScreen("DepartmentDetail").getKey()).performTest();
		
		//restart ET...
		WN.restart();
		sleep(20);
		getScreen().getActiveWindow().inputKeys(SpecialKeys.ENTER);
		sleep(2);
		//OPEN ET...
		
		System.out.println("after restart:"+PropertiesView.getKeyOfScreen("DepartmentDetail").getKey());
		vpManual("keyOfRestart",keybefore,PropertiesView.getKeyOfScreen("DepartmentDetail").getKey()).performTest();
		
	}
}
//passed
