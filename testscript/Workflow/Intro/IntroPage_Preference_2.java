package testscript.Workflow.Intro;
import resources.testscript.Workflow.Intro.IntroPage_Preference_2Helper;
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
import component.entity.Preference;

/**
 * Description   : Functional Test Script
 * @author whan
 */
public class IntroPage_Preference_2 extends IntroPage_Preference_2Helper
{
	/**
	 * Script Name   : <b>IntroPage_Preference_2</b>
	 * Generated     : <b>Mar 13, 2012 8:12:33 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/13
	 * @author whan
	 */
	public void testMain(Object[] args) 
	{
	Preference.setMoblieWorkFlowFormsEditor("true");
	vpManual("P1",true,Preference.getMoblieWorkFlowFormsEditor()).performTest();
		
	Preference.setMoblieWorkFlowFormsEditor("false");
	vpManual("P2",false,Preference.getMoblieWorkFlowFormsEditor()).performTest();
		
	}
}

