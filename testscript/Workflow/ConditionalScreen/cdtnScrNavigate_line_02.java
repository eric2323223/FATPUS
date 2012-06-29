package testscript.Workflow.ConditionalScreen;
import resources.testscript.Workflow.ConditionalScreen.cdtnScrNavigate_line_02Helper;
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
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.ObjectQuery;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WizardRunner;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class cdtnScrNavigate_line_02 extends cdtnScrNavigate_line_02Helper
{
	/**
	 * Script Name   : <b>cdtnScrNavigate_line_02</b>
	 * Generated     : <b>Oct 12, 2011 1:58:18 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/12
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here

		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
//		ObjectQuery oq = new ObjectQuery().name("findByPrimaryKey")
//			.parameter("dept_id,int,true,dept_id")
//			.returnType(ObjectQueryWizard.RT_SINGLE)
//			.startParameter(WN.projectNameWithVersion(Cfg.projectName)+"->Mobile Business Objects->Department");
//		new ObjectQueryWizard().create(oq, new WizardRunner()); 

		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.addScreen("myScreen");
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("Submit")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.parametermapping("dept_id,Department_dept_id_attribKey"));
		
		PropertiesView.addConditionTableOfMenuItem("mycondition2","myScreen");
		vpManual("ButtonState","mycondition2,myScreen",PropertiesView.getConditionTableOfMenuItem().getKey()).performTest();

		vpManual("haslink","/ConditionnalOperation",WorkFlowEditor.getLinkTypeBetween("Start", "myScreen")).performTest();
	}
}
//passed
