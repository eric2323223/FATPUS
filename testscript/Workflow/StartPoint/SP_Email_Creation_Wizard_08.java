package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Email_Creation_Wizard_08Helper;
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
import component.entity.WorkFlowEditor;
import component.entity.model.ObjectQuery;
import component.entity.model.StartPoint;
import component.entity.model.WizardRunner;
import component.entity.wizard.ObjectQueryWizard;
import component.entity.wizard.WorkFlowCreateionWizard;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Email_Creation_Wizard_08 extends SP_Email_Creation_Wizard_08Helper
{
	/**
	 * Script Name   : <b>SP_Email_Creation_Wizard_08</b>
	 * Generated     : <b>Sep 14, 2011 10:21:07 AM</b>
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
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->customer (dba)"), 10, 10);
		ObjectQuery oq = new ObjectQuery().name("oq1")
			.parameter("p1,STRING,true,fname")
			.returnType(ObjectQueryWizard.RT_RESULTSET)
//			.queryDefinition("SELECT x.* FROM Customer x WHERE x.fname = :p1")
			.startParameter(WN.projectNameWithVersion(Cfg.projectName)+"->Mobile Business Objects->Customer");
			new ObjectQueryWizard().create(oq, new WizardRunner());
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Customer"));
		vpManual("hasObjectquery",false,PropertiesView.ifhasObjectQuery("op1")).performTest();
	}
}
//passed
