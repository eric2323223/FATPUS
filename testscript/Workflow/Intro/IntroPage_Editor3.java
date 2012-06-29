package testscript.Workflow.Intro;
import resources.testscript.Workflow.Intro.IntroPage_Editor3Helper;
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

import component.entity.Preference;
import component.entity.WN;
import component.entity.WorkFlow;

/**
 * Description   : Functional Test Script
 * @author whan
 */
public class IntroPage_Editor3 extends IntroPage_Editor3Helper
{
	/**
	 * Script Name   : <b>IntroPage_Editor3</b>
	 * Generated     : <b>Mar 15, 2012 3:06:34 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/15
	 * @author whan
	 */
	public void testMain(Object[] args) 
	{
		
		WN.useProject(Cfg.projectName);
	    WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
			  .name("myWF")
			  .option(WorkFlow.SP_CLIENT_INIT));
	    
		Preference.setMoblieWorkFlowFormsEditor("false");  
		
		vpManual("P1",true,TabFolderHelper.hasItem( DOF.getCTabFolder(DOF.getRoot(),"Flow Design"), "Introduction")).performTest();
	
		WN.closeAll();
    	WN.openWorkFlow(Cfg.projectName,"myWF.xbw");
			
		vpManual("P2",false,TabFolderHelper.hasItem( DOF.getCTabFolder(DOF.getRoot(),"Flow Design"), "Introduction")).performTest();
	}
}

