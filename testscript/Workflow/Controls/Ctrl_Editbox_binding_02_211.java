package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Editbox_binding_02_211Helper;
import testscript.Workflow.cfg.Cfg;

import com.rational.test.ft.object.interfaces.GefEditPartTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WFScreen;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.Modifications;
import component.entity.model.WFEditBox;
import component.entity.model.WFKey;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Editbox_binding_02_211 extends Ctrl_Editbox_binding_02_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 13, 2011 1:37:09 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/13
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
		
	    //******************add key from key tab	
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		PropertiesView.clickTab("Keys");
		PropertiesView.NewKey("key1,string");
		
		//**************end
		
	   //verify key duplicate
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("editbox:"));
		
	    TestObject[] boxes = DOF.getWFEditBoxFigures(DOF.getRoot());
	    TestObject box = boxes[0];
		((GefEditPartTestObject)box).click();
		PropertiesView.clickTab("General");
		
		DOF.getButton(DOF.getGroup(DOF.getRoot(), "Input Data Binding"), "&New key...").click();
		WO.setTextField(DOF.getDialog("Key"), DOF.getTextField(DOF.getDialog("Key"), "Name:"), "key1");
		
		String actual = DOF.getLabelByAncestorLine(DOF.getDialog("Key"), "Composite->Composite->Shell->Shell").getProperty("text").toString();
		vpManual("keyduplicate","Key names must be unique.   The key name key1 already exists.",actual).performTest();
		DOF.getButton(DOF.getDialog("Key"), "Cancel").click();
		
				//end
		PropertiesView.NewKey("key2,list");
		PropertiesView.clickTab("General");
		String s1 = PropertiesView.verifykeylist();
		System.out.println(s1);
		vpManual("unmatchkey","",s1).performTest();
		
		
		PropertiesView.NewKey("key3,string");
		PropertiesView.clickTab("General");
		String s2 = PropertiesView.verifykeylist();
		vpManual("matchedkey","key3,",s2).performTest();
		
	}
}

