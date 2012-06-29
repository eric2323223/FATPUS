package testscript.Workflow.Migration;
import resources.testscript.Workflow.Migration.Mgrt_21To213_ComplexListviewHelper;
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
public class Mgrt_21To213_ComplexListview extends Mgrt_21To213_ComplexListviewHelper
{
	/**
	 * Script Name   : <b>Mgrt_21To213_ComplexListview</b>
	 * Generated     : <b>May 10, 2012 4:54:09 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/05/10
	 * @author jiaozhou
	 */
	public void testMain(Object[] args) 
	{
		WN.deleteAllProject();
		WN.importProjectFromFile(Cfg.PROJECT21);
		sleep(2);
		WN.openLegacyProjectMbo("projectfor21", "Department", true);
		vpManual("noError", 0 , WN.getMigratingResult().errors().size()).performTest();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName2)
				.server("My Unwired Server")
                .mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		WorkFlowEditor.open("projectfor21", "relationwf");
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName2, "relationwf"))
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
			.parameter(new Email().to("dt").subject("relation21"));
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

