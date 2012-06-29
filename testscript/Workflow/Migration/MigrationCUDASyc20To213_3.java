package testscript.Workflow.Migration;
import java.util.List;

import resources.testscript.Workflow.Migration.MigrationCUDASyc20To213_3Helper;
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
public class MigrationCUDASyc20To213_3 extends MigrationCUDASyc20To213_3Helper
{
	/**
	 * Script Name   : <b>MigrationCUDASyc20To213_3</b>
	 * Generated     : <b>Apr 25, 2012 4:03:44 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/04/25
	 * @author jiaozhou
	 */
	public void testMain(Object[] args) 
	{
		//Test Async delete operation for 2.0 project can work well after migration
		WN.deleteAllProject();
		
		WN.importProjectFromFile(Cfg.PROJECT20);
		WN.openLegacyProjectMbo("pro20", "Department", true);
		vpManual("noError", 0 , WN.getMigratingResult().errors().size()).performTest();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
                .mode(DeployOption.MODE_REPLACE)
				.serverConnectionMapping("My Sample Database,sampledb"));
		WFCustomizer.runTest(new WorkFlowPackage()
		.startParameter(WN.filePath(Cfg.projectName, "asynccrudwf"))
		.unwiredServer("My Unwired Server")
		.deployToServer("true")
		.assignToSelectedUser("dt"),
		customTestScript()
//		"tplan.Workflow.iconcommon.BB.Mgrt_icons.Script"
		);
		sleep(10);
		vpManual("db",0,getDB("select * from Department where dept_id=1")).performTest();	
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
		
		s.screen("Start_Screen").moveTo("Department_delete").throughMenuItem("Open Department_delete");
		s.screen("Department_delete").setData("Department_delete_dept_id_paramKey", "1");
		s.screen("Department_delete").menuItem("Delete");
		return s;
	}
}

