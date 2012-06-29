package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.NQ_15Helper;
import testscript.Workflow.cfg.Cfg;

import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.ListHelper;
import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.ObjectQuery;
import component.entity.model.StartPoint;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.wizard.ObjectQueryWizard;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class NQ_15 extends NQ_15Helper
{
	/**
	 * Script Name   : <b>NQ_15</b>
	 * Generated     : <b>Oct 12, 2011 2:57:57 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/12
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		//MBOProperties mbo = new MBOProperties(Cfg.projectName, "Department");
		WN.createObjectQuery(new ObjectQuery()
			.name("ObjQuery")
			.startParameter(WN.mboPath(Cfg.projectName, "Department"))
			.parameter("name,string,true,dept_name")
			.queryDefinition("SELECT x.* FROM Department x WHERE x.dept_name = :name")
			.returnType(ObjectQueryWizard.RT_RESULTSET)
		);
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
		);
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addScreen("invokeScr");
		WorkFlowEditor.addScreen("resultScr");
		WorkFlowEditor.link("Client-initiated", "invokeScr");
		//
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "invokeScr", 
				new WFEditBox().label("dept_Name")
							   .logicalType("TEXT")
							   .newKey("nameVal,string")
		);
		WorkFlowEditor.addMenuItem("invokeScr", new WFScreenMenuItem()
				.name("ObjectQuery")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.defaultSuccessScreen("resultScr")
		);
		//
		PropertiesView.clickTab("General");
		DOF.getButton(DOF.getRoot(), "Invoke object &query").click();
		DOF.getCCombo(DOF.getRoot(), "Object query:").click();
		String[] list = ListHelper.getItems(DOF.getCCombo(DOF.getRoot(), "Object query:"));
		boolean flg = false;
		for (String item : list) {
			if (item.equals("ObjQuery")){
				flg = true;
				break;
			}
		}
		vpManual("objectquery", false, flg).performTest();
	}
}

