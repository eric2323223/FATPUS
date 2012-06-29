package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_Listview_AddHelper;
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
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.ListHelper;

import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Relationship;
import component.entity.model.StartPoint;
import component.entity.model.WFLview;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class ACT_Listview_Add extends ACT_Listview_AddHelper
{
	/**
	 * Script Name   : <b>ACT_Listview_Add</b>
	 * Generated     : <b>Oct 12, 2011 9:00:08 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/12
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->employee (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Department"))
			.target("Employee")
			.mapping("dept_id,dept_id")
			.composite("true")
			.type(Relationship.TYPE_OTM));
		//wf
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
		);
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addScreen("scr1");
		WorkFlowEditor.addScreen("scr2");
		WorkFlowEditor.link("Client-initiated", "scr1");
		//
		WorkFlowEditor.addMenuItem("scr1", new WFScreenMenuItem()
			.name("Add")
		);
		//
		PropertiesView.clickTab("General");
		//DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
		DOF.getCCombo(DOF.getRoot(), "Type:").click();
		String[] list = ListHelper.getItems(DOF.getCCombo(DOF.getRoot(), "Type:"));
		boolean flg = false;
		for (String item : list) {
			if (item.equals("Create Key Collection")){
				flg = true;
				break;
			}
		}
		vpManual("type", false, flg).performTest();
		//
		WorkFlowEditor.addWidget(Cfg.projectName, "myWF.xbw", "scr1", new WFLview()
			.newKey("list,list")
		);
		WorkFlowEditor.setMenuItem("scr1", new WFScreenMenuItem()
			.name("Add")
			.type("Create Key Collection")
			.screen("scr2")
		);
		//
		PropertiesView.clickTab("General");
		//DOF.gettabbedlistElement(DOF.getRoot(), "0").click();
		DOF.getCCombo(DOF.getRoot(), "Type:").click();
		vpManual("type", true, DOF.getCCombo(DOF.getRoot(),"Type:").getProperty("text").equals("Create Key Collection")).performTest();
		//
		PropertiesView.clickTab("General");
		DOF.getCCombo(DOF.getRoot(), "Screen:").click();
		vpManual("exist", true, DOF.getCCombo(DOF.getRoot(),"Screen:").getProperty("text").equals("scr2")).performTest();
		
	}
}

