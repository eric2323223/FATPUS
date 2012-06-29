package testscript.Workflow.WS;
import resources.testscript.Workflow.WS.WS_ComplexArray_Result_BBHelper;
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
import component.entity.EE;
import component.entity.WN;
import component.entity.model.DeployOption;
import component.entity.model.WSCP;
import component.entity.model.WSMBO;
import component.entity.resource.Criteria;
import component.entity.resource.RC;
import component.entity.resource.WS;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class WS_ComplexArray_Result_BB extends WS_ComplexArray_Result_BBHelper
{
	/**
	 * Script Name   : <b>WS_ComplexArray_Result_BB</b>
	 * Generated     : <b>Apr 19, 2012 2:29:02 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/04/19
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
//		EE.createWSCP(new WSCP().name("wscp").wsdl(Cfg.simpleArray), true);
//		sleep(2);
//		DOF.getEETree().click(RIGHT, atPath(EE.getCPFullPath("Web Services->wscp")));
//		DOF.getContextMenu().click(atPath("Connect"));
		
//		WN.useProject(Cfg.projectName);
		EE.createMboOperation(new WSMBO().startParameter("Web Services->wscp->SimpleArray->Sort")
				);
//		tbd>>>>>>>>>>>>>>>>>
		
//		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
//				.mode(DeployOption.MODE_REPLACE)
//				.server("My Unwired Server")
//				.serverConnectionMapping("ws_complex,ws_complex"));
		
		
		
		
	}
}

