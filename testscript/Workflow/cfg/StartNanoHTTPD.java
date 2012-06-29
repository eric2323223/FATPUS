package testscript.Workflow.cfg;
import resources.testscript.Workflow.cfg.StartNanoHTTPDHelper;
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
import component.entity.WFCustomizer.NanoHTTPD;
import component.entity.WFCustomizer.WFClientResult;

/**
 * Description   : Functional Test Script
 * @author test
 */
public class StartNanoHTTPD extends StartNanoHTTPDHelper
{
	/**
	 * Script Name   : <b>StartNanoHTTPD</b>
	 * Generated     : <b>May 7, 2012 10:30:58 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/05/07
	 * @author test
	 */
	public void testMain(Object[] args) 
	{
		NanoHTTPD httpd = NanoHTTPD.getInstance(8008);
		WFClientResult result = new WFClientResult();
		httpd.registerOnDataReceivedListener(result);
		httpd.run();
		result.waitForComplete();
	}
}

