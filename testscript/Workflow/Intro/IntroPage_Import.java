package testscript.Workflow.Intro;
import resources.testscript.Workflow.Intro.IntroPage_ImportHelper;
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
import com.sybase.automation.framework.widget.helper.TabFolderHelper;

import component.entity.GlobalConfig;
import component.entity.Preference;
import component.entity.WN;
import component.entity.WorkFlow;

/**
 * Description   : Functional Test Script
 * @author whan
 */
public class IntroPage_Import extends IntroPage_ImportHelper
{
	/**
	 * Script Name   : <b>IntroPage_Import</b>
	 * Generated     : <b>Mar 18, 2012 7:04:33 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/18
	 * @author whan
	 */
	public void testMain(Object[] args) 
	{
		
		
		Preference.setMoblieWorkFlowFormsEditor("true");  
		
		WN.useProject(Cfg.projectName);
	    WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
			  .name("myWF")
			  .option(WorkFlow.SP_CLIENT_INIT));
	  
		
		vpManual("P1",true,TabFolderHelper.hasItem( DOF.getCTabFolder(DOF.getRoot(),"Flow Design"), "Introduction")).performTest();
		
		WN.exportProject(Cfg.projectName, GlobalConfig.getRFTProjectRoot()+ "\\testscript\\Workflow\\Intro\\Project_for_testing\\TestProject.zip");
		
		
		WN.deleteProject(Cfg.projectName);
		
		WN.importProjectFromFile(GlobalConfig.getRFTProjectRoot()+ "\\testscript\\Workflow\\Intro\\Project_for_testing\\TestProject.zip");
		
		
		Preference.setMoblieWorkFlowFormsEditor("false");  
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		
		vpManual("P2",false,TabFolderHelper.hasItem( DOF.getCTabFolder(DOF.getRoot(),"Flow Design"), "Introduction")).performTest();
		
		
		
		
		
	}
}

