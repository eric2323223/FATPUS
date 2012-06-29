package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_HTML_View_prop_211Helper;
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
import com.sybase.automation.framework.widget.helper.WO;
import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFHtmlView;
import component.entity.model.WFSignature;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_HTML_View_prop_211 extends Ctrl_HTML_View_prop_211Helper
{
	/**
	 * Script Name   : <b>Script2</b>
	 * Generated     : <b>Oct 19, 2011 2:15:19 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/19
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		
//		WN.useProject(Cfg.projectName);
//		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wf2")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		
		//******************add key from key tab	
		 DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		PropertiesView.clickTab("Keys");
		PropertiesView.NewKey("key1,string");
		PropertiesView.NewKey("key2,bool");
	   //**************end
		
		//verify key list and set value for it
		WorkFlowEditor.addWidget(Cfg.projectName, "wf2.xbw", "Start", new WFHtmlView());
		TestObject[] boxes = DOF.getWFHtmlViewFigures(DOF.getRoot());
		TestObject box = boxes[0];
		((GefEditPartTestObject)box).click();
		PropertiesView.clickTab("General");
		String s = PropertiesView.verifykeylist();
		System.out.print(s);
		vpManual("keylist","key1,",s).performTest();
		WO.setCCombo(DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")),s.split(",")[0]);
		
		PropertiesView.set(new WFHtmlView()
		.defaultValue("<body><h1>HTML View Test</h1></body>")
		);
		
		//*****reopen workflow
		 DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		sleep(1);
	    WN.closeAll();
	    WN.openWorkFlow("wf", "wf2.xbw");

	  //************************verify whether the new added control exist**************
	    DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
        TestObject[] boxes1 = DOF.getWFHtmlViewFigures(DOF.getRoot());	    
	    TestObject box1 = boxes1[0];
	    ((GefEditPartTestObject)box1).click();
	    PropertiesView.clickTab("General");
	    vpManual("addeddefault","<body><h1>HTML View Test</h1></body>",DOF.getTextField(DOF.getRoot(), "Default value:").getProperty("text").toString()).performTest();
	//************************ end**************
	    
	}
}

