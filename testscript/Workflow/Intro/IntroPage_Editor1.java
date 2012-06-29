package testscript.Workflow.Intro;
import resources.testscript.Workflow.Intro.IntroPage_Editor1Helper;
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
public class IntroPage_Editor1 extends IntroPage_Editor1Helper
{
	/**
	 * Script Name   : <b>IntroPage_Editor1</b>
	 * Generated     : <b>Mar 14, 2012 10:56:18 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/15
	 * @author whan
	 */
	public void testMain(Object[] args) 
	{
		
		Preference.setMoblieWorkFlowFormsEditor("true");  
		
		WN.useProject(Cfg.projectName);
	    WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
			  .name("myWF")
			  .option(WorkFlow.SP_CLIENT_INIT));
			
	//		vpManual("P",true,DOF.getCTabItemExist(DOF.getRoot(), "Introduction")).performTest();
	    
	    vpManual( "P",true, TabFolderHelper.hasItem( DOF.getCTabFolder(DOF.getRoot(),"Flow Design"), "Introduction") ).performTest();
	 
	}
} 

