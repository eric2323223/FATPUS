package testscript.Workflow.Migration;
import resources.testscript.Workflow.Migration.Mgrt_212To213_ComplexListviewHelper;
import testscript.Workflow.Migration.cfg.Cfg;

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
import component.entity.WN;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author jiaozhou
 */
public class Mgrt_212To213_ComplexListview extends Mgrt_212To213_ComplexListviewHelper
{
	/**
	 * Script Name   : <b>Mgrt_212To213_ComplexListview</b>
	 * Generated     : <b>May 25, 2012 1:04:36 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/05/25
	 * @author jiaozhou
	 */
	public void testMain(Object[] args) 
	{
		WN.deleteAllProject();
		WN.importProjectFromFile(Cfg.PROJECT212);
		sleep(2);
		WN.openLegacyProjectMbo("pro212", "Department", true);
		vpManual("noError", 0 , WN.getMigratingResult().errors().size()).performTest();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName4)
				.server("My Unwired Server")
                .mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		WorkFlowEditor.open("pro212", "relationwf");
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName4, "relationwf"))
		.unwiredServer("My Unwired Server")
		.deployToServer("true")
		.assignToSelectedUser("dt"),
		customTestScript(), 
//		"tplan.Workflow.iconcommon.BB.Mgrt_icons_srv.Script",
		sendNotification());
		
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=Product_id_attribKey,value=300|"+
				"id=Product_name_attribKey,value=Ladies Shirt|"+
				"id=Product_description_attribKey,value=Ladies polo Shirt"));
	}
	private CallBackMethod sendNotification() {
		return new CallBackMethod().receiver(WorkFlowEditor.class)
			.methodName("sendNotification")
			.parameter(new Email().to("dt").subject("rltn212"));
	}
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Department1").moveTo("Department1Detail").throughListItem("2");
		s.screen("Department1Detail").moveTo("Product").throughMenuItem("Open Product");
		s.screen("Product").moveTo("ProductDetail").throughListItem("0");
		s.screen("ProductDetail").getData("Product_id_attribKey");
		s.screen("ProductDetail").getData("Product_name_attribKey");
		s.screen("ProductDetail").getData("Product_description_attribKey");
		
		return s;
	}
}

