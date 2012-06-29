package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Listview_prop_01_211Helper;
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
import component.entity.PropertiesView;
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFEditBox;
import component.entity.model.WFLview;
import component.entity.model.WFNewscreen;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Listview_prop_01_211 extends Ctrl_Listview_prop_01_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 24, 2011 12:40:38 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/24
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wfmbocreate")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		WorkFlowEditor.addScreen("s");
		
		WorkFlowEditor.addWidget(Cfg.projectName, "wfmbocreate.xbw", "Start", new WFLview()
		.newKey("listviewkey,list")
		.alternateColor("#gg0000")
		.emptyMessage("~!@#$%^&*()+<>?/")
		.lvDetailScreen("s"));
		
		vpManual("colorwrong","Value not updated, the backcolor must be black, blue, brown, gray, green, orange, pink, purple, red, white, yellow, or #rrggbb where rr, gg, and bb are the hexadecimal values for the red, green and blue.",PropertiesView.getStatusMessage()).performTest();

		//choose color picker TBD*******
		
		//Verify a list detail screen flow was assigned between the listview screen and specified list detail screen
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		WFFlowDesigner.arrangeAll();
		WFFlowDesigner.deleteLink(WorkFlow.SP_CLIENT_INIT, "Start");
		vpManual("detailarrow","true",WFFlowDesigner.hasLinkBetween("Start", "s")).performTest();
        
		
		DOF.getWFFlowDesignCanvas().click();
	    WN.closeAll();
	    WN.openWorkFlow("wf", "wfmbocreate.xbw");
	    
	    
	    DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
		 TestObject[] boxes = DOF.getWFListViewFigures(DOF.getRoot());
		 TestObject box = boxes[0];
		 ((GefEditPartTestObject)box).click();
		 PropertiesView.clickTab("General");
	    
	    String key = DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text").toString();
		vpManual("keylist","listviewkey",key).performTest();
	
		vpManual("ifdetailscrright","s",DOF.getCCombo(DOF.getRoot(), "Listview Details Screen:").getProperty("text")).performTest();
		 vpManual("Emptylist","~!@#$%^&*()+<>?/",DOF.getTextField(DOF.getRoot(), "On empty list:").getProperty("text").toString()).performTest();
		PropertiesView.clickTab("General");
		WO.setTextField(DOF.getRoot(),DOF.getLayoutComposite(DOF.getRoot(), "Alternate row color:"),"#gg0000");
		
		
		
	}
}

