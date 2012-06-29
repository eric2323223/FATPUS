package testscript.Workflow.Migration;
import java.io.File;
import java.util.ArrayList;

import resources.testscript.Workflow.Migration.MigrationCRUDSync20To213Helper;
import testscript.Workflow.Customization.cfg.Config;
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
import com.sybase.automation.framework.common.CDBUtil;

import component.entity.EE;
import component.entity.GlobalConfig;
import component.entity.WN;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.ScrapbookCP;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class MigrationCRUDSync20To213 extends MigrationCRUDSync20To213Helper
{
	/**
	 * Script Name   : <b>MigrationCRUDSync20To213</b>
	 * Generated     : <b>Mar 6, 2012 6:23:19 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/06
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.deleteAllProject();
		EE.runSQL(new ScrapbookCP().database("sampledb")
				.type("Sybase_ASA_12.x").name("My Sample Database"), 
				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Migration/createMyTable/allDataType.sql");
		WN.importProjectFromFile(Cfg.PROJECT20);
		WN.openLegacyProjectMbo("pro20", "Department", true);
		vpManual("noError", 0 , WN.getMigratingResult().errors().size()).performTest();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
	//	E2E part added by jennie
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
                .mode(DeployOption.MODE_UPDATE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "synccrudwf20"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser("dt"),
			customTestScript(), 
			"tplan.Workflow.iconcommon.BB.myWF_icon.Script");
		WFCustomizer.verifyResult(new WFClientResult().data("Confirm to add it for project2.0=Department_create"));
		
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("dept_id=1");
		clause.add("dept_name='a'");
		clause.add("dept_head_id=1");
		vpManual("dbresult", 1, CDBUtil.getRecordCount("localhost", "pro20", "Department", clause)).performTest();
	
	}
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.setMethod("customBeforeSubmit", new File(Config.Cust_Func_customBeforeSubmit_2));
		
		s.screen("Start Screen").moveTo("Department_create").throughMenuItem("Open Department_create");
		s.screen("Department_create").setData("Department_create_dept_id_paramKey", "1");
		s.screen("Department_create").setData("Department_create_dept_id_paramKey", "a");
		s.screen("Department_create").setData("Department_create_dept_head_id_paramKey", "1");
		s.screen("Department_create").throughMenuItem("Create");
		
		return s;
	}
}

