package testscript.Workflow.I18N;
import resources.testscript.Workflow.I18N.I18N_ErrMsgHelper;
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
import component.entity.GlobalConfig;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;
import component.view.properties.workflow.WFControlObject;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class I18N_ErrMsg extends I18N_ErrMsgHelper
{
	/**
	 * Script Name   : <b>I18N_ErrMsg</b>
	 * Generated     : <b>Nov 29, 2011 9:01:42 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/29
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/I18N/conf/I18N_ddl.sql");
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->I18N (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		//WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "I18N");
		WorkFlowEditor.link("Start Screen", "I18N_create");
		WorkFlowEditor.setMenuItem("I18N_create", new WFScreenMenuItem()
				.name("Create")
				.generateErrorScreen(true));
		WorkFlowEditor.addScreen("ErrorField");
		GuiTestObject obj = WFControlObject.getListViewOnScreen("ErrorDetail");
		obj.click();
		PropertiesView.clickTab("General");
		TestObject[] combos = DOF.getRoot().find(atDescendant("class",	"org.eclipse.swt.custom.CCombo"));
		if (combos != null && combos.length > 0) {
			for (int i = 0; i < combos.length; i++) {
				if (null != combos[i].getProperty(".priorLabel")) {
					if (DOF.isVisible(combos[i])
							&& combos[i].getProperty(".priorLabel").equals("Listview Details Screen:")) {
						((ScrollTestObject) combos[i]).click();
						DOF.getPoppedUpList().click(
								atText("ErrorField"));
					}
				}
			}
		}
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "ErrorField", 
				new WFEditBox().label("ErrorDetail")
							   .logicalType("TEXT")
							   .key("value")
							   .lines("5"));
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		//
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser("supAdmin")
			.unwiredServer("My Unwired Server")
			.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
			customTestScript(), 
			"tplan.Workflow.common.StartWF_android");
		WFCustomizer.verifyResult(new WFClientResult().data("id=value,value=com.sybase.jdbc3.jdbc.SybSQLException:SQL Anywhere 错误 -193: 表 'I18N' 的主键不唯一:主键值 (''姓名'')"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("I18N_create").throughMenuItem("Open I18N_create");
		s.screen("I18N_create").setData("I18N_create_name_paramKey", "姓名");
		s.screen("I18N_create").setData("I18N_create_info_paramKey", "姓名inf");
		s.screen("I18N_create").moveTo("ErrorList").throughMenuItem("Create");
		s.screen("ErrorList").moveTo("ErrorDetail").throughListItem("0");
		s.screen("ErrorDetail").moveTo("ErrorField").throughListItem("message->0");
		s.screen("ErrorField").getData("value");
//		s.screen("I18N").checkListItem("创建", "0");
		//
		return s;
	}
}
