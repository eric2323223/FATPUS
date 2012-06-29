package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_HTML_View_act_211Helper;
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
public class Ctrl_HTML_View_act_211 extends Ctrl_HTML_View_act_211Helper
{
	/**
	 * Script Name   : <b>Script2</b>
	 * Generated     : <b>Oct 19, 2011 1:46:19 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/19
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wf2")
				.option(WorkFlow.SP_CLIENT_INIT));
	
		
		WorkFlowEditor.addWidget(Cfg.projectName, "wf2.xbw", "Start", new WFHtmlView());	
		
		TestObject[] boxes = DOF.getWFHtmlViewFigures(DOF.getRoot());
	    System.out.print("changdu=" + boxes.length);
	    vpManual("ifaddhtmlsucc",1,boxes.length).performTest();
		
	 //*************reopen   
	    DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
	    WN.closeAll();
	    WN.openWorkFlow("wf", "wf2.xbw");
	    
	  //************************verify whether the new added control exist**************
	    DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
        TestObject[] boxes1 = DOF.getWFHtmlViewFigures(DOF.getRoot());
	    System.out.print("changdu=" + boxes1.length);
	    vpManual("ifaddhtmlsucc",1,boxes1.length).performTest();
	//************************ end**************

   //****************delete from keyboard
       TestObject[] boxes2 = DOF.getWFHtmlViewFigures(DOF.getRoot());
      //System.out.print(boxes2);
       TestObject box2 = boxes2[0];
      ((GefEditPartTestObject)box2).click();	
      DOF.getRoot().inputKeys("{ExtDelete}");
      MainMenu.saveAll(); 
//************end
    
		//************verify whether the control delete.
    TestObject[] boxes3 = DOF.getWFHtmlViewFigures(DOF.getRoot());
    System.out.print("changdu=" + boxes3.length);
    vpManual("ifdeletesucc",0,boxes3.length).performTest();
     //***********end
    
	}
}

