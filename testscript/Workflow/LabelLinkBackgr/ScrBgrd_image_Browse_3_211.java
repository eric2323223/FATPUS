package testscript.Workflow.LabelLinkBackgr;
import resources.testscript.Workflow.LabelLinkBackgr.ScrBgrd_image_Browse_3_211Helper;
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
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class ScrBgrd_image_Browse_3_211 extends ScrBgrd_image_Browse_3_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Nov 3, 2011 1:40:57 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/03
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//test ScrBgrd_image_Browse_3
		
//		WN.useProject(Cfg.projectName);
//		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//					.server("My Unwired Server")
//					.serverConnectionMapping("My Sample Database,sampledb"));
////	
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name("wfmbocreate")
//				.option(WorkFlow.SP_CLIENT_INIT));
//		
//		WorkFlowEditor.addScreen("scr1");
//		WorkFlowEditor.link("Start Screen", "scr1");
//		
//		DOF.getWFScreenFigure(DOF.getRoot(), "scr1").doubleClick();
//		DOF.getWFScreenDisplayFigure().click();
//		
//		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
//		PropertiesView.clickTab("General");
		
	//need to prepare 4 different image bmp/png/jpg/gif first then locate them under the project
		
		//Verify the app support this 4 different image,how to get the text
//		
		DOF.getButton(DOF.getRoot(), "&Browse...").click();
		vpManual("dialogpopup","Open",getScreen().getActiveWindow().getText());
		getScreen().getActiveWindow().inputChars("C:\\Documents and Settings\\min zhao\\workspace1\\wf\\2011-10-28_1125.png");
		getScreen().getActiveWindow().inputKeys(SpecialKeys.ENTER);
		getScreen().getActiveWindow().inputKeys(SpecialKeys.ENTER);
		
		//WO.setTextField(DOF.getRoot(),DOF.getLayoutComposite(DOF.getRoot(), "Background image:"),"sss.bmp");
		vpManual("png","\\2011-10-28_1125.png",DOF.getTextField(DOF.getLayoutComposite(DOF.getRoot(), "Background image:")).getProperty("text")).performTest();
		
//		WO.setTextField(DOF.getRoot(),DOF.getLayoutComposite(DOF.getRoot(), "Background image:"),"2011-10-28_1125.png");
//		vpManual("png","\\2011-10-28_1125.png",DOF.getTextField(DOF.getLayoutComposite(DOF.getRoot(), "Background image:")).getProperty("text")).performTest();
//		
//		
		DOF.getButton(DOF.getRoot(), "&Browse...").click();
		vpManual("dialogpopup","Open",getScreen().getActiveWindow().getText());
		getScreen().getActiveWindow().inputChars("C:\\Documents and Settings\\min zhao\\workspace1\\wf\\www.jpg");
		getScreen().getActiveWindow().inputKeys(SpecialKeys.ENTER);
		getScreen().getActiveWindow().inputKeys(SpecialKeys.ENTER);

//		
//		WO.setTextField(DOF.getRoot(),DOF.getLayoutComposite(DOF.getRoot(), "Background image:"),"www.jpg");
		vpManual("jpg","\\www.jpg",DOF.getTextField(DOF.getLayoutComposite(DOF.getRoot(), "Background image:")).getProperty("text")).performTest();
		
		DOF.getButton(DOF.getRoot(), "&Browse...").click();
		vpManual("dialogpopup","Open",getScreen().getActiveWindow().getText());
		getScreen().getActiveWindow().inputChars("C:\\Documents and Settings\\min zhao\\workspace1\\wf\\iii.gif");
		getScreen().getActiveWindow().inputKeys(SpecialKeys.ENTER);
		getScreen().getActiveWindow().inputKeys(SpecialKeys.ENTER);
//		
//		WO.setTextField(DOF.getRoot(),DOF.getLayoutComposite(DOF.getRoot(), "Background image:"),"iii.gif");
		vpManual("gif","\\iii.gif",DOF.getTextField(DOF.getLayoutComposite(DOF.getRoot(), "Background image:")).getProperty("text")).performTest();
	}
}

