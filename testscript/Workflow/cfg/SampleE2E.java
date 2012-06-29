package testscript.Workflow.cfg;
import java.util.Properties;

import resources.testscript.Workflow.cfg.SampleE2EHelper;
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
import component.entity.WFCustomizer.OnDataReceivedListener;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.WFCustomizer.WorkFlowNameHandler;
import component.entity.WFCustomizer.NanoHTTPD.Response;
import component.entity.model.E2ETestScript;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
@E2ETestScript
public class SampleE2E extends SampleE2EHelper
{
	/**
	 * Script Name   : <b>SampleE2E</b>
	 * Generated     : <b>Feb 20, 2012 10:49:31 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/02/20
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		NanoHTTPD httpd = getHttpd();
		WFClientResult result = new WFClientResult();
		httpd.registerOnDataReceivedListener(result);
		WorkFlowNameHandler myHandler = new WorkFlowNameHandler();
		String wfName = "myWF";
		myHandler.setUriResponse("/getWorkflowName", getHttpd().new Response(wfName));
		getHttpd().registerHandler(myHandler);
		getHttpd().run();
		result.waitForComplete();
		System.out.println("done");
		

	}

	private NanoHTTPD getHttpd() {
		return NanoHTTPD.getInstance(8008);
		
	}
	

}

