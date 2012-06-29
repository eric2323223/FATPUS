package testscript.Workflow.I18N;
import resources.testscript.Workflow.I18N.I18N_allHelper;
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

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class I18N_all extends I18N_allHelper
{
	/**
	 * Script Name   : <b>I18N_all</b>
	 * Generated     : <b>Feb 12, 2012 9:25:34 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/02/12
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		callScripts("testscript.Workflow.I18N.*","Android");
	}
}

