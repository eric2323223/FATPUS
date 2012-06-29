package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Listview_binding_211Helper;
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
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.Relationship;
import component.entity.model.WFChoice;
import component.entity.model.WFLview;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Listview_binding_211 extends Ctrl_Listview_binding_211Helper
{
	/**
	 * Script Name   : <b>Script2</b>
	 * Generated     : <b>Oct 24, 2011 10:10:28 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/24
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//test Ctrl_Listview_binding
		
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"));
		
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->employee (dba)"));
		
		WN.createRelationship(new Relationship().startParameter(WN.mboPath(Cfg.projectName, "Department"))
				.name("employees")
				.mapping("dept_id,dept_id")
				.composite("true")
				.target("Employee")
				.type(Relationship.TYPE_OTM));
	
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("wflistview")
				.option(WorkFlow.SP_SERVER_INIT));


		PropertiesView.jumpStart(new WorkFlow()
		        .mbo("Department")
				.objectQuery("findAll")
				.subject("getall")
				.subjectMatchingRule("getall")
				);

		WorkFlowEditor.addScreen("linktoemail");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "linktoemail");
		
		//******************add key from key tab	
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		 DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
		PropertiesView.clickTab("Keys");
		PropertiesView.NewKey("key1,string");
		PropertiesView.NewKey("key2,bool");
		PropertiesView.NewKey("key3,decimal");
		PropertiesView.NewKey("key4,list");
		PropertiesView.NewKey("key5,DateTime");
	//**************end
			
		WorkFlowEditor.addWidget(Cfg.projectName, "wflistview.xbw", "linktoemail", new WFLview());
		PropertiesView.clickTab("General");
		String s = PropertiesView.verifykeylist();
		boolean b = s.contains("key4");
		vpManual("keylist",true,b).performTest();
		
		DOF.getWFScreenDesignCanvas().click();
		//create relationshipkey
		//send by server could not select in second key dialog 
		PropertiesView.setNewKeyBindMBORelationshipInScrDesign("listkey1,list,Department,employees,emp_id");
		TestObject[] boxes = DOF.getWFListViewFigures(DOF.getRoot());
	    TestObject box = boxes[0];
	   ((GefEditPartTestObject)box).click();
		PropertiesView.clickTab("General");
		boolean b1 = PropertiesView.verifykeylist().contains("listkey1");
		vpManual("keylist1",true,b1).performTest();
		
		
		PropertiesView.NewKey("listkey2,list");
		
		//Verify the new key added to the email event but not bound to the HTML view control
		PropertiesView.clickTab("General");
		boolean b2 = PropertiesView.verifykeylist().contains("key4,listkey1,listkey2");
		vpManual("keylist2",true,b2).performTest();
		DOF.getWFScreenDesignCanvas().click();
		PropertiesView.clickTab("Keys");		
		vpManual("ifkeyexist","true",TableHelper.hasDataInColumn(DOF.getTable(DOF.getRoot()),"Key Name","listkey2")).performTest();
		//end
	}
}

