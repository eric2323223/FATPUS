package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.DynaChoice_E2E_Android_01Helper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.DeployedMbo;
import component.entity.model.Relationship;
import component.entity.model.WFChoice;
import component.entity.model.WFNewscreen;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DynaChoice_E2E_Android_01 extends DynaChoice_E2E_Android_01Helper
{
	/**
	 * Script Name   : <b>DynaChoice_E2E_Android_01</b>
	 * Generated     : <b>Aug 31, 2011 3:12:54 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/08/31
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->employee (dba)"), 10, 10);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 100, 10);
		WN.createRelationship(new Relationship().startParameter(WN.mboPath(Cfg.projectName, "Employee"))
				.name("employees")
				.mapping("dept_id,dept_id")
				.composite("true")
				.target("Department")
				.type(Relationship.TYPE_OTM));
		 WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,simpledb"));
		vpManual("deploy", true, EE.ifMboDeployed(new DeployedMbo()
					.connectionProfile("My Unwired Server")
					.domain("default")
					.name("Department")
					.pkg(Cfg.projectName+":1.0"))).performTest();
			
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name(
					"wfDynaChoice").option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addScreen("linktoemail");
		WorkFlowEditor.addWidget(Cfg.projectName, "wfDynaChoice.xbw", "linktoemail", new WFChoice().label("choice")
				.newKey("key1,string").option("Dynamic, , ") );
		
		
		
		//TBD.....
		
		
//		TestResult result = Robot.run(this);
//		vpManual("DeviceTest", true, result.isPass()).performTest();
		
	}
}

