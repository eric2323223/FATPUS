package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Editbox_binding_01_211Helper;
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
import com.sybase.automation.framework.widget.helper.ListHelper;

import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFEditBox;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Editbox_binding_01_211 extends Ctrl_Editbox_binding_01_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 12, 2011 7:35:20 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/12
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
			
		//******go to Screen Flow
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), "Start" ).doubleClick();
		//**************end
		
	//******************add key from key tab	
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		PropertiesView.clickTab("Keys");
		PropertiesView.NewKey("key1,string");
		PropertiesView.NewKey("key2,bool");
		PropertiesView.NewKey("key3,decimal");
		PropertiesView.NewKey("key4,list");
		PropertiesView.NewKey("key5,DateTime");
	//**************end
		
	WorkFlowEditor.addWidget(Cfg.projectName, "wfmbocreate.xbw", "Start", new WFEditBox().label("editbox:"));
	PropertiesView.clickTab("General");
	String s = PropertiesView.verifykeylist();
	vpManual("keylist","key1,key3,key5,",s).performTest();
	
		//********change logictype,verify keybinding changed
	PropertiesView.set(new WFEditBox().logicalType("DATETIME"));
	PropertiesView.clickTab("General");
	String s1 = PropertiesView.verifykeylist();
	vpManual("keylist","key1,key5,",s1).performTest();
//	***********end
	 
	//*******************set key to blank,verify no key bind
	String key = DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text").toString();
	System.out.print(">>>>>>>>"+key);
	vpManual("keyblank","",key).performTest();
	//*****************end		 
		
	}
}

