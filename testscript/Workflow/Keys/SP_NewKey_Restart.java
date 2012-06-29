package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.SP_NewKey_RestartHelper;
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
public class SP_NewKey_Restart extends SP_NewKey_RestartHelper
{
	/**
	 * Script Name   : <b>SP_NewKey_Restart</b>
	 * Generated     : <b>Nov 26, 2011 4:54:49 AM</b>
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
		
		System.out.println("before restart:"+PropertiesView.getKeyAttributeofStartPoint().getKey());
		String keybefore = "Department,list,Department:Department_dept_id_attribKey,int,Department/dept_id:Department_dept_name_attribKey,string,Department/dept_name:Department_dept_head_id_attribKey,int,Department/dept_head_id";
		vpManual("keyOfRestart",keybefore,PropertiesView.getKeyAttributeofStartPoint().getKey()).performTest();
		
		//restart ET...
		WN.restart();
		sleep(20);
		getScreen().getActiveWindow().inputKeys(SpecialKeys.ENTER);
		sleep(2);
		//OPEN ET...
		
		System.out.println("before restart:"+PropertiesView.getKeyAttributeofStartPoint().getKey());
		vpManual("keyOfRestart",keybefore,PropertiesView.getKeyAttributeofStartPoint().getKey()).performTest();
	}
}
//passed
