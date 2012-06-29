package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Email_Creation_Wizard_05Helper;
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
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Email_Creation_Wizard_05 extends SP_Email_Creation_Wizard_05Helper
{
	/**
	 * Script Name   : <b>SP_Email_Creation_Wizard_05</b>
	 * Generated     : <b>Sep 14, 2011 10:04:54 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/14
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->bonus (dba)"), 10, 10);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName)
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Bonus")
				.objectQuery("findByEmp_id")
				.subject("emp0")
				.subjectMatchingRule("emp")
				.setParameterValue("emp_id,Subject,emp"));
		vpManual("haserror",0,Problems.getErrors().size()).performTest();
		System.out.print(PropertiesView.getStartPoint(WorkFlow.SP_SERVER_INIT).getKey());
		vpManual("Properies", "emp_id,int,subject => emp<emp_id>", PropertiesView.getStartPoint(WorkFlow.SP_SERVER_INIT).getKey().split(":")[0]).performTest();
	}
}
//passed
