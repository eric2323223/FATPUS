package testscript.Workflow.Intro;
import resources.testscript.Workflow.Intro.IntroPage_Preference_3Helper;
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
import com.sybase.automation.framework.widget.helper.PropertiesTabHelper;

import component.entity.Preference;
import component.entity.WN;
import component.entity.WorkFlow;

/**
 * Description   : Functional Test Script
 * @author whan
 */
public class IntroPage_Preference_3 extends IntroPage_Preference_3Helper
{
	/**
	 * Script Name   : <b>IntroPage_Preference_3</b>
	 * Generated     : <b>Mar 14, 2012 12:05:12 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/14
	 * @author whan
	 */
	public void testMain(Object[] args) 
	{
		Preference.setMoblieWorkFlowFormsEditor("defaults");  
				
		WN.useProject(Cfg.projectName);
	    WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
			  .name("myWF")
			  .option(WorkFlow.SP_CLIENT_INIT));
			
		
		
		vpManual("P",true,PropertiesTabHelper.hasTabName("Introduction")).performTest();
		

	}
}

