package testscript.Workflow.Migration;
import resources.testscript.Workflow.Migration.Mgrt_201To213_CtrlHelper;
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
public class Mgrt_201To213_Ctrl extends Mgrt_201To213_CtrlHelper
{
	/**
	 * Script Name   : <b>Mgrt_201To213_Ctrl</b>
	 * Generated     : <b>May 10, 2012 1:26:08 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/05/10
	 * @author jiaozhou
	 */
	public void testMain(Object[] args) 
	{
		WN.deleteAllProject();
		WN.importProjectFromFile(Cfg.PROJECT201);
		sleep(2);
		WN.openLegacyProjectMbo("pro201", "Department", true);
		vpManual("noError", 0 , WN.getMigratingResult().errors().size()).performTest();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName1)
				.server("My Unwired Server")
                .mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		WorkFlowEditor.open("pro201", "controlwf");
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName1, "controlwf"))
		.unwiredServer("My Unwired Server")
		.deployToServer("true")
		.assignToSelectedUser("dt"),
		customTestScript(), 
//		"tplan.Workflow.iconcommon.BB.Mgrt_icons_srv.Script",
		sendNotification());
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=AllMBODataType_type_BOOLEAN_attribKey,value=true|"+
				"id=AllMBODataType_type_LONG_attribKey,value=1"));
		
	}
	private CallBackMethod sendNotification() {
		return new CallBackMethod().receiver(WorkFlowEditor.class)
			.methodName("sendNotification")
			.parameter(new Email().to("dt").subject("alldata201"));
	}
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("AllMBODataType").moveTo("AllMBODataTypeDetail").throughListItem("0");
		s.screen("AllMBODataTypeDetail").moveTo("AllMBODataType_update_instance").throughMenuItem("Open AllMBODataType_update_instance");
		s.screen("AllMBODataType_update_instance").getData("AllMBODataType_type_BOOLEAN_attribKey");
		s.screen("AllMBODataType_update_instance").getData("AllMBODataType_type_LONG_attribKey");
//		s.screen("AllMBODataType_update_instance").getData("choice");
		s.screen("AllMBODataType_update_instance").moveTo("AllMBODataTypeDetail").throughMenuItem("Cancel");
		s.screen("AllMBODataTypeDetail").moveTo("AllMBODataType_delete_instance").throughMenuItem("Open AllMBODataType_delete_instance");
		s.screen("AllMBODataType_delete_instance").menuItem("Delete");
		
		return s;
	}
}

