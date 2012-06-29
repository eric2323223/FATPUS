package testscript.Workflow.Migration;
import java.io.File;
import java.util.List;

import resources.testscript.Workflow.Migration.Mgrt_211To213_CUD_1Helper;
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
import com.sybase.automation.framework.common.DBUtil;

import component.entity.WN;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.WorkFlowPackage;
import component.entity.resource.ASA;
import component.entity.resource.IDBResource;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author jiaozhou
 */
public class Mgrt_211To213_CUD_1 extends Mgrt_211To213_CUD_1Helper
{
	/**
	 * Script Name   : <b>Mgrt_211To213_CUD_1</b>
	 * Generated     : <b>May 11, 2012 2:45:06 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/05/11
	 * @author jiaozhou
	 */
	public void testMain(Object[] args) 
	{
		WN.deleteAllProject();
		WN.importProjectFromFile(Cfg.PROJECT211);
		sleep(3);
		WN.openLegacyProjectMbo("projectfor211", "Department", true);
		vpManual("noError", 0 , WN.getMigratingResult().errors().size()).performTest();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName3)
				.server("My Unwired Server")
                .mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName3, "synccrudwf"))
		.unwiredServer("My Unwired Server")
		.deployToServer("true")
		.assignToSelectedUser("dt"),
		customTestScript()
//		"tplan.Workflow.iconcommon.BB.Mgrt_icons.Script"
		);
	WFCustomizer.verifyResult(new WFClientResult().data("Confirm to add it for project=Department_create"));
	sleep(3);
	vpManual("db",1,getDB("select * from Department where dept_id=4 and dept_name='d' and dept_head_id=4")).performTest();
		
	}
	public static int getDB(String sql){
		try {
		ASA db = new ASA();
		db.setProperty("host", "localhost");
		db.setProperty("login", "dba");
		db.setProperty("password", "sql");
		db.setProperty("port", "5500");
		db.setProperty("driverClass", "com.sybase.jdbc3.jdbc.SybDriver");
		List result = DBUtil.runSQLForResult((IDBResource) db, sql);
		System.out.println(result.size());
		return result.size();
		} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException("Failed to get record data from DB");
		}
		}
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.setMethod("customAfterSubmit", new File(Config.Cust_Func_customAfterSubmit_2));
		
		s.screen("Start_Screen").moveTo("Department_create").throughMenuItem("Open Screen Department_create");
		s.screen("Department_create").setData("Department_create_dept_id_paramKey", "4");
		s.screen("Department_create").setData("Department_create_dept_name_paramKey", "d");
		s.screen("Department_create").setData("Department_create_dept_head_id_paramKey", "4");
		s.screen("Department_create").moveTo("Department_delete").throughMenuItem("Create");
		s.screen("Department_delete").setData("Department_delete_dept_id_paramKey", "2");
		return s;
	}
}

