package testscript.Workflow.Intro;
import resources.testscript.Workflow.Intro.IntroPage_Upgrade_2Helper;
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

/**
 * Description   : Functional Test Script
 * @author whan
 */
public class IntroPage_Upgrade_2 extends IntroPage_Upgrade_2Helper
{
	/**
	 * Script Name   : <b>IntroPage_Upgrade_2</b>
	 * Generated     : <b>Mar 18, 2012 7:49:27 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/18
	 * @author whan
	 */
	public void testMain(Object[] args) 
	{
		Preference.setMoblieWorkFlowFormsEditor("true");  
		System.out.printf(GlobalConfig.getRFTProjectRoot());
		WN.importProjectFromFile(GlobalConfig.getRFTProjectRoot()+ "\\testscript\\Workflow\\Intro\\Project_for_testing\\Project2.1.zip");
		WN.openWorkFlow("Project2.1", "actions.xbw");
    	vpManual("P1",true,TabFolderHelper.hasItem( DOF.getCTabFolder(DOF.getRoot(),"Flow Design"), "Introduction")).performTest();
		

		
		WN.deleteAllProject();
		Preference.setMoblieWorkFlowFormsEditor("false"); 
		WN.importProjectFromFile(GlobalConfig.getRFTProjectRoot()+ "\\testscript\\Workflow\\Intro\\Project_for_testing\\Project2.1.zip");
		WN.openWorkFlow("Project2.1", "actions.xbw");
			
		vpManual("P2",false,TabFolderHelper.hasItem( DOF.getCTabFolder(DOF.getRoot(),"Flow Design"), "Introduction")).performTest();
		
	}
}

