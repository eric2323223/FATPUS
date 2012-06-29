package testscript.Workflow.Supplement;

import resources.testscript.Workflow.Supplement.S674183_Special_ASCII_ResultHelper;
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
import component.entity.EE;
import component.entity.GlobalConfig;
import component.entity.MainMenu;
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

/**
 * Description : Functional Test Script
 * 
 * @author flvxp
 */
public class S674183_Special_ASCII_Result extends
		S674183_Special_ASCII_ResultHelper {
	/**
	 * Script Name : <b>S674183_Special_ASCII_Result</b> Generated : <b>Nov 4,
	 * 2011 10:06:02 AM</b> Description : Functional Test Script Original Host :
	 * WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2011/11/04
	 * @author flvxp
	 */
	public void testMain(Object[] args) {
		WN.useProject(Cfg.projectName);
		EE.runSQL(new ScrapbookCP().database("sampledb").type("Sybase_AllDT_12.x")
				.name("My Sample Database"),
				GlobalConfig.getRFTProjectRoot() + "/testscript/Workflow/Actions/conf/AllDT_dll.sql");
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllDT (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.mode(DeployOption.MODE_REPLACE).server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name(
				"myWF").option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "AllDT");
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", new WFEditBox()
				.label("id")
				.logicalType("TEXT")
				.newKey("id,int")
				.setDefaultValue("1"));
		//
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("findByPrimaryKey")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("AllDT")
				.objectQuery("findByPrimaryKey")
				.parametermapping("int1,id")
				.defaultSuccessScreen("AllDTDetail"));
		sleep(1);
		MainMenu.saveAll();
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		//
		WFCustomizer.runTest(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
				.assignToUser("supAdmin")
				.unwiredServer("My Unwired Server")
				.deployToServer("true")
				.verifyResult("Assigning workflow myWF to supAdmin", true), customTestScript(), "tplan.Workflow.common.StartWF_android");
		//
		java.util.Map<String,String> map = new java.util.HashMap<String,String>();
		map.put("id=AllDT_string2_attribKey", "value=~!@#$%^&*()_+|}{:\"?><,./;'[]\\=-");
		WFCustomizer.verifyResult(new WFClientResult().data(map));
	}

	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start_Screen").moveTo("AllDTDetail").throughMenuItem("findByPrimaryKey");
		s.screen("AllDTDetail").getData("AllDT_string2_attribKey");
		return s;
	}
}