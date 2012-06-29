package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_HTML_binding_211Helper;
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
import com.sybase.jaguar.system.ListHelper;

import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.DeployedMbo;
import component.entity.model.Relationship;
import component.entity.model.WFChoice;
import component.entity.model.WFHtmlView;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_HTML_binding_211 extends Ctrl_HTML_binding_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 23, 2011 8:24:12 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/23
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
	
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("wfhtml")
				.option(WorkFlow.SP_SERVER_INIT));


		PropertiesView.jumpStart(new WorkFlow()
		        .mbo("Department")
				.objectQuery("findByPrimaryKey")
				.subject("deptid=2")
				.subjectMatchingRule("deptid=")
				.setParameterValue("dept_id,Subject,deptid="));

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
		
		
		
		WorkFlowEditor.addWidget(Cfg.projectName, "wfhtml.xbw", "linktoemail", new WFHtmlView());
		PropertiesView.clickTab("General");
		String s = PropertiesView.verifykeylist();
		vpManual("keylist","key1,",s).performTest();


       //*****need to check other attribute in the key popup window
		WorkFlowEditor.addWidget(Cfg.projectName, "wfhtml.xbw", "linktoemail", new WFHtmlView()
		.newKeyBindMbo("htmlkey,int,Department,dept_id"));
		
		//Verify the new key added to the email event but not bound to the HTML view control
		PropertiesView.clickTab("General");
		vpManual("keylist","key1,",PropertiesView.verifykeylist()).performTest();
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
		DOF.getWFScreenDesignCanvas().click();
		PropertiesView.clickTab("Keys");		
		vpManual("ifkeyexist","true",TableHelper.hasDataInColumn(DOF.getTable(DOF.getRoot()),"Key Name","htmlkey")).performTest();
		//end
	}
}

