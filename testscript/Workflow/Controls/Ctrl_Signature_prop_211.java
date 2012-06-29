package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Signature_prop_211Helper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFSignature;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Signature_prop_211 extends Ctrl_Signature_prop_211Helper
{
	/**
	 * Script Name   : <b>Script2</b>
	 * Generated     : <b>Oct 18, 2011 11:58:41 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/18
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,sampledb"));
	
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wf2")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		
		//******************add key from key tab	
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
		PropertiesView.clickTab("Keys");
		PropertiesView.NewKey("key1,string");
		PropertiesView.NewKey("key2,bool");
	//**************end
		
		//verify key list and set value for it
		WorkFlowEditor.addWidget(Cfg.projectName, "wf2.xbw", "Start Screen", new WFSignature().label("signature:"));
		TestObject[] boxes = DOF.getWFSignatureFigures(DOF.getRoot());
		TestObject box = boxes[0];
		((GefEditPartTestObject)box).click();
		PropertiesView.clickTab("General");
		String s = PropertiesView.verifykeylist();
		System.out.print(s);
		vpManual("keylist","key1,",s).performTest();
		WO.setCCombo(DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")),s.split(",")[0]);
		
		PropertiesView.set(new WFSignature()
		.label("signature_prop_modify")
		.labelPosition("TOP")
		.ifReadonly(false)
		.ifRequired(true)
		);
		
		 DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		sleep(1);
	    WN.closeAll();
	    WN.openWorkFlow("wf", "wf2.xbw");
		
		//************************verify whether the new added control exist**************
	    DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
        TestObject[] boxes1 = DOF.getWFSignatureFigures(DOF.getRoot());
        TestObject box1 = boxes1[0];
        ((GefEditPartTestObject)box1).click();
        PropertiesView.clickTab("General");
        
        vpManual("addsignatureexist","signature_prop_modify",DOF.getTextField(DOF.getRoot(), "Label:").getProperty("text").toString()).performTest();
        vpManual("changedposition","TOP",DOF.getCCombo(DOF.getRoot(), "Label position:").getProperty("text").toString()).performTest();
        vpManual("ifonlycheck","false",DOF.getButton(DOF.getRoot(),"Read &only").invoke("getSelection")).performTest();
        vpManual("verifynewkey","key1",DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text").toString()).performTest();
        PropertiesView.clickTab("Advanced");
        vpManual("ifrequiredcheck","true",DOF.getButton(DOF.getRoot(),"&Required").invoke("getSelection")).performTest();   
	//************************ end**************
	}
}

