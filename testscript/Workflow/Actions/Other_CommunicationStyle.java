package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.Other_CommunicationStyleHelper;
import testscript.Workflow.cfg.Cfg;

import com.sybase.automation.framework.widget.DOF;
import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Operation;
import component.entity.model.StartPoint;
import component.entity.model.WFKey;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvVm
 */
public class Other_CommunicationStyle extends Other_CommunicationStyleHelper
{
	/**
	 * Script Name   : <b>Other_CommunicationStyle</b>
	 * Generated     : <b>Oct 10, 2011 9:50:58 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/10
	 * @author flvVm
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Department");
		mbo.addOperation(new Operation().startParameter(WN.mboPath(Cfg.projectName, "Department"))
				.name("O_HParas")
				.sqlQuery("DELETE FROM sampledb.dba.department WHERE dept_id = :dept_id")
		);
		mbo.addOperation(new Operation().startParameter(WN.mboPath(Cfg.projectName, "Department"))
				.name("O_NParas")
				.sqlQuery("DELETE FROM sampledb.dba.department WHERE dept_id = 100")
		);
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
		);
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addScreen("Screen1");
		WorkFlowEditor.link("Client-initiated", "Screen1");
		//
		WorkFlowEditor.addMenuItem("Screen1", new WFScreenMenuItem()
				.name("OTher")
				.type("Submit Workflow")
				.project(Cfg.projectName)
				.mbo("Department")
				.operation("O_HParas")
				.parametermapping("dept_id,"+new WFKey().name("key1").type("int").toString())
		);
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		//
		WorkFlowEditor.setMenuItem("Screen1", new WFScreenMenuItem()
				.name("OTher")
				.type("Online Request")
		);
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
	}
}

